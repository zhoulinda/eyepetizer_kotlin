package com.linda.lib_common.utils

import java.text.SimpleDateFormat

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/1
 */
class DateUtil {
    companion object {
        fun getDuration(seconds: Int): String {
            var duration = ""
            val min: Int
            val second: Int
            var hour = seconds / 3600
            min = (seconds - hour * 3600) / 60;
            second = seconds - hour * 3600 - min * 60;
            if (hour > 0) {
                duration += "$hour:"
            }
            if (min < 10) {
                duration += "0$min:";
            } else {
                duration += "$min:";
            }
            if (second < 10) {
                duration += "0$second";
            } else {
                duration += second.toString();
            }
            return duration;
        }

        fun getFormatDate(time: Long, format: String): String {
            val sdf = SimpleDateFormat(
                format
            )
            return sdf.format(time)
        }
    }
}