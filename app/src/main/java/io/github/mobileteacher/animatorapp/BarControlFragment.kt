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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_bar_control.*

const val Y = 1
const val Z = 2
/**
 * A simple [Fragment] subclass.
 *
 */
class BarControlFragment : Fragment() {

    private lateinit var animationViewModel: AnimationViewModel
    private var axis: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        axis = arguments?.getInt("axis") ?: 0
        return inflater.inflate(R.layout.fragment_bar_control, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        axis_label_textview.text = if (axis == Y) "RY" else "RZ"

        //inicializar o ViewModel
        activity?.let {
            animationViewModel = ViewModelProviders
                .of(it)
                .get(AnimationViewModel::class.java)
        }

        rotation_seekbar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser){
                        if (axis == Y){
                            animationViewModel.rotationY.value = progress
                        } else {
                            animationViewModel.rotationZ.value = progress
                        }
                    }

                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}

            }
        )

        subscribe()
    }

    private fun subscribe(){
        if (axis == Y){
            animationViewModel.rotationY.observe(this, Observer{ rot->
                value_textview.text = rot.toString()
            })
        } else {
            animationViewModel.rotationZ.observe(this, Observer { rot ->
                if (axis == Z) {
                    value_textview.text = rot.toString()
                }
            })
        }
    }

    companion object{
        fun newInstance(axis: Int): BarControlFragment{
            val fragment = BarControlFragment()
            val bundle = Bundle()
            bundle.putInt("axis", axis)
            fragment.arguments = bundle
            return fragment
        }
    }

}
