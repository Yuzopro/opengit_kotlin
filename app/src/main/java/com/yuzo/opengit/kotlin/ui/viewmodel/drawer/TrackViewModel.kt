package com.yuzo.opengit.kotlin.ui.viewmodel.drawer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrackViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is track Fragment"
    }
    val text: LiveData<String> = _text
}