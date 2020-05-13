package Lesson_1.lambda;

public class Calculator  {
    Operationable op;
    public double calculateSumm(double x, double y) {
        op = (firstNumber, secondNumber) -> firstNumber + secondNumber;
        return op.calculate(x, y);
    }

    public double calculateDifference(double x, double y) {
        op = (firstNumber, secondNumber) -> firstNumber - secondNumber;
        return op.calculate(x, y);
    }

    public double calculateMultiply(double x, double y) {
        op = (firstNumber, secondNumber) -> firstNumber * secondNumber;
        return op.calculate(x, y);
    }

    public double calculateDivision(double x, double y) {
        op = (firstNumber, secondNumber) -> firstNumber / secondNumber;
        return op.calculate(x, y);
    }
}
