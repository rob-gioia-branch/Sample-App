package com.robg.sampleapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import io.branch.referral.Branch
import io.branch.referral.util.BRANCH_STANDARD_EVENT
import io.branch.referral.util.BranchEvent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Branch.sessionBuilder(this).withCallback { branchUniversalObject, linkProperties, error ->
            if (error != null) {
                Log.e("BranchSDK_Tester", "branch init failed. Caused by -" + error.message)
            } else {
                Log.e("BranchSDK_Tester", "branch init complete!")
                if (branchUniversalObject != null) {
                    Log.e("BranchSDK_Tester", "title " + branchUniversalObject.title)
                    Log.e("BranchSDK_Tester", "CanonicalIdentifier " + branchUniversalObject.canonicalIdentifier)
                    Log.e("BranchSDK_Tester", "metadata " + branchUniversalObject.contentMetadata.convertToJson())
                }
                if (linkProperties != null) {
                    Log.e("BranchSDK_Tester", "Channel " + linkProperties.channel)
                    Log.e("BranchSDK_Tester", "control params " + linkProperties.controlParams)
                }
            }
        }.withData(this.intent.data).init()

        val addToCartButton = findViewById<Button>(R.id.add_to_cart_button)
        val purchaseButton = findViewById<Button>(R.id.purchase_button)
        val customEventButton = findViewById<Button>(R.id.custom_event_button)

        addToCartButton.setOnClickListener {
            BranchEvent(BRANCH_STANDARD_EVENT.ADD_TO_CART).logEvent(applicationContext)
        }

        purchaseButton.setOnClickListener {
            BranchEvent(BRANCH_STANDARD_EVENT.PURCHASE).logEvent(applicationContext)
        }

        customEventButton.setOnClickListener {
            BranchEvent("Custom Event").logEvent(applicationContext)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Branch.sessionBuilder(this).withCallback { referringParams, error ->
            if (error != null) {
                Log.e("BranchSDK_Tester", error.message)
            } else if (referringParams != null) {
                Log.e("BranchSDK_Tester", referringParams.toString())
            }
        }.reInit()
    }
}