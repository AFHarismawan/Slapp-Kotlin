package com.harismawan.slapp.util

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.View
import com.harismawan.slapp.config.Constant
import com.harismawan.slapp.data.APIHelper
import com.harismawan.slapp.data.RetrofitClient
import eu.davidea.flexibleadapter.helpers.AnimatorHelper.setDuration
import android.view.View.GONE
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import eu.davidea.flexibleadapter.helpers.AnimatorHelper.setDuration
import android.animation.ValueAnimator




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

        fun expand(v: View, length: Int) {
            val anim = ValueAnimator.ofInt(v.height, v.measuredHeight + length)
            anim.addUpdateListener { valueAnimator ->
                val vAnim = valueAnimator.animatedValue as Int
                val layoutParams = v.layoutParams
                layoutParams.height = vAnim

                v.layoutParams = layoutParams
            }
            anim.duration = 200
            anim.start()
        }

        fun collapse(v: View, length: Int) {
            val anim = ValueAnimator.ofInt(v.height, v.measuredHeight - length)
            anim.addUpdateListener { valueAnimator ->
                val vAnim = valueAnimator.animatedValue as Int
                val layoutParams = v.layoutParams
                layoutParams.height = vAnim
                v.layoutParams = layoutParams
            }
            anim.duration = 200
            anim.start()
        }

//        fun expand(v: View, height : Int) {
//            v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//            val targetHeight = height
//
//            // Older versions of android (pre API 21) cancel animations for views with a height of 0.
//            v.layoutParams.height = 1
//            v.visibility = View.VISIBLE
//            val a = object : Animation() {
//                override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
//                    v.layoutParams.height = if (interpolatedTime == 1f)
//                        ViewGroup.LayoutParams.WRAP_CONTENT
//                    else
//                        (targetHeight * interpolatedTime).toInt()
//                    v.requestLayout()
//                }
//
//                override fun willChangeBounds(): Boolean = true
//            }
//
//            // 1dp/ms
//            a.duration = ((targetHeight / v.context.resources.displayMetrics.density).toInt()).toLong()
//            v.startAnimation(a)
//        }
//
//        fun collapse(v: View, height: Int) {
//            val initialHeight = height
//
//            val a = object : Animation() {
//                override fun applyTransformation(interpolatedTime: Float, t: Transformation) =
//                        if (interpolatedTime == 1f) {
//                            v.setVisibility(View.GONE)
//                        } else {
//                            v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
//                            v.requestLayout()
//                        }
//
//                override fun willChangeBounds(): Boolean = true
//            }
//
//            // 1dp/ms
//            a.duration = ((initialHeight / v.context.resources.displayMetrics.density).toInt()).toLong()
//            v.startAnimation(a)
//        }
    }
}
