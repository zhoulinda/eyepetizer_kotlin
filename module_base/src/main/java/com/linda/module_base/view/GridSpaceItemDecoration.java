package com.linda.module_base.view;

import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2019/6/6
 */
public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int right;
    private int bottom;
    private int orientation;
    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    public GridSpaceItemDecoration(int right, int bottom, int orientation) {
        this.right = right;
        this.bottom = bottom;
        this.orientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int pos = parent.getChildAdapterPosition(view);
        int spanCount = getSpanCount(parent);
        int column = (pos) % spanCount;// 计算这个child 处于第几列

        if (orientation == VERTICAL) {
            outRect.left = column * right / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = right - (column + 1) * right / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (!isLastRow(parent, pos, spanCount, parent.getAdapter().getItemCount())) {
                outRect.bottom = bottom; // item bottom
            }
        } else {
            outRect.top = column * bottom / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.bottom = bottom - (column + 1) * bottom / spanCount; // spanCount;
            if (!isLastRow(parent, pos, spanCount, parent.getAdapter().getItemCount())) {
                outRect.right = right; // item bottom
            }
        }
    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }

    private boolean isLastRow(RecyclerView parent, int pos, int spanCount,
                              int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int ranger = childCount % spanCount;
            if (ranger == 0) {
                ranger = spanCount;
            }
            childCount = childCount - ranger;
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                return true;
        }
        return false;
    }

    private boolean isLastColumn(RecyclerView parent, int pos, int spanCount,
                                 int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0 || (pos == childCount - 1)) {// 如果是最后一列
                return true;
            }
        }
        return false;
    }
}
