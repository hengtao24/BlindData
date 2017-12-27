package servlet;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.Service;
import service.ServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FgpLet extends HttpServlet {

	private static final long serialVersionUID = 369840050351775312L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
//
//		String username = request.getParameter("username");
//		username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
//		System.out.println(username);
//
//		ServiceImpl mService = new ServiceImpl();
//
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//
//		List<String> mail = mService.forgetpd(username);
//		if (mail == null) {
//			System.out.println("GetMail Failed");
//			out.print("fail");
//		}else {
//			System.out.println("GetMail Success");
//			out.print(mail.get(0));
//			new Thread(new NewMail((mail.get(1)),mail.get(0))).start();
//		}
		Service service = new ServiceImpl();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		List<Object> list = service.getList();
		PrintWriter out = null;
//		try {
//			out = response.getWriter();
//			out.write(list.toString());
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}

		try {
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = (Map<String, Object>) list.get(i);
			jsonObject.put("StudentId",map.get("StudentId"));
			jsonObject.put("Password",map.get("Password"));
//				jsonObject.put("StudentId", "111");
//				jsonObject.put("Password", "222");
				jsonArray.add(jsonObject);
			}
			out = response.getWriter();
			out.println(jsonArray);
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
