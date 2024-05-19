package com.example.diethub

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

data class UserInfo(
    var followers: Int = 20,
    var date: Int = 1,
    var following: Int = 52,
    var weightLoss: Int = 12,
    var targetWeight: Int = 57,
    var height: Int = 166,
    var muscleMass: Float = 42.18f,
    var weight: Int = 62,
    var bmi: Double = 19.0,
    var sampleCharacterProfileId: Int = R.drawable.character1
){


}
