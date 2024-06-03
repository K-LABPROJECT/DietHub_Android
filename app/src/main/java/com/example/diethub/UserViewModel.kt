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

        fun fetchMyInfo() {
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
                        val updatedInfo = currentInfo.copy(
                                height = height,
                                weight = weight,
                                muscleMass = muscleMass
                        )
                        _myInfo.value = updatedInfo
                        updateMyInfoOnServer(updatedInfo)
                }
        }

        private fun updateMyInfoOnServer(updatedInfo: MyInfo) {
                viewModelScope.launch {
                        try {
                                val response = RetrofitInstance.api.updateMyinfo(
                                        memberId = 1, // memberId를 적절히 설정
                                        myInfo = updatedInfo
                                )
                                if (response.isSuccessful) {
                                        fetchMyInfo()
                                } else {
                                        // 에러 처리
                                }
                        } catch (e: Exception) {
                                // 에러 처리
                        }
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