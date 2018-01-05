package servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet("/test")
public class JSONTest extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        String acceptjson = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer("");
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
            acceptjson = sb.toString();
            if (acceptjson != "") {
                JSONObject jo = JSONObject.fromObject(acceptjson);
                System.out.println("json text " + jo.get("we"));
                System.out.println(jo.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
