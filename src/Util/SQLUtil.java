package Util;

import Bean.Person;

public class SQLUtil {

    public static String InsertPerson(Person person){
        if (person == null){
            return "";
        }else {
            String sql;
            sql = "insert into person values(" + person.getStudentId()+","+
                    person.getName()+","+person.getSex()+","+
                    person.getAge()+","+person.getHeight()+","+
                    person.getWeight()+")";
            return sql;
        }
    }
}
