package com.harismawan.slapp.ui

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.harismawan.slapp.R
import com.harismawan.slapp.util.BasePageTransformer
import com.harismawan.slapp.util.ColorTransformer
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        vpIntro.setPageTransformer(false, pageTransformer)
        vpIntro.adapter = ViewPagerAdapter(supportFragmentManager)
        tabDots.setupWithViewPager(vpIntro, true)
        vpIntro.setPageTransformer(false, ColorTransformer())
        vpIntro.addOnPageChangeListener(this)
    }

    private val pageTransformer = object : BasePageTransformer() {
        override fun transformPage(page: View, pageIndex: Int, position: Float) {
            ColorTransformer()
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        if (position == 3) {
            btnGetStarted.visibility = View.VISIBLE
            btnGetStarted.setOnClickListener({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            })
        } else {
            btnGetStarted.visibility = View.GONE
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    enum class Content constructor(val text: String, val color: Int) {
        ONE("1", Color.CYAN),
        TWO("2", Color.MAGENTA),
        THREE("3", Color.YELLOW),
        FOUR("4", Color.BLUE)
    }

    private class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment = IntroFragment.newInstance(position)

        override fun getCount(): Int = Content.values().size
    }
}
