package com.rex.yangtzeu.regex;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式 解析CET查分结果
 * Created by rex on 2014/8/2.
 */
public class CetRegex {
    /**
     * 判断是否错误
     * @param content 返回的html页
     * @return true为错误
     */
    public static boolean get_error(String content){
        String pdl_pregex = "class=\"error alignC";

        Pattern pat1 = Pattern.compile(pdl_pregex);
        Matcher mat1 = pat1.matcher(content);
        return mat1.find();
    }

    public static boolean can_get_score(String content){
        String cgs_mark_pregex = "查询结果";
        Pattern pat1 = Pattern.compile(cgs_mark_pregex);
        Matcher mat1 = pat1.matcher(content);
        return mat1.find();
    }

    public static Map<String,String> get_score(){
        return null;
    }
}
