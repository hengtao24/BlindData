package db;

import Bean.Person;
import Bean.User;

import java.util.List;

import static db.DBManager.createInstance;

public class DaoImpl implements Dao{

        @Override
        public User login(String studentId) {
            DBManager db = createInstance();
            User user = db.login(studentId);
            return user;
        }

        @Override
        public boolean register(User user){
            DBManager db = createInstance();
            String sql = "select * from user where StudentId=" +user.getStudentId();
            String insertSql = "insert into user values(" + user.getStudentId() + "," + user.getPassword() +")";
            if (db.getRs(sql) == null){
                return false;
            }else {
                try{
                    db.executeQuery(insertSql);
                    return true;
                }catch(Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        }

        @Override
        public Person getPerson(String studentId){
            DBManager db = createInstance();
            Person person = db.getPerson(studentId);
            return person;
        }

        @Override
        public List<Object> getList(String sql, Object[] params) {
            DBManager db = createInstance();
            return db.query(sql, params);
        }

}
