package com.lxy.edialog.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;


/**
 * date: 2020/1/4
 * @author lxy
 */
public class MaterialEasyDialog extends BaseDialog {

    private static final String FTag = "MaterialEasyDialog";
//    private static final String KEY_PARAMS = "key_params";
//    public static final int BUTTON_POSITIVE = -1;
//    public static final int BUTTON_NEGATIVE = -2;
//    public static final int BUTTON_NEUTRAL = -3;
//    private DialogParams params;
//
    static MaterialEasyDialog newInstance(DialogParams params) {

        Bundle args = new Bundle();
        args.putSerializable(KEY_PARAMS,params);
        MaterialEasyDialog fragment = new MaterialEasyDialog();
        fragment.setArguments(args);
        return fragment;
    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Bundle bundle = getArguments();
//        if (bundle != null){
//            params = (DialogParams) bundle.getSerializable(KEY_PARAMS);
//        }
//    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),params.themeResId);
        builder.setTitle(params.title)
                .setIcon(params.mIconId)
                .setMessage(params.message)
                .setCancelable(params.isCancelable)
                .setNegativeButton(params.negativeText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        params.negativeBtnListener.onClick(((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_NEGATIVE));
                    }
                })
                .setNeutralButton(params.neutralText,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        params.neutralBtnListener.onClick(((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_NEUTRAL));
                    }
                })
                .setPositiveButton(params.positiveText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        params.positiveBtnListener.onClick(((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE));
                    }
                });
        if (params.isSingChoice){
            builder.setSingleChoiceItems(params.items, params.checkedItem, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (params.clickListener != null) {
                        params.clickListener.onClick(i);
                    }
                }
            });
        }
        if (params.isMultiChoice){
            builder.setMultiChoiceItems(params.items, params.checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                    if (params.multiChoiceClickListener != null) {
                        params.multiChoiceClickListener.onClick(i,b);
                    }
                }
            });
        }
        builder.setView(params.mView)
                .setView(params.mViewId);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(params.isCancelOutside);
        return dialog;
    }

    public static class Builder extends BaseDialog.Builder{

        public Builder(@NonNull Context context) {
            super(context);
        }

        public Builder(@NonNull Context context, int themeId) {
            super(context, themeId);
        }


        @Override
        protected MaterialEasyDialog createDialog() {
            return MaterialEasyDialog.newInstance(params);
        }
    }


}
