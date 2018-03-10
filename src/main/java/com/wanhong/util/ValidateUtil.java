package com.wanhong.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangmeng247
 * @date 2018-03-10 16:55
 */
public class ValidateUtil {

    public static boolean validateParaPhone(String paraPhone){
        String regEx = "^1[234578]\\d{9}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(paraPhone);
        return  matcher.matches();
    }
}
