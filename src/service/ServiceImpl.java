package service;
/*
 *  ͨ通过调用本类方法完成登陆/注册的服务;
 */

import db.DBManager;
import db.Dao;
import db.DaoImpl;

import java.util.List;

public class ServiceImpl implements Service{

	Dao dao = new DaoImpl();
	DBManager db = DBManager.createInstance();

	@Override
	public List<Object> getList() {
		String sql = "select * from user";
		return dao.getList(sql, null);
	}

	@Override
	public int insert(Object[] params) {
		String sql ="insert into test(name) values(?)";
//		return dao.update(sql, params);
		return 1;
	}

	@Override
	public int modify(Object[] params) {
		String sql ="update test set name = ? where id = ?";
//		return dao.update(sql, params);
		return 1;
	}

	@Override
	public int delete(Object[] params) {
		String sql ="delete from test where id = ?";
//		return dao.update(sql, params);
		return 1;
	}

	@Override
	public int insert(String sql){
		try{
			db.connectDB();
			return db.executeUpdate(sql);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			db.closeDB();
		}
		return  0;
	}
//	public Boolean login(String username,String password){
//
//		String logsql = "select * from user where username ='" + username +
//				"' and password ='" + password + "'";
//
//		DBManager sql = DBManager.createInstance();
//		sql.connectDB();
//
//		try{
//			ResultSet rSet = sql.executeQuery(logsql);
//			if(rSet.next()){
//				sql.closeDB();
//				return true;
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		sql.closeDB();
//		return false;
//	}
//
//	public Boolean register(String username, String password, String mail){
//
//		int id = 0;
//		String IDsql = "select * from user";
//		DBManager sql = DBManager.createInstance();
//		sql.connectDB();
//
//        try {
//            ResultSet ret = sql.executeQuery(IDsql);
//            while (ret.next())  {
//            	id++;
//            }
//        } catch (SQLException e)  {
//            e.printStackTrace();
//        }
//
//		id ++;
//		String regSql = "insert into user values('"+ id+ "','"+ username+ "','"+ password+ "','"+ mail+ "') ";
//
//		int ret  = sql.executeUpdate(regSql);
//		if(ret != 0){
//			sql.closeDB();
//			return true;
//		}
//		sql.closeDB();
//		return false;
//	}
//
//	public List<String> forgetpd(String username){
//
//		//String mail;
//		List<String> list = new ArrayList<String>();
//		String Usersql = "select * from user where username ='" + username+"'";
//		DBManager sql = DBManager.createInstance();
//		sql.connectDB();
//
//		try{
//			ResultSet rSet = sql.executeQuery(Usersql);
//			if(rSet.next()){
//				list.add(rSet.getString("mail"));
//				list.add(rSet.getString("password"));
//				sql.closeDB();
//				return list;
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		sql.closeDB();
//		return null;
//	}

}
