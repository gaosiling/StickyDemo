package com.example.gaoll.stickydemo.jiekou;

/**
 * 判断数据
 */
public interface ISticky {
    //是否是同类型数据中的第一条
    boolean isFirstPosition(int pos);
    //获取分组数据的标题
    String getGroupTitle(int pos);
}
