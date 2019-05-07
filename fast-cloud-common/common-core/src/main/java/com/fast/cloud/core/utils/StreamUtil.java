package com.fast.cloud.core.utils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-01-11 9:36
 */
public class StreamUtil {
    public static <T, R> List<R> map(List<T> data, Function<T, R> mapFunc) {
        // stream replace foreach
        return data.stream().map(mapFunc).collect(Collectors.toList());
    }
}
