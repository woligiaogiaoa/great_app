package com.jsn.navgationpro.tab1

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsn.navgationpro.data.ArticleListEntity
import com.jsn.navgationpro.data.ArticleRepository
import com.jsn.navgationpro.data.BaseData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ArticleViewModel @ViewModelInject constructor(val articleRepository: ArticleRepository):ViewModel(){


    private val _articles=MutableLiveData<Result<BaseData<ArticleListEntity>>>()

    val articles :LiveData<Result<BaseData<ArticleListEntity>>>
    get() = _articles

    init {
        getArticles()
    }

    fun getArticles(){
        viewModelScope.launch{
            _articles.value=Result.Loading
            _articles.value = try {
                Result.Success(articleRepository.getArticles("0"))
            }catch (e:java.lang.Exception){
                Result.Error(e)
            }
        }
    }
}
fun ViewModel.viewModelScopeLaunchDefault(block: suspend CoroutineScope.() -> Unit)=
    viewModelScope.launch {
    block.invoke(this)
}

sealed class Result<out T>{ //a wrapper for data containing its loading state
    data class Success<T>(val data:T) : Result<T>() //这里是一种实现
    data class Error(val error:Exception) : Result<Nothing>()
    object Loading:Result<Nothing>()

    override fun toString(): String {
        return when(this){
            is Success -> {"success:"+data.toString()}
            is Error -> {"error:${error.message}"}
            Loading -> {"Result.loading"}
        }
    }
}
