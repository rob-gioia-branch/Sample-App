package com.robg.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addToCartButton = findViewById<Button>(R.id.add_to_cart_button)
        val purchaseButton = findViewById<Button>(R.id.purchase_button)
        val customEventButton = findViewById<Button>(R.id.custom_event_button)

        addToCartButton.setOnClickListener {
            //track add to cart event
        }

        purchaseButton.setOnClickListener {
            //track purchase event
        }

        customEventButton.setOnClickListener {
            //track custom event
        }
    }
}