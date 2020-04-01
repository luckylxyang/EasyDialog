package com.lxy.edialog.dialog;

import android.content.DialogInterface;
import android.view.KeyEvent;

/**
 * Creator : lxy
 * date: 2020/1/4
 */
public interface IDialogInterface {

    int BUTTON_NEUTRAL = -3;
    int BUTTON_NEGATIVE = -2;
    int BUTTON_POSITIVE = -1;

    void cancel();

    void dismiss();

    public interface OnShowListener {
        void onShow(IDialogInterface var1);
    }

    public interface OnMultiChoiceClickListener {
        void onClick(int position, boolean choose);
    }

    public interface OnKeyListener {
        boolean onKey(IDialogInterface var1, int var2, KeyEvent var3);
    }

    public interface OnDismissListener {
        void onDismiss(IDialogInterface var1);
    }

    public interface OnClickListener {
        void onClick(int position);
    }

    public interface OnCancelListener {
        void onCancel(IDialogInterface var1);
    }
}
