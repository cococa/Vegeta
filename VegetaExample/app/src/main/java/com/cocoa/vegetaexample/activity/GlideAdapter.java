package com.cocoa.vegetaexample.activity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cocoa.vegetaexample.R;


/**
 * Created by cocoa on 2015/9/18.14:22
 * email:385811416@qq.com
 */
public class GlideAdapter extends BaseAdapter {

    private String[] imgUrl;
    private Activity mActivity;
    private LayoutInflater mInflater;

    public GlideAdapter( String[] imgUrl, Activity mActivity) {
        this.imgUrl = imgUrl;
        mInflater = LayoutInflater.from(mActivity);
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {
        return  imgUrl.length;
    }

    @Override
    public String getItem(int position) {
        return imgUrl[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            mInflater =LayoutInflater.from(mActivity);
            convertView = mInflater.inflate(R.layout.item_glide_test, null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.glide_test_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String imgUrl= getItem(position);

        Glide.with(mActivity)
                .load(imgUrl)//设置加载图片
                .centerCrop()
                .placeholder(R.drawable.ic_photo_black_48dp)//设置加载中图片
                .error(R.drawable.ic_photo_black_48dp)//设置加载图片错误时的图片
                .into(viewHolder.img);//设置加载图片的控件

        return convertView;
    }

    static class ViewHolder {
        ImageView img;
    }


}
