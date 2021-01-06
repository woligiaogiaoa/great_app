package com.jsn.great.data

class BaseData<T>(var `data`: T,
                  var errorCode: Int,
                  var errorMsg: String){
    fun resultSuccessOrNull()=if(errorCode==0) data else null
}


fun <T> BaseData<T>.successOrJustNull()= resultSuccessOrNull()