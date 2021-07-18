package com.example.nutria.utils

import java.math.RoundingMode
import java.text.DecimalFormat

class Utils {
    companion object {
        fun getTruncatedFloatStr(value: Float): String {
            val df = DecimalFormat("##.##")
            df.setRoundingMode(RoundingMode.DOWN)
            return df.format(value)
        }
    }
}