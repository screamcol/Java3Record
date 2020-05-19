package Lesson_1.FruitBoxHT;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Box<Apple> boxApple = new Box<Apple>();
        Box<Apple> boxApple2 = new Box<Apple>();
        Box<Orange> boxOrange = new Box<Orange>();
        Box<Orange> boxOrange2 = new Box<Orange>();

        Apple[] apples = {new Apple("яблоко1"), new Apple("яблоко2"), new Apple("яблоко3")};
        Apple[] apples1 = {new Apple("яблоко4"), new Apple("яблоко5"), new Apple("яблоко6")};
        Orange[] oranges = {new Orange("апельсин1"), new Orange("апельсин2"), new Orange("апельсин3"), new Orange("апельсин4"), new Orange("апельсин5")};
        Orange[] oranges1 = {new Orange("апельсин1"), new Orange("апельсин2")};
        List<Apple> appleList = new LinkedList<Apple>(Arrays.asList(apples));
        List<Apple> appleList1 = new LinkedList<Apple>(Arrays.asList(apples1));
        List<Orange> orangeList = new LinkedList<Orange>(Arrays.asList(oranges));
        List<Orange> orangeList1 = new LinkedList<Orange>(Arrays.asList(oranges1));

        boxApple.setFruits(appleList);
        boxApple2.setFruits(appleList1);

        boxOrange.setFruits(orangeList);
        boxOrange2.setFruits(orangeList1);

        System.out.println(boxApple.getWeight());
        System.out.println(boxOrange.getWeight());
        System.out.println(boxApple.compare(boxOrange));
        System.out.println(boxApple.compare(boxApple2));
        System.out.println(boxApple.compare(boxOrange2));

        System.out.println(boxOrange.getFruits());
        System.out.println(boxOrange2.getFruits());
        boxOrange.intersperse(boxOrange2);
        System.out.println(boxOrange.getFruits());
        System.out.println(boxOrange2.getFruits());
    }
}
