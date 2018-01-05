package db;

import Bean.Person;
import Bean.User;

import java.util.ArrayList;
import java.util.List;
public class DaoImpl implements Dao{

        @Override
        public User login(String studentId) {
            DBManager db = DBManager.createInstance();
            User user = db.login(studentId);
            return user;
        }

        @Override
        public boolean register(User user){
            DBManager db = DBManager.createInstance();
            try {
                return db.register(user);
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public Person getPerson(String studentId){
            DBManager db = DBManager.createInstance();
            Person person = db.getPerson(studentId);
            return person;
        }

        @Override
        public List<Object> getList(String sql, Object[] params) {
            DBManager db = DBManager.createInstance();
            return db.query(sql, params);
        }

        @Override
        public List<Person> getMatchPerson(String sql){
            DBManager db = DBManager.createInstance();
            return db.getMatchPerson(sql);
        }

}
