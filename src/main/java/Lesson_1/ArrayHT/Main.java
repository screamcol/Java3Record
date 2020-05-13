package Lesson_1.ArrayHT;

public class Main {

    public static void main(String s[]) {
        Object[] array = {1, 2, "Man", 3};
        MyArray myArray = new MyArray(array);
        myArray.changeElementsInArrayFromTo(2, 3);
        for (Object arr : myArray.getMyArray()) {
            System.out.println(arr);
        }
        System.out.println(myArray.convertToArrayList().getClass());
    }

    public static class Elem1 {
    }

    public static class Elem2 {
    }

    public static class Elem3 {
    }
}
