package com.fast.cloud.design.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author qinfuxiang
 * @Date 2020/5/7 16:44
 */
public class AtomicIntegerArrayTest {
    static int[] value = new int[]{1, 2};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.compareAndSet(0, 1, 3);
//        ai.getAndSet(0, 3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);
    }
}
/**
 * 需要注意的是，数组value通过构造方法传递进去，然后AtomicIntegerArray会将当前数组
 * 复制一份，所以当AtomicIntegerArray对内部的数组元素进行修改时，不会影响传入的数组。
 *
 * . AtomicIntegerArray：原子更新整型数组里的元素。
 * · AtomicLongArray：原子更新长整型数组里的元素。
 * · AtomicReferenceArray：原子更新引用类型数组里的元素。
 */