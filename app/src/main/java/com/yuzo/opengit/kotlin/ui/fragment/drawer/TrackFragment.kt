package com.yuzo.opengit.kotlin.ui.fragment.drawer

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
import com.yuzo.opengit.kotlin.ui.viewmodel.drawer.TrackViewModel

class TrackFragment : Fragment() {

    private lateinit var shareViewModel: TrackViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(DrawerCoordinateHelper.getInstance())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shareViewModel =
            ViewModelProviders.of(this).get(TrackViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_track, container, false)
        val textView: TextView = root.findViewById(R.id.text_share)
        shareViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}