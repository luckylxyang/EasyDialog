package lxy.com.easydialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import lxy.com.easydialog.utils.ToastUtils;

/**
 * Creator : lxy
 * date: 2020/1/1
 */
public class MDialog extends DialogFragment {
    private static final String TAG = "MDialog";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.i(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_dialog,container,false);
//        return mView;
        return null;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Log.i(TAG,"onCreateDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.fragment_dialog);
        builder.setTitle("title")
                .setMessage("message")
                .setCancelable(false)
                .setSingleChoiceItems(new String[]{"df","2222","3333"},1,(dialogInterface, i) -> {
                    ToastUtils.showShort(getContext(),String.valueOf(i));
                })
                .setMultiChoiceItems(new String[]{"df","2222","3333"},new boolean[3],(dialogInterface, i, b) -> {
                    ToastUtils.showShort(getContext(),i + "\t" + b);
                })
                .setPositiveButton("确定",null)
                .setNegativeButton("取消",null);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        Log.i(TAG,"dismiss");
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.i(TAG,"onDismiss");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG,"onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG,"onDetach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG,"onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
//        final View mView = getView();
//        Window window = getDialog().getWindow();
//        View decorView = window.getDecorView();
//        final EditText et = decorView.findViewById(R.id.edittext);
//        et.post(new Runnable() {
//            @Override
//            public void run() {
//                et.setFocusable(true);
//                et.setFocusableInTouchMode(true);
//                et.requestFocus();
//                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (imm != null){
//                    imm.showSoftInput(et,InputMethodManager.SHOW_IMPLICIT);
//                }
//            }
//        });
//
//        Button button = ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View mView) {
//                Toast.makeText(getContext(),"toast",Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG,"onDestroyView");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }
}
