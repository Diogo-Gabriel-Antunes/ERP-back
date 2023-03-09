package org.acme.Util.PrimitiveUtil;

public class StringUtil {

    public static boolean stringValida(String str) {
        if ((str == null) || ((str).trim().length() <= 0)) {
            return false;
        } else {
            return true;
        }
    }



}
