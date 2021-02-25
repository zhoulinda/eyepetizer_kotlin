package com.linda.module_community.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.linda.module_base.bean.Card
import com.youth.banner.adapter.BannerAdapter
import org.jetbrains.anko.imageResource

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/13
 */
class ItemAdapter(cards: List<Card>) : BannerAdapter<Card, ItemAdapter.BannerViewHolder>(cards) {

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent?.context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder?, card: Card?, position: Int, size: Int) {
        if (holder?.itemView is ImageView)
            Glide.with(holder.itemView.context)
                .load(card?.data?.actionUrl)
                .into(holder.itemView as ImageView)
    }


    class BannerViewHolder(imageView: ImageView) : RecyclerView.ViewHolder(imageView)
}