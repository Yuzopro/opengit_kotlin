package com.yuzo.opengit.kotlin.ui.viewmodel.search

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuzo.opengit.kotlin.common.SimpleTextWatcher

class SearchViewModel : ViewModel() {

    val text = MutableLiveData<String>("")
    val actionBtnState = MutableLiveData<Boolean>(false)

    val textWatcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)

            val str = s.toString()
            text.value = str

            if (str.isNullOrEmpty()) {
                actionBtnState.value = false
            } else {
                actionBtnState.value = true
            }
        }
    }
}