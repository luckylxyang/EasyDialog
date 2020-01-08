package lxy.com.easydialog.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Creator : lxy
 * date: 2020/1/4
 */
public class ToastUtils {

    public static void showShort(Context context,CharSequence message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
