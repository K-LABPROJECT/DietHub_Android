package com.example.diethub

data class UserInfo(
    var followers: Int = 20, // 팔로워 수
    var date: Int = 1, // 앱 사용 일수
    var following: Int = 52, // 팔로잉 수
    var firstWeight : Int = 90, // 시작 체중
    var targetWeight: Int = 25, // 목표 체중
    var height: Float = 166.0F, // 키
    var muscleMass: Float = 42.18f, // 근육량
    var weight: Float = 50.0f, // 현재 체중
    var weightLoss: Float = firstWeight - weight, // 지금까지 감량한 체중
    var bmi : Float = (weight / (((height/100))*((height/100)))), // bmi
    var characterProfileId: Int = R.drawable.character1 // 남녀 캐릭터 설정용 ID
)
