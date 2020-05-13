package Lesson_1.lambda;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calculator = new Calculator();
        while (true) {
            System.out.println("Введите 2 числа и операцию над ними через пробел (+, -, *, /)");
            String[] str = sc.nextLine().split(" ");
            double firstNumber = Double.parseDouble(str[0]);
            double secondNumber = Double.parseDouble(str[1]);
            String operation = str[2];
            double result;

            if (operation.equals("+")) result = calculator.calculateSumm(firstNumber, secondNumber);
            else if (operation.equals("-")) result = calculator.calculateDifference(firstNumber, secondNumber);
            else if (operation.equals("*")) result = calculator.calculateMultiply(firstNumber, secondNumber);
            else if (operation.equals("/")) result = calculator.calculateDivision(firstNumber, secondNumber);
            else break;

            System.out.println(result);
        }
    }
}
