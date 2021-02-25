package com.linda.lib_smartplayer.view;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.linda.lib_smartplayer.R;
import com.linda.lib_smartplayer.player.SmartPlayerManager;
import com.linda.lib_smartplayer.utils.DisplayUtils;
import com.linda.lib_smartplayer.utils.StringUtils;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * 描述 :    处理UI，手势
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2019-12-15
 */
public abstract class SmartVideoControlView extends SmartVideoView implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener {

    /**
     * 调整亮度因子
     */
    private static final float BRIGHTNESS_FACTOR = 0.5f;

    /**
     * 调整音量因子
     */
    private static final float AUDIO_FACTOR = 0.2f;

    /**
     * 调整播放进度因子
     */
    private static final float CHANGE_PERCENT_FACTOR = 0.6f;

    protected static final String PROGRESS_FORMAT = "%s / %s";

    //返回键
    private ImageView mBack;
    //标题
    private TextView mVideoTitle;
    //重新播放
    private Button mRePlay;
    //进度条
    private SeekBar mSeekBar;
    //进度text
    private TextView mProgressTime;
    //全屏按钮
    private ImageView mFullscreen;

    private CountDownTimer countDownTimer;
    private GestureDetector mGestureDetector;
    private long currentPosition;
    private float currentBrightness;
    private boolean isChangeProgress;
    private boolean isChangeBrightness;
    private boolean isChangeAudio;

    public SmartVideoControlView(@NonNull Context context) {
        this(context, null);
    }

    public SmartVideoControlView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmartVideoControlView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initGesture(context);
    }

    private void init() {
        mBack = findViewById(R.id.back);
        mVideoTitle = findViewById(R.id.video_title);
        mRePlay = findViewById(R.id.rePlay);
        mSeekBar = findViewById(R.id.seekBar);
        mProgressTime = findViewById(R.id.progressTime);
        mFullscreen = findViewById(R.id.fullscreen);
        mBack.setOnClickListener(this);
        mVideoTitle.setOnClickListener(this);
        mRePlay.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
        mFullscreen.setOnClickListener(this);
    }

    private void initGesture(Context context) {
        mGestureDetector = new GestureDetector(context.getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

            private float downX;
            private float downY;
            private long initPosition;
            private float initBrightness;
            private AudioManager audioManager;
            private float maxVolume;
            private float initVolume;


            @Override
            public boolean onDown(MotionEvent motionEvent) {
                downX = motionEvent.getX();
                downY = motionEvent.getY();
//                Log.e("mGestureDetector", "*******onDown********" + "   x:  " + motionEvent.getX() + "  y:  " + motionEvent.getY());
                return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
//                Log.e("mGestureDetector", "*******onShowPress********");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
//                Log.e("mGestureDetector", "*******onSingleTapUp********");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                float moveX = motionEvent1.getX();
                float moveY = motionEvent1.getY();
                float deltaX = moveX - downX;
                float deltaY = moveY - downY;

                if (Math.abs(deltaX) > Math.abs(deltaY)) {//水平方向
                    if (!isChangeBrightness && !isChangeAudio) {
                        if (!isChangeProgress) {
                            initPosition = SmartPlayerManager.getInstance().getMediaPlayer().getCurrentPosition();
                            isChangeProgress = true;
                        }
                        SmartPlayerManager.getInstance().getMediaPlayer().pause();
                        int screenWidth = getWidth();
                        long duration = SmartPlayerManager.getInstance().getMediaPlayer().getDuration();
                        long offset = (long) (deltaX / screenWidth * duration);
                        currentPosition = initPosition + offset;
                        currentPosition = currentPosition < 0 ? 0 : currentPosition;
                        currentPosition = currentPosition > duration ? duration : currentPosition;
                        showProgressDialog(currentPosition, duration);
                    }
                } else {//竖直方向
                    int halfWidth = getWidth() / 2;
                    int height = getHeight();
                    if (downX <= halfWidth) {//左半屏 调整亮度
                        if (!isChangeProgress && !isChangeAudio) {
                            if (!isChangeBrightness) {
                                initBrightness = getWindowCurrentBrightness() * 255f;
                                isChangeBrightness = true;
                            }
                            float offsetBrightness = -(deltaY / height * 255f);
                            currentBrightness = initBrightness + offsetBrightness;
                            currentBrightness = currentBrightness < 0 ? 0 : currentBrightness;
                            currentBrightness = currentBrightness > 255f ? 255f : currentBrightness;
                            setWindowBrightness(currentBrightness);
                            showBrightnessDialog(getWindowCurrentBrightness());
                        }
                    } else {//右半屏 调整音量
                        if (!isChangeProgress && !isChangeBrightness) {
                            if (!isChangeAudio) {
                                audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
                                maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                                initVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                                isChangeAudio = true;
                            }
                            float offsetVolume = -(deltaY / height * maxVolume);
                            float currentVolume = initVolume + offsetVolume;
                            currentVolume = currentVolume < 0 ? 0 : currentVolume;
                            currentVolume = currentVolume > maxVolume ? maxVolume : currentVolume;
                            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (int) (currentVolume), 0);
                            float percent = currentVolume / maxVolume;
                            showAudioDialog(percent);
                        }
                    }
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
//                Log.e("mGestureDetector", "*******onLongPress********");
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
//                Log.e("mGestureDetector", "*******onFling********");
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent motionEvent) {
//                Log.e("mGestureDetector", "*******onDoubleTap*******");
                if (SmartPlayerManager.getInstance().getMediaPlayer().isPlaying()) {
                    SmartPlayerManager.getInstance().getMediaPlayer().pause();
                } else {
                    SmartPlayerManager.getInstance().getMediaPlayer().start();
                }
                return false;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
//                Log.e("mGestureDetector", "*******onSingleTapConfirmed*******");
                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent motionEvent) {
//                Log.e("mGestureDetector", "*******onDoubleTapEvent*******");
                return false;
            }
        });
    }

    /**
     * 显示进度dialog
     */
    protected abstract void showProgressDialog(long currentPosition, long duration);

    /**
     * 关闭进度dialog
     */
    protected abstract void dismissProgressDialog();

    /**
     * 显示亮度dialog
     *
     * @param currentBrightness
     */
    protected abstract void showBrightnessDialog(float currentBrightness);

    /**
     * 关闭亮度dialog
     */
    protected abstract void dismissBrightnessDialog();

    /**
     * 显示声音dialog
     *
     * @param percent
     */
    protected abstract void showAudioDialog(float percent);

    /**
     * 关闭声音dialog
     */
    protected abstract void dismissAudioDialog();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (DisplayUtils.isPortrait(getContext())) {
            return false;
        }
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            if (isChangeProgress) {
                SmartPlayerManager.getInstance().getMediaPlayer().seekTo(currentPosition);
                SmartPlayerManager.getInstance().getMediaPlayer().start();
                dismissProgressDialog();
                isChangeProgress = false;
            } else if (isChangeBrightness) {
                dismissBrightnessDialog();
                isChangeBrightness = false;
            } else if (isChangeAudio) {
                dismissAudioDialog();
                isChangeAudio = false;
            }
            return true;
        }
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.back) {
            if (onPlayerControlViewCallback != null) {
                onPlayerControlViewCallback.onClickBack();
            }
        } else if (id == R.id.rePlay) {
            reStart();
            startCountDownTimer();
            if (onPlayerControlViewCallback != null) {
                onPlayerControlViewCallback.onClickReplay();
            }
        } else if (id == R.id.fullscreen) {
            DisplayUtils.toggleScreenOrientation((Activity) getContext());
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        int duration = (int) SmartPlayerManager.getInstance().getMediaPlayer().getDuration();
        int currentTime = (progress * duration) / 100;
        mProgressTime.setText(String.format(PROGRESS_FORMAT, StringUtils.stringForTime(currentTime), StringUtils.stringForTime(duration)));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        startCountDownTimer();
        long duration = SmartPlayerManager.getInstance().getMediaPlayer().getDuration();
        long progress = seekBar.getProgress() * duration / 100L;
        SmartPlayerManager.getInstance().getMediaPlayer().seekTo(progress);
        start();
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer mediaPlayer, int percent) {
        if (percent >= 99) {
            percent = 100;
        }
        mSeekBar.setSecondaryProgress(percent);
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        startCountDownTimer();
    }

    @Override
    public void onSeekComplete(IMediaPlayer iMediaPlayer) {
    }

    @Override
    public void onCompletion(IMediaPlayer mediaPlayer) {
        cancelCountDownTimer();
        int duration = (int) mediaPlayer.getDuration();
        mProgressTime.setText(String.format(PROGRESS_FORMAT, StringUtils.stringForTime(duration), StringUtils.stringForTime(duration)));
    }

    /**
     * 获得系统亮度
     */
    private int getSystemBrightness() {
        int systemBrightness = 0;
        try {
            systemBrightness = Settings.System.getInt(getContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return systemBrightness;
    }

    /**
     * 获取当前窗口亮度
     */
    private float getWindowCurrentBrightness() {
        Window window = ((Activity) getContext()).getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        return lp.screenBrightness;
    }

    /**
     * 设置当前窗口亮度
     *
     * @param brightness
     */
    private void setWindowBrightness(float brightness) {
        Window window = ((Activity) getContext()).getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        if (brightness == -1) {
            lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
        } else {
            lp.screenBrightness = (brightness <= 0 ? 1 : brightness) / 255f;
        }
        window.setAttributes(lp);
    }

    /**
     * 设置进度和时间显示
     */
    private void setProgressAndText() {
        int duration = (int) SmartPlayerManager.getInstance().getMediaPlayer().getDuration();
        int position = (int) SmartPlayerManager.getInstance().getMediaPlayer().getCurrentPosition();
        int progress = (position * 100 / (duration == 0 ? 1 : duration));
        mProgressTime.setText(String.format(PROGRESS_FORMAT, StringUtils.stringForTime(position), StringUtils.stringForTime(duration)));
        mSeekBar.setProgress(progress);
    }

    /**
     * 开启计时器
     */
    private void startCountDownTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        countDownTimer = new CountDownTimer(Integer.MAX_VALUE, 300) {
            @Override
            public void onTick(long l) {
                setProgressAndText();
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }

    /**
     * 销毁计时器
     */
    private void cancelCountDownTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancelCountDownTimer();
    }
}
