package lxy.com.easydialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import lxy.com.easydialog.R;

/**
 * Creator : lxy
 * date: 2020/1/7
 */
public class CustomDialog extends BaseDialog {

    private ViewHolder holder;
    private OnViewHandleListener handleListener;
    private float widthP, heightP;

    static CustomDialog newInstance(DialogParams params) {

        Bundle args = new Bundle();
        args.putSerializable(KEY_PARAMS,params);
        CustomDialog fragment = new CustomDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.translation)));
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (params.mView == null){
            dialog.setContentView(params.mViewId);
        }else {
            dialog.setContentView(params.mView);
        }
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();

        params.width = (int) (params.width * widthP);
        params.height = (int) (params.height * heightP);
        window.setAttributes(params);
        holder = ViewHolder.get(getContext(), window.getDecorView());
        if (handleListener != null){
            handleListener.onClick(this,holder);
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

    public interface OnViewHandleListener {
        void onClick(CustomDialog dialog, ViewHolder holder);
    }

    public static class Builder extends BaseDialog.Builder<Builder>{
        private OnViewHandleListener listener;
        private float widthP,heightP;
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

        @Override
        protected BaseDialog createDialog() {
            CustomDialog dialog = CustomDialog.newInstance(params);
            dialog.setViewHandlerListener(listener);
            dialog.setWidthP(widthP);
            dialog.setHeightP(heightP);
            return dialog;
        }
    }
}
