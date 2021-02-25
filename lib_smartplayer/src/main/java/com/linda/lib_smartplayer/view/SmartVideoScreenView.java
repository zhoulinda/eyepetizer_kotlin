package com.linda.lib_smartplayer.view;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.linda.lib_smartplayer.utils.DisplayUtils;

/**
 * 描述 :    控制屏幕全屏、旋转等
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2019-12-15
 */
public abstract class SmartVideoScreenView extends SmartVideoControlView {

    public SmartVideoScreenView(@NonNull Context context) {
        this(context, null);
    }

    public SmartVideoScreenView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmartVideoScreenView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) getLayoutParams();
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            params.width = LinearLayout.LayoutParams.MATCH_PARENT;
            params.height = DisplayUtils.dp2px(getContext(), 200);
        } else {
            params.width = LinearLayout.LayoutParams.MATCH_PARENT;
            params.height = LinearLayout.LayoutParams.MATCH_PARENT;
        }
        setLayoutParams(params);
    }
}
