package com.example.gaoll.stickydemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gaoll.stickydemo.R;
import com.example.gaoll.stickydemo.model.Model;

import java.util.List;

/**
 * RecyclerView的适配器：把数据绑定到对应子布局中的控件上
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Model> mModelList;//数据集合
    private LayoutInflater mInflater;//布局填充器

    /**
     * 有参构造：初始化数据、布局填充器
     * @param context
     * @param mModelList
     */
    public RecyclerAdapter(Context context, List<Model> mModelList) {
        this.mModelList = mModelList;
        this.mInflater = LayoutInflater.from(context);
    }

    /**
     * 创建ViewHolder类: 加载子布局，并将子view给ViewHolder类
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //布局转View
        View view = mInflater.inflate(R.layout.item_1, viewGroup, false);
        return new ViewHolder(view);
    }

    /**
     * 绑定数据： 把adapter构造中的数据给viewHolder中的控件对应设置
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Model model = mModelList.get(i);
        viewHolder.mTextView.setText(model.getmContent());
        viewHolder.mImageView.setImageResource(model.getmRsId());
    }

    /**
     * 子条目的数量： 等于数据的总数
     * @return
     */
    @Override
    public int getItemCount() {
        return mModelList.size();
    }

    /**
     * 找到子布局中的控件，减少find的次数
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        //item布局中的控件
        private ImageView mImageView;
        private TextView mTextView;

        //找到控件
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.img);
            mTextView = itemView.findViewById(R.id.txt);
        }
    }
}
