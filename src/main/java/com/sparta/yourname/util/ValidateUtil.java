package com.sparta.yourname.util;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

    // username의 정규식 패턴
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-z0-9]{4,10}$");

    // password의 정규식 패턴
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,15}$");

    // username 유효성 검사
    // username 유효성 검사
    public static boolean isValidUsername(String username) {
        if (!USERNAME_PATTERN.matcher(username).matches()) {
            throw new IllegalArgumentException("Invalid username. Username should be between 4 and 10 characters long and consist of lowercase letters and numbers only.");
        }
        return true;
    }

    // password 유효성 검사
    public static boolean isValidPassword(String password) {
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException("Invalid password. Password should be between 8 and 15 characters long and consist of at least one uppercase letter, one lowercase letter, one digit, and one special character.");
        }
        return true;
    }
}