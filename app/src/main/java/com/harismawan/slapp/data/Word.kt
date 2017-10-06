package com.harismawan.slapp.data

import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.harismawan.slapp.R
import com.harismawan.slapp.util.Utils
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFilterable
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import kotlinx.android.synthetic.main.item_word.view.*


class Word : AbstractFlexibleItem<Word.WordViewHolder>(), IFilterable {

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("nama")
    @Expose
    var name: String? = null

    @SerializedName("videoUrl")
    @Expose
    var link: String? = null

    @SerializedName("kategori")
    @Expose
    var category: String? = null

    @SerializedName("deskripsi")
    @Expose
    var desc: String? = null

    override fun equals(other: Any?): Boolean = false

    override fun getLayoutRes(): Int = R.layout.item_word

    override fun createViewHolder(view: View?, adapter: FlexibleAdapter<out IFlexible<*>>?): WordViewHolder =
            WordViewHolder(view, adapter)

    override fun bindViewHolder(adapter: FlexibleAdapter<out IFlexible<*>>?, holder: WordViewHolder?,
                                position: Int, payloads: MutableList<Any?>?) {
        val v = holder?.itemView
        Glide.with(v?.context)
                .load("https://img.youtube.com/vi/$link/0.jpg")
                .fitCenter()
                .into(v?.videoThumbnail)

        val d = v?.desc
        if (d?.lineCount == 1) d.visibility = View.GONE

        val b = v?.btnHide
        b?.setOnClickListener({
            if (d?.maxLines == 1 && d.lineCount != 1) {
                b.animate().rotation(180f).start()
                d.maxLines = d.lineCount

                //43 height/line
                Utils.expand(v, 43 * (d.lineCount - 1))
            } else {
                b.animate().rotation(0f).start()
                d?.maxLines = 1

                //43 height/line
                Utils.collapse(v, 43 * (d!!.lineCount - 1))
            }
        })

        val into = "$name : $category"
        v?.word?.text = into
        d?.text = desc
    }

    override fun filter(constraint: String?): Boolean =
            name?.toLowerCase()?.trim()!!.contains(constraint!!)

    class WordViewHolder(view: View?, adapter: FlexibleAdapter<out IFlexible<*>>?) : FlexibleViewHolder(view, adapter)
}