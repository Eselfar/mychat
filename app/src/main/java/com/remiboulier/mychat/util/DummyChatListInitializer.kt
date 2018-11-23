package com.remiboulier.mychat.util

import com.remiboulier.mychat.model.Item
import com.remiboulier.mychat.model.Message
import com.remiboulier.mychat.model.TimeSeparator
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Remi BOULIER on 23/11/2018.
 * email: boulier.r.job@gmail.com
 */

fun generateDummyChatList(): MutableList<Item> {
    val items = mutableListOf<Item>()

    items.addTimeSeparator("2018-11-20 23:42:00")

    items.addMessage("2018-11-20 23:42:00", false, true, "Lorem ipsum")
    items.addMessage("2018-11-20 23:42:10", true, false, "Duis lectus lacus")
    items.addMessage("2018-11-20 23:42:20", true, true, "eleifend sed erat et,")
    items.addMessage("2018-11-20 23:42:30", false, false, "dolor sit amet")
    items.addMessage("2018-11-20 23:42:40", false, false, "Mauris euismod mattis")
    items.addMessage("2018-11-20 23:42:50", false, true, "finibus efficitur purus")

    items.addTimeSeparator("2018-11-21 09:15:00")

    items.addMessage("2018-11-21 09:15:00", false, false, "Phasellus eget condimentum odio")
    items.addMessage("2018-11-21 09:15:10", false, false, "sit amet facilisis sapien.")
    items.addMessage("2018-11-21 09:15:20", false, true, "Nam a vestibulum libero.\nIn dignissim nisi id arcu venenatis vehicula.")
    items.addMessage("2018-11-21 09:16:00", false, true, "Morbi mattis feugiat aliquet.")

    return items
}

fun MutableList<Item>.addMessage(date: String, isUser: Boolean, hasTail: Boolean, text: String) {
    add(Message(text, stringToDate(date), isUser, hasTail))
}

fun MutableList<Item>.addTimeSeparator(date: String) {
    add(TimeSeparator(stringToDate(date)))
}

fun stringToDate(string: String): Date {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    return format.parse(string)
}