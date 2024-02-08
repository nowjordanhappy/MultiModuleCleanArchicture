package com.devsu.core

import java.text.SimpleDateFormat
import java.util.Locale

sealed class DateFormatType(val format: String){
    object Full: DateFormatType("yyyy-MM-dd'T'HH:mm:ss'Z'")
    object Short: DateFormatType("yyyy-MM-dd HH:mm:ss")
    object ShortAMPM: DateFormatType("yyyy-MM-dd h:mma")
    data class Custom(val custom: String): DateFormatType(custom)
}

object DateUtils {
    fun convertDateFormat(originalDate: String?, inputFormat: DateFormatType, outputFormat: DateFormatType): String? {
        if(originalDate == null) return null
        try {
            val localDateTime = SimpleDateFormat(inputFormat.format, Locale.getDefault()).parse(originalDate)

            val formattedFormatter = SimpleDateFormat(outputFormat.format, Locale.getDefault())
            return formattedFormatter.format(localDateTime)
        }catch (e: Exception){
            return null
        }
    }

}