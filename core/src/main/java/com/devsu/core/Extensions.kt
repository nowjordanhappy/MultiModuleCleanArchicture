package com.devsu.core

fun Int.toHexColor(): String? {
    if(this < 0) return null
    return String.format("#%06X", 0xFFFFFF and this)
}

fun String?.getValueOrNull(): String? {
    return this.takeIf { it?.isNotEmpty() == true }
}