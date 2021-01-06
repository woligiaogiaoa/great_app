package com.jsn.navgationpro

import android.app.Application
import android.content.Context
import android.util.Log
import com.bun.miitmdid.core.MdidSdkHelper
import com.bun.miitmdid.interfaces.IIdentifierListener
import com.bun.miitmdid.interfaces.IdSupplier
import com.jsn.navgationpro.db.User
import com.jsn.navgationpro.db.UserRoomDatabase
import com.jsn.navgationpro.tab1.showToast
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@HiltAndroidApp
class App :Application() {

    @Inject
    lateinit var applicationScope:CoroutineScope

    @Inject
    lateinit var database: UserRoomDatabase

    companion object{
         var instance:Application?=null
    }
    var a:String?=null

    override fun onCreate() {
        super.onCreate()
        instance=this
        debugDeviceIds(this)
        testDB()
    }

    private fun testDB() {
        applicationScope.launch {
            database.dao().insert(User(111,"jsn","zj"))
            val users = withContext(Dispatchers.IO){
                database.dao().allUsers().firstOrNull()
            }
            showToast(users?.toString() ?: "empty user")
        }
    }

    private fun debugDeviceIds(app: App) {
        applicationScope.launch {
            val st=System.currentTimeMillis()
            val oaid =try {
                app.awaitOAID()
            }catch (e:java.lang.Exception){
                null
            }
            val end=System.currentTimeMillis()
            val ms=(end-st).toFloat()/1000
            Log.e("haoshi",ms.toString()+"s")
            oaid?.also { showToast("oaid:${it}") } ?: showToast("empty oaid")
        }
    }
}
suspend fun Context.awaitOAID():String= suspendCancellableCoroutine { continuation ->
    MdidSdkHelper.InitSdk(this,true, object :IIdentifierListener{
        override fun OnSupport(p0: Boolean, idSupplier: IdSupplier?) {

            Log.e("support","onSupport")

            idSupplier?: continuation.resumeWithException(Exception("faild to get oaid"))

            idSupplier?.apply {

                oaid?.also { continuation.resume(oaid) }
                    ?: continuation.resumeWithException(java.lang.Exception("oaid failure"))
            }
        }
    })
}

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule{

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context:Context)=UserRoomDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideCoroutineScope()= CoroutineScope(Dispatchers.Main.immediate+ SupervisorJob())
}