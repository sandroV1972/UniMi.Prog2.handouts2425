package it.unimi.di.prog2.e18;

public class SignumExample {
    public static void main(String[] args) {
        int result1 = (int)Math.signum(5 - 3); // 2 -> 1.0
        double result2 = Math.signum(3 - 5); // -2 -> -1.0
        double result3 = Math.signum(5 - 5); // 0 -> 0.0

        System.out.println(result1); // Output: 1.0
        System.out.println(result2); // Output: -1.0
        System.out.println(result3); // Output: 0.0
    }
}