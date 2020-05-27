package Lesson_3.homework.server;

import java.io.Serializable;

public class Animal implements Serializable {
    public Animal() {
        System.out.println("Animal default constructor");
    }

    public void info() {
        System.out.println("info about animal");
    }
}
