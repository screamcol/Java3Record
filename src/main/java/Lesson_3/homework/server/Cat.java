package Lesson_3.homework.server;

import java.io.Serializable;

public class Cat extends Animal implements Serializable {
    public String getName() {
        return name;
    }

    private String name;
    private int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void info() {
        System.out.println("info about Cat");
    }

    public void personalInfo() {
        System.out.println("Far from home");
    }
}
