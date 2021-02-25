package com.linda.lib_smartplayer.player;

import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 描述 :     IjkPlayerManager
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/23
 */
public class IjkPlayerManager {

    private IjkMediaPlayer mediaPlayer;

    public static IjkPlayerManager getInstance() {
        return IjkPlayerManagerHolder.instance;
    }

    private static class IjkPlayerManagerHolder {
        private static final IjkPlayerManager instance = new IjkPlayerManager();
    }

    public IjkMediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void initVideoPlayer(Context context, Uri uri) {
        mediaPlayer = new IjkMediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(context, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void release() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
