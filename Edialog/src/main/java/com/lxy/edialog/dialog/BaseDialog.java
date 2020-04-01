package com.lxy.edialog.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

/**
 * Creator : lxy
 * date: 2020/1/4
 */
public class BaseDialog extends DialogFragment {

    private static final String FTag = "BaseDialog";

    protected static final String KEY_PARAMS = "key_params";
    public static final int BUTTON_POSITIVE = -1;
    public static final int BUTTON_NEGATIVE = -2;
    public static final int BUTTON_NEUTRAL = -3;
    protected DialogParams params;

    static BaseDialog newInstance(DialogParams params) {

        Bundle args = new Bundle();
        args.putSerializable(KEY_PARAMS,params);
        BaseDialog fragment = new BaseDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            params = (DialogParams) bundle.getSerializable(KEY_PARAMS);
        }
    }

    public static abstract class Builder<T extends Builder>{

        protected DialogParams params;
        private Context context;
        private FragmentManager fragmentManager;

        public Builder(@NonNull Context context) {
            if (!(context instanceof Activity)){
                throw new IllegalArgumentException("Context must be Activity");
            }
            this.context = context;
            fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            init();
        }

        public Builder(@NonNull Context context,@StyleRes int themeId) {
            if (!(context instanceof Activity)){
                throw new IllegalArgumentException("Context must be Activity");
            }
            this.context = context;
            fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            init();
            params.themeResId = themeId;
        }

        private void init(){
            params = new DialogParams();
        }

        public T setTitle(CharSequence title) {
            params.title = title;
            return (T) this;
        }

        @NonNull
        public Context getContext() {
            return context;
        }

        public T setMessage(int messageId) {
            params.message = getContext().getString(messageId);
            return (T) this;
        }

        public Builder setMessage(@Nullable CharSequence message) {
            params.message = message;
            return (T) this;
        }

        public T setIcon(int iconId) {
            params.mIconId = iconId;
            return (T) this;
        }

        public Builder setView(View view){
            params.mView = view;
            params.mViewId = 0;
            return this;
        }

        public T setView(int viewId){
            params.mView = null;
            params.mViewId = viewId;
            return (T) this;
        }

        public Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, final IDialogInterface.OnClickListener listener) {
            params.items = items;
            params.clickListener = listener;
            params.checkedItem = checkedItem;
            params.isSingChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(int itemsId, int checkedItem, final IDialogInterface.OnClickListener listener) {
            params.items = context.getResources().getStringArray(itemsId);
            params.clickListener = listener;
            params.checkedItem = checkedItem;
            params.isSingChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, IDialogInterface.OnMultiChoiceClickListener listener) {
            params.items = items;
            params.multiChoiceClickListener = listener;
            params.checkedItems = checkedItems;
            params.isMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(int itemsId, boolean[] checkedItems, IDialogInterface.OnMultiChoiceClickListener listener) {
            params.items = context.getResources().getStringArray(itemsId);
            params.multiChoiceClickListener = listener;
            params.checkedItems = checkedItems;
            params.isMultiChoice = true;
            return this;
        }

        public Builder setPositiveButton(int textId, View.OnClickListener listener) {
            params.positiveText = context.getString(textId);
            params.positiveBtnListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, View.OnClickListener listener) {
            params.positiveText = text;
            params.positiveBtnListener = listener;
            return this;
        }

        public Builder setNegativeButton(int textId, View.OnClickListener listener) {
            params.negativeText = context.getString(textId);
            params.negativeBtnListener = listener;
            return this;
        }

        public Builder setNegativeButton(CharSequence text, View.OnClickListener listener) {
            params.negativeText = text;
            params.negativeBtnListener = listener;
            return this;
        }

        public Builder setNeutralButton(int textId, View.OnClickListener listener) {
            params.neutralText = context.getText(textId);
            params.neutralBtnListener = listener;
            return this;
        }

        public Builder setNeutralButton(CharSequence text, View.OnClickListener listener) {
            params.neutralText = text;
            params.neutralBtnListener = listener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            params.isCancelable = cancelable;
            return this;
        }

        public T setCancelOutside(boolean cancelable) {
            params.isCancelOutside = cancelable;
            return (T) this;
        }

        public Builder setOnCancelListener(IDialogInterface.OnCancelListener onCancelListener) {
            return this;
        }

        public Builder setOnDismissListener(IDialogInterface.OnDismissListener onDismissListener) {
            return this;
        }

        public Builder setOnKeyListener(View.OnKeyListener onKeyListener) {
            return this;
        }

        public BaseDialog build(){
            return this.createDialog();
        }

        public BaseDialog show(){
            BaseDialog dialog = build();
            dialog.show(fragmentManager,FTag);
            return dialog;
        }

        protected abstract BaseDialog createDialog();

    }
}
