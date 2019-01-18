package com.fast.cloud.design.builder;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-17 14:32
 */
public class User {
    private String name;
    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    //静态内部类 这样可以通过User直接调用 User.UserBuilder可以当成一个新类看
    public static class UserBuilder {

        private String name;
        private Integer age;

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setAge(Integer name) {
            this.age = age;
            return this;
        }

        //通过UserBuilder调用链式方法实例化，返回对应的User对象
        public User build() {
            return new User(name, age);
        }
    }

    public static void main(String[] args) {
        User user = new User.UserBuilder()
                .setName("Evan")
                .setAge(10).build();
    }
}
