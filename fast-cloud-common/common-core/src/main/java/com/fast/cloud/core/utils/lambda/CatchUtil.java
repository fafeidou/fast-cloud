package com.fast.cloud.core.utils.lambda;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-01-11 9:36
 */
public class CatchUtil {
    public static <T, R> R tryDo(T t, Function<T, R> func) {
        try {
            return func.apply(t);
        } catch (Exception e) {
            e.printStackTrace();  // for log
            throw new RuntimeException(e.getCause());
        }
    }

    public static <T> void tryDo(T t, Consumer<T> func) {
        try {
            func.accept(t);
        } catch (Exception e) {
            e.printStackTrace();  // for log
            throw new RuntimeException(e.getCause());
        }
    }
}
