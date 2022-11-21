package com.example.findyourlove.data

import androidx.annotation.DrawableRes
import com.example.findyourlove.R
data class User(
    val id:Int,
    @DrawableRes val imageResourceId: Int
)
val users = listOf(
    User(321,R.drawable.ellipse1),
    User(561,R.drawable.ellipse2),
    User(731,R.drawable.ellipse3),
    User(311,R.drawable.ellipse4),
    User(211,R.drawable.ellipse5),
    User(161,R.drawable.ellipse6),
)
