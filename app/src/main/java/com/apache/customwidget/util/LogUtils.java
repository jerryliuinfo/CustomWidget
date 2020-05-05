package com.apache.customwidget.util;

import java.util.logging.Logger;

/**
 * Created by Jerry on 2020-05-05.
 */
public class LogUtils {
    public static final String TAG = LogUtils.class.getSimpleName();
    /**
     * 将日志写入到文件中 d
     *
     * @param format 格式化日志
     * @param args   格式化日志参数
     */
    public static void d(String format, Object... args) {
        Logger.getLogger(TAG).info(buildWholeMessage(format,args));
    }

    private static String buildWholeMessage(String format, Object...args)
    {
        if (args == null || args.length == 0)
            return format;

        String msg = String.format(format, args);
        return msg;
    }
}
