package com.remiboulier.mychat

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.remiboulier.mychat.ChatListAdapter.Companion.TYPE_CONTACT
import com.remiboulier.mychat.ChatListAdapter.Companion.TYPE_SEPARATOR
import com.remiboulier.mychat.ChatListAdapter.Companion.TYPE_USER
import com.remiboulier.mychat.model.Item
import com.remiboulier.mychat.model.Message
import com.remiboulier.mychat.model.TimeSeparator
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Remi BOULIER on 21/11/2018.
 * email: boulier.r.job@gmail.com
 */
@RunWith(MockitoJUnitRunner::class)
class ChatListAdapterTestGetView {

    /**
     * Workaround to prevent any(Class) from Mockito to throw an IllegalStateException
     * See https://stackoverflow.com/a/48805160/1827254
     */
    private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)

    @Test
    fun getView_when_type_user() {
        val adapter = spy(ChatListAdapter(
                mock(Context::class.java),
                MutableList(1) { mock(Message::class.java) }))

        doReturn(TYPE_USER).`when`(adapter).getItemViewType(anyInt())
        doReturn(mock(LinearLayout::class.java)).`when`(adapter)
                .getUserView(any(View::class.java), any(ViewGroup::class.java), any(Message::class.java))

        adapter.getView(0, mock(View::class.java), mock(ViewGroup::class.java))

        verify(adapter, times(1))
                .getUserView(any(View::class.java), any(ViewGroup::class.java), any(Message::class.java))
    }

    @Test
    fun getView_when_type_contact() {
        val adapter = spy(ChatListAdapter(
                mock(Context::class.java),
                MutableList(1) { mock(Message::class.java) }))

        doReturn(TYPE_CONTACT).`when`(adapter).getItemViewType(anyInt())
        doReturn(mock(LinearLayout::class.java)).`when`(adapter)
                .getContactView(any(View::class.java), any(ViewGroup::class.java), any(Message::class.java))

        adapter.getView(0, mock(View::class.java), mock(ViewGroup::class.java))

        verify(adapter, times(1))
                .getContactView(any(View::class.java), any(ViewGroup::class.java), any(Message::class.java))
    }

    @Test
    fun getView_when_type_separator() {
        val adapter = spy(ChatListAdapter(
                mock(Context::class.java),
                MutableList(1) { mock(TimeSeparator::class.java) }))

        doReturn(TYPE_SEPARATOR).`when`(adapter).getItemViewType(anyInt())
        doReturn(mock(LinearLayout::class.java)).`when`(adapter)
                .getSeparatorView(any(View::class.java), any(ViewGroup::class.java), any(TimeSeparator::class.java))

        adapter.getView(0, mock(View::class.java), mock(ViewGroup::class.java))

        verify(adapter, times(1))
                .getSeparatorView(any(View::class.java), any(ViewGroup::class.java), any(TimeSeparator::class.java))
    }

    @Test(expected = AdapterTypeUnknown::class)
    fun getView_when_type_unknown() {
        val adapter = spy(ChatListAdapter(
                mock(Context::class.java),
                MutableList(1) { mock(Item::class.java) }))

        doReturn(-1).`when`(adapter).getItemViewType(anyInt())

        adapter.getView(0, mock(View::class.java), mock(ViewGroup::class.java))
    }
}