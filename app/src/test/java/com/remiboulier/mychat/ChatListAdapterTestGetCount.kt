package com.remiboulier.mychat

import android.content.Context
import com.remiboulier.mychat.model.Item
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Remi BOULIER on 21/11/2018.
 * email: boulier.r.job@gmail.com
 */
@RunWith(MockitoJUnitRunner::class)
class ChatListAdapterTestGetCount {

    @Test
    fun getCount() {
        val size = 3
        val adapter = ChatListAdapter(
                mock(Context::class.java),
                MutableList(size) { mock(Item::class.java) })

        assertEquals(size, adapter.count)
    }
}