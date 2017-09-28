package ren.yale.android.baseutilslib;

import android.text.TextUtils;

import java.util.regex.Pattern;


public class RegularUtils {

    private RegularUtils() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

   
    private static final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";
   
    private static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-8])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$";
   
    private static final String REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}";
   
    private static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
   
    private static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?";
   
    private static final String REGEX_CHZ = "^[\\u4e00-\\u9fa5]+$";
   
    private static final String REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";
   
    private static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";

    //If u want more please visit http://toutiao.com/i6231678548520731137/

   
    public static boolean isMobileSimple(String string) {
        return isMatch(REGEX_MOBILE_SIMPLE, string);
    }

   
    public static boolean isMobileExact(String string) {
        return isMatch(REGEX_MOBILE_EXACT, string);
    }

   
    public static boolean isTel(String string) {
        return isMatch(REGEX_TEL, string);
    }

   
    public static boolean isEmail(String string) {
        return isMatch(REGEX_EMAIL, string);
    }

   
    public static boolean isURL(String string) {
        return isMatch(REGEX_URL, string);
    }

   
    public static boolean isChz(String string) {
        return isMatch(REGEX_CHZ, string);
    }

   
    public static boolean isUsername(String string) {
        return isMatch(REGEX_USERNAME, string);
    }

   
    public static boolean isMatch(String regex, String string) {
        return !TextUtils.isEmpty(string) && Pattern.matches(regex, string);
    }
}
