package ru.greenatom.atomcore.organization_attribute_groups.steps;

import java.util.Scanner;

public class CalcTest {
    public static void main(String[] args) {
        scanAndCalculate();
    }

    /* сканирование происходит поэтапно: сначала юзер вводит 1-е число, затем 2-е и последним этапом вводит желаемую операцию
    из задания было не совсем понятно, обязательно ли принимать данные у пользователя только в один заход или нет,
    поэтому я реализовала вариант с несколькими этапами
    */
    public static void scanAndCalculate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Простой калькулятор. Введите сначала первое число, потом второе, а затем операцию в формате (+, -, *, /)");
        while (true) {
            System.out.println("Для выхода из программы напишите exit, для продолжения работы программы - любой символ");
            String input = sc.next();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            int number1 = getNumber(sc);
            int number2 = getNumber(sc);
            char scanOperationType = scanOperationType(sc);
            calculateOperation(number1, number2, scanOperationType);
            System.out.println("Хотите выйти?");
        }
        System.out.println("Выход из программы");
        sc.close();
    }

    private static int getNumber(Scanner sc) {
        System.out.println("Введите число: ");
        if (sc.hasNextInt()) {
            int number = sc.nextInt();
            System.out.println("Введенное число: " + number);
            return number;
        }
        else throw new IllegalArgumentException("Вы ввели не число, попробуйте снова");
    }

    public static char scanOperationType(Scanner sc) {
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

    public static void calculateOperation(int number1, int number2, char operation) {
        switch (operation) {
            case '+' -> {
                int sum = sum(number1, number2);
                System.out.println("Результат сложения = " + sum);
            }
            case '-' -> {
                int sub = subtract(number1, number2);
                System.out.println("Результат вычитания = " + sub);

            }
            case '*' -> {
                int multi = multiply(number1, number2);
                System.out.println("Результат умножения = " + multi);
            }
            case '/' -> {
                int div = divide(number1, number2);
                System.out.println("Результат деления = " + div);
            }
        }
    }

    public static int sum(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int divide(int a, int b) {
        if (b != 0) {
            return a / b;
        } else throw new IllegalArgumentException("На ноль делить нельзя!");
    }
}

