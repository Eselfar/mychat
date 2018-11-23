package com.remiboulier.mychat

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var onLayoutChangeListener: View.OnLayoutChangeListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeList.adapter = ChatListAdapter(mutableListOf(), this)
        homeBtnSend.setOnClickListener { onButtonPressed() }
    }

    fun onButtonPressed() {
        if (!homeMessageField.text.isBlank()) {
            val text = homeMessageField.text.toString()
            onLayoutChangeListener = getOnLayoutChangeListener(text)
            // LayoutChangeListener is required as we need to wait for the TextView to redraw before playing the animation
            homeMovingText.addOnLayoutChangeListener(onLayoutChangeListener)
            homeMovingText.text = text
        }
    }

    fun getOnLayoutChangeListener(text: String): View.OnLayoutChangeListener =
            View.OnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
                homeMovingText.removeOnLayoutChangeListener(onLayoutChangeListener)
                homeMovingText.visibility = View.VISIBLE
                homeMessageField.text.clear()
                (homeList.adapter as ChatListAdapter).addUserMessageHidden(text)
                animate {
                    homeMovingText.visibility = View.INVISIBLE
                    (homeList.adapter as ChatListAdapter).showLastMessage()
                }
            }

    fun animate(onAnimationEndCallback: () -> Unit) {
        val endX = homeList.right - homeMovingText.width
        val endY = homeList.bottom - homeMovingText.bottom
        val translateX = getTranslationAnimator(homeMovingText, "translationX", endX)
        val translateY = getTranslationAnimator(homeMovingText, "translationY", endY)

        AnimatorSet().apply {
            playTogether(translateX, translateY)
            addListener(onAnimationEnd(onAnimationEndCallback))
            start()
        }
    }

    fun getTranslationAnimator(target: View, propertyName: String, endPosition: Int) =
            ObjectAnimator.ofFloat(target, propertyName, 0f, endPosition.toFloat()).apply {
                duration = 800
            }

    fun onAnimationEnd(onAnimationEndCallback: () -> Unit) = object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            onAnimationEndCallback()
        }
    }
}
