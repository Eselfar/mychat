package com.remiboulier.mychat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


/**
 * Created by Remi BOULIER on 21/11/2018.
 * email: boulier.r.job@gmail.com
 */

class ChatListAdapter(private val items: MutableList<String>, context: Context)
    : ArrayAdapter<String>(context, android.R.layout.simple_list_item_1) {

    override fun getCount() = items.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = (convertView
                ?: LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)) as TextView

        view.text = items[position]

        return view
    }

    fun addItem(item: String) {
        items.add(item)
        notifyDataSetChanged()
    }
}