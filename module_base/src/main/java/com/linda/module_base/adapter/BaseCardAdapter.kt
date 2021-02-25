package com.linda.module_base.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linda.module_base.bean.Card
import com.linda.module_base.bean.ItemData
import com.linda.module_base.bean.VideoItem
import com.linda.module_base.listener.OnMultiViewClickListener
import com.linda.module_base.view.*
import kotlinx.android.synthetic.main.view_auto_play_card.view.*
import kotlinx.android.synthetic.main.view_follow_card.view.portrait

/**
 * 描述 :     发现Adapter
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/6
 */
class BaseCardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var datas: ArrayList<VideoItem<ItemData>> = ArrayList()

    companion object {
        const val ITEM_TYPE_HORIZONTAL_SCROLL_CARD = 1
        const val ITEM_TYPE_TEXT_CARD = 2
        const val ITEM_TYPE_BANNER = 3
        const val ITEM_TYPE_BANNER3 = 4
        const val ITEM_TYPE_BRIEF_CARD = 5
        const val ITEM_TYPE_FOLLOW_CARD = 6
        const val ITEM_TYPE_COLUMN_CARD_LIST = 7
        const val ITEM_TYPE_VIDEO_SMALL_CARD = 8
        const val ITEM_TYPE_AUTO_PLAY_AD = 9
        const val ITEM_TYPE_INFORMATION_CARD = 10
        const val ITEM_TYPE_AUTO_PLAY_FOLLOW_CARD = 11
        const val ITEM_TYPE_COMMUNITY_COLUMNS_CARD = 12
        const val ITEM_TYPE_SPECIAL_SQUARE_CARD_COLLECTION = 13
        const val ITEM_TYPE_VIDEO_COLLECTION_OF_HORIZONTAL_SCROLL_CARD = 14
        const val ITEM_TYPE_EMPTY_CARD = 0 //empty类型
    }

    private val layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )

    fun setData(list: ArrayList<VideoItem<ItemData>>) {
        datas.clear()
        datas.addAll(list)
        notifyDataSetChanged()
    }

    fun addData(list: ArrayList<VideoItem<ItemData>>) {
        datas.addAll(list)
        notifyDataSetChanged()
    }


    /**
     * 插入新数据
     */
    fun insertData(list: ArrayList<VideoItem<ItemData>>) {
        var index = 0
        for (data in list) {
            datas.add(data)
            index++
        }
        notifyItemRangeInserted(datas.size - index, index)
    }

    fun getData(): ArrayList<VideoItem<ItemData>> {
        return datas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_HORIZONTAL_SCROLL_CARD ->
                ItemHolder(HorizontalScrollCardView(parent.context))
            ITEM_TYPE_BANNER ->
                ItemHolder(BannerCardView(parent.context))
            ITEM_TYPE_BANNER3 ->
                ItemHolder(AdvertisementView(parent.context))
            ITEM_TYPE_AUTO_PLAY_AD ->
                ItemHolder(AutoPlayAdView(parent.context))
            ITEM_TYPE_SPECIAL_SQUARE_CARD_COLLECTION ->
                ItemHolder(SquareCardView(parent.context))
            ITEM_TYPE_COLUMN_CARD_LIST ->
                ItemHolder(ColumnCardView(parent.context))
            ITEM_TYPE_TEXT_CARD ->
                ItemHolder(TextCardView(parent.context))
            ITEM_TYPE_VIDEO_SMALL_CARD ->
                ItemHolder(VideoCardView(parent.context))
            ITEM_TYPE_BRIEF_CARD ->
                ItemHolder(BriefCardView(parent.context))
            ITEM_TYPE_FOLLOW_CARD ->
                ItemHolder(FollowCardView(parent.context))
            ITEM_TYPE_AUTO_PLAY_FOLLOW_CARD ->
                ItemHolder(AutoPlayCardView(parent.context))
            ITEM_TYPE_COMMUNITY_COLUMNS_CARD ->
                ItemHolder(CommunityColumnsCard(parent.context))
            ITEM_TYPE_VIDEO_COLLECTION_OF_HORIZONTAL_SCROLL_CARD ->
                ItemHolder(VideoCollectionHorizontalScrollCardView(parent.context))
            else ->
                ItemHolder(View(parent.context))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemHolder) {
            val itemData = datas[position].data
            when (getItemViewType(position)) {
                ITEM_TYPE_HORIZONTAL_SCROLL_CARD ->
                    bindScrollCard(
                        position,
                        holder.itemView,
                        itemData.itemList
                    )
                ITEM_TYPE_BANNER ->
                    bindBannerCard(position, holder.itemView, itemData)
                ITEM_TYPE_BANNER3 ->
                    bindAdvertisement(position, holder.itemView, itemData)
                ITEM_TYPE_AUTO_PLAY_AD ->
                    bindAutoPlayAdView(position, holder.itemView, itemData)
                ITEM_TYPE_SPECIAL_SQUARE_CARD_COLLECTION ->
                    bindSquareCard(
                        position,
                        holder.itemView,
                        itemData
                    )
                ITEM_TYPE_COLUMN_CARD_LIST ->
                    bindColumnCard(
                        position,
                        holder.itemView,
                        itemData
                    )
                ITEM_TYPE_TEXT_CARD ->
                    bindTextCard(position, holder.itemView, itemData)
                ITEM_TYPE_BRIEF_CARD ->
                    bindBriefCard(position, holder.itemView, itemData)
                ITEM_TYPE_FOLLOW_CARD ->
                    bindFollowCard(position, holder.itemView, itemData, ITEM_TYPE_FOLLOW_CARD)
                ITEM_TYPE_VIDEO_SMALL_CARD ->
                    bindVideoCard(position, holder.itemView, itemData, ITEM_TYPE_VIDEO_SMALL_CARD)
                ITEM_TYPE_AUTO_PLAY_FOLLOW_CARD ->
                    bindAutoPlayCard(
                        position,
                        holder.itemView,
                        itemData,
                        ITEM_TYPE_AUTO_PLAY_FOLLOW_CARD
                    )
                ITEM_TYPE_COMMUNITY_COLUMNS_CARD ->
                    bindCommunityColumnsCard(
                        position,
                        holder.itemView,
                        itemData,
                        ITEM_TYPE_COMMUNITY_COLUMNS_CARD
                    )
                ITEM_TYPE_VIDEO_COLLECTION_OF_HORIZONTAL_SCROLL_CARD ->
                    bindVideoCollectionHorizontalScrollCard(
                        position,
                        holder.itemView,
                        itemData,
                        ITEM_TYPE_VIDEO_COLLECTION_OF_HORIZONTAL_SCROLL_CARD
                    )
                else -> bindTextCard(position, holder.itemView, itemData)

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (datas[position].type) {
            "horizontalScrollCard" ->
                ITEM_TYPE_HORIZONTAL_SCROLL_CARD
            "banner" ->
                ITEM_TYPE_BANNER
            "banner3" ->
                ITEM_TYPE_BANNER3
            "autoPlayVideoAd" ->
                ITEM_TYPE_AUTO_PLAY_AD
            "specialSquareCardCollection" ->
                ITEM_TYPE_SPECIAL_SQUARE_CARD_COLLECTION
            "columnCardList" ->
                ITEM_TYPE_COLUMN_CARD_LIST
            "textCard" ->
                ITEM_TYPE_TEXT_CARD
            "videoSmallCard" ->
                ITEM_TYPE_VIDEO_SMALL_CARD
            "briefCard" ->
                ITEM_TYPE_BRIEF_CARD
            "followCard" ->
                ITEM_TYPE_FOLLOW_CARD
            "informationCard" ->
                ITEM_TYPE_INFORMATION_CARD
            "autoPlayFollowCard" ->
                ITEM_TYPE_AUTO_PLAY_FOLLOW_CARD
            "communityColumnsCard" ->
                ITEM_TYPE_COMMUNITY_COLUMNS_CARD
            "videoCollectionOfHorizontalScrollCard" ->
                ITEM_TYPE_VIDEO_COLLECTION_OF_HORIZONTAL_SCROLL_CARD
            else -> ITEM_TYPE_TEXT_CARD
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    /**
     * 自动播放
     */
    private fun bindAutoPlayAdView(position: Int, itemView: View, data: ItemData) {
        if (itemView is AutoPlayAdView) {
            itemView.setData(data)
        }
    }

    /**
     * 海报
     */
    private fun bindBannerCard(position: Int, itemView: View, data: ItemData) {
        if (itemView is BannerCardView) {
            itemView.layoutParams = layoutParams
            itemView.setData(data)
        }
    }

    /**
     *  Banner
     */
    private fun bindScrollCard(
        position: Int,
        itemView: View,
        cards: ArrayList<Card>?
    ) {
        if (itemView is HorizontalScrollCardView) {
            itemView.layoutParams = layoutParams
            itemView.setData(cards)
        }
    }


    /**
     * BriefCard
     */
    private fun bindBriefCard(position: Int, itemView: View, data: ItemData) {
        if (itemView is BriefCardView) {
            itemView.layoutParams = layoutParams
            itemView.setData(data)
        }
    }

    /**
     * NormalCard
     */
    private fun bindFollowCard(position: Int, itemView: View, data: ItemData, type: Int) {
        if (itemView is FollowCardView) {
            itemView.layoutParams = layoutParams
            itemView.setData(data)
            itemView.setOnClickListener {
                onMultiViewClickListener?.onViewClick(position, itemView, data, type)
            }
            itemView.portrait.setOnClickListener {
                onMultiViewClickListener?.onViewClick(position, it, data, type)
            }
        }
    }

    /**
     * VideoCard
     */
    private fun bindVideoCard(position: Int, itemView: View, data: ItemData, type: Int) {
        if (itemView is VideoCardView) {
            itemView.layoutParams = layoutParams
            itemView.setData(data)
            itemView.setOnClickListener {
                onMultiViewClickListener?.onViewClick(position, itemView, data, type)
            }
        }
    }

    /**
     * TextCard
     */
    private fun bindTextCard(position: Int, itemView: View, data: ItemData) {
        if (itemView is TextCardView) {
            itemView.setData(data)
        }
    }

    /**
     * 广告
     */
    private fun bindAdvertisement(position: Int, itemView: View, data: ItemData) {
        if (itemView is AdvertisementView) {
            itemView.setData(data)
        }
    }


    /**
     *  热门分类
     */
    private fun bindSquareCard(
        position: Int,
        itemView: View,
        data: ItemData
    ) {
        if (itemView is SquareCardView) {
            itemView.setData(data)
        }
    }

    /**
     * 专题策划
     */
    private fun bindColumnCard(
        position: Int,
        itemView: View,
        data: ItemData
    ) {
        if (itemView is ColumnCardView) {
            itemView.setData(data)
        }
    }

    /**
     * 自动播放View
     */
    private fun bindAutoPlayCard(
        position: Int,
        itemView: View,
        data: ItemData,
        type: Int
    ) {
        if (itemView is AutoPlayCardView) {
            itemView.setData(position, data)
            itemView.setOnClickListener {
                onMultiViewClickListener?.onViewClick(position, itemView, data, type)
            }
            itemView.videoPlayer.startButton.setOnClickListener {
                onMultiViewClickListener?.onViewClick(position, it, data, type)
            }
            itemView.portrait.setOnClickListener {
                onMultiViewClickListener?.onViewClick(position, it, data, type)
            }
        }
    }

    /**
     * 瀑布流Card
     */
    private fun bindCommunityColumnsCard(
        position: Int,
        itemView: View,
        data: ItemData,
        type: Int
    ) {
        if (itemView is CommunityColumnsCard) {
            itemView.setData(data)
            itemView.setOnClickListener {
                onMultiViewClickListener?.onViewClick(position, itemView, data, 0)
            }
        }
    }

    private fun bindVideoCollectionHorizontalScrollCard(
        position: Int,
        itemView: View,
        data: ItemData,
        type: Int
    ) {
        if (itemView is VideoCollectionHorizontalScrollCardView) {
            itemView.layoutParams = layoutParams
            itemView.setData(data)
        }
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var onMultiViewClickListener: OnMultiViewClickListener<ItemData>? = null

    fun setOnMultiViewClickListener(onMultiViewClickListener: OnMultiViewClickListener<ItemData>) {
        this.onMultiViewClickListener = onMultiViewClickListener
    }
}