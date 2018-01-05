package Util;

import java.util.ArrayList;
import java.util.List;

public class HobbyUtil {

    public static List<String> StringToList(String hobby){
        List<String> hobbyList = new ArrayList<>();
        String[] hobbyArray = hobby.split("&");
//        for (String str : hobbyList) {
//            hobbyList.add(str);
//            System.out.println("hobby : " +str );
//        }
        for (int i=0;i<hobbyArray.length;i++){
            hobbyList.add(hobbyArray[i]);
            System.out.println("hobby : " +hobbyList.get(i) );
        }
        return hobbyList;
    }

    public static String ListToString(List<String> hobbyList){
        if (hobbyList == null){
            return "";
        }
        String hobby = "";
        for (String str : hobbyList) {
            hobby += str + "&";
        }
        if (hobby.length()>1) {
            hobby = hobby.substring(0, hobby.length() - 1);
        }
        return hobby;
    }

}
