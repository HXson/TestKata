import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    private static String calc(String input) {
        String[] parts = input.split(" ");
        String result;

        if (parts.length != 3)
            throw new IllegalArgumentException("Неверный формат выражения");

        String first = parts[0];
        String second = parts[2];
        String operator = parts[1];

        int a, b, intResult;

        if (isRoman(first) && isRoman(second)) {
            a = romanToInt(first);
            b = romanToInt(second);
            intResult = calculateIntResult(a, b, operator);
            result = intToRoman(intResult);
        } else {
            a = Integer.parseInt(first);
            b = Integer.parseInt(second);
            if (a < 1 || a > 10 || b < 1 || b > 10)
                throw new IllegalArgumentException("Неверный формат чисел");
            intResult = calculateIntResult(a, b, operator);
            result = String.valueOf(intResult);
        }

        return result;
    }


    private static int calculateIntResult(int a, int b, String operator) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new IllegalArgumentException("Некорректный оператор математической операции");
        };
    }


    private static int romanToInt(String input) {
        for (RomanNumeral romanNumeral : RomanNumeral.values()) {
            if (romanNumeral.name().equals(input)) {
                return romanNumeral.getValue();
            }
        }
        return -1;
    }


    private static String intToRoman(int number) {
        if (number < 1 || number > 3999)
            throw new IllegalArgumentException("Некорректный формат результата");

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        StringBuilder romanNumber = new StringBuilder();

        romanNumber.append(thousands[number / 1000]);
        romanNumber.append(hundreds[(number % 1000) / 100]);
        romanNumber.append(tens[(number % 100) / 10]);
        romanNumber.append(units[number % 10]);

        return romanNumber.toString();
    }


    private static boolean isRoman(String input) {
        for (RomanNumeral romanNumeral : RomanNumeral.values()) {
            if (romanNumeral.name().equals(input)) {
                return true;
            }
        }
        return false;
    }
}

