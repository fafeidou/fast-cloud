package com.fast.cloud.domain.request;

import java.io.*;
import java.util.List;


/**
 * @author Batman.qin
 */
public class CommonSearchRequest<T> implements Serializable {
    private List<String> fields;
    private T condition;
    private Sort sortBy;

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }

    public Sort getSortBy() {
        return sortBy;
    }

    public void setSortBy(Sort sortBy) {
        this.sortBy = sortBy;
    }

    public static class Sort implements Serializable {
        private String field;
        // 1: 正序; -1: 反序; 其他: 正序
        private Integer direction;

        @Override
        public String toString() {
            return "Sort{" +
                    "field='" + field + '\'' +
                    ", direction=" + direction +
                    '}';
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public Integer getDirection() {
            return direction;
        }

        public void setDirection(Integer direction) {
            this.direction = direction;
        }
    }

    public static CommonSearchRequest deepClone(CommonSearchRequest src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(src);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        Object o = ois.readObject();
        ois.close();
        return (CommonSearchRequest) o;
    }
}
