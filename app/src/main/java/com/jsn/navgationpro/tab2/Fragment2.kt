package com.jsn.navgationpro.tab2
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jsn.navgationpro.databinding.Fragment2Binding
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.fragment_2.*
import javax.inject.Inject
import javax.inject.Qualifier

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