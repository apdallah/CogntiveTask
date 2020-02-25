package com.apdallahy3.basearch.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object {
        fun formatDate(dateText: Date): String {
            val format = SimpleDateFormat("yyyy-MM-dd")
            return format.format(dateText.time)
        }
    }
}