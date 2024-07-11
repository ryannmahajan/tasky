package com.ryannm.tasky.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task (
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val completed:Boolean = false,
    val title:String = "",
    val description:String = ""
) {
}