package Lesson_1.FruitBoxHT;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit> {
    private List<T> fruits = new ArrayList<T>();

    public List<T> getFruits() {
        return fruits;
    }

    public void setFruits(List<T> fruits) {
        this.fruits = fruits;
    }

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public float getWeight() {
        if (fruits.get(0) instanceof Apple) return 1.0f * fruits.size();
        else return 1.5f * fruits.size();
    }

    public boolean compare(Box anotherBox) {
        return Math.abs(this.getWeight() - anotherBox.getWeight()) < 0.00001;
    }

    public void intersperse(Box<? super T> anotherBox) {
        anotherBox.fruits.addAll(this.fruits);
        this.fruits.clear();
    }
}
