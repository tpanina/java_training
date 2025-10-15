package ru.stqa.pft.sandbox;

import java.util.Scanner;

public class CalculatorTest {

    public static void main(String[] args) {
        CalculatorTest calculator = new CalculatorTest();
        calculator.calculateOperation();
    }

    public void calculateOperation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Простой калькулятор. Введите сначала первое число, потом второе, а затем операцию в формате (+, -, *, /)");
        while (true) {
            System.out.println("Для выхода из программы напишите exit, для продолжения работы программы - любой символ");
            String input = sc.next();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                int number1 = getNumber(sc);
                int number2 = getNumber(sc);
                char operationType = scanOperationType(sc);
                Operation operation = getOperation(number1, number2, operationType);
                int result = operation.calculate();
                System.out.println("Результат = " + result);
            } catch (Exception e) {
                System.out.println("Произошла непредвиденная ошибка: " + e.getMessage());
            }
            System.out.println("Хотите продолжить?");
        }
        System.out.println("Выход из программы");
        sc.close();
    }

    public Operation getOperation(int number1, int number2, char operationType) {
        return switch (operationType) {
            case '+' -> new Sum(number1, number2);
            case '-' -> new Subtract(number1, number2);
            case '*' -> new Multiply(number1, number2);
            case '/' -> new Divide(number1, number2);
            default -> throw new IllegalArgumentException("Неверная операция!");
        };
    }

    public abstract class Operation {
        protected int number1, number2;

        protected Operation(int number1, int number2) {
            this.number1 = number1;
            this.number2 = number2;
        }

        public abstract int calculate();
    }

    public class Sum extends Operation {
        protected Sum(int number1, int number2) {
            super(number1, number2);
        }

        @Override
        public int calculate() {
            return number1 + number2;
        }
    }

    public class Subtract extends Operation {
        protected Subtract(int number1, int number2) {
            super(number1, number2);
        }

        @Override
        public int calculate() {
            return number1 - number2;
        }
    }

    public class Multiply extends Operation {
        protected Multiply(int number1, int number2) {
            super(number1, number2);
        }

        @Override
        public int calculate() {
            return number1 * number2;
        }
    }

    public class Divide extends Operation {
        protected Divide(int number1, int number2) {
            super(number1, number2);
        }

        @Override
        public int calculate() {
            if (number2 == 0) {
                throw new IllegalArgumentException("На ноль делить нельзя!");
            }
            return number1 / number2;
        }
    }

    private int getNumber(Scanner sc) {
        System.out.println("Введите число: ");
        if (sc.hasNextInt()) {
            int number = sc.nextInt();
            System.out.println("Введенное число: " + number);
            return number;
        } else throw new IllegalArgumentException("Вы ввели не число, попробуйте снова");
    }

    public char scanOperationType(Scanner sc) {
        System.out.println("Введите желаемую операцию в формате (+, -, *, /): ");
        String operationString = sc.next();
        if (!operationString.isEmpty()) {
            System.out.println("Введенная операция: " + operationString);
            char operationSign = operationString.charAt(0);
            switch (operationSign) {
                case '+' -> System.out.println("Выбрана операция сложения");
                case '-' -> System.out.println("Выбрана операция вычитания");
                case '*' -> System.out.println("Выбрана операция умножения");
                case '/' -> System.out.println("Выбрана операция деления");
                default -> System.out.println("Неизвестная операция, попробуйте снова");
            }
            return operationSign;
        } else throw new IllegalArgumentException("Вы не ввели операцию для выполнения");
    }
}