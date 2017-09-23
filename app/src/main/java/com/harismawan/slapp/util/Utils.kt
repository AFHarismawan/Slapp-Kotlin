package com.harismawan.slapp.util

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import com.harismawan.slapp.config.Constant
import com.harismawan.slapp.data.APIHelper
import com.harismawan.slapp.data.RetrofitClient

class Utils {

    companion object {
        fun getAPIHelper(): APIHelper? =
                RetrofitClient.client?.create(APIHelper::class.java)

        fun overrideFont(context: Context, defaultFontNameToOverride: String, customFontFileNameInAssets: String) {
            try {
                val customFontTypeface = Typeface.createFromAsset(context.assets, customFontFileNameInAssets)
                val defaultFontTypefaceField = Typeface::class.java.getDeclaredField(defaultFontNameToOverride)
                defaultFontTypefaceField.isAccessible = true
                defaultFontTypefaceField.set(null, customFontTypeface)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
