package com.jsn.great

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.core.view.WindowCompat
import androidx.core.view.updatePadding
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jsn.great.tab1.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    lateinit var navHostFrament:NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navHostFrament=supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController=navHostFrament.navController
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav?.setupWithNavController(navController)
        Handler(Looper.getMainLooper()).postDelayed({
            showToast(stringFromJNI()?: "jni return empty")
        },0)
        contentPaddingInsets()
        full()
    }

    private fun contentPaddingInsets() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if(ev?.action==MotionEvent.ACTION_DOWN){
           /* val controller = window.decorView.windowInsetsController*/
           /* controller?.setSystemBarsBehavior(
                WindowInsetsController.BEHAVIOR_SHOW_BARS_BY_SWIPE
            )
            // When we want to hide the system bars
            controller?.hide(WindowInsets.Type.systemBars())*/
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(hasFocus){
            // Immersive is now...
           /* val controller = window.decorView.windowInsetsController
            controller?.setSystemBarsBehavior(
                WindowInsetsController.BEHAVIOR_SHOW_BARS_BY_SWIPE
            )*/
          /*  // When we want to hide the system bars
            controller?.hide(WindowInsets.Type.systemBars())*/
        }
    }

    private fun full() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        //window.decorView.systemUiVisibility= FLAGS_FULLSCREEN
        v_content.setOnApplyWindowInsetsListener { v, insets ->
            v.updatePadding(left = insets.systemWindowInsetLeft)
            v.updatePadding(right = insets.systemWindowInsetRight)
            insets.replaceSystemWindowInsets(
                0, insets.systemWindowInsetTop,
                0, insets.systemWindowInsetBottom
            )
            insets
        }
        val paddingOriginal=bottom_nav.paddingBottom
        bottom_nav.setOnApplyWindowInsetsListener { v, insets ->
            val newPadding=insets.systemWindowInsetBottom+paddingOriginal
            v.updatePadding(bottom = newPadding)
            insets
        }


    }

    external fun stringFromJNI():String?

    companion object{
        init {
            System.loadLibrary("native-lib")
        }

        /** Combination of all flags required to put activity into immersive mode */
        @SuppressLint("InlinedApi")
        const val FLAGS_FULLSCREEN =
           // View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or

                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or

                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
}
