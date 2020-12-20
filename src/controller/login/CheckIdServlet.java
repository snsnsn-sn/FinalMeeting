package controller.login;

import dao.impl.*;
import vo.Driver;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value="/CheckId")
public class CheckIdServlet extends HttpServlet {
    UserImpl user = new UserImpl();
    AdminImpl admin = new AdminImpl();
    HotelImpl hotel = new HotelImpl();
    DriverImpl driver = new DriverImpl();
    CarTeamImpl carteam = new CarTeamImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String id= req.getParameter("id");
        String type = req.getParameter("type");
        List<Driver> drivers = new ArrayList<>();
        List<User> users = new ArrayList<>();


        String CheckId = null;
        if(type.equals("driver")){
            drivers = driver.findByDriverId(id);
            for(Driver d:drivers){
                CheckId = d.getDriverId();
            }
            if(CheckId==null){
                resp.getWriter().write("true");
            }
            else{
                resp.getWriter().write("false");
            }
        }
        else if(type.equals("user")){
            users = user.findByUserId(id);
            for(User u:users){
                CheckId = u.getUserId();
            }
            if(CheckId==null){
                resp.getWriter().write("true");
            }
            else{
                resp.getWriter().write("false");
            }
        }
        else{
            resp.getWriter().write("no");
        }
    }
}
