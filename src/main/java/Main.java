import java.util.Scanner;

public class Main {
    static String[] romanNumerals = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII",
            "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV",
            "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI",
            "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI",
            "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Введите выражение для расчетов (q - для завершения работы): ");
            String input = in.nextLine();
            if (input.equals("q")) {
                break;
            } else {
                System.out.println("Результат: " + calc(input));
            }
        }
    }

    public static String calc(String input) {
        String[] strings = input.split(" ");
        String operation;

        if ((strings.length >= 2) && ((strings[1].equals("+")) || (strings[1].equals("-")) || (strings[1].equals("*")) || (strings[1].equals("/")))) {
            operation = strings[1];
        } else {
            operation = null;
        }

        if ((strings.length < 3) || (operation == null)) {
            throw new RuntimeException("//т.к. строка не является математической операцией");
        }

        if (strings.length > 3) {
            throw new RuntimeException("//т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        int operandFirst = 0;
        int operandSecond = 0;

        if (arabic(strings[0]) && arabic(strings[2])) {
            operandFirst = Integer.parseInt(strings[0]);
            operandSecond = Integer.parseInt(strings[2]);
            if (!((operandFirst > 0) && (operandFirst <= 10) && (operandSecond > 0) && (operandSecond <= 10))) {
                throw new RuntimeException("//т.к. арабские числа не удовлетворяют условию задания: от 1 до 10");
            }
        } else if (roman(strings[0]) && roman(strings[2])) {
            for (int i = 0; i < 10; i++) {
                if (strings[0].equals(romanNumerals[i])) {
                    operandFirst = i + 1;
                }
                if (strings[2].equals(romanNumerals[i])) {
                    operandSecond = i + 1;
                }
            }
            if ((operandFirst == 0) || (operandSecond == 0)) {
                throw new RuntimeException("//т.к. римские числа не удовлетворяют условию задания: от I до X");
            }
        } else if ((roman(strings[0]) && arabic(strings[2])) || (arabic(strings[0]) && roman(strings[2]))) {
            throw new RuntimeException("//т.к. используются одновременно разные системы счисления");
        } else {
            throw new RuntimeException("//т.к. введена не поддерживаемая система счисления, разрешено использовать арабские целые числа от 1 до 10, и римские от I до X");
        }

        String result = null;
        int res;
        switch (operation) {
            case "+" -> res = operandFirst + operandSecond;
            case "-" -> res = operandFirst - operandSecond;
            case "*" -> res = operandFirst * operandSecond;
            case "/" -> res = operandFirst / operandSecond;
            default -> throw new IllegalStateException("Не верно указана операция: " + operation);
        }
        if (arabic(strings[0]) & arabic(strings[2])) {
            result = String.valueOf(res);
        } else if (roman(strings[0]) & roman(strings[2])) {
            if (res < 1) {
                throw new RuntimeException("//т.к. результатом работы калькулятора с римскими числами могут быть только положительные числа");
            }
            result = romanNumerals[res - 1];
        }
        return result;
    }

    public static boolean arabic(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean roman(String str) {
        for (String s : romanNumerals) {
            if (str.equals(s)) {
                return true;
            }
        }
        return false;
    }
}



