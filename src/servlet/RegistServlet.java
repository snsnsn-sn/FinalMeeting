package servlet;


import dao.DBConnection.DBConn;
import dao.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(value="/Regist")
//这是注册信息插入的
public class RegistServlet extends HttpServlet {
    Connection conn = DBConn.getConnection();
    UserImpl user = new UserImpl();
    DriverImpl driver = new DriverImpl();
    AdminImpl admin = new AdminImpl();
    HotelImpl hotel = new HotelImpl();
    CarTeamImpl carteam = new CarTeamImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //查id是否存在

        String idValid = req.getParameter("idValid");//根据注册页面的ajax设置的isValid值判断是否有效

        String type = req.getParameter("type");
        //以下是从表单得到的注册信息，下面四项是user和driver通用的
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String name = req.getParameter("name");

        String part = req.getParameter("part");

        String passengerStr = req.getParameter("passengers");
        Integer passengerInt;
        if(passengerStr!=null)
            passengerInt = Integer.parseInt(passengerStr);
        else passengerInt = null;

        if((type!=null)&&type.equals("driver")){
            if(idValid.equals("no")){
                resp.setContentType("text/html; charset=utf-8");
                req.setAttribute("registInfo","用户名不符合规范");
                req.getRequestDispatcher("registDriver.jsp").forward(req,resp);
            }
            else{
                driver.insert(id,password,phone,passengerInt,0,name);
                resp.sendRedirect("/Final/index.jsp");
            }
        }
        else if((type!=null)&&type.equals("user")){
            if(idValid.equals("no")){
                resp.setContentType("text/html; charset=utf-8");
                req.setAttribute("registInfo","用户名不符合规范");
                req.getRequestDispatcher("registUser.jsp").forward(req,resp);
            }
            else{
                user.insert(id,password,phone,part,name);
                resp.sendRedirect("/Final/index.jsp");
            }
        }
        else{
            resp.sendRedirect("/Final/index.jsp");
        }
    }
}

