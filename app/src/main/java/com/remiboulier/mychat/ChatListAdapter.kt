package com.remiboulier.mychat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.item_list_chat_penpal.view.*


/**
 * Created by Remi BOULIER on 21/11/2018.
 * email: boulier.r.job@gmail.com
 */

class ChatListAdapter(private val items: MutableList<String>,
                      context: Context)
    : ArrayAdapter<String>(context, 0) {

    companion object {
        const val VIEW_TYPE_COUNT = 2
        const val TYPE_USER = 0
        const val TYPE_PENPAL = 1
    }

    override fun getCount() = items.size

    override fun getItemViewType(position: Int) =
            TYPE_USER//  if (position.rem(2) == 0) TYPE_USER else TYPE_PENPAL

    override fun getViewTypeCount(): Int {
        return VIEW_TYPE_COUNT
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val text = items[position]
        when (getItemViewType(position)) {
            TYPE_USER -> {
                view = (convertView
                        ?: LayoutInflater.from(parent.context).inflate(R.layout.item_list_chat_user, parent, false)) as LinearLayout
                view.itemText.text = text
            }
            TYPE_PENPAL -> {
                view = (convertView
                        ?: LayoutInflater.from(parent.context).inflate(R.layout.item_list_chat_penpal, parent, false)) as LinearLayout
                view.itemText.text = text
            }
            else -> throw RuntimeException("Type not defined")
        }

        return view
    }

    fun addItem(item: String) {
        items.add(item)
        notifyDataSetChanged()
    }
}