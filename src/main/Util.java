/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author HP Omen
 */
public class Util {
    public static List<String> matchAllRegex(String s, String reg){
        List<String> allMatches = new ArrayList<>();
        Matcher m = Pattern.compile(reg).matcher(s);
        while(m.find()){
            allMatches.add(m.group());
        }
        return allMatches;
    }
}
