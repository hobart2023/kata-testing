import java.util.Arrays;
import java.util.Scanner;

class Main {
    private final static int MIN_NUMBER = 1;
    private final static int MAX_NUMBER = 10;

    public static String calc(String input) {
        NumberType op1 = null;
        NumberType op2 = null;
        Convertor convertor = new Convertor();
        Computer counter = new Computer();
        int a = 0;
        int b = 0;
        String[] arr = input.split("\\s");
        if (arr.length != 3) {
            throw new IllegalArgumentException("Неверный формат математической операции. Должно быть например: 4 + 2");
        }
        if (Arrays.stream(Operation.values())
                .noneMatch(o -> o.getValue().equals(arr[1]))) {
            throw new IllegalArgumentException("Между операторами недопустимая операция");
        }
        if (convertor.contains(arr[0])) {
            op1 = NumberType.RIM;
        }
        if (convertor.contains(arr[2])) {
            op2 = NumberType.RIM;
        }
        if (op1 != NumberType.RIM || op2 != NumberType.RIM) {
            if (op1 != op2) {
                throw new IllegalArgumentException("аргументы д.б совместимого типа");
            }
            try {
                a = Integer.parseInt(arr[0]);
                op1 = NumberType.ARABIC;
                b = Integer.parseInt(arr[2]);
                if (a > MAX_NUMBER || a < MIN_NUMBER || b > MAX_NUMBER || b < MIN_NUMBER) {
                    throw new IllegalArgumentException("аргументы д.б в диапазоне от 1 до 10");
                }
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("переданные аргументы не являются числами");
            }
        }
        if (op1 == NumberType.ARABIC) {
            return String.valueOf(getResult(counter, a, b, arr));
        } else {
            a = convertor.rimToArabic(arr[0]);
            b = convertor.rimToArabic(arr[2]);
            if (a <= b && Operation.DIFF.getValue().equals(arr[1])) {
                throw new IllegalArgumentException(
                        "Недопустимая операция. Первый аргумент <= второго. " +
                                "При вычитании этих римских чисел результат будет меньше либо равен нулю");
            }
            int res = getResult(counter, a, b, arr);
            return convertor.arabicToRim(res);
        }
    }

    private static int getResult(Computer counter, int a, int b, String[] arr) {
        Operation op = Arrays.stream(Operation.values())
                .filter(o -> o.getValue().equals(arr[1])).findFirst().orElseThrow();
        return counter.compute(op, a, b);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            while (true){
                String input = sc.nextLine();
                if (input.equals("exit")) {
                    break;
                }
                String rsl = calc(input);
                System.out.println(rsl);
            }
        }
    }
}
