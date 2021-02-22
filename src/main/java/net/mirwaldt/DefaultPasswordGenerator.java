package net.mirwaldt;

import java.util.Arrays;
import java.util.List;

public class DefaultPasswordGenerator implements PasswordGenerator {
    private final static List<String> FORBIDDEN_LETTERS = Arrays.asList("i", "o", "l");
    private final String LAST_INVALID_PASSWORD = "z".repeat(8);

    @Override
    public String generateNextPassword(String oldPassword) {
        String currentPassword = oldPassword;
        while(!isValidPassword(currentPassword) && !currentPassword.equals(LAST_INVALID_PASSWORD)){
            for (int index = currentPassword.length()-1; 0 <= index; index--) {
                String currentLetter = currentPassword.substring(index, index + 1);
                if(!currentLetter.equals("z")) {
                    String nextLetter = String.valueOf((char)(currentLetter.charAt(0) + 1));
                    if(hasForbiddenLetters(nextLetter)) {
                        nextLetter = String.valueOf((char)(currentLetter.charAt(0) + 2));
                    }
                    currentPassword = currentPassword.substring(0, index)
                            + nextLetter
                            + "a".repeat(oldPassword.length() - index - 1);
                    break;
                }
            }
        }
        if(currentPassword.equals(LAST_INVALID_PASSWORD)) {
            throw new IllegalArgumentException("Cannot find a next password for '" + oldPassword + "'.");
        } else {
            return currentPassword;
        }
    }

    boolean isValidPassword(String password) {
        return !hasForbiddenLetters(password)
                && hasTwoDifferentNonOverlappingPairs(password)
                && hasStraight(password);
    }

    public static boolean hasStraight(String password) {
        for (int i = 0; i < password.length() - 3; i++) {
            final String threeLetters = password.substring(i, i + 3);
            if(isStraight(threeLetters)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isStraight(String threeLetters) {
        return threeLetters.charAt(0) + 1 == threeLetters.charAt(1)
                && threeLetters.charAt(1) + 1 == threeLetters.charAt(2);
    }

    public static boolean hasForbiddenLetters(String password) {
        for (String forbiddenLetter : FORBIDDEN_LETTERS) {
            if(password.contains(forbiddenLetter)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasTwoDifferentNonOverlappingPairs(String password) {
        String firstPair = "";
        for (int index = 0; index < password.length() - 1; index++) {
            final String twoLetters = password.substring(index, index + 2);
            if(twoLetters.charAt(0)==twoLetters.charAt(1)) {
                if(firstPair.isEmpty()) {
                    firstPair = twoLetters;
                } else if(!firstPair.equals(twoLetters)) {
                    return true;
                }
            }
        }
        return false;
    }
}
