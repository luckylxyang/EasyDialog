package com.lxy.edialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.lxy.edialog.R;

import static android.content.ContentValues.TAG;


/**
 * Creator : lxy
 * date: 2020/1/7
 */
public class CustomDialog extends BaseDialog {

    private ViewHolder holder;
    private OnViewHandleListener handleListener;
    private float widthP = 1, heightP = 1;
    private boolean isBottomDialog;

    /**
     * 背景是否透明
     */
    private boolean isTransparent = true;

    static CustomDialog newInstance(DialogParams params) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_PARAMS,params);
        CustomDialog fragment = new CustomDialog();
        fragment.setArguments(args);
        return fragment;
    }

    private CustomDialog(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (!isBottomDialog) {
            return super.onCreateDialog(savedInstanceState);
        }else {
            return new BottomSheetDialog(getContext(),getTheme());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (isBottomDialog) {
            BottomSheetDialog sheetDialog = (BottomSheetDialog) dialog;
            if (params.mView == null) {
                sheetDialog.setContentView(params.mViewId);
            } else {
                sheetDialog.setContentView(params.mView);
            }

        }else {
            if (params.mView == null) {
                dialog.setContentView(params.mViewId);
            } else {
                dialog.setContentView(params.mView);
            }
        }
        Window window = dialog.getWindow();
        dialog.setCanceledOnTouchOutside(params.isCancelOutside);
        WindowManager.LayoutParams params = window.getAttributes();
        DisplayMetrics outMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        Log.d(TAG, "width = " + width + ",height = " + height);
        params.width = (int) (width * widthP);
        params.height = (int) (height * heightP);
        window.setAttributes(params);
        holder = ViewHolder.get(getContext(), window.getDecorView());
        if (handleListener != null){
            handleListener.onClick(this,holder);
        }

        setupViews();
    }

    private void setupViews() {
        if (isTransparent) {
            if (isBottomDialog) {
                getDialog().getWindow().findViewById(R.id.design_bottom_sheet)
                        .setBackgroundResource(android.R.color.transparent);
            }
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.translation)));
        }
    }


    protected void setViewHandlerListener(OnViewHandleListener listener){
        this.handleListener = listener;
    }

    private void setWidthP(float widthP){
        this.widthP = widthP;
    }

    private void setHeightP(float heightP){
        this.heightP = heightP;
    }

    public void setBottomDialog(boolean bottomDialog) {
        isBottomDialog = bottomDialog;
    }

    public void setBackgroundTransparent(boolean transparent){
        this.isTransparent = transparent;
    }

    public interface OnViewHandleListener {
        /**
         * dialog 的点击处理事件
         * @param dialog
         * @param holder
         */
        void onClick(CustomDialog dialog, ViewHolder holder);
    }

    public static class Builder extends BaseDialog.Builder<Builder>{
        private OnViewHandleListener listener;
        private float widthP = 1, heightP = 1;
        private boolean isBottomDialog;
        private boolean isTransparent = true;
        public Builder(@NonNull Context context) {
            super(context);
        }

        public Builder setViewHandlerListener(OnViewHandleListener listener){
            this.listener = listener;
            return this;
        }

        public Builder setWidthP(float widthP){
            this.widthP = widthP;
            return this;
        }

        public Builder setHeight(float widthP){
            this.heightP = widthP;
            return this;
        }

        public Builder setBottomDialog(boolean bottomDialog) {
            isBottomDialog = bottomDialog;
            return this;
        }

        public Builder setBackgroundTransparent(boolean transparent){
            this.isTransparent = transparent;
            return this;
        }

        @Override
        protected BaseDialog createDialog() {
            CustomDialog dialog = CustomDialog.newInstance(params);
            dialog.setViewHandlerListener(listener);
            dialog.setWidthP(widthP);
            dialog.setHeightP(heightP);
            dialog.setBottomDialog(isBottomDialog);
            dialog.setBackgroundTransparent(isTransparent);
            return dialog;
        }
    }
}
