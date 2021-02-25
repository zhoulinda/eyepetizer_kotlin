package com.linda.lib_smartplayer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.linda.lib_smartplayer.R;

/**
 * 描述 :     播放器状态View
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2019-12-12
 */
public class ErrorStateView extends FrameLayout {

    public ErrorStateView(Context context) {
        this(context, null);
    }

    public ErrorStateView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ErrorStateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_error_state, this, true);
        RelativeLayout errorView = view.findViewById(R.id.errorView);
        RelativeLayout errorInfo = view.findViewById(R.id.errorInfo);
        TextView errorText = view.findViewById(R.id.errorText);
        Button retry = view.findViewById(R.id.retry);
        ImageView play = view.findViewById(R.id.play);
        ProgressBar loading = view.findViewById(R.id.loading);
    }
}
