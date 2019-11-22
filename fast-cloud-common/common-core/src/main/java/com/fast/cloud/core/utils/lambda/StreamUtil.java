package com.fast.cloud.core.utils.lambda;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;

/**
 * @author Batman.qin
 * @create 2019-01-11 9:36
 */
public class StreamUtil {
    /**
     * 集合转换成List
     */
    public static <T, R> List<R> mapToList(Collection<T> data, Function<T, R> mapFunc) {
        if (!CollectionUtils.isEmpty(data)) {
            return data.stream().map(mapFunc).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * 将集合转换成Set
     */
    public static <T, R> Set<R> mapToSet(Collection<T> data, Function<T, R> mapFunc) {
        if (!CollectionUtils.isEmpty(data)) {
            return data.stream().map(mapFunc).collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    /**
     * join
     */
    public static <T> String mapToJoin(Collection<T> data, Function<T, String> function, String delimiter) {
        if (!CollectionUtils.isEmpty(data)) {
            return data.stream().map(function).collect(joining(delimiter));
        }
        return StringUtils.EMPTY;
    }

    /**
     * 集合转换LinkedHashMap
     */
    public static <DATA, KEY, VALUE> LinkedHashMap<KEY, VALUE> collectToLinkedHashMap(Collection<DATA> data,
                                                                                      Function<DATA, KEY> key,
                                                                                      Function<DATA, VALUE> value) {
        if (!CollectionUtils.isEmpty(data)) {
            return data.stream().collect(toMap(key, value, (t1, t2) -> t1, LinkedHashMap::new));
        }
        return null;
    }

    /**
     * 集合转换LinkedHashMap
     */
    public static <DATA, KEY> LinkedHashMap<KEY, DATA> collectToLinkedHashMap(Collection<DATA> data,
                                                                              Function<DATA, KEY> key) {
        if (!CollectionUtils.isEmpty(data)) {
            return data.stream().collect(toMap(key, Function.identity(), (t1, t2) -> t1, LinkedHashMap::new));
        }
        return null;
    }

    /**
     * 集合转换Map
     */
    public static <DATA, KEY> Map<KEY, DATA> collectToMap(Collection<DATA> data,
                                                          Function<DATA, KEY> key) {
        if (!CollectionUtils.isEmpty(data)) {
            return data.stream().collect(toMap(key, Function.identity(), (t1, t2) -> t1));
        }
        return null;
    }

    /**
     * 集合转换Map
     */
    public static <DATA, KEY, VALUE> Map<KEY, VALUE> collectToMap(Collection<DATA> data,
                                                                  Function<DATA, KEY> key,
                                                                  Function<DATA, VALUE> value) {
        if (!CollectionUtils.isEmpty(data)) {
            return data.stream().collect(toMap(key, value, (t1, t2) -> t1));
        }
        return Collections.EMPTY_MAP;
    }

    /**
     * 求和
     */
    public static <DATA> Long counting(Collection<DATA> data) {
        if (!CollectionUtils.isEmpty(data)) {
            return data.stream().collect(Collectors.counting());
        }
        return null;
    }

    /**
     * map 中的value转换成List
     */
    public static <K, V> List<V> convertMapValuesToList(Map<K, V> map) {
        List<V> result = new ArrayList<>();
        map.values().stream().forEach(result::add);
        return result;
    }

    /**
     * map 中的key 转换成List
     */
    public static <K, V> List<K> convertMapKeysToList(Map<K, V> map) {
        List<K> result = new ArrayList<>();
        map.keySet().stream().forEach(result::add);
        return result;
    }

    private StreamUtil() { /** prevent instantiation */}

    /**
     * Convert an {@link Optional} to a {@link Stream}
     *
     * @param optional the optional to convert to a stream
     * @param <T>      the type of the element
     * @return a stream containing the optional's value or empty stream if not present
     */
    public static <T> Stream<T> asStream(Optional<T> optional) {
        return optional.map(Stream::of).orElse(Stream.empty());
    }

    /**
     * Convert an {@link Iterator} to a {@link Stream}.
     *
     * @param iterator the iterator to convert to a stream
     * @param <T>      the type of a single element
     * @return a stream containing the values of the iterator
     */
    public static <T> Stream<T> asStream(Iterator<T> iterator) {
        return asStream(iterator, false);
    }

    /**
     * Convert an {@link Iterator} to a {@link Stream}.
     *
     * @param iterator the iterator to convert to a stream
     * @param <T>      the type of a single element
     * @param parallel if true then the returned stream is a parallel stream; if false the returned stream is a sequential stream.
     * @return a stream containing the values of the iterator
     */
    public static <T> Stream<T> asStream(Iterator<T> iterator, boolean parallel) {
        return asStream(() -> iterator, parallel);
    }

    /**
     * Convert an {@link Iterable} to a {@link Stream}
     *
     * @param iterable the iterable to convert
     * @param <T>      the type of a single element
     * @return a (non-parallel) stream containing the values of the iterable
     */
    public static <T> Stream<T> asStream(Iterable<T> iterable) {
        return asStream(iterable, false);
    }

    /**
     * Convert an {@link Iterable} to a {@link Stream}
     *
     * @param iterable the iterable to convert
     * @param parallel if true then the returned stream is a parallel stream; if false the returned stream is a sequential stream.
     * @param <T>      the type of a single element
     * @return a stream containing the values of the iterable
     */
    public static <T> Stream<T> asStream(Iterable<T> iterable, boolean parallel) {
        return Optional.ofNullable(iterable)
                .map(it -> StreamSupport.stream(it.spliterator(), parallel))
                .orElse(Stream.empty());
    }

    /**
     * Concatenate the given streams to a single stream. Follows the semantics
     * of {@link Stream#concat(Stream, Stream)}.
     *
     * @param streams the streams to concatenate
     * @param <T>     type of the stream element
     * @return a stream containing all of the elements in all of the streams
     */
    @SafeVarargs
    public static <T> Stream<T> concat(Stream<T>... streams) {
        return Stream.of(streams)
                .filter(Objects::nonNull)
                .reduce(Stream::concat).get();
    }

    public static void main(String[] args) {
        Map<String, Object> h1 = new HashMap<>();
        h1.put("12", "fdsa");
        h1.put("123", "fdsa");
        h1.put("124", "fdsa");
        h1.put("125", "fdsa");

        Map<String, Object> h2 = new HashMap<>();
        h2.put("h12", "fdsa");
        h2.put("h123", "fdsa");
        h2.put("h124", "fdsa");
        h2.put("h125", "fdsa");

        Map<String, Object> h3 = new HashMap<>();
        h3.put("h12", "fdsa");
        h3.put("h3123", "fdsa");
        h3.put("h3124", "fdsa");
        h3.put("h3125", "fdsa");

        List<Map<String, Object>> lists = new ArrayList<>();
        lists.add(h1);
        lists.add(h2);
        lists.add(h3);
        mergeListMapToOneMap(lists);
    }

    public static Map<String, Object> mergeListMapToOneMap(List<Map<String, Object>> lists) {
        if (!CollectionUtils.isEmpty(lists)) {
            return lists.stream()
                    .map(Map::entrySet)
                    .flatMap(Set::stream)
                    .distinct()
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        return Maps.newHashMap();
    }

    public static Set<String> mergeListMapToOneMapKey(List<Map<String, Object>> lists) {
        return mergeListMapToOneMap(lists).keySet();
    }

    public static Collection<Object> mergeListMapToOneMapValue(List<Map<String, Object>> lists) {
        return mergeListMapToOneMap(lists).values();
    }

    /**
     * 求和
     *
     * @param field  传入字段
     * @param result 待计算集合
     * @param <T>    总和
     * @return
     */
    private <T> Long summarySum(Function<T, Long> field, Supplier<List<T>> result) {
        return result.get().stream().map(model -> field.apply(model)).reduce(Long::sum).orElse(0l);
    }

    /**
     * 求平均值
     *
     * @param field  传入字段
     * @param result 待计算集合
     * @param <T>    平均值
     * @return
     */
    private <T> Double summaryRate(Function<T, Double> field, Supplier<List<T>> result) {
        return formatDouble(result.get().stream().mapToDouble(model -> field.apply(model)).average().orElse(0.0));
    }

    /**
     * 四舍五入格式化两位小数
     *
     * @param num 需要格式化的数
     * @return
     */
    private double formatDouble(double num) {
        return BigDecimal.valueOf(num)
                .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
