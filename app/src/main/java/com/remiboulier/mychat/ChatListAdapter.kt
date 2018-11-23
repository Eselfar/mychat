package com.remiboulier.mychat

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.remiboulier.mychat.extension.toReadableDate
import com.remiboulier.mychat.extension.updateTailState
import com.remiboulier.mychat.model.Item
import com.remiboulier.mychat.model.Message
import com.remiboulier.mychat.model.TimeSeparator
import com.remiboulier.mychat.util.Constants
import kotlinx.android.synthetic.main.item_list_chat_penpal.view.*
import kotlinx.android.synthetic.main.item_list_chat_separator.view.*
import java.util.*


/**
 * Created by Remi BOULIER on 21/11/2018.
 * email: boulier.r.job@gmail.com
 */

class ChatListAdapter(context: Context,
                      private val items: MutableList<Item>)
    : ArrayAdapter<Item>(context, 0) {

    companion object {
        const val VIEW_TYPE_COUNT = 3
        const val TYPE_USER = 0
        const val TYPE_CONTACT = 1
        const val TYPE_SEPARATOR = 2
    }

    override fun getCount() = items.size

    override fun getItemViewType(position: Int) =
            when (val item = items[position]) {
                is Message -> when {
                    item.isUser -> TYPE_USER
                    else -> TYPE_CONTACT
                }
                is TimeSeparator -> TYPE_SEPARATOR
            }

    override fun getViewTypeCount() = VIEW_TYPE_COUNT

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = items[position]
        return when (getItemViewType(position)) {
            TYPE_USER -> getUserView(convertView, parent, item as Message)
            TYPE_CONTACT -> getContactView(convertView, parent, item as Message)
            TYPE_SEPARATOR -> getSeparatorView(convertView, parent, item as TimeSeparator)
            else -> throw AdapterTypeUnknown()
        }
    }

    fun getUserView(convertView: View?, parent: ViewGroup, item: Message): View =
            inflateView(convertView, parent, R.layout.item_list_chat_user).apply {
                itemText.text = item.text
                itemText.setBackgroundResource(if (item.hasTail) R.drawable.bubble_right_tail else R.drawable.bubble_right)
                itemText.visibility = if (item.isVisible) View.VISIBLE else View.INVISIBLE
            }

    fun getContactView(convertView: View?, parent: ViewGroup, item: Message): View =
            inflateView(convertView, parent, R.layout.item_list_chat_penpal).apply {
                itemText.text = item.text
                itemText.setBackgroundResource(if (item.hasTail) R.drawable.bubble_left_tail else R.drawable.bubble_left)
            }

    fun getSeparatorView(convertView: View?, parent: ViewGroup, item: TimeSeparator): View =
            inflateView(convertView, parent, R.layout.item_list_chat_separator).apply {
                separatorTime.text = item.timestamp.toReadableDate()
            }

    fun inflateView(convertView: View?, parent: ViewGroup, @LayoutRes layoutRes: Int) =
            (convertView
                    ?: LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)) as LinearLayout

    fun addUserMessageHidden(text: String) = addNewMessage(text, true, false)

    fun addContactMessage(text: String) = addNewMessage(text, false)

    fun addNewMessage(text: String, isUser: Boolean, isVisible: Boolean = true) {
        val currentTime = Date()
        val previous: Message? = if (items.size > 0) items[items.size - 1] as Message else null
        if (previous != null && previous.isUser == isUser)
            previous.updateTailState(currentTime)
        val message = Message(text, currentTime, isUser, true, isVisible)

        if (isTimeSeparatorRequired(currentTime, previous))
            items.add(TimeSeparator(currentTime))
        items.add(message)

        notifyDataSetChanged()
    }

    fun isTimeSeparatorRequired(currentTime: Date, previous: Message?) =
            previous != null && currentTime.time - previous.timestamp.time > Constants.ONE_HOUR_IN_MILLIS

    fun showLastMessage() {
        (items[items.size - 1] as Message).isVisible = true
        notifyDataSetChanged()
    }
}

class AdapterTypeUnknown : java.lang.RuntimeException("Unknown type")