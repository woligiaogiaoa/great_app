package com.jsn.great.jsnse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jsn.great.R

class JsnseFragment :Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val inf=inflater.inflate(R.layout.fragment_jsnse,container,false)
        return inf
    }

}