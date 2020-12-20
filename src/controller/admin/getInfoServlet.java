package controller.admin;

import dao.impl.DriverImpl;
import dao.impl.HotelImpl;
import dao.impl.UserImpl;
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
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String type = req.getParameter("type");

        switch (type){
            case "driver":
                String did = req.getParameter("driverId");
                Driver driver = d.findByDriverId1(did);
                req.getSession().setAttribute("driver",driver);
                resp.sendRedirect("/Final/adminPage/driverControl/info.jsp");
                break;
            case "hotel":
                String hid = req.getParameter("hotelId");
                Hotel hotel = h.findByHotelId(hid);
                req.getSession().setAttribute("hotel",hotel);
                resp.sendRedirect("/Final/adminPage/hotelControl/info.jsp");
                break;
            case "user":
                String uid = req.getParameter("userId");
                User user = us.findByUserId1(uid);
                req.getSession().setAttribute("user",user);
                resp.sendRedirect("/Final/adminPage/index.jsp");
                break;
        }
    }
}
