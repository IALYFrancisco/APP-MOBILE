package com.mobiletelephony

import android.Manifest
import android.content.pm.PackageManager
import android.telephony.SubscriptionManager
import androidx.core.content.ContextCompat
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class MobileTelephonyModule : Module() {

    override fun definition() = ModuleDefinition {
        Name("MobileTelephony")

        AsyncFunction("getActiveSubscriptions") {
            val context = appContext.reactContext
                ?: throw Exception("React context is not available")

            val permissionStatus = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            )

            if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
                throw Exception("READ_PHONE_STATE permission is not granted")
            }

            val subscriptionManager =
                context.getSystemService(
                    SubscriptionManager::class.java
                )

            val activeSubscriptions =
                subscriptionManager.activeSubscriptionInfoList

            val defaultDataSubscriptionId =
                SubscriptionManager.getDefaultDataSubscriptionId()

            activeSubscriptions?.map { subscription ->

                mapOf(
                    "subscriptionId" to subscription.subscriptionId,
                    "simSlotIndex" to subscription.simSlotIndex,
                    "carrierName" to subscription.carrierName?.toString(),
                    "countryIso" to subscription.countryIso,
                    "isDefaultDataSubscription" to (
                        subscription.subscriptionId ==
                            defaultDataSubscriptionId
                        )
                )
            } ?: emptyList()
        }
    }
}