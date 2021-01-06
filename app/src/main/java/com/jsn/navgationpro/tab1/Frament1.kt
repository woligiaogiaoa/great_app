package com.jsn.navgationpro.tab1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jsn.navgationpro.databinding.Fragment1Binding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_1.*

@AndroidEntryPoint
class Frament1 :Fragment(){

    val  articleViewModel: ArticleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        val inflate = Fragment1Binding.inflate(inflater, container, false)
        bindViewModel()
        return inflate.root
    }

    private fun bindViewModel() {
        articleViewModel.articles.observe(viewLifecycleOwner){
            if(it is Result.Success){
                val data = it.data
                data.resultSuccessOrNull()?.apply {
                    datas[0]?.apply {
                        //context?.showToast(this.title+" "+link)
                        link?.also {
                            try {
                                //WebViewActivity.start(requireActivity(),link)
                            }catch (e:Exception){
                                e.message?.also{context?.showToast(it)}
                            }
                        }
                    }
                }
            }
            pb.isVisible= it is Result.Loading
        }
    }
}
fun Context.showToast(s:String)= Toast.makeText(this,s,Toast.LENGTH_SHORT).show()