package com.example.nocountrydonation

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.nocountrydonation.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            val message = if (isGranted) "permiso concedido" else "permiso rechazado"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    private val nameCanal = "test"
    private val idCanal = "id"
    private val notificationId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        screenSplash.setKeepOnScreenCondition {
            lifecycleScope.launch {
                delay(2000)
            }
            false
        }
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                as NavHostFragment
        navHostFragment.navController.apply {
            binding.bottomNavigationView.setupWithNavController(this)
            binding.bottomNavigationView.itemIconTintList = null
            if (getPrefs()) {
                navigate(R.id.action_onboardingFragment_to_loginFragment)
            } else {
                navigate(R.id.action_onboardingFragment_self)
            }
        }
    }
    fun showPermission() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
    private fun getPrefs() = getPreferences(Context.MODE_PRIVATE).getBoolean("onBoardingFinished", false)
    fun showBottomNav(show: Boolean) {
        binding.bottomNavigationView.isVisible = show
    }
    fun sendNotification(title:String, body:String, description:String){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            val importCanal = NotificationManager.IMPORTANCE_HIGH
            val canal = NotificationChannel(idCanal,nameCanal,importCanal)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(canal)
        }

        val notification = NotificationCompat.Builder(this,idCanal).also {
            it.setContentTitle(title)
            it.setContentText(body)
            it.setStyle(NotificationCompat.BigTextStyle().bigText(description))
            it.setSmallIcon(R.drawable.notification)
            it.priority = NotificationCompat.PRIORITY_HIGH
        }.build()
        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager.notify(notificationId, notification)
    }
}