package io.github.mobileteacher.animatorapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_bar_control.*
import kotlinx.android.synthetic.main.fragment_card_viewer.*

class MainActivity : AppCompatActivity() {

    val fragments = listOf(BarControlFragment.newInstance(Y),
                            BarControlFragment.newInstance(Z))

    private lateinit var animationViewModel: AnimationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animationViewModel = ViewModelProviders
            .of(this)
            .get(AnimationViewModel::class.java)
        setUpListeners()
        subscribe()
    }

    fun setUpListeners(){
        bottom_navigationview.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.rotationy_item -> animationViewModel.currentAxis.value = Y
                else -> animationViewModel.currentAxis.value = Z
            }

            true
        }
    }

    fun subscribe(){
        animationViewModel.currentAxis.observe(this, Observer {
            val currentFragment = if (it == Y){
                fragments[0]
            } else {
                fragments[1]
            }

            supportFragmentManager
                .beginTransaction().apply {
                    replace(R.id.container, currentFragment)
                    commit()
            }
        })
    }
}
