package io.github.mobileteacher.animatorapp


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_card_viewer.*

/**
 * A simple [Fragment] subclass.
 *
 */
class CardViewerFragment : Fragment() {

    private lateinit var animationViewModel: AnimationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_viewer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            animationViewModel = ViewModelProviders
                .of(it)
                .get(AnimationViewModel::class.java)
        }
        subscribe()
    }

    private fun subscribe(){
        animationViewModel.rotationY.observe(this, Observer {
            square.rotationY = it.toFloat()
        })

        animationViewModel.rotationZ.observe(this, Observer {
            square.rotation = it.toFloat()
        })

        animationViewModel.flipped.observe(this, Observer {
            if (it){
                square.setCardBackgroundColor(Color.MAGENTA)
            } else {
                square.setCardBackgroundColor(Color.YELLOW)
            }
        })
    }


}
