package com.harismawan.slapp.data

import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.harismawan.slapp.R
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import kotlinx.android.synthetic.main.item_word.view.*

class Word : AbstractFlexibleItem<Word.WordViewHolder>() {

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("nama")
    @Expose
    var name: String? = null

    @SerializedName("link")
    @Expose
    var link: String? = null

    override fun equals(other: Any?): Boolean = false

    override fun getLayoutRes(): Int = R.layout.item_word

    override fun createViewHolder(view: View?, adapter: FlexibleAdapter<out IFlexible<*>>?): WordViewHolder =
            WordViewHolder(view, adapter)

    override fun bindViewHolder(adapter: FlexibleAdapter<out IFlexible<*>>?, holder: WordViewHolder?,
                                position: Int, payloads: MutableList<Any?>?) {
        val v = holder?.itemView
        Glide.with(v?.context)
                .load("""https://img.youtube.com/vi/$link/0.jpg""")
                .fitCenter()
                .into(v?.videoThumbnail)
        v?.word?.text = name?.toUpperCase()
    }

    class WordViewHolder(view: View?, adapter: FlexibleAdapter<out IFlexible<*>>?) : FlexibleViewHolder(view, adapter)
}