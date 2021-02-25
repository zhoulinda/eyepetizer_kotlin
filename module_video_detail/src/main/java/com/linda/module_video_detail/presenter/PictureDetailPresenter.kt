package com.linda.module_video_detail.presenter

import com.linda.module_video_detail.contract.PictureDetailContract
import com.linda.module_video_detail.model.PictureDetailModel

/**
 * 描述 :     视频详情Presenter
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/8
 */
class PictureDetailPresenter(private val mView: PictureDetailContract.View) :
    PictureDetailContract.Presenter {

    private val pictureDetailModel: PictureDetailModel by lazy {
        PictureDetailModel()
    }

    override fun getPictureDetail(pictureId: Int, resourceType: String) {
        pictureDetailModel.getPictureDetail(pictureId, resourceType)
            .subscribe({
                mView.onGetPictureDetailSuccess(it)
            }, {
                mView.onGetPictureDetailError()
            })
    }

    override fun onDestoryPresenter() {
    }
}