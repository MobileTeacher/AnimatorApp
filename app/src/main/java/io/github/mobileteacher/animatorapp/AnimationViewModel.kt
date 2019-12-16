package io.github.mobileteacher.animatorapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnimationViewModel: ViewModel() {
    //var flipped: Boolean = false
    val flipped = MutableLiveData<Boolean>().apply { value = false }
//    var rotationY: Int = 0
    val rotationY = MutableLiveData<Int>().apply {
        value = 0
    }

    val rotationZ = MutableLiveData<Int>().apply {
        value = 0
    }

    val currentAxis = MutableLiveData<Int>().apply { value = Y }

    init {
        rotationY.observeForever {rotation->
            val isFlipped = flipped.value ?: false
            if (rotation > 90 && !isFlipped){
                flipped.value = true
            } else if (rotation < 90 && isFlipped){
                flipped.value = false
            }
        }
    }
}