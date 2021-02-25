package com.linda.lib_smartplayer.view;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.linda.lib_smartplayer.R;
import com.linda.lib_smartplayer.callback.OnPlayerControlViewCallback;
import com.linda.lib_smartplayer.callback.OnSmartPlayerInitListener;
import com.linda.lib_smartplayer.media.IRenderView;
import com.linda.lib_smartplayer.media.TextureRenderView;
import com.linda.lib_smartplayer.player.SmartPlayerManager;

import org.jetbrains.annotations.Nullable;

import java.net.URI;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 描述 :    播放器View
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2019-12-11
 */
public abstract class SmartVideoView extends FrameLayout implements OnSmartPlayerInitListener, SurfaceHolder.Callback2 {

    private static final String TAG = SmartVideoView.class.getSimpleName();

    protected OnPlayerControlViewCallback onPlayerControlViewCallback;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Uri mUri;
    private int mVideoWidth;
    private int mVideoHeight;
    private IMediaPlayer mediaPlayer;
    private IRenderView mRenderView;
    private int mVideoSarNum;
    private int mVideoSarDen;

    private static final int[] s_allAspectRatio = {
            IRenderView.AR_ASPECT_FIT_PARENT,
            IRenderView.AR_ASPECT_FILL_PARENT,
            IRenderView.AR_ASPECT_WRAP_CONTENT,
            // IRenderView.AR_MATCH_PARENT,
            IRenderView.AR_16_9_FIT_PARENT,
            IRenderView.AR_4_3_FIT_PARENT};

    private int mCurrentAspectRatio = s_allAspectRatio[0];

    public SmartVideoView(@NonNull Context context) {
        this(context, null);
    }

    public SmartVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmartVideoView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(getLayoutResId(), this, true);
        surfaceView = view.findViewById(R.id.surfaceView);
        surfaceView.getHolder().addCallback(this);
        SmartPlayerManager.getInstance().setOnSmartPlayerInitListener(this);
        initRenders();
    }

    private void initRenders() {
        TextureRenderView renderView = new TextureRenderView(getContext());
        if (mediaPlayer != null) {
            renderView.setVideoSize(mediaPlayer.getVideoSarNum(), mediaPlayer.getVideoSarDen());
            renderView.setVideoSampleAspectRatio(mediaPlayer.getVideoSarNum(), mediaPlayer.getVideoSarDen());
            renderView.setAspectRatio(s_allAspectRatio[0]);
        }
        setRenderView(renderView);
    }

    public void setRenderView(IRenderView renderView) {
        if (mRenderView != null) {
            if (mediaPlayer != null) {
                mediaPlayer.setDisplay(null);

                View renderUIView = mRenderView.getView();
//                mRenderView.removeRenderCallback(mSHCallback);
                mRenderView = null;
                removeView(renderUIView);
            }

            if (renderView == null) {
                return;
            }

            mRenderView = renderView;
            renderView.setAspectRatio(mCurrentAspectRatio);
            if (mVideoWidth > 0 && mVideoHeight > 0)
                renderView.setVideoSize(mVideoWidth, mVideoHeight);
            if (mVideoSarNum > 0 && mVideoSarDen > 0)
                renderView.setVideoSampleAspectRatio(mVideoSarNum, mVideoSarDen);

            View renderUIView = mRenderView.getView();
            LayoutParams lp = new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER);
            renderUIView.setLayoutParams(lp);
            addView(renderUIView);

//            mRenderView.addRenderCallback(mSHCallback);
//            mRenderView.setVideoRotation(mVideoRotationDegree);
        }
    }

    protected abstract int getLayoutResId();

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        surfaceHolder = holder;
        if (mediaPlayer != null) {
            mediaPlayer.setDisplay(surfaceHolder);
        } else {
        }
    }

    @Override
    public void surfaceRedrawNeeded(@NonNull SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        SmartPlayerManager.getInstance().getMediaPlayer().setDisplay(null);
        surfaceHolder.getSurface().release();
    }

    /**
     * 设置播放路径
     *
     * @param path
     */
    public void setPath(String path) {
        setUri(Uri.parse(path));
    }

    public void setUri(Uri uri) {
        mUri = uri;
        prepareVideo();
    }

    public void prepareVideo() {
        if (mUri == null || surfaceHolder == null) {
            return;
        }
        release();
        mediaPlayer = SmartPlayerManager.getInstance().getMediaPlayer();

    }


    /**
     * 开始播放
     */
    public void start() {
        if (SmartPlayerManager.getInstance().getMediaPlayer() != null
                && !SmartPlayerManager.getInstance().getMediaPlayer().isPlaying()) {
            SmartPlayerManager.getInstance().getMediaPlayer().start();
        }
    }


    /**
     * 暂停播放
     */
    public void pause() {
        if (SmartPlayerManager.getInstance().getMediaPlayer() != null
                && SmartPlayerManager.getInstance().getMediaPlayer().isPlaying()) {
            SmartPlayerManager.getInstance().getMediaPlayer().pause();
        }
    }

    /**
     * 重新播放
     */
    public void reStart() {
        SmartPlayerManager.getInstance().getMediaPlayer().seekTo(0);
        start();
    }

    /**
     * 释放
     */
    public void release() {
        SmartPlayerManager.getInstance().getMediaPlayer().release();
    }

    public void setPlayerControlViewCallback(OnPlayerControlViewCallback onPlayerControlViewCallback) {
        this.onPlayerControlViewCallback = onPlayerControlViewCallback;
    }
}
