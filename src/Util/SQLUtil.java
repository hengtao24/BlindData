package Util;

import Bean.MatchPerson;
import Bean.Person;

public class SQLUtil {

    public static String InsertPerson(Person person){
        if (person == null){
            return "";
        }else {
            String sql;
            sql = "insert into person (StudentId,Name,Sex,Age,Height,Weight,Hobby,Department) " +
                    "values ('" + person.getStudentId()+"','"+
                    person.getName()+"','"+person.getSex()+"',"+
                    person.getAge()+","+person.getHeight()+","+
                    person.getWeight()+",'"+HobbyUtil.ListToString(person.getHobby())+"','" +
                    person.getDepartment()+"')";
            return sql;
        }
    }

    public static String MatchPerson(MatchPerson matchPerson){
        String sql = "";
        if (matchPerson == null){
            return "";
        }else {
            sql = "select * from person where Age between " + matchPerson.getMinage() + " and " + matchPerson.getMaxage() +
                    " and Height between " + matchPerson.getMinheight() + " and " + matchPerson.getMaxheight() +
                    " and Weight between " + matchPerson.getMinweight() + " and " + matchPerson.getMaxweight() +
                    " and Department=" + "'" + matchPerson.getDepartment() + "'" + " and Hobby=" + "'" +
                    matchPerson.getHobby().get(0) + "'" + " and Sex=" +"'" + matchPerson.getSex() + "'";
            return sql;
        }
    }
}
