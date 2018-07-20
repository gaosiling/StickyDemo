package com.example.gaoll.stickydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gaoll.stickydemo.adapter.RecyclerAdapter;
import com.example.gaoll.stickydemo.jiekou.ISticky;
import com.example.gaoll.stickydemo.model.Model;
import com.example.gaoll.stickydemo.util.StickyItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 获取状态栏高度——方法
         * */
        int statusBarHeight = 0;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }

        mRecycler = (RecyclerView) findViewById(R.id.rv);

        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        //模拟数据
        final List<Model> modelList=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            modelList.add(new Model("标题1","Content:1_"+i,R.mipmap.ic_launcher));
        }
        for (int i = 0; i < 10; i++) {
            modelList.add(new Model("标题2","Content:2_"+i,R.mipmap.ic_launcher));
        }
        for (int i = 0; i < 10; i++) {
            modelList.add(new Model("标题3","Content:3_"+i,R.mipmap.ic_launcher));
        }
        for (int i = 0; i < 10; i++) {
            modelList.add(new Model("标题4","Content:4_"+i,R.mipmap.ic_launcher));
        }


        mRecycler.addItemDecoration(new StickyItemDecoration(this, 100, new ISticky() {
            @Override
            public boolean isFirstPosition(int pos) {
                return pos==0||
                        !modelList.get(pos).getmTitle().equals(modelList.get(pos-1).getmTitle());
            }

            @Override
            public String getGroupTitle(int pos) {
                return modelList.get(pos).getmTitle();
            }
        }));

        mRecycler.setAdapter(new RecyclerAdapter(this,modelList));

    }
}
