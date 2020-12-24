package controller.admin;

import dao.impl.CarTeamImpl;
import dao.impl.DriverImpl;
import dao.impl.HotelImpl;
import dao.impl.UserImpl;
import vo.CarTeam;
import vo.Driver;
import vo.Hotel;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/GetInfo")
public class getInfoServlet extends HttpServlet {
    UserImpl us = new UserImpl();
    DriverImpl d = new DriverImpl();
    HotelImpl h = new HotelImpl();
    CarTeamImpl ct = new CarTeamImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String fun = req.getParameter("fun");
        switch (fun){
            case "driverDelete":
                String did = req.getParameter("did");
                d.deleteByDriverId(did);
                req.getSession().removeAttribute("driver");
                resp.getWriter().print(1);
                break;
            case "driverUpdate":
                String did1 = req.getParameter("did");
                d.updatePassword(did1,"111111");
                req.getSession().setAttribute("driver",d.findByDriverId1(did1));
                resp.getWriter().print(1);
                break;
            case "hotelDelete":
                String hid = req.getParameter("hid");
                h.deleteById(hid);
                req.getSession().removeAttribute("hotel");
                resp.getWriter().print(1);
                break;
            case "hotelUpdate":
                String hid1 = req.getParameter("hid");
                h.updatePassword(hid1,"111111");
                req.getSession().setAttribute("hotel",h.findByHotelId(hid1));
                resp.getWriter().print(1);
                break;
            case "userDelete":
                String uid = req.getParameter("uid");
                us.deleteByUserId(uid);
                req.getSession().removeAttribute("user");
                resp.getWriter().print(1);
                break;
            case "userUpdate":
                String uid1 = req.getParameter("uid");
                us.updatePassword(uid1,"111111");
                req.getSession().setAttribute("user",us.findByUserId1(uid1));
                resp.getWriter().print(1);
                break;
            case "carTeamDelete":
                String teamid = req.getParameter("teamid");
                ct.deleteById(teamid);
                req.getSession().removeAttribute("carteam");
                resp.getWriter().print(1);
                break;
            case "carTeamUpdate":
                String teamid1 = req.getParameter("teamid");
                ct.updatePassword(teamid1,"111111");
                req.getSession().setAttribute("carteam",ct.findById(teamid1));
                resp.getWriter().print(1);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String type = req.getParameter("type");

        switch (type){
            case "driver":
                req.getSession().removeAttribute("hotel");
                req.getSession().removeAttribute("user");
                req.getSession().removeAttribute("driver");
                req.getSession().removeAttribute("carteam");

                String did = req.getParameter("driverId");
                Driver driver = d.findByDriverId1(did);
                req.getSession().setAttribute("driver",driver);
                resp.sendRedirect("/Final/adminPage/driverControl/info.jsp");
                break;
            case "hotel":
                req.getSession().removeAttribute("hotel");
                req.getSession().removeAttribute("user");
                req.getSession().removeAttribute("driver");
                req.getSession().removeAttribute("carteam");

                String hid = req.getParameter("hotelId");
                Hotel hotel = h.findByHotelId(hid);
                req.getSession().setAttribute("hotel",hotel);
                resp.sendRedirect("/Final/adminPage/hotelControl/info.jsp");
                break;
            case "user":
                req.getSession().removeAttribute("hotel");
                req.getSession().removeAttribute("user");
                req.getSession().removeAttribute("driver");
                req.getSession().removeAttribute("carteam");

                String uid = req.getParameter("userId");
                User user = us.findByUserId1(uid);
                req.getSession().setAttribute("user",user);
                resp.sendRedirect("/Final/adminPage/index.jsp");
                break;
            case "carteam":
                req.getSession().removeAttribute("hotel");
                req.getSession().removeAttribute("user");
                req.getSession().removeAttribute("driver");
                req.getSession().removeAttribute("carteam");

                String teamid = req.getParameter("carTeamId");
                CarTeam carTeam = ct.findById(teamid);
                req.getSession().setAttribute("carteam",carTeam);
                resp.sendRedirect("/Final/adminPage/carTeamControl/info.jsp");
                break;
        }
    }
}
