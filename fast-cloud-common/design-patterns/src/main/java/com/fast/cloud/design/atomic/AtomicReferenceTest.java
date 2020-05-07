package com.fast.cloud.design.atomic;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author qinfuxiang
 * @Date 2020/5/7 17:05
 */
public class AtomicReferenceTest {
    public static AtomicReference<User> atomicUserRef = new
            AtomicReference<User>();

    public static void main(String[] args) {
        User user = new User("conan", 15);
        atomicUserRef.set(user);
        User updateUser = new User("Shinichi", 17);
        atomicUserRef.compareAndSet(user, updateUser);
        System.out.println(atomicUserRef.get().getName());
        System.out.println(atomicUserRef.get().getOld());
        //测试AtomicReferenceFieldUpdater
        AtomicReferenceFieldUpdater<User, String> updater = AtomicReferenceFieldUpdater.newUpdater(User.class, String.class, "name");
        String result = updater.getAndAccumulate(user, "123", (s, s2) -> s2);
        System.out.println(result + "  " + user.getName());

    }

    static class User {
        public volatile String name;
        public int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }
    }
}
/**
 * 原子更新基本类型的AtomicInteger，只能更新一个变量，如果要原子更新多个变量，就需
 * 要使用这个原子更新引用类型提供的类。Atomic包提供了以下3个类。
 * ·AtomicReference：原子更新引用类型。
 * ·AtomicReferenceFieldUpdater：原子更新引用类型里的字段。
 * ·AtomicMarkableReference：原子更新带有标记位的引用类型。可以原子更新一个布尔类
 * 型的标记位和引用类型。构造方法是AtomicMarkableReference（V initialRef，boolean
 * initialMark）。
 */