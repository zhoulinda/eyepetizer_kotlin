package com.linda.lib_smartplayer.callback;

/**
 * 描述 :    播放器 回调
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2019-12-15
 */
public interface OnPlayerControlViewCallback {

    /**
     * 点击了屏幕
     */
    void onClickScreen();

    /**
     * 点击了返回按钮
     */
    void onClickBack();

    /**
     * 点击了全屏按钮
     */
    void onClickFullScreen();

    /**
     * 点击了重新播放
     */
    void onClickReplay();


}
