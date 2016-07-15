package mr_immortalz.com.stereoview.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Mr_immortalZ on 2016/7/15.
 * email : mr_immortalz@qq.com
 */
public class ToastUtil {
    public static void showInfo(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }
}
