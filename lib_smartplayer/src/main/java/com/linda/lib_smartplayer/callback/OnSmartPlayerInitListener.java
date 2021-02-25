package com.linda.lib_smartplayer.callback;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * 描述 :     IjkMediaPlayer的播放监听
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2019-12-16
 */
public interface OnSmartPlayerInitListener {

    void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i);

    void onCompletion(IMediaPlayer iMediaPlayer);

    void onError(IMediaPlayer iMediaPlayer, int i, int i1);

    void onInfo(IMediaPlayer iMediaPlayer, int i, int i1);

    void onPrepared(IMediaPlayer iMediaPlayer);

    void onSeekComplete(IMediaPlayer iMediaPlayer);

    void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3);
}
