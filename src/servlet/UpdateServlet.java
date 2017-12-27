package servlet;

import Bean.Person;
import Bean.User;
import db.DaoImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 369840050351771646L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        System.out.println(request.getHeader("username"));
        DaoImpl daoImpl = new DaoImpl();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();

        String message;
        boolean status;
        Person person = null;

        if(username != null && username.length()>0 && password != null && password.length()>0) {
            User user = daoImpl.login(username);
            if (user != null && user.getPassword().equals(password)){
                message = "登陆成功";
                status = true;
                person = daoImpl.getPerson(username);
            }else{
                message = "登陆失败,用户名或密码错误,请检查";
                status = false;
            }
        }else {
            message = "登陆失败,请输入完整的用户名和密码";
            status = false;
            person = null;
        }

//		List<String> list = new ArrayList<>();
//		list.add("体育");
//		list.add("电影");

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status",status);
            jsonObject.put("message",message);
//			jsonObject.put("hobby",list);
            if (person != null) {
                jsonObject.put("person",person);
            }else{
                jsonObject.put("person","null");
            }
            out.println(jsonObject);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{

    }
}
