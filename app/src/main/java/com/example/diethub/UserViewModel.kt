package com.example.diethub
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diethub.api.MyInfo
import com.example.diethub.api.RetrofitInstance
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UserViewModel : ViewModel() {
        private val _myInfo = MutableLiveData<MyInfo>()
        val myInfo: LiveData<MyInfo> get() = _myInfo

        init {
                fetchMyInfo()
        }

        private fun fetchMyInfo() {
                viewModelScope.launch {
                        try {
                                val response = RetrofitInstance.api.getMyinfo(memberId = 1) // memberId를 적절히 설정
                                _myInfo.value = response
                        } catch (e: Exception) {
                                // 에러 처리
                        }
                }
        }

        fun updateMyInfo(height: Float, weight: Float, muscleMass: Float) {
                val currentInfo = _myInfo.value
                if (currentInfo != null) {
                        _myInfo.value = currentInfo.copy(
                                height = height,
                                weight = weight,
                                muscleMass = muscleMass
                        )
                }
        }

        fun getDaysUsed(): Long {
                val currentInfo = _myInfo.value ?: return 0
                val formatter = DateTimeFormatter.ISO_DATE
                val createdAt = LocalDate.parse(currentInfo.createdAt, formatter)
                val today = LocalDate.now()
                return createdAt.until(today).days.toLong()
        }
}