package com.harismawan.slapp.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.harismawan.slapp.R
import kotlinx.android.synthetic.main.fragment_intro.*

class IntroFragment : Fragment() {

    companion object {
        private val argPosition = "position"

        fun newInstance(position: Int): IntroFragment {
            val args = Bundle()
            args.putInt(argPosition, position)
            val fragment = IntroFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.fragment_intro, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val position = arguments.getInt(argPosition)
        val text = IntroActivity.Content.values()[position].text

        view!!.tag = position

        contentTextView.text = text
    }
}

