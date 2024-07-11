package com.ryannm.tasky.domain.model

data class Task (
    val completed:Boolean = false,
    val title:String = "",
    val description:String = ""
) {
}