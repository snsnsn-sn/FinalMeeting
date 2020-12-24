package controller.login;

import dao.impl.*;
import vo.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet(value="/Pick")
public class OrderServlet extends HttpServlet {
    OrderCarImpl oc = new OrderCarImpl();
    OrderHotelImpl oh = new OrderHotelImpl();
    HotelImpl hi = new HotelImpl();
    PickUserImpl pu = new PickUserImpl();
    DriverImpl di = new DriverImpl();
    UserImpl ui = new UserImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        String method = req.getParameter("method");
//        switch(method){
//            case "addPickOrder":
//                boolean flag=true;
//                String ID = req.getParameter("alterName");
//                String peoStr = req.getParameter("alterPeople");
//
//                if(!pattern.matcher(peoStr).matches()){
//                    req.getRequestDispatcher("/Final/userPage/MyOrder/pickOrder.jsp").forward(req,resp);
//                    break;
//                }
//                else{
//                    int people = Integer.parseInt(peoStr);
//                    String place = req.getParameter("alterPlace");
//                    String date = req.getParameter("alterTime").replace("T"," ");
//                    oc.insert(ID,people,place,0,date);
//                    resp.sendRedirect("/Final/Pick?method=showOrderCars");
//                }
//                break;
//            case "addHotelOrder":
//                boolean flag1=true;
//                String ID1 = req.getParameter("alterName");
//                String peoStr1 = req.getParameter("alterPeople");
//                if(!pattern.matcher(peoStr1).matches()){
//                    req.getRequestDispatcher("/Final/userPage/MyOrder/hotelOrder.jsp").forward(req,resp);
//                }
//                else{
//                    int people1 = Integer.parseInt(peoStr1);
//                    String hotelName = req.getParameter("alterHotel");
//                    oh.insert(ID1,people1,0,hotelName);
//                    resp.sendRedirect("/Final/Pick?method=showOrderHotels");
//                }
//                break;
//        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String ID = (String)req.getSession().getAttribute("userID");
        String method = req.getParameter("method");
        switch(method){
            case "showOrderCars":
                List<OrderCar> cars = new ArrayList<>();
                cars = oc.findByUserId(ID);
                req.getSession().setAttribute("cars",cars);
                resp.sendRedirect("/Final/userPage/MyOrder/pickOrder.jsp");
                break;
            case "showOrderHotels":
                List<OrderHotel> hotels = new ArrayList<>();
                hotels = oh.findByUserId(ID);
                req.getSession().setAttribute("hotels",hotels);
                resp.sendRedirect("/Final/userPage/MyOrder/hotelOrder.jsp");
                break;
            case "showPickInfo":
                List<PickUser> PU = new ArrayList<>();
                PU = pu.findByUserId(ID);
                req.getSession().setAttribute("pickers",PU);
                resp.sendRedirect("/Final/pickInfo.jsp");
                break;
            case "showHotelInfo":
                String hotelName = req.getParameter("hotelName");
                Hotel hotel = hi.findByHotelName(hotelName);
                req.getSession().setAttribute("hotelInfo",hotel);
                resp.sendRedirect("/Final/hotelInfo.jsp");
                break;
            case "showDriverInfo":
                String driverId = req.getParameter("driverId");
                List<Driver> d = new ArrayList<>();
                d = di.findByDriverId(driverId);
                req.getSession().setAttribute("driverInfo",d);
                resp.sendRedirect("/Final/driverInfo.jsp");
                break;
            case "showUserInfo":
                String userId = req.getParameter("userId");

                User U = ui.findByUserId1(userId);
                req.getSession().setAttribute("UserInfo",U);
                resp.sendRedirect("/Final/carPage/orderCharge/showUserInfo.jsp");
                break;
        }
    }
}
