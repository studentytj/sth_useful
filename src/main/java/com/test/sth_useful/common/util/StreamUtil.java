package com.test.sth_useful.common.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * 流处理工具类
 *
 * @author ytj
 * @date 2018/06/05
 */
public class StreamUtil {
    public static void close(Closeable... stream) {
        for (Closeable c : stream) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
