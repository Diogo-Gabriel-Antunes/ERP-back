package org.acme.Util.PrimitiveUtil;

public class StringUtil {

    public static boolean stringValida(String str) {
        if ((str == null) || ((str).trim().length() <= 0)) {
            return false;
        } else {
            return true;
        }
    }


    public static boolean validaStringAposValidadeSubClass(String newSubClass) {
        if(newSubClass.length() == 2 && newSubClass.contains("{}") && newSubClass.equals("{}")){
            return true;
        }else{
            return false;
        }
    }
}
