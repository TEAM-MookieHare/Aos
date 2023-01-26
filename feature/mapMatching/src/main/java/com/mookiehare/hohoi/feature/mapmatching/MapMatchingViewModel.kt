package com.mookiehare.hohoi.feature.mapmatching

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationServices
import com.mookiehare.hohoi.feature.mapmatching.model.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MapMatchingViewModel @Inject constructor(

): ViewModel() {

    private val _lastSelectedLocation = MutableStateFlow(Location(0.0, 0.0))
    val lastSelectedLocation = _lastSelectedLocation.asStateFlow()

    private fun setLocation(loc: Location) {
        _lastSelectedLocation.value = loc
    }

    fun getCurrentLocation(context: Context) {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        try {

            val locationResult = fusedLocationProviderClient.lastLocation

            locationResult.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result?.let {
                        setLocation(Location(it.latitude, it.longitude))
                    }
                } else {
                    Log.d("Exception", " Current User location is null")
                }
            }

        } catch (e: SecurityException) {
            Log.d("Exception", "Exception:  $e.message.toString()")
        }
    }

}