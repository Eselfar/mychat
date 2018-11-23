package com.remiboulier.mychat.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Remi BOULIER on 22/11/2018.
 * email: boulier.r.job@gmail.com
 */

fun Date.toReadableDate(): String {
    val df = SimpleDateFormat("EEEE HH:mm", Locale.UK)

    return df.format(this)
}