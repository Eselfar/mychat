package com.remiboulier.mychat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeList.adapter = ChatListAdapter(mutableListOf(), this)

        homeBtnSend.setOnClickListener { onButtonPressed() }
    }

    fun onButtonPressed() {
        if (!homeMessageField.text.isBlank()) {
            (homeList.adapter as ChatListAdapter).addItem(homeMessageField.text.toString())
            homeMessageField.text.clear()
        }
    }
}
