package com.example.gaoll.stickydemo.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.example.gaoll.stickydemo.jiekou.ISticky;

import java.lang.reflect.Type;

public class StickyItemDecoration extends RecyclerView.ItemDecoration {
    private ISticky mISticky;//判断数据的接口

    private int mRectHeight;//矩形高度
    private int mTextPaintSize;//文字

    private Paint mTextPaint;//文字的画笔
    private Paint mRectPaint;//矩形的画笔
    private Paint mDividerPaint;//分割线的画笔

    private int mStatusBarHeight;//手机状态栏的高度



    /**
     * 有参构造：初始化接口实现，状态栏高度，矩形高度，文字大小，画笔
     * @param mISticky
     * @param mStatusBarHeight
     */
    public StickyItemDecoration(Context context, int mStatusBarHeight, ISticky mISticky) {
        this.mISticky = mISticky;
        this.mStatusBarHeight = mStatusBarHeight;
        mRectHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                30, context.getResources().getDisplayMetrics());
        mTextPaintSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                17, context.getResources().getDisplayMetrics());

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(mTextPaintSize);

        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setColor(Color.parseColor("#DDDDDD"));

        mDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDividerPaint.setStyle(Paint.Style.FILL);
        mDividerPaint.setColor(Color.WHITE);
    }

    /**
     * 绘制分割线：
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //获取RecyclerView的child总数，即：数据的类别，不同类别之间绘制Item分割性
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);//item view
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            int top = view.getTop() - 1;
            int bottom = view.getBottom();
            //绘制item分割线
            c.drawRect(left, top, right, bottom, mDividerPaint);
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int childCount=parent.getChildCount();
        int itemCount=state.getItemCount();
        int left=parent.getPaddingLeft();
        int right=parent.getWidth()-parent.getPaddingRight();

        String preGroupTitle;//前一个分组标题
        String groupTitle="";//当前的分组标题
        for (int i = 0; i < childCount; i++) {
            View child=parent.getChildAt(i);
            int pos=parent.getChildLayoutPosition(child);
            preGroupTitle=groupTitle;
            groupTitle=mISticky.getGroupTitle(pos);
            //如果当前分组名和之前分组名一样，忽略此次循环
            if (groupTitle.equals(preGroupTitle)) {
                continue;
            }

            //文字的基线，保证显示完全
            int textBaseLine=Math.max(mRectHeight,child.getTop());

            //分组标题
            String title=mISticky.getGroupTitle(pos);

            int viewBottom=child.getBottom();
            //加入限定 防止数组越界
            if (pos + 1 < itemCount) {
                String nextGroupTitle=mISticky.getGroupTitle(pos+1);
                //当分组不一样  并且改组要向上移动时候
                if (!nextGroupTitle.equals(groupTitle) && viewBottom < textBaseLine) {
                    //将上一个往上移动
                    textBaseLine = viewBottom;
                }
            }
            //绘制边框
            c.drawRect(left, textBaseLine - mRectHeight, right, textBaseLine, mRectPaint);

            //绘制文字并且实现文字居中
            int value= (int) Math.abs(mTextPaint.getFontMetrics().descent
                    +mTextPaint.getFontMetrics().ascent);
            c.drawText(title, left,
                    textBaseLine-(mRectHeight+value-mStatusBarHeight)/2,
                    mTextPaint);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int pos=parent.getChildLayoutPosition(view);
        if (mISticky.isFirstPosition(pos)) {
            outRect.top=mRectHeight;
            outRect.bottom=1;
        }else {
            outRect.bottom=1;
        }
    }
}
