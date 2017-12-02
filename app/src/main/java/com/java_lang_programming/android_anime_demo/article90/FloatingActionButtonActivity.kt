package com.java_lang_programming.android_anime_demo.article90

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.java_lang_programming.android_anime_demo.R
import kotlinx.android.synthetic.main.activity_floating_action_button.*

class FloatingActionButtonActivity : AppCompatActivity() {
    enum class FloatingActionState {
        NORMAL, ANIMATED
    }

    private lateinit var fab: FloatingActionButton
    private lateinit var state: FloatingActionState
    private lateinit var openingAnimation: Animator
    private lateinit var closingAnimation: Animator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floating_action_button)
        setSupportActionBar(toolbar)
        state = FloatingActionState.NORMAL

        fab = findViewById(R.id.fab)
        fab.setOnClickListener(toggleFloatingActionButtonListener)

        openingAnimation = createOpenFloatingActionButton()
        closingAnimation = createCloseFloatingActionButton()
    }

    override fun onPause() {
        super.onPause()
        if (openingAnimation.isRunning) {
            openingAnimation.cancel()
        }

        if (closingAnimation.isRunning) {
            closingAnimation.cancel()
        }
    }

    // SAMコンストラクタ
    private var toggleFloatingActionButtonListener = View.OnClickListener {
        if (state == FloatingActionState.NORMAL && !openingAnimation.isRunning) {
            openFloatingActionButton()
        } else if (state == FloatingActionState.ANIMATED && !closingAnimation.isRunning) {
            closeFloatingActionFragment()
        }
    }

    private fun createOpenFloatingActionButton(): Animator {
        val anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.fab_open)
        anim.setTarget(fab)
        anim.interpolator = DecelerateInterpolator()
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                state = FloatingActionState.ANIMATED
            }

            override fun onAnimationCancel(animation: Animator?) {
                animation?.end()
                state = FloatingActionState.ANIMATED
            }
        })
        return anim
    }

//    private fun createOpenFloatingActionButton(): Animator {
//        return AnimatorInflater.loadAnimator(applicationContext, R.animator.fab_open).apply {
//            setTarget(fab)
//            interpolator = DecelerateInterpolator()
//            addListener(object : AnimatorListenerAdapter() {
//                override fun onAnimationEnd(animation: Animator?) {
//                    state = FloatingActionState.ANIMATED
//                }
//
//                override fun onAnimationCancel(animation: Animator?) {
//                    animation?.end()
//                    state = FloatingActionState.ANIMATED
//                }
//            })
//        }
//    }

    private fun createCloseFloatingActionButton(): Animator {
        val anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.fab_close)
        anim.setTarget(fab)
        anim.interpolator = AccelerateInterpolator()
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                state = FloatingActionState.NORMAL
            }

            override fun onAnimationCancel(animation: Animator?) {
                animation?.end()
                state = FloatingActionState.NORMAL
            }
        })
        return anim
    }

    private fun openFloatingActionButton() {
        openingAnimation.start()
    }

    private fun closeFloatingActionFragment() {
        closingAnimation.start()
    }
}
