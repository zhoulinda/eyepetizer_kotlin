package com.linda.lib_smartplayer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.linda.lib_smartplayer.R;

/**
 * 描述 :     播放器底部View
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2019-12-12
 */
public class BottomView extends FrameLayout {

    public BottomView(@NonNull Context context) {
        this(context, null);
    }

    public BottomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_bottom, this, true);
        Button button = view.findViewById(R.id.retry);
        SeekBar seekBar = view.findViewById(R.id.seekBar);
        TextView progress = view.findViewById(R.id.progressTime);
        ImageView fullscreen = view.findViewById(R.id.fullscreen);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
