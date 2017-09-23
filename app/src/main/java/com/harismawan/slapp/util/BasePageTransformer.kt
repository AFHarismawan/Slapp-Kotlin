package com.harismawan.slapp.util

import android.support.v4.view.ViewPager
import android.view.View

abstract class BasePageTransformer : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        val pageIndex = page.tag as Int
        transformPage(page, pageIndex, position)
    }

    protected abstract fun transformPage(page: View, pageIndex: Int, position: Float)

    override fun toString(): String = javaClass.simpleName

    companion object {

        fun inRange(position: Float): Boolean = position <= 1.0 && position >= -1.0

        fun isLeftPage(position: Float): Boolean = position < 0

        fun isRightPage(position: Float): Boolean = position > 0
    }
}