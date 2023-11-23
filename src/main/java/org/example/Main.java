package org.example;

/*
Создай консольное приложение “Калькулятор”. Приложение должно читать из консоли введенные пользователем строки,
числа, арифметические операции проводимые между ними и выводить в консоль результат их выполнения.
Реализуй класс Main с методом public static String calc(String input).
Метод должен принимать строку с арифметическим выражением между двумя числами и возвращать строку с результатом их выполнения.
Ты можешь добавлять свои импорты, классы и методы.
Добавленные классы не должны иметь модификаторы доступа (public или другие)

Требования:
1. Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b.
Данные передаются в одну строку!
Решения, в которых каждое число и арифмитеческая операция передаются с новой строки считаются неверными.

2. Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.

3. Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.
На выходе числа не ограничиваются по величине и могут быть любыми.

4. Калькулятор умеет работать только с целыми числами.

5. Калькулятор умеет работать только с арабскими или римскими цифрами одновременно,
при вводе пользователем строки вроде 3 + II калькулятор должен выбросить исключение и прекратить свою работу.

6. При вводе римских чисел, ответ должен быть выведен римскими цифрами, соответственно,
при вводе арабских - ответ ожидается арабскими.

7. При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает свою работу.
8. При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций,
приложение выбрасывает исключение и завершает свою работу.

9. Результатом операции деления является целое число, остаток отбрасывается.

10. Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль.
Результатом работы калькулятора с римскими числами могут быть только положительные числа,
если результат работы меньше единицы, выбрасывается исключение.
*/

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.parseInt;


public class Main {
    public static boolean isIntParsable(String text){
        try {
            Integer.parseInt(text);
            return true;
        } catch(NumberFormatException ex){
            return false;
        }
    }

    public static String Units(int unit) {
        String Unit = "";
        switch (unit) {
            case 1: Unit = "I";
                break;
            case 2: Unit = "II";
                break;
            case 3: Unit = "III";
                break;
            case 4: Unit = "IV";
                break;
            case 5: Unit = "V";
                break;
            case 6: Unit = "VI";
                break;
            case 7: Unit = "VII";
                break;
            case 8: Unit = "VIII";
                break;
            case 9: Unit = "IX";
                break;
        }
        return Unit;
    }

    public static String Tens(int tens) {
        String Ten = "";
        switch (tens) {
            case 1: Ten = "X";
                break;
            case 2: Ten = "XX";
                break;
            case 3: Ten = "XXX";
                break;
            case 4: Ten = "XL";
                break;
            case 5: Ten ="L";
                break;
            case 6: Ten = "LX";
                break;
            case 7: Ten = "LXX";
                break;
            case 8: Ten = "LXXX";
                break;
            case 9: Ten = "XC";
                break;
            case 10: Ten = "C";
                break;
        }
        return Ten;
    }

    public static String calc(String input) throws IOException {
        String[] str = input.split(" ");
        String[] Rom = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int num1;
        int num2;
        boolean isRom = false;

        if (str.length != 3) {
            throw new IOException("Неверный формат строки");
        }

        if (isIntParsable(str[0]) && isIntParsable(str[2])) {
            num1 = parseInt(str[0]);
            num2 = parseInt(str[2]);
            if ((num1 < 1 || num1 > 10) || (num2 <1 || num2 > 10)){
                throw new IOException("Ошибка. Введите числа от 1 до 10 (от I до X)");
            }
        } else if(!isIntParsable(str[0]) && !isIntParsable(str[2])) {
            if (Arrays.asList(Rom).contains(str[0]) && Arrays.asList(Rom).contains(str[2])){
                num1 = Arrays.asList(Rom).indexOf(str[0]) + 1;
                num2 = Arrays.asList(Rom).indexOf(str[2]) + 1;
                isRom = true;
            } else throw new IOException("Ошибка. Введите числа от 1 до 10 (от I до X)");
        } else throw new IOException("Неверный формат строки. Введите числа от 1 до 10 (от I до X)");

        char op = str[1].charAt(0);
        int res;

        switch (op){
            case '+' :
                res = num1 + num2;
                break;
            case '-' :
                res = num1 - num2;
                break;
            case '*' :
                res = num1 * num2;
                break;
            case '/' :
                res = num1 / num2;
                break;
            default:
                throw new RuntimeException("Неподдерживаемая операция");
        }
        if (isRom){
            if (res <= 0) {
                throw new RuntimeException("В римской системе только положительные числа");
            }

            int tens1 = res/10;
            int tens = (res%100)/10;
            int units = res%10;

            return Tens(tens1)+Tens(tens)+Units(units);
        }
        return Integer.toString(res);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическую операцию:");
        String inputString = scanner.nextLine();
        System.out.println(calc(inputString));
    }
}