package com.linda.lib_smartplayer.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;

import com.linda.lib_smartplayer.R;

import org.jetbrains.annotations.Nullable;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * 描述 :     大图浏览里的播放器
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/19
 */
public class SmartSimpleVideoPlayer extends SmartVideoView {

    public SmartSimpleVideoPlayer(@NonNull Context context) {
        this(context, null);
    }

    public SmartSimpleVideoPlayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmartSimpleVideoPlayer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.view_simple_video;
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {

    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public void onError(IMediaPlayer iMediaPlayer, int i, int i1) {

    }

    @Override
    public void onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {

    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public void onSeekComplete(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

    }
}
