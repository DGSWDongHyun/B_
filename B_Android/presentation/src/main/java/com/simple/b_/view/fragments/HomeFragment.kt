package com.simple.b_.view.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.simple.b_.R
import com.simple.b_.base.BaseFragment
import com.simple.b_.databinding.FragmentHomeBinding
import com.simple.b_.viewmodel.home.HomeViewModel
import com.simple.data.utils.Constants


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    val locationManager by lazy { requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager }

    override val viewModel: HomeViewModel
        get() = HomeViewModel(requireActivity().application)
    override val layoutRes: Int
        get() = R.layout.fragment_home

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
                Toast.makeText(requireContext(), "위치 정보 설정을 해주세요. [설정] > [권한]에서 변경이 가능합니다.", Toast.LENGTH_LONG).show()
                requireActivity().finishAndRemoveTask()
            }
        }
    }

    private fun getLocation(lastKnownLocation : Location) {
        viewModel.gpsData!!.apply {
            value?.latitude = lastKnownLocation.latitude
            value?.longitude = lastKnownLocation.longitude
        }
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