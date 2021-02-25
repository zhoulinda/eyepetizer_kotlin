package com.linda.lib_smartplayer.player;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.SurfaceHolder;

import com.linda.lib_smartplayer.callback.OnSmartPlayerInitListener;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 描述 :    IJKMediaPlayer的管理
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2019-12-14
 */

public class SmartPlayerManager implements IMediaPlayer.OnPreparedListener, IMediaPlayer.OnCompletionListener,
        IMediaPlayer.OnBufferingUpdateListener, IMediaPlayer.OnSeekCompleteListener, IMediaPlayer.OnErrorListener,
        IMediaPlayer.OnVideoSizeChangedListener, IMediaPlayer.OnInfoListener {

    public static final String TAG = SmartPlayerManager.class.getSimpleName();

    private OnSmartPlayerInitListener onSmartPlayerInitListener;

    private IMediaPlayer mediaPlayer;

    public SmartPlayerManager() {

    }

    public static SmartPlayerManager getInstance() {
        return SmartMediaPlayerHolder.instance;
    }

    private static class SmartMediaPlayerHolder {
        private static final SmartPlayerManager instance = new SmartPlayerManager();
    }

    /**
     * 设置MediaPlayerListener
     */
    public void setMediaPlayerListener() {
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setScreenOnWhilePlaying(true);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnInfoListener(this);
        mediaPlayer.setOnVideoSizeChangedListener(this);
        mediaPlayer.prepareAsync();
    }


    /**
     * 重置MediaPlayerListener
     */
    public void resetMediaPlayerListener() {
        mediaPlayer.setOnCompletionListener(null);
        mediaPlayer.setOnBufferingUpdateListener(null);
        mediaPlayer.setScreenOnWhilePlaying(false);
        mediaPlayer.setOnPreparedListener(null);
        mediaPlayer.setOnSeekCompleteListener(null);
        mediaPlayer.setOnErrorListener(null);
        mediaPlayer.setOnInfoListener(null);
        mediaPlayer.setOnVideoSizeChangedListener(null);
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
        Log.e(TAG, "    onBufferingUpdate  +  i:  " + i + "   iMediaPlayer:  " + iMediaPlayer.hashCode());
        if (onSmartPlayerInitListener != null) {
            onSmartPlayerInitListener.onBufferingUpdate(iMediaPlayer, i);
        }
    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        Log.e(TAG, "    onCompletion");
        if (onSmartPlayerInitListener != null) {
            onSmartPlayerInitListener.onCompletion(iMediaPlayer);
        }
    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
        Log.e(TAG, "    onError");
        if (onSmartPlayerInitListener != null) {
            onSmartPlayerInitListener.onError(iMediaPlayer, i, i1);
        }
        return false;
    }

    @Override
    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
        Log.e(TAG, "    onInfo");
        if (onSmartPlayerInitListener != null) {
            onSmartPlayerInitListener.onInfo(iMediaPlayer, i, i1);
        }
        return false;
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        Log.e(TAG, "          onPrepared");
        start();
        if (onSmartPlayerInitListener != null) {
            onSmartPlayerInitListener.onPrepared(iMediaPlayer);
        }
    }

    @Override
    public void onSeekComplete(IMediaPlayer iMediaPlayer) {
        Log.e(TAG, "          onSeekComplete");
        if (onSmartPlayerInitListener != null) {
            onSmartPlayerInitListener.onSeekComplete(iMediaPlayer);
        }
    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {
        Log.e(TAG, "          onVideoSizeChanged");
        if (onSmartPlayerInitListener != null) {
            onSmartPlayerInitListener.onVideoSizeChanged(iMediaPlayer, i, i1, i2, i3);
        }
    }

    /**
     * 获取MediaPlayer实例
     *
     * @return
     */
    public IMediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * 开始播放
     *
     * @param url
     */
    public void startPlay(Context context, String url, SurfaceHolder surfaceHolder) {
        release();

        mediaPlayer = new IjkMediaPlayer();

        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setScreenOnWhilePlaying(true);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnInfoListener(this);
        mediaPlayer.setOnVideoSizeChangedListener(this);

        try {
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setDisplay(surfaceHolder);

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setScreenOnWhilePlaying(true);
        mediaPlayer.prepareAsync();
        start();
    }


    /**
     * 开始播放
     */
    public void start() {
        mediaPlayer.start();
    }


    /**
     * 释放资源
     */
    public void release() {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            resetMediaPlayerListener();
            mediaPlayer = null;
        }
    }

    public void setOnSmartPlayerInitListener(OnSmartPlayerInitListener onSmartPlayerInitListener) {
        this.onSmartPlayerInitListener = onSmartPlayerInitListener;
    }

}
