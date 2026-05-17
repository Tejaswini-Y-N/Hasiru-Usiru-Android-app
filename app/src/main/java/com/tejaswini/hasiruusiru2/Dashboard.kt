package com.tejaswini.hasiruusiru2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(
            resources.getIdentifier(
                "activity_dashboard",
                "layout",
                packageName
            )
        )
        val backButton = findViewById<Button>(R.id.btnBack)

        backButton.setOnClickListener {

            finish()
        }
    }
}