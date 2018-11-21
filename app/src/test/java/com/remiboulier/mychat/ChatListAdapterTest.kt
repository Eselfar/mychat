package com.remiboulier.mychat

import android.content.Context
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
class ChatListAdapterTest {

    @Test
    fun getItemViewType_when_even() {
        val context = mock(Context::class.java)
        val adapter = ChatListAdapter(mutableListOf("A", "B", "C"), context)

        val res = adapter.getItemViewType(0)

        assertEquals(ChatListAdapter.TYPE_USER, res)
    }

    @Test
    fun getItemViewType_when_odd() {
        val context = mock(Context::class.java)
        val adapter = ChatListAdapter(mutableListOf("A", "B"), context)

        val res = adapter.getItemViewType(1)

        assertEquals(ChatListAdapter.TYPE_PENPAL, res)
    }
}