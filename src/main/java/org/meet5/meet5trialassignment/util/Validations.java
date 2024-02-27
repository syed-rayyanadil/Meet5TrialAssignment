package org.meet5.meet5trialassignment.util;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validations {
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static int calculateAgeFromDateOfBirth(Date dob) {
        LocalDate birthDate = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }

    public static boolean isValidStringLength(String value, int minLength, int maxLength) {
        if (value == null) {
            return false;
        }
        int length = value.length();
        return length >= minLength && length <= maxLength;
    }

    public static boolean isValidIntegerRange(Integer value, Integer min, Integer max) {
        if (value == null) {
            return false;
        }
        return value >= min && value <= max;
    }

    public static boolean isValidFloatRange(Float value, Float min, Float max) {
        if (value == null) {
            return false;
        }
        return value >= min && value <= max;
    }

    public static boolean isValidInteger(String value) {
        if (value == null) {
            return false;
        }
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidFloat(String value) {
        if (value == null) {
            return false;
        }
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static <T> boolean isValidDataType(String dataType, T data) {
        if (dataType == null || data == null) {
            return false;
        }

        switch (dataType.toLowerCase()) {
            case "string":
                return data instanceof String;
            case "integer":
                return data instanceof Integer;
            case "float":
                return data instanceof Float;
            default:
                return false;
        }
    }
    public static <T> boolean isValidDataTypeString(String dataType) {
        String[] VALID_DATA_TYPES = {"string", "integer", "float", "double", "boolean"};

        if (dataType == null || dataType.trim().isEmpty()) {
            return false;
        }
        dataType = dataType.toLowerCase();
        if (!isValidStringLength(dataType, 1, 255)) {
            return false;
        }
        return Arrays.asList(VALID_DATA_TYPES).contains(dataType);
    }

}
