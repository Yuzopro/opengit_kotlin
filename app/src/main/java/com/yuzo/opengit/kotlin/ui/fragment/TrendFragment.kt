package com.yuzo.opengit.kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.ui.DrawerCoordinateHelper
import com.yuzo.opengit.kotlin.ui.viewmodel.TrendViewModel

class TrendFragment : Fragment() {

    private lateinit var sendViewModel: TrendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(DrawerCoordinateHelper.getInstance())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sendViewModel =
            ViewModelProviders.of(this).get(TrendViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_trend, container, false)
        val textView: TextView = root.findViewById(R.id.text_send)
        sendViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}