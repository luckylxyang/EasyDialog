package com.lxy.edialog.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Spannable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;


/**
 * Created by 刘晓阳 on 2018/2/1.
 */

public class ViewHolder{

    private SparseArray<View> views;
    private Context mContext;
    public View convertView;

    private ViewHolder(Context context, View itemView) {
        this.mContext = context;
        this.convertView = itemView;
        views = new SparseArray<>();
    }

    public static ViewHolder get(Context context, View view){

        ViewHolder holder = new ViewHolder(context,view);
        return holder;
    }

    public <T extends View> T getView(int viewId){
        View view = views.get(viewId);
        if (view == null){
            view = convertView.findViewById(viewId);
            views.append(viewId,view);
        }

        return (T) view;
    }

    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public ViewHolder setText(int viewId, String message){
        TextView tv = getView(viewId);
        tv.setText(message);
        return this;
    }

    public ViewHolder setText(int viewId, Spannable message){
        TextView tv = getView(viewId);
        tv.setText(message);
        return this;
    }

    public ViewHolder setText(int viewId, CharSequence message){
        TextView tv = getView(viewId);
        tv.setText(message);
        return this;
    }

    @SuppressLint("ResourceType")
    public ViewHolder setImageResource(int viewId, @DrawableRes int imageId){
        ImageView iv = getView(viewId);
        iv.setImageResource(imageId);
        return this;
    }


}
