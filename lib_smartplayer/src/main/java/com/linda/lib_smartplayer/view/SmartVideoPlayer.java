package com.linda.lib_smartplayer.view;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.linda.lib_smartplayer.R;
import com.linda.lib_smartplayer.utils.StringUtils;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * 描述 :     处理UI的显示、隐藏
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2019-12-15
 */
public class SmartVideoPlayer extends SmartVideoScreenView {

    //播放进度dialog
    private Dialog progressDialog;
    //进度值
    private TextView progressValue;
    //亮度dialog
    private Dialog brightDialog;
    //亮度值
    private TextView brightness;
    //音量dialog
    private Dialog audioDialog;
    //音量值
    private TextView audioValue;

    public SmartVideoPlayer(@NonNull Context context) {
        this(context, null);
    }

    public SmartVideoPlayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmartVideoPlayer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.view_video;
    }

    @Override
    protected void showProgressDialog(long currentPosition, long duration) {
        if (progressDialog == null) {
            View audioView = View.inflate(getContext(), R.layout.dialog_progress, null);
            progressValue = audioView.findViewById(R.id.progressValue);
            progressDialog = new Dialog(getContext(), R.style.Dialog);
            progressDialog.setContentView(audioView);
            Window window = progressDialog.getWindow();
            window.setGravity(Gravity.CENTER);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        }
        progressDialog.show();
        progressValue.setText(String.format(PROGRESS_FORMAT, StringUtils.stringForTime((int) currentPosition), StringUtils.stringForTime((int) duration)));
    }

    @Override
    protected void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    protected void showAudioDialog(float percent) {
        if (audioDialog == null) {
            View audioView = View.inflate(getContext(), R.layout.dialog_audio, null);
            audioValue = audioView.findViewById(R.id.audioValue);
            audioDialog = new Dialog(getContext(), R.style.Dialog);
            audioDialog.setContentView(audioView);
            Window window = audioDialog.getWindow();
            window.setGravity(Gravity.CENTER);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        }
        audioDialog.show();
        audioValue.setText((int) (percent * 100) + "%");
    }

    @Override
    protected void dismissAudioDialog() {
        if (audioDialog != null) {
            audioDialog.dismiss();
            audioDialog = null;
        }
    }

    @Override
    protected void showBrightnessDialog(float currentBrightness) {
        if (brightDialog == null) {
            View brightView = View.inflate(getContext(), R.layout.dialog_brightness, null);
            brightness = brightView.findViewById(R.id.brightness);
            brightDialog = new Dialog(getContext(), R.style.Dialog);
            brightDialog.setContentView(brightView);
            Window window = brightDialog.getWindow();
            window.setGravity(Gravity.CENTER);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        }
        brightDialog.show();
        brightness.setText((int) (currentBrightness * 100) + "%");
    }

    @Override
    protected void dismissBrightnessDialog() {
        if (brightDialog != null) {
            brightDialog.dismiss();
            brightDialog = null;
        }
    }

    @Override
    public void onError(IMediaPlayer iMediaPlayer, int i, int i1) {

    }

    @Override
    public void onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {

    }

    @Override
    public void onSeekComplete(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

    }
}
