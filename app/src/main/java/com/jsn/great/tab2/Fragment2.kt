package com.jsn.great.tab2
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jsn.great.databinding.Fragment2Binding
import dagger.hilt.android.AndroidEntryPoint

fun Context.showText(s:String)=Toast.makeText(this,s,Toast.LENGTH_SHORT).show()

@AndroidEntryPoint
class Fragment2 :Fragment(){

    val viewModel :TwoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        val inflate = Fragment2Binding.inflate(inflater, container, false)
        subscribe()
        return inflate.root
    }

    fun subscribe(){

    }
}