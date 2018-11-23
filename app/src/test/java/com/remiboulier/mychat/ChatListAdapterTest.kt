package com.remiboulier.mychat

import android.content.Context
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

/**
 * Created by Remi BOULIER on 21/11/2018.
 * email: boulier.r.job@gmail.com
 */
@RunWith(MockitoJUnitRunner::class)
class ChatListAdapterTest {

    @Test
    fun getItemViewType_is_user() {
        val item = Message("Text", Date(), true, false)
        val adapter = ChatListAdapter(
                mutableListOf(mock(Item::class.java), item),
                mock(Context::class.java))

        val res = adapter.getItemViewType(1)

        assertEquals(ChatListAdapter.TYPE_USER, res)
    }

    @Test
    fun getItemViewType_is_contact() {
        val item = Message("Text", Date(), false, false)
        val adapter = ChatListAdapter(
                mutableListOf(mock(Item::class.java), item),
                mock(Context::class.java))

        val res = adapter.getItemViewType(1)

        assertEquals(ChatListAdapter.TYPE_CONTACT, res)
    }

    @Test
    fun getItemViewType_is_separator() {
        val item = TimeSeparator(Date())
        val adapter = ChatListAdapter(
                mutableListOf(mock(Item::class.java), item),
                mock(Context::class.java))

        val res = adapter.getItemViewType(1)

        assertEquals(ChatListAdapter.TYPE_SEPARATOR, res)
    }
}