package net.mirwaldt;

public interface PasswordGenerator {
    String generateNextPassword(String oldPassword);
}
