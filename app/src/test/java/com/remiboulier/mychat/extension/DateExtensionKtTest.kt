package com.remiboulier.mychat.extension

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * Created by Remi BOULIER on 22/11/2018.
 * email: boulier.r.job@gmail.com
 */
class DateExtensionKtTest {

    @Test
    fun toReadableDate_has_valid_display_afternoon() {
        val cal = Calendar.getInstance().also {
            it.set(2018, Calendar.NOVEMBER, 22, 23, 59)
        }
        val date = Date(cal.timeInMillis)

        val dateToCheck = "Thursday 23:59"
        val dateString = date.toReadableDate()

        assertEquals(dateToCheck, dateString)
    }

    @Test
    fun toReadableDate_has_valid_display_morning() {
        val cal = Calendar.getInstance().also {
            it.set(2018, Calendar.NOVEMBER, 22, 9, 1)
        }
        val date = Date(cal.timeInMillis)

        val dateToCheck = "Thursday 09:01"
        val dateString = date.toReadableDate()

        assertEquals(dateToCheck, dateString)
    }
}