package com.yuzo.opengit.kotlin.ui.viewmodel.drawer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrendViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is trend Fragment"
    }
    val text: LiveData<String> = _text
}