package com.remiboulier.mychat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.remiboulier.mychat.extension.toReadableDate
import com.remiboulier.mychat.model.Item
import com.remiboulier.mychat.model.Message
import com.remiboulier.mychat.model.TimeSeparator
import kotlinx.android.synthetic.main.item_list_chat_penpal.view.*
import kotlinx.android.synthetic.main.item_list_chat_separator.view.*
import java.util.*


/**
 * Created by Remi BOULIER on 21/11/2018.
 * email: boulier.r.job@gmail.com
 */

class ChatListAdapter(private val items: MutableList<Item>,
                      context: Context)
    : ArrayAdapter<Item>(context, 0) {

    companion object {
        const val VIEW_TYPE_COUNT = 3
        const val TYPE_USER = 0
        const val TYPE_CONTACT = 1
        const val TYPE_SEPARATOR = 2

        const val TWENTY_SECONDS_IN_MILLIS = 20_000
        const val ONE_HOUR_IN_MILLIS = 60 * 60 * 1_000
    }

    override fun getCount() = items.size

    override fun getItemViewType(position: Int) =
            when (val item = items[position]) {
                is Message -> when {
                    item.user -> TYPE_USER
                    else -> TYPE_CONTACT
                }
                is TimeSeparator -> TYPE_SEPARATOR
            }

    override fun getViewTypeCount() = VIEW_TYPE_COUNT

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val item = items[position]
        when (getItemViewType(position)) {
            TYPE_USER -> {
                view = (convertView
                        ?: LayoutInflater.from(parent.context).inflate(R.layout.item_list_chat_user, parent, false)) as LinearLayout
                view.itemText.text = (item as Message).text
                view.itemText.setBackgroundResource(if (item.hasTail) R.drawable.bubble_right_tail else R.drawable.bubble_right)
                view.itemText.visibility = if (item.isVisible) View.VISIBLE else View.INVISIBLE
            }
            TYPE_CONTACT -> {
                view = (convertView
                        ?: LayoutInflater.from(parent.context).inflate(R.layout.item_list_chat_penpal, parent, false)) as LinearLayout
                view.itemText.text = (item as Message).text
                view.itemText.setBackgroundResource(if (item.hasTail) R.drawable.bubble_left_tail else R.drawable.bubble_left)
            }
            TYPE_SEPARATOR -> {
                view = (convertView
                        ?: LayoutInflater.from(parent.context).inflate(R.layout.item_list_chat_separator, parent, false)) as LinearLayout
                view.separatorTime.text = (item as TimeSeparator).timestamp.toReadableDate()
            }
            else -> throw AdapterTypeUnknown()
        }

        return view
    }

    fun addUserMessageHidden(text: String) {
        val currentTime = Date()

        if (items.size == 0) {
            items.add(TimeSeparator(currentTime))
        } else {
            val previous = items[items.size - 1] as Message
            if (currentTime.time - previous.timestamp.time > ONE_HOUR_IN_MILLIS) {
                items.add(TimeSeparator(currentTime))
            }
            if (currentTime.time - previous.timestamp.time < TWENTY_SECONDS_IN_MILLIS) {
                previous.hasTail = false
            }
        }

        val message = Message(text, currentTime, true, true, false)
        items.add(message)
        notifyDataSetChanged()
    }

    fun showLastMessage() {
        (items[items.size - 1] as Message).isVisible = true
        notifyDataSetChanged()
    }
}

class AdapterTypeUnknown : java.lang.RuntimeException("Unknown type")