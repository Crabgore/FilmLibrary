package com.geekbrains.team.filmlibrary

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.geekbrains.team.filmlibrary.adapters.OnItemSelectedListener
import com.geekbrains.team.filmlibrary.fragments.favorites.FavoriteFragmentDirections
import com.geekbrains.team.filmlibrary.fragments.mainScreen.MainScreenFragmentDirections
import com.geekbrains.team.filmlibrary.fragments.search.SearchFragmentDirections
import com.geekbrains.team.filmlibrary.fragments.top.TopFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemSelectedListener {
    val minLux = 15

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavigation()
        setSensor()
    }

    private fun setUpNavigation() {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        NavigationUI.setupWithNavController(btv, navController)
    }

    private fun setSensor() {
        val mySensorManager =
            getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val lightSensor: Sensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        mySensorManager.registerListener(
            lightSensorListener,
            lightSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    private val lightSensorListener: SensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_LIGHT) {
                val currentLux = event.values[0]
                if (currentLux < minLux && AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    recreate()
                } else if (currentLux > minLux && AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_NO) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    recreate()
                }
            }
        }
    }

    override fun mainOpenItemInfo(id: Int) {
        val directions = MainScreenFragmentDirections.navigateToItemInfo(id)
        navController.navigate(directions)
    }

    override fun topOpenItemInfo(id: Int) {
        val directions = TopFragmentDirections.navigateToItemInfo(id)
        navController.navigate(directions)
    }

    override fun searchOpenItemInfo(id: Int) {
        val directions = SearchFragmentDirections.navigateToItemInfo(id)
        navController.navigate(directions)
    }

    override fun favoriteOpenItemInfo(id: Int) {
        val directions = FavoriteFragmentDirections.navigateToItemInfo(id)
        navController.navigate(directions)
    }
}
