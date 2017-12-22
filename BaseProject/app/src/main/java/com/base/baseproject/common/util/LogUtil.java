package com.base.baseproject.common.util;

import android.content.Context;
import android.util.Log;

/**
 * Created by WangHao
 *
 * @ 创建时间 2017/10/13  11:16
 */

public class LogUtil {
    private static String  TAG   = "logger";
    private static boolean DEBUG = true;

    private LogUtil() {
    }

    public static void logger(String msg) {
        if (DEBUG) {
            Log.w(TAG, msg);
        }
    }

    public static void setLogState(Context context) {
        DEBUG = PropertyUtil.isLogAvailable(context);
    }
}
