package com.abacus.utils;

import android.text.TextUtils;

public class UtilityMethods {

    public static boolean isEmpty(String text){

        if(TextUtils.isEmpty(text)){
            return true;
        }
        return false;

    }
}
