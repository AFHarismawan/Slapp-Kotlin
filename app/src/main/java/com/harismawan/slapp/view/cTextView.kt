package com.harismawan.slapp.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

class cTextView : TextView {

    constructor(context: Context) : super(context) {
        setTypeFace()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setTypeFace()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setTypeFace()
    }

    private fun setTypeFace() {
        val montserrat = Typeface.createFromAsset(context.assets, "fonts/montserrat_regular.ttf")
        typeface = montserrat
    }
}