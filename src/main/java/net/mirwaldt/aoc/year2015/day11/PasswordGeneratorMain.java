package net.mirwaldt.aoc.year2015.day11;

public class PasswordGeneratorMain {
    public static void main(String[] args) {
        final PasswordGenerator passwordGenerator = new DefaultPasswordGenerator();
        System.out.println(passwordGenerator.generateNextPassword("vzbxkghb")); // result - vzbxxyzz
        System.out.println(passwordGenerator.generateNextPassword("vzbxxyzz")); // result - vzcaabcc
    }
}
