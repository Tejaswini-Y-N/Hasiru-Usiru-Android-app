package com.tejaswini.hasiruusiru2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    // COUNTERS

    private var oxygenValue = 85
    private var treeCount = 0
    private var pitCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // OXYGEN SCORE

        val txtOxygen =
            findViewById<TextView>(R.id.txtOxygen)

        txtOxygen.text = "$oxygenValue%"

        // OXYGEN COLOR

        updateOxygenColor(txtOxygen)

        // MAP

        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map)
                    as SupportMapFragment

        mapFragment.getMapAsync(this)

        // FLOATING BUTTONS

        val btnLocation =
            findViewById<FloatingActionButton>(R.id.btnLocation)

        val btnTree =
            findViewById<FloatingActionButton>(R.id.btnTree)

        val btnPit =
            findViewById<FloatingActionButton>(R.id.btnPit)

        // LOCATION BUTTON

        btnLocation.setOnClickListener {

            val bengaluru =
                LatLng(12.9716, 77.5946)

            mMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    bengaluru,
                    15f
                )
            )

            Toast.makeText(
                this,
                "Location Opened",
                Toast.LENGTH_SHORT
            ).show()
        }

        // TREE BUTTON

        btnTree.setOnClickListener {

            treeCount++

            oxygenValue += 2

            if (oxygenValue > 100) {

                oxygenValue = 100
            }

            txtOxygen.text = "$oxygenValue%"

            updateOxygenColor(txtOxygen)

            // RANDOM TREE LOCATION

            val randomLat =
                12.9716 + (Math.random() - 0.5) / 100

            val randomLng =
                77.5946 + (Math.random() - 0.5) / 100

            val treeLocation =
                LatLng(randomLat, randomLng)

            // RANDOM TREE NAME

            val treeNames = arrayOf(
                "Neem Tree",
                "Mango Tree",
                "Peepal Tree",
                "Banyan Tree",
                "Coconut Tree"
            )

            val treeName =
                treeNames.random()

            // ADD TREE MARKER

            val marker = mMap.addMarker(

                MarkerOptions()
                    .position(treeLocation)
                    .title(treeName)
                    .snippet("Tree #$treeCount Added Successfully")
                    .icon(
                        BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_GREEN
                        )
                    )
            )

            mMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    treeLocation,
                    17f
                )
            )

            marker?.showInfoWindow()

            Toast.makeText(
                this,
                "$treeName Added",
                Toast.LENGTH_SHORT
            ).show()
        }

        // EMPTY PIT BUTTON

        btnPit.setOnClickListener {

            pitCount++

            val randomLat =
                12.9750 + (Math.random() - 0.5) / 100

            val randomLng =
                77.5980 + (Math.random() - 0.5) / 100

            val pitLocation =
                LatLng(randomLat, randomLng)

            val marker = mMap.addMarker(

                MarkerOptions()
                    .position(pitLocation)
                    .title("Empty Pit #$pitCount")
                    .snippet("Available For Plantation")
                    .icon(
                        BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_ORANGE
                        )
                    )
            )

            mMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    pitLocation,
                    17f
                )
            )

            marker?.showInfoWindow()

            Toast.makeText(
                this,
                "Empty Pit Added",
                Toast.LENGTH_SHORT
            ).show()
        }

        // BOTTOM NAVIGATION

        val bottomNav =
            findViewById<BottomNavigationView>(R.id.bottomNav)

        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.nav_map -> {

                    Toast.makeText(
                        this,
                        "Map Opened",
                        Toast.LENGTH_SHORT
                    ).show()

                    true
                }

                R.id.nav_dashboard -> {

                    startActivity(
                        Intent(
                            this,
                            DashboardActivity::class.java
                        )
                    )

                    true
                }

                R.id.nav_tree -> {

                    startActivity(
                        Intent(
                            this,
                            Plantation::class.java
                        )
                    )

                    true
                }

                R.id.nav_profile -> {

                    startActivity(
                        Intent(
                            this,
                            ProfileActivity::class.java
                        )
                    )

                    true
                }

                else -> false
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        val bengaluru =
            LatLng(12.9716, 77.5946)

        mMap.addMarker(
            MarkerOptions()
                .position(bengaluru)
                .title("Bengaluru")
        )

        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                bengaluru,
                13f
            )
        )
    }

    // OXYGEN COLOR FUNCTION

    private fun updateOxygenColor(txtOxygen: TextView) {

        if (oxygenValue >= 90) {

            txtOxygen.setTextColor(Color.GREEN)

        } else if (oxygenValue >= 80) {

            txtOxygen.setTextColor(
                Color.rgb(255, 165, 0)
            )

        } else {

            txtOxygen.setTextColor(Color.RED)
        }
    }
}