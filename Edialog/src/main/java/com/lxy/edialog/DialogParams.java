package com.lxy.edialog;

import android.view.View;


import java.io.Serializable;

/**
 * Creator : lxy
 * date: 2020/1/3
 */
class DialogParams implements Serializable {

    public int mIconId = 0;

    public int themeResId;

    public CharSequence title;

    public CharSequence message;

    public CharSequence positiveText;

    public CharSequence neutralText;

    public CharSequence negativeText;

    public CharSequence[] items;

    public boolean[] checkedItems;

    public boolean isMultiChoice;

    public boolean isSingChoice;

    public int checkedItem;

    public boolean isCancelable = true;
    public boolean isCancelOutside = true;

    public View.OnClickListener positiveBtnListener;
    public View.OnClickListener negativeBtnListener;
    public View.OnClickListener neutralBtnListener;

    public IDialogInterface.OnClickListener clickListener;
    public IDialogInterface.OnMultiChoiceClickListener multiChoiceClickListener;

    public View mView;
    public int mViewId;
}
