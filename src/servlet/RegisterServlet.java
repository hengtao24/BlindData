package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Person;
import Bean.User;
import Util.SQLUtil;
import db.Dao;
import db.DaoImpl;
import net.sf.json.JSONObject;
import service.Service;
import service.ServiceImpl;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

	private static final long serialVersionUID = 369840051351775312L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

		request.setCharacterEncoding("UTF-8");
		String username = request.getHeader("studentId");
		String password = request.getHeader("password");
		String name = request.getHeader("name");
		String sex = request.getHeader("sex");
		int age = request.getIntHeader("age");
		double height = 0.0d;
		if (request.getHeader("height") != null) {
			height = Double.parseDouble(request.getHeader("height"));
		}
		double weight = 0.0d;
		if (request.getHeader("weight") != null){
			weight = Double.parseDouble(request.getHeader("weight"));
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();

		String message;
		boolean status;

		User user = new User(username,password);
		Person person = new Person(username,name,sex,age,height,weight);

		Dao daoImpl = new DaoImpl();
		Service serviceImpl = new ServiceImpl();
		if (username != null) {
		    System.out.println(user.getStudentId() + "11111");
			if (daoImpl.register(user)) {
				serviceImpl.insert(SQLUtil.InsertPerson(person));
				status = true;
				message = "注册成功";
			} else {
				status = false;
				message = "注册失败,用户名已存在";
			}
		}else {
			status = false;
			message = "注册失败,信息填写不完整";
		}

		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status",status);
			jsonObject.put("message",message);
			out.println(jsonObject);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		out.flush();
		out.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException{
		
	}
}
