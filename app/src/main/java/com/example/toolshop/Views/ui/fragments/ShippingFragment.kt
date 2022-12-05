package com.example.toolshop.Views.ui.fragments

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.toolshop.BuildConfig
import com.example.toolshop.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.osmdroid.config.Configuration

import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker

class ShippingFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    lateinit var map: MapView

    companion object{
        const val REQUEST_CODE_LOCATION=0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shipping, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            this.childFragmentManager.findFragmentById(R.id.mapGoogle) as SupportMapFragment
        mapFragment.getMapAsync(this)

        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID)

        //Open Street Map
        map = view.findViewById(R.id.mapOpen)
        map.setTileSource(TileSourceFactory.MAPNIK)

        // Localización
        val lat = 4.7121593
        val long =  -74.071372
        val geoPoint = GeoPoint(lat, long)
        val mapController = map.controller
        mapController.setZoom(16.0)
        mapController.setCenter(geoPoint)
        //marcador
        val marker = Marker(map)
        marker.setPosition(geoPoint)
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.setTitle("Robot Shop")
        map.overlays.add(marker)

    }

    override fun onMapReady(googleMap: GoogleMap) {

        //Add a marker in Colombia and move the camera
        val colombia = LatLng(4.7121593,  -74.071372)
        googleMap?.let {
            this.mMap = it
            googleMap.addMarker(MarkerOptions()
                .position(colombia)
                .title("Robot Shop")
            )
        }

        enableLocation()
    }

    @SuppressLint("MissingPermission")
    fun enableLocation() {
        if (!::mMap.isInitialized) return
            if (isLocationPermissionGrated()) {
                mMap.isMyLocationEnabled = true
            } else {
            requestLocationPermission()
        }
    }

    fun isLocationPermissionGrated()= ContextCompat.checkSelfPermission(
        this.requireContext(),
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    fun requestLocationPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this.requireActivity(),android.Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(this.context, "Requires permissions to be activated in the phone settings",Toast.LENGTH_LONG).show()

        }else{
            ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                com.example.toolshop.Views.ui.fragments.ShippingFragment.Companion.REQUEST_CODE_LOCATION
            )

            }
        }
    }
