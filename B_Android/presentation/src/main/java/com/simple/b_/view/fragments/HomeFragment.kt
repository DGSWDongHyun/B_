package com.simple.b_.view.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.gms.maps.model.LatLng
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.simple.b_.R
import com.simple.b_.databinding.FragmentHomeBinding
import com.simple.b_.view.adapters.WeatherAdapter
import com.simple.b_.viewmodel.home.HomeViewModel
import com.simple.b_.viewmodel.weather.WeatherViewModel
import com.simple.data.utils.Constants
import kotlin.math.ceil


class HomeFragment : Fragment() {

    private lateinit var homeBinding : FragmentHomeBinding
    val locationManager by lazy { requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View {
        homeBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkPermission()
    }


    private fun checkPermission() {
        if(ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), Constants.REQUEST_GPS)
        }else{
            val lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

            if(lastKnownLocation != null)
                getLocation(lastKnownLocation)
            else {
                homeBinding.tempTitle.apply {
                    text = "안녕하세요. 좋은 날이죠? \n현재 기온 정보를 가져오시려면 GPS를 활성화해주세요."
                }
            }
        }
    }

    private fun getLocation(lastKnownLocation : Location) {
        homeBinding.homeViewModel = HomeViewModel(lastKnownLocation.latitude, lastKnownLocation.longitude)
        homeBinding.lifecycleOwner = requireActivity()
    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            Constants.REQUEST_GPS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkPermission()
                } else {
                    Toast.makeText(requireContext(), "위치 정보 설정을 해주세요. [설정] > [권한]에서 변경이 가능합니다.", Toast.LENGTH_LONG).show()
                    requireActivity().finishAndRemoveTask()
                }
            }
        }
    }
}