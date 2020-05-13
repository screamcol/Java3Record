package Lesson_1.ArrayHT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyArray {
    private Object[] myArray;

    public MyArray(Object[] myArray) {
        this.myArray = myArray;
    }

    public Object[] getMyArray() {
        return myArray;
    }

    public void changeElementsInArrayFromTo(int from, int to) {
        try {
            Object temp = myArray[from];
            myArray[from] = myArray[to];
            myArray[to] = temp;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Неверные индексы элементов, которые надо поменять местами. Элементы должны быть в пределах массива");
        }
    }

    public ArrayList<Object> convertToArrayList() {
        List<Object> aList = Arrays.asList(myArray);
        return new ArrayList<Object>(aList);
    }
}
