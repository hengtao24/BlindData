package db;

import Bean.MatchPerson;
import Bean.Person;
import Bean.User;
import Util.HobbyUtil;
import Util.SQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *  单例模式构建DBManager对象;
 *  定义数据库连接、关闭以及增删改查的基本操作，返回结果集;
 */
public class DBManager{

	// 数据库连接常量
	public static final String Driver = "com.mysql.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/blinddata?characterEncoding=utf8&useSSL=true";
	public static final String USER = "root";
	public static final String PASS = "shtt960421?";
	
	// 静态成员，支持单列模式
	private static DBManager per = null;
	private Connection mConnection = null;
	private Statement mStatement = null;
    private PreparedStatement mPreStatement = null;

    // 单列模式-懒汉模式
	private DBManager(){		
	}
	
	public static DBManager createInstance(){
		if(per == null){
			per = new DBManager();
			per.initDB();
		}
		return per;
	}
	
	// 加载驱动
	public void initDB(){
		try{
			Class.forName(Driver);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// 连接数据库，获取句柄+对象
	public void connectDB(){
		System.out.println("Connecting to database...");
		try{
			mConnection = DriverManager.getConnection(url,USER,PASS);
			mStatement = mConnection.createStatement();
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println("SqlManager:Connect to database successful");
	}
	
	// 关闭数据库,关闭对象，释放句柄
	public void closeDB(){
		System.out.println("Close connection to database..");
		try{
			mStatement.close();
			mConnection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println("Close connection successful");
	}
	
	// 查询
	public ResultSet executeQuery(String sql){
		ResultSet rSet = null;
		try{
			rSet = mStatement.executeQuery(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rSet;
	}
	
	// 增添/删除/修改
	public int executeUpdate(String sql){
		int ret = 0;
		try{
			ret = mStatement.executeUpdate(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return ret;
	}

    public ResultSet getRs(String sql) {

        ResultSet rSet = null;
        try{
            rSet = mStatement.executeQuery(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rSet;
    }

    //获取结果集
    public ResultSet getRs(String sql,Object[] params){
        ResultSet rSet = null;
        try {
            mPreStatement = mConnection.prepareStatement(sql);
            if(params!=null){
                for(int i = 0;i<params.length;i++){
                    mPreStatement.setObject(i+1, params[i]);
                }
            }
            rSet = mPreStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rSet;
    }

    public List<Object> query(String sql, Object[] params){
        connectDB();
        List<Object> list = new ArrayList<>();
        ResultSet rs = null;
        if(params!=null){
            rs = getRs(sql, params);
        }else{
            rs = getRs(sql);
        }

        ResultSetMetaData rsmd = null;
        int columnCount = 0;

        try {
            rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
            while(rs.next()){
                Map<String, Object> map = new HashMap<>();
                for(int i = 1;i <= columnCount;i++){
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            System.out.println("结果集解析出错："+e.getMessage());
        } finally {
            closeDB();
        }
        return list;
    }

	public User login(String studentId){

		connectDB();
		ResultSet resultSet;
		User user = null;

		try {
			String sql = "select * from user where StudentId=?";
			mPreStatement = mConnection.prepareStatement(sql);
			mPreStatement.setString(1, studentId);
			resultSet = mPreStatement.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setStudentId(resultSet.getString("StudentId"));
				user.setPassword(resultSet.getString("PassWord"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return user;
	}

	public boolean register(User user){

	    connectDB();
        ResultSet resultSet;

        try {
			String sql = "select * from user where StudentId=?";
            mPreStatement = mConnection.prepareStatement(sql);
            mPreStatement.setString(1, user.getStudentId());
            resultSet = mPreStatement.executeQuery();
			int C =0 ;
			while (resultSet.next()){
				C++;
			}
            if (C > 0){
                return false;
            }else {
                String insertSql = "insert into user values(" + user.getStudentId() + "," + user.getPassword() + ")";
                executeUpdate(insertSql);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return false;
    }

	public Person getPerson(String studentId){

		connectDB();
		ResultSet resultSet;
		Person person = null;

		try {
			String sql = "select * from person where StudentId=?";
			mPreStatement = mConnection.prepareStatement(sql);
			mPreStatement.setString(1, studentId);
			resultSet = mPreStatement.executeQuery();
			while (resultSet.next()) {
				person = new Person();
				person.setStudentId(resultSet.getString("StudentId"));
				person.setName(resultSet.getString("Name"));
				person.setAge(resultSet.getInt("Age"));
				person.setSex(resultSet.getString("Sex"));
				person.setHeight(resultSet.getDouble("Height"));
				person.setWeight(resultSet.getDouble("Weight"));
				person.setHobby(HobbyUtil.StringToList(resultSet.getString("HObby")));
				person.setDepartment(resultSet.getString("Department"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return person;
	}

	public List<Person> getMatchPerson(String sql){
		connectDB();
		List<Person> persons = new ArrayList<>();
		ResultSet resultSet = null;
		resultSet = getRs(sql);

		try {
			while (resultSet.next()){
				Person person = new Person();
				person.setStudentId(resultSet.getString("StudentId"));
				person.setName(resultSet.getString("Name"));
				person.setAge(resultSet.getInt("Age"));
				person.setSex(resultSet.getString("Sex"));
				person.setHeight(resultSet.getDouble("Height"));
				person.setWeight(resultSet.getDouble("Weight"));
				person.setHobby(HobbyUtil.StringToList(resultSet.getString("Hobby")));
				person.setDepartment(resultSet.getString("Department"));
				persons.add(person);
			}
		} catch (SQLException e) {
			System.out.println("结果集解析出错："+e.getMessage());
		} finally {
			closeDB();
		}
		return persons;
	}
}