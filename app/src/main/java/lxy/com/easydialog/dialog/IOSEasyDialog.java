package lxy.com.easydialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import lxy.com.easydialog.R;

/**
 * Creator : lxy
 * date: 2020/1/4
 */
public class IOSEasyDialog extends BaseDialog {

    private static final String FTag = "MaterialEasyDialog";
    private TextView mTvTitle;
    private TextView mTvMessage;
    private TextView mTvNegativeText;
    private TextView mTvPositiveText;

    private View mLine;
    private View mLine2;

    public static IOSEasyDialog newInstance(DialogParams p) {

        Bundle args = new Bundle();
        args.putSerializable(KEY_PARAMS, p);
        IOSEasyDialog fragment = new IOSEasyDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.translation)));
        return inflater.inflate(R.layout.ios_fragment_dialog, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        setupView();

    }

    @Override
    public void onResume() {
        super.onResume();
        installContent();
    }

    private void setupView() {
        Dialog dialog = getDialog();
        View decorView = dialog.getWindow().getDecorView();
        mTvTitle = decorView.findViewById(R.id.ios_dialog_title);
        mTvMessage = decorView.findViewById(R.id.ios_dialog_message);
        mTvNegativeText = decorView.findViewById(R.id.ios_dialog_option_negative);
        mTvPositiveText = decorView.findViewById(R.id.ios_dialog_option_positive);
        mLine = decorView.findViewById(R.id.ios_dialog_option_line);
        mLine2 = decorView.findViewById(R.id.ios_dialog_option_line2);

    }

    private void installContent() {
        mTvTitle.setText(params.title);
        mTvMessage.setText(params.message);
        mTvNegativeText.setText(params.negativeText);
        mTvPositiveText.setText(params.positiveText);

        setupOption();
        mTvNegativeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (params.negativeBtnListener != null){
                    params.negativeBtnListener.onClick(view);
                }
                dismiss();
            }
        });
        mTvPositiveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (params.positiveBtnListener != null){
                    params.positiveBtnListener.onClick(view);
                }
                dismiss();
            }
        });
    }

    private void setupOption() {
        if (TextUtils.isEmpty(params.negativeText)) {
            mTvNegativeText.setVisibility(View.GONE);
            mLine2.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(params.positiveText)) {
            mLine2.setVisibility(View.GONE);
            mTvPositiveText.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(params.title)) {
            mTvTitle.setVisibility(View.GONE);
        } else {
            mTvTitle.setVisibility(View.VISIBLE);
        }

    }

    public static class Builder extends BaseDialog.Builder {

        public Builder(@NonNull Context context) {
            super(context);
        }

        public Builder(@NonNull Context context, int themeId) {
            super(context, themeId);
        }

        @Override
        protected IOSEasyDialog createDialog() {
            return IOSEasyDialog.newInstance(params);
        }
    }
}
