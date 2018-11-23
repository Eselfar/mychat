package com.remiboulier.mychat.extension

import com.remiboulier.mychat.model.Message
import com.remiboulier.mychat.util.Constants
import java.util.*

/**
 * Created by Remi BOULIER on 23/11/2018.
 * email: boulier.r.job@gmail.com
 */

fun Message.updateTailState(currentTime: Date) {
    hasTail = currentTime.time - timestamp.time >= Constants.TWENTY_SECONDS_IN_MILLIS
}