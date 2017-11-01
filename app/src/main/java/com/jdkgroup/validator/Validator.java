package com.jdkgroup.validator;

import android.support.v7.widget.AppCompatEditText;
import android.widget.EditText;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.RegexValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Validator {

    public static String patternPassword = "[^\\w\\d]*(([0-9]+.*[A-Za-z]+.*)|[A-Za-z]+.*([0-9]+.*))";
    public static String patternBusinessName = "^[a-zA-Z0-9]+$";
    public static String patternEmail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z]{2,8}" +
            ")+";
    public final static String patternName = "[a-zA-Z]{3,20}$";
    public final static String patternMobile = "^[0-9]{10,10}$";

    public static String getText(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static boolean isEmpty(AppCompatEditText appCompatEditText) {
        return StringUtils.isEmpty(appCompatEditText.getText()) || StringUtils.isBlank(appCompatEditText.getText()) || Strings.isNullOrEmpty(appCompatEditText.getText().toString());
    }

    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str) || StringUtils.isBlank(str) || Strings.isNullOrEmpty(str);
    }

    public static boolean isRegexValidator(AppCompatEditText appCompatEditText, String expression) {
        RegexValidator sensitive = new RegexValidator(expression);
        return sensitive.isValid(appCompatEditText.getText().toString());
    }

    public static boolean isEqual(final String strA, final String strB) {
        boolean check = StringUtils.equals(strA, strA);
        return check;
    }

    public static String rangeMobile(AppCompatEditText appCompatEditText)
    {
        return CharMatcher.inRange('0', '9').retainFrom(appCompatEditText.getText());
    }

}
