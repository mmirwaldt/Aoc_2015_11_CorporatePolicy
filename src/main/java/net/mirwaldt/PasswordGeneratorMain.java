package net.mirwaldt;

public class PasswordGeneratorMain {
    public static void main(String[] args) {
        final PasswordGenerator passwordGenerator = new DefaultPasswordGenerator();
        System.out.println(passwordGenerator.generateNextPassword("vzbxkghb")); // result - vzbxxyzz
    }
}
