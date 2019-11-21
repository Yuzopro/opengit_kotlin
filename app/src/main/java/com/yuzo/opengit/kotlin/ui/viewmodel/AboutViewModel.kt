package com.yuzo.opengit.kotlin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is about Fragment"
    }
    val text: LiveData<String> = _text
}