package com.remiboulier.mychat.model

import java.util.*

/**
 * Created by Remi BOULIER on 22/11/2018.
 * email: boulier.r.job@gmail.com
 */
sealed class Item

data class Message(val text: String, val timestamp: Date, val isUser: Boolean, var hasTail: Boolean, var isVisible: Boolean = true) : Item()
data class TimeSeparator(val timestamp: Date) : Item()