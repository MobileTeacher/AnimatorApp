package io.github.mobileteacher.animatorapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_bar_control.*


/**
 * A simple [Fragment] subclass.
 *
 */
class BarControlFragment : Fragment() {

    private lateinit var animationViewModel: AnimationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bar_control, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //inicializar o ViewModel
        activity?.let {
            animationViewModel = ViewModelProviders
                .of(it)
                .get(AnimationViewModel::class.java)
        }

        rotation_seekbar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    animationViewModel.rotationY.value = progress
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}

            }
        )

        subscribe()
    }

    private fun subscribe(){
        animationViewModel.rotationY.observe(this, Observer{ rot->
            value_textview.text = rot.toString()
        })
    }


}
