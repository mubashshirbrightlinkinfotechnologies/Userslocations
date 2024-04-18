package com.example.placesapi.ui

import android.Manifest
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.placesapi.R
import com.example.placesapi.adapters.UserLocationAdapter

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.placesapi.databinding.ActivityMapsBinding
import com.example.placesapi.remote.data.Location
import com.example.placesapi.viewmodel.PlacesViewModel
import com.google.android.gms.maps.CameraUpdate

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var viewModel: PlacesViewModel
    private lateinit var userLocationAdapter: UserLocationAdapter

    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                Toast.makeText(this, "fine Permission granted", Toast.LENGTH_SHORT).show()
            }

            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()

            }

            else -> {
                // No location access granted.
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()

            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(PlacesViewModel::class.java)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        viewModel.keepUpdatingLocation()
        observeLiveData()
    }

    private fun setupRecyclerView(items: List<Location>) {
        userLocationAdapter = UserLocationAdapter(items ?: emptyList()) {
            mMap.apply {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it, 15f))
            }
        }
        binding.mapBottomSheet.userNamesRecyclerView.adapter = userLocationAdapter
        binding.mapBottomSheet.userNamesRecyclerView.layoutManager =
            LinearLayoutManager(this@MapsActivity)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        addMarkerOnMap(sydney, null)


    }

    private fun addMarkerOnMap(latLng: LatLng, text: String?) {
        mMap.addMarker(MarkerOptions().position(latLng).title(text ?: ""))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }


    private fun observeLiveData() {
        viewModel.mapResponse.observe(this) { response ->
            mMap.clear()
            setupRecyclerView(response.locations ?: emptyList())
            response.locations?.forEach { location ->
                val latLng = LatLng(location.latitude, location.longitude)
                addMarkerOnMap(latLng, text = location.name)
            }
        }

    }


    override fun onStart() {
        super.onStart()
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}