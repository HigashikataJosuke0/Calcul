import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    private static Scanner console = new Scanner(System.in);
    private static int a, b;
    private static Boolean res = false;

    private static String[] data;
    private static HashMap<String, Integer> m = new HashMap<String, Integer>() {{
        put("0",0);
        put("I", 1);
        put("II", 2);
        put("III", 3);
        put("IV", 4);
        put("V", 5);
        put("VI", 6);
        put("VII", 7);
        put("VIII", 8);
        put("IX", 9);
        put("X", 10);
        put("XL", 40);
        put("L", 50);
        put("XC", 90);
        put("C", 100);

    }};

    public static void converter(String str) {

        data = str.split("(?=[-+*/])|(?<=[-+*/])");

        try {
            a = Integer.parseInt(data[0]);
            b = Integer.parseInt(data[2]);
            if (a > 11 || b > 11){
                throw new ArithmeticException("числа не могут быть больше 10");
            }
        } catch (NumberFormatException e) {

            a = m.get(data[0]);
            b = m.get(data[2]);
            res = true;
        }
        // System.out.println(a + " " + b);

    }


    public static void converterResult(int result) {

        StringBuilder romanResult = new StringBuilder(" ");


        int hundreds = result / 100;
        int tens = (result % 100) / 10;
        int ones = result % 10;


        if (tens == 5) {
            romanResult.append("L");
        } else if (tens == 9) {
            romanResult.append("XC");
        } else if (tens == 4) {
            romanResult.append("XL");
        } else if (hundreds == 1) {
            romanResult.append("C");
        } else if (tens == 6) {
            romanResult.append("LX");
        } else if (tens == 7) {
            romanResult.append("LXX");
        } else if (tens == 8) {
            romanResult.append("LXXX");
        } else if (tens > 0 && tens != 5 && tens != 4 && tens != 9) {
            for (int i = 0; i < tens; i++) {
                romanResult.append("X");
            }
        }

        for (Map.Entry<String, Integer> map : m.entrySet()) {
            String key = map.getKey();
            Integer value = map.getValue();
            if (ones == value) {
                romanResult.append(key);
            }else if(ones == -value){
                throw new ArithmeticException("Римские числа не могут быть отрицательными");
            }
        }
        if(romanResult.length() == 0){
            romanResult.append("0");
        }
        System.out.println(romanResult);

    }


    public static void main(String[] args) {
        System.out.println("Введите выражение");
        String str = console.nextLine();


        converter(str);

        int result = 0;

        switch (data[1]) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Деление на ноль");
                }
                result = a / b;
                break;
            default:

        }
        if (res) {
            converterResult(result);

        } else {
            System.out.println(result);
        }


    }
}
