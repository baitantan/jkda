package core.service;

import java.util.Hashtable;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chenshuai
 * @date 2019/8/27 15:36
 * Student.class
 */
public class Student {
    private int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age);
    }


}
