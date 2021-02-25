package com.linda.module_base.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.linda.module_base.utils.DisplayUtil;

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/2
 */
public class StaggeredGridItemDecoration extends RecyclerView.ItemDecoration {

    private int right;
    private int top;
    private int spanCount;

    /**
     * @param right
     * @param top
     */
    public StaggeredGridItemDecoration(int right, int top, int spanCount) {
        this.right = right;
        this.top = top;
        this.spanCount = spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (view.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            int position = parent.getChildAdapterPosition(view);
            int spanIndex = layoutParams.getSpanIndex();
            RecyclerView.Adapter adapter = parent.getAdapter();
            int itemCount = adapter.getItemCount();
            int childCount = layoutManager.getChildCount();
            if (position < itemCount && spanCount == 2) {
                if (spanIndex != StaggeredGridLayoutManager.LayoutParams.INVALID_SPAN_ID) {
                    if (spanIndex % 2 == 0) {
                        outRect.left = 0;
                        outRect.right = DisplayUtil.Companion.dip2px(right >> 1);
                    } else {
                        outRect.left = DisplayUtil.Companion.dip2px(right >> 1);
                        outRect.right = 0;
                    }
                    if (childCount == 1 || childCount == 2) {
                        outRect.top = 0;
                    } else {
                        outRect.top = DisplayUtil.Companion.dip2px(top >> 1);
                    }
                }
            }
        }
    }
}