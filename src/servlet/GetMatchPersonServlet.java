package servlet;

import Bean.MatchPerson;
import Bean.Person;
import Bean.User;
import Util.HobbyUtil;
import Util.SQLUtil;
import db.Dao;
import db.DaoImpl;
import net.sf.json.JSONObject;
import service.Service;
import service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getMatchPerson")
public class GetMatchPersonServlet extends HttpServlet{

    private static final long serialVersionUID = 369840151351775582L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String acceptjson = "";
        JSONObject jo = new JSONObject();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer("");
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
            acceptjson = sb.toString();
            if (!acceptjson.equals("")) {
                jo = JSONObject.fromObject(acceptjson);
				System.out.println("json text : " + jo.get("minage"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setCharacterEncoding("UTF-8");
        int minage = jo.getInt("minage");
        int maxage = jo.getInt("maxage");
        double minheight = jo.getDouble("minheight");
        double maxheight = jo.getDouble("maxheight");
        double minweight = jo.getDouble("minweight");
        double maxweight = jo.getDouble("maxweight");
        String hobby = jo.get("hobby").toString();
        String department = jo.get("department").toString();
        String sex = jo.getString("sex");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();

        String message ="";
        boolean status = false;

        MatchPerson matchPerson = new MatchPerson(minage,maxage,minheight,maxheight,
                minweight,maxweight,HobbyUtil.StringToList(hobby),department,sex);

        Dao daoImpl = new DaoImpl();
        try{
            List<Person> persons = daoImpl.getMatchPerson(SQLUtil.MatchPerson(matchPerson));
            System.out.println(SQLUtil.MatchPerson(matchPerson));
            if (persons.size() > 0){
                System.out.println("person:"+persons.get(0).getDepartment());
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status",true);
            jsonObject.put("message","匹配成功");
            jsonObject.put("persons",persons);
            out.println(jsonObject);
        }catch (Exception e){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status",false);
            jsonObject.put("message","匹配失败");
            jsonObject.put("persons",null);
            out.println(jsonObject);
            System.out.println(e.getMessage());
        }

        out.flush();
        out.close();
    }

}
