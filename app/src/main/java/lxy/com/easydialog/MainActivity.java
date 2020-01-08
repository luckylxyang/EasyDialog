package lxy.com.easydialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import kale.ui.view.dialog.BaseCustomDialog;
import kale.ui.view.dialog.EasyDialog;
import lxy.com.easydialog.databinding.ActivityMainBinding;
import lxy.com.easydialog.dialog.CustomDialog;
import lxy.com.easydialog.dialog.IOSEasyDialog;
import lxy.com.easydialog.dialog.MaterialEasyDialog;
import lxy.com.easydialog.dialog.ViewHolder;
import lxy.com.easydialog.utils.ToastUtils;
import top.limuyang2.customldialog.IOSMsgDialog;
import top.limuyang2.customldialog.MaterialMsgDialog;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.tv.setOnClickListener(view -> {
            new IOSEasyDialog.Builder(MainActivity.this)
                    .setTitle("title")
//                    .setMessage("message")
                    .setNegativeButton("cancel", view13 -> ToastUtils.showShort(MainActivity.this, "cancel"))
                    .setPositiveButton("sure", view1 -> ToastUtils.showShort(MainActivity.this, "sure"))
                    .show();
        });

        binding.button.setOnClickListener(view -> {
//            MDialog dialog = new MDialog();
//            dialog.show(getSupportFragmentManager(), "MDialog");
            IOSMsgDialog.Companion.init(getSupportFragmentManager())
                    .setTitle("titlewsdfhkdsfjkdshgjidfutgrdfmggbrgtitlewsdfhkdsfjkdshgjidfutgrdfmggbrg")
//                    .setMessage("message")
                    .setNegativeButton("cancel",v->ToastUtils.showShort(MainActivity.this, "cancel"))
                    .show();

        });
        binding.coustom.setOnClickListener(view -> {
            new CustomDialog.Builder(this)
                    .setView(R.layout.custom_dialog)
                    .setViewHandlerListener((dialog, holder) -> {
                        holder.setOnClickListener(R.id.custom_dialog_cancel,view1 -> {
                            ToastUtils.showShort(this, "sure");
                            dialog.dismiss();
                        });
                    })
                    .setHeight(0.2f)
                    .setWidthP(0.2f)
                    .setCancelOutside(false)
                    .show();
        });

        binding.choose.setOnClickListener(v -> {
            new MaterialEasyDialog.Builder(this)
                    .setTitle("MaterialEasyDialog")
                    .setMessage("message")
                    .setSingleChoiceItems(new String[]{"df","2222","3333"},1,i -> {
                        ToastUtils.showShort(this, String.valueOf(i));
                    })
                    .setMultiChoiceItems(new String[]{"df","2222","3333"},new boolean[3],( i, b) -> {
                        ToastUtils.showShort(this,i + "\t" + b);
                    })
                    .setView(R.layout.ios_fragment_dialog)
                    .setNegativeButton("cancel", view13 -> ToastUtils.showShort(MainActivity.this, "cancel"))
                    .setNeutralButton("忽略", view12 -> ToastUtils.showShort(MainActivity.this, "忽略"))
                    .setPositiveButton("sure", view1 -> ToastUtils.showShort(MainActivity.this, "sure"))
                    .show();
        });
    }
}
