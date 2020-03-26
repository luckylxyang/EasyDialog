package lxy.com.easydialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.ViewGroup;

import lxy.com.easydialog.databinding.ActivityMainBinding;
import lxy.com.easydialog.dialog.BaseCustomDialog;
import lxy.com.easydialog.dialog.BottomDialog;
import lxy.com.easydialog.dialog.CustomDialog;
import lxy.com.easydialog.dialog.IOSEasyDialog;
import lxy.com.easydialog.dialog.MaterialEasyDialog;
import lxy.com.easydialog.utils.ToastUtils;

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
            new MCustomDialog()
                    .show(getSupportFragmentManager());

        });
        binding.coustom.setOnClickListener(view -> {
            new CustomDialog.Builder(this)
                    .setBottomDialog(true)
                    .setView(R.layout.custom_dialog)
                    .setViewHandlerListener((dialog, holder) -> {
                        holder.setOnClickListener(R.id.custom_dialog_cancel,view1 -> {
                            ToastUtils.showShort(this, "sure");
                            dialog.dismiss();
                        });
                    })
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
