package com.linda.module_base.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.linda.lib_common.CommonApplication
import com.linda.lib_common.utils.DisplayUtil
import com.linda.module_base.R
import com.linda.module_base.bean.Card
import com.linda.module_base.bean.ItemData
import com.linda.module_base.bean.VideoItem
import com.linda.module_base.constants.Constants
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.databinding.*
import com.linda.module_base.listener.OnMultiViewClickListener
import com.linda.module_base.view.*
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.view_auto_play_card.view.*
import kotlinx.android.synthetic.main.view_follow_card.view.portrait
import org.jetbrains.anko.layoutInflater

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
                ItemHolder(
                    DataBindingUtil.inflate<ViewScrollCardBinding>(
                        parent.context.layoutInflater,
                        R.layout.view_scroll_card,
                        parent,
                        false
                    ), onMultiViewClickListener, viewType
                )
            ITEM_TYPE_BANNER ->
                ItemHolder(
                    DataBindingUtil.inflate<ViewBannerCardBinding>(
                        parent.context.layoutInflater,
                        R.layout.view_banner_card,
                        parent,
                        false
                    ), onMultiViewClickListener, viewType
                )
            ITEM_TYPE_BANNER3 ->
                ItemHolder(
                    DataBindingUtil.inflate<ViewAdvertisementBinding>(
                        parent.context.layoutInflater,
                        R.layout.view_advertisement,
                        parent,
                        false
                    ), onMultiViewClickListener, viewType
                )
            ITEM_TYPE_AUTO_PLAY_AD ->
                ItemHolder(
                    DataBindingUtil.inflate<ViewAutoPlayAdBinding>(
                        parent.context.layoutInflater,
                        R.layout.view_auto_play_ad,
                        parent,
                        false
                    ), onMultiViewClickListener, viewType
                )
            ITEM_TYPE_SPECIAL_SQUARE_CARD_COLLECTION ->
                ItemHolder(
                    DataBindingUtil.inflate<ViewSpecialSquareBinding>(
                        parent.context.layoutInflater,
                        R.layout.view_special_square,
                        parent,
                        false
                    ), onMultiViewClickListener, viewType
                )
            ITEM_TYPE_COLUMN_CARD_LIST ->
                ItemHolder(
                    DataBindingUtil.inflate<ViewColumnCardBinding>(
                        parent.context.layoutInflater,
                        R.layout.view_column_card,
                        parent,
                        false
                    ), onMultiViewClickListener, viewType
                )
            ITEM_TYPE_TEXT_CARD ->
                ItemHolder(
                    DataBindingUtil.inflate<ViewTextCardBinding>(
                        parent.context.layoutInflater,
                        R.layout.view_text_card,
                        parent,
                        false
                    ), onMultiViewClickListener, viewType
                )
            ITEM_TYPE_VIDEO_SMALL_CARD ->
                ItemHolder(
                    DataBindingUtil.inflate<ViewVideoSmallCardBinding>(
                        parent.context.layoutInflater,
                        R.layout.view_video_small_card,
                        parent,
                        false
                    ), onMultiViewClickListener, viewType
                )
            ITEM_TYPE_BRIEF_CARD ->
                ItemHolder(
                    DataBindingUtil.inflate<ViewBriefCardBinding>(
                        parent.context.layoutInflater,
                        R.layout.view_brief_card,
                        parent,
                        false
                    ), onMultiViewClickListener, viewType
                )
            ITEM_TYPE_FOLLOW_CARD ->
                ItemHolder(
                    DataBindingUtil.inflate<ViewFollowCardBinding>(
                        parent.context.layoutInflater,
                        R.layout.view_follow_card,
                        parent,
                        false
                    ), onMultiViewClickListener, viewType
                )
            ITEM_TYPE_AUTO_PLAY_FOLLOW_CARD ->
                ItemHolder(
                    DataBindingUtil.inflate<ViewAutoPlayCardBinding>(
                        parent.context.layoutInflater,
                        R.layout.view_auto_play_card,
                        parent,
                        false
                    ), onMultiViewClickListener, viewType
                )
            ITEM_TYPE_COMMUNITY_COLUMNS_CARD ->
                ItemHolder(
                    DataBindingUtil.inflate<ViewColumnsCardBinding>(
                        parent.context.layoutInflater,
                        R.layout.view_columns_card,
                        parent,
                        false
                    ), onMultiViewClickListener, viewType
                )
            ITEM_TYPE_VIDEO_COLLECTION_OF_HORIZONTAL_SCROLL_CARD ->
                ItemHolder(
                    DataBindingUtil.inflate<ViewVideoCollectionScrollCardBinding>(
                        parent.context.layoutInflater,
                        R.layout.view_video_collection_scroll_card,
                        parent,
                        false
                    ), onMultiViewClickListener, viewType
                )
            else ->
                ItemHolder(
                    DataBindingUtil.inflate<ViewEmptyBinding>(
                        parent.context.layoutInflater,
                        R.layout.view_empty,
                        parent,
                        false
                    ), onMultiViewClickListener, viewType
                )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemHolder) {
            val itemData = datas[position].data
            holder.bind(itemData, position)
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

    class ItemHolder(
        private val binding: ViewDataBinding,
        private val onMultiViewClickListener: OnMultiViewClickListener<ItemData>?,
        private val type: Int
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ItemData, position: Int) {
            when (binding) {
                is ViewScrollCardBinding -> {
                    binding.banner.run {
                        adapter = object : BannerImageAdapter<Card>(data.itemList) {
                            override fun onBindView(
                                holder: BannerImageHolder,
                                data: Card?,
                                position: Int,
                                size: Int
                            ) {
                                Glide.with(context)
                                    .load(data?.data?.image)
                                    .into(holder.imageView)
                            }
                        }
                        setBannerRound(DisplayUtil.dip2px(3f).toFloat())
                        setOnBannerListener { data, _ ->
                            ARouter.getInstance().build(RouterPaths.WEBVIEW_ACTIVITY)
                                .withString("url", (data as Card).data?.actionUrl)
                                .navigation()
                        }
                        isAutoLoop(false)
                    }
                }
                is ViewBannerCardBinding -> {
                    binding.itemData = data
                }

                is ViewAdvertisementBinding -> {
                    binding.itemData = data
                }

                is ViewAutoPlayAdBinding -> {
                    binding.itemData = data
                }

                is ViewSpecialSquareBinding -> {
                    binding.run {
                        recyclerView.run {
                            layoutManager =
                                GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
                            if (itemDecorationCount > 0) removeItemDecorationAt(0)
                            addItemDecoration(
                                GridSpaceItemDecoration(
                                    DisplayUtil.dip2px(6f),
                                    DisplayUtil.dip2px(6f),
                                    RecyclerView.HORIZONTAL
                                )
                            )
                            adapter =
                                SquareCardAdapter().apply { data.itemList?.let { setData(it) } }
                        }
                        itemData = data
                    }
                }

                is ViewColumnCardBinding -> {
                    binding.run {
                        recyclerView.run {
                            layoutManager = GridLayoutManager(context, 2)
                            addItemDecoration(
                                GridSpaceItemDecoration(
                                    DisplayUtil.dip2px(6f),
                                    DisplayUtil.dip2px(6f),
                                    RecyclerView.VERTICAL
                                )
                            )
                            data.itemList?.let { ColumnCardAdapter().setData(it) }
                        }
                        itemData = data
                    }
                }

                is ViewTextCardBinding -> {
                    binding.itemData = data
                }

                is ViewVideoSmallCardBinding -> {
                    binding.run {
                        itemData = data
                        listener = View.OnClickListener {
                            onMultiViewClickListener?.onViewClick(position, it, data, type)
                        }
                    }
                }

                is ViewBriefCardBinding -> {
                    binding.itemData = data
                }

                is ViewFollowCardBinding -> {
                    binding.run {
                        itemData = data
                        listener = View.OnClickListener {
                            onMultiViewClickListener?.onViewClick(position, it, data, type)
                        }
                    }
                }

                is ViewAutoPlayCardBinding -> {
                    binding.run {
                        videoPlayer.run {
                            val imageView = ImageView(CommonApplication.getContext())
                            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                            Glide.with(CommonApplication.getContext())
                                .load(data.content?.data?.cover?.feed)
                                .into(imageView)
                            thumbImageView = imageView
                            backButton.visibility = View.GONE
                            thumbImageViewLayout.visibility = View.VISIBLE
                            startButton.setOnClickListener {
                                onMultiViewClickListener?.onViewClick(
                                    position,
                                    it,
                                    data,
                                    type
                                )
                            }
                        }
                        itemData = data
                        listener = View.OnClickListener {
                            onMultiViewClickListener?.onViewClick(position, it, data, type)
                        }
                    }
                }

                is ViewColumnsCardBinding -> {
                    binding.run {
                        cover.layoutParams = binding.cover.layoutParams.apply {
                            val originWidth = data.content?.data?.width
                            val originHeight = data.content?.data?.height
                            val realWidth =
                                (DisplayUtil.getScreenWidth(CommonApplication.getContext()) / 2) - DisplayUtil.dip2px(
                                    15f
                                )
                            val realHeight = realWidth * originHeight!! / originWidth!!
                            width = realWidth
                            height = realHeight.toInt()
                        }
                        itemData = data
                        listener = View.OnClickListener {
                            onMultiViewClickListener?.onViewClick(position, it, data, type)
                        }
                    }
                }

                is ViewVideoCollectionScrollCardBinding -> {
                    binding.run {
                        banner.run {
                            adapter = object : VideoCollectionBannerImageAdapter(data.itemList) {
                                override fun onBindView(
                                    holder: ItemBannerHolder?,
                                    data: Card?,
                                    position: Int,
                                    size: Int
                                ) {
                                    data?.let { holder?.itemView?.setData(it) }
                                }
                            }
                            setBannerRound(DisplayUtil.dip2px(3f).toFloat())
                            setOnBannerListener { data, _ ->
                                ARouter.getInstance()
                                    .build(RouterPaths.DETAIL_VIDEO_DETAIL_ACTIVITY)
                                    .withInt(
                                        Constants.VIDEO_ID,
                                        (data as Card).data?.content?.data?.id!!
                                    )
                                    .withString(Constants.RESOURCE_TYPE, data.data?.content?.type)
                                    .navigation()
                            }
                            isAutoLoop(false)
                        }
                        itemData = data
                    }
                }

                is ViewEmptyBinding -> {
                    binding.itemData = data
                }
            }
        }
    }

    private var onMultiViewClickListener: OnMultiViewClickListener<ItemData>? = null

    fun setOnMultiViewClickListener(onMultiViewClickListener: OnMultiViewClickListener<ItemData>) {
        this.onMultiViewClickListener = onMultiViewClickListener
    }
}