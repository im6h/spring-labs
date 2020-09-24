package com.vnptit.demo.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionHelper {
  public static boolean checkStatus(String status) {
    return status.trim().toUpperCase().equals("ACTIVE") ||
            status.trim().toUpperCase().equals("DISABLE") ||
            status.trim().toUpperCase().equals("INACTIVE");
  }

  public static boolean checkSpace(String s) {
    return s != null && !s.trim().equals("");
  }

  public static boolean regexString(String s) {
    if (s.equals("")){
      return true;
    }else {
      String regex = "^[a-zA-Z0-9 ]+$";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(s);
      return matcher.matches();
    }
  }


  public static boolean regexIcon(String s) {
    String regex = "^(http://www\\\\.|https://www\\\\.|http:/|https:/|www\\\\.).*|^(/[A-Za-z0-9].*\\?|$)|(/[A-Za-z0-9].*|$)+.(png|gif|img|svg)$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(s);
    System.out.println(matcher.matches());
    return matcher.matches();
  }

  public static boolean regexUrl(String s) {
    String regex = "^(http://www\\\\.|https://www\\\\.|http:/|https:/|www\\\\.).*|^(/[A-Za-z0-9].*\\?|$)|(/[A-Za-z0-9].*|$)";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(s);

    return matcher.matches();
  }


  public static Long convertToLong(String id) throws NumberFormatException {
    Long result = Long.parseLong(id.trim());
    return result;
  }

  public static Integer convertToInt(String id) throws NumberFormatException {
    Integer result = Integer.parseInt(id.trim());
    return result;
  }
}
