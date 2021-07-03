package org.hgq.bst;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-07-03 15:00
 **/
public class Student implements Comparable<Student> {
    private int age;
    private String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {
        return this.age - o.age;
    }

    @Override
    public String toString() {
        return this.age + "";
    }
}
