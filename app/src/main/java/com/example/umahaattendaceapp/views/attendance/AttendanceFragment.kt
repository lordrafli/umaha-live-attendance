package com.example.umahaattendaceapp.views.attendance

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.umahaattendaceapp.Manifest
import com.example.umahaattendaceapp.R
import com.example.umahaattendaceapp.databinding.FragmentAttendanceBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class AttendanceFragment : Fragment(), OnMapReadyCallback {

    companion object {
        private const val REQUEST_CODE_MAP_PERMISSION = 1000
    }
        private val mapPermissions = arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )






    private var mapAttendance: SupportMapFragment? = null
    private var map: GoogleMap? = null
    private var binding: FragmentAttendanceBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {binding = FragmentAttendanceBinding.inflate(inflater, container, false)
        return binding?.root}

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMaps()
    }

    private fun setupMaps() {
        mapAttendance = childFragmentManager.findFragmentById(R.id.map_attendance) as SupportMapFragment
        mapAttendance?.getMapAsync(this)
    }


    private fun checkPermission(): Boolean {
        var isHasPermission = false
        context?.let {
            for (permission in mapPermissions){
                isHasPermission = ActivityCompat.checkSelfPermission(it, permission) == PackageManager.PERMISSION_GRANTED
            }
        }
        return isHasPermission
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        if (checkPermission()) {
            val umaha = LatLng(-7.349545304399485, 112.68895525231115)
            map?.moveCamera(CameraUpdateFactory.newLatLng(umaha))
            map?.animateCamera(CameraUpdateFactory.zoomTo(20f))
            goToCurrentLocation()
        } else {
            setRequestPermission()
        }
    }

    private fun setRequestPermission() {
        TODO("Not yet implemented")
    }

    private fun goToCurrentLocation() {
        TODO("Not yet implemented")
    }
}