package db;

import Bean.Person;
import Bean.User;

import java.util.List;

public interface Dao {

    User login(String studentId);
    Person getPerson(String studentId);
    boolean register(User user);
    List<Object> getList(String sql, Object[] params);
    List<Person> getMatchPerson(String sql);
}
