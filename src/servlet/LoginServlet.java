package servlet;

import dao.impl.*;
import vo.Hotel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//这个是根据登录类型进行验证
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserImpl user = new UserImpl();
        AdminImpl admin = new AdminImpl();
        HotelImpl hotel = new HotelImpl();
        DriverImpl driver = new DriverImpl();
        CarTeamImpl carteam = new CarTeamImpl();

        req.setCharacterEncoding("UTF-8");
        String type = req.getParameter("type");
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        //获取验证码
        String verifycode = req.getParameter("verifycode").toLowerCase();
        String TrueCode = (String)req.getSession().getAttribute("VerifyCode");
        TrueCode = TrueCode.toLowerCase();
        switch (type){
            case "driver":
                if(driver.check(id,password)&&verifycode.equals(TrueCode)){
                    resp.sendRedirect("show.jsp");
                }
                else if(!verifycode.equals(TrueCode)){
                    req.setAttribute("registInfo","验证码错误");
                    req.getRequestDispatcher("index.jsp").forward(req,resp);
                }
                else {
                    req.setAttribute("registInfo","用户名密码错误");
                    req.getRequestDispatcher("index.jsp").forward(req,resp);
                }
                break;
            case "user":
                if(user.check(id,password)&&verifycode.equals(TrueCode)){
                    req.getSession().setAttribute("userID",id);
                    req.getSession().setAttribute("userName",user.findByUserId1(id).getName());
                    resp.sendRedirect("/Final/userPage/index.jsp");
                }
                else if(!verifycode.equals(TrueCode)){
                    req.setAttribute("registInfo","验证码错误");
                    req.getRequestDispatcher("index.jsp").forward(req,resp);
                }
                else {
                    req.setAttribute("registInfo","用户名密码错误");
                    req.getRequestDispatcher("index.jsp").forward(req,resp);
                }
                break;
            case "admin":
                if(admin.check(id,password)&&verifycode.equals(TrueCode)){
                    resp.sendRedirect("show.jsp");
                }
                else if(!verifycode.equals(TrueCode)){
                    req.setAttribute("registInfo","验证码错误");
                    req.getRequestDispatcher("index.jsp").forward(req,resp);
                }
                else {
                    req.setAttribute("registInfo","用户名密码错误");
                    req.getRequestDispatcher("index.jsp").forward(req,resp);
                }
                break;
            case "carteam":
                if(carteam.check(id,password)&&verifycode.equals(TrueCode)){
                    resp.sendRedirect("show.jsp");
                }
                else if(!verifycode.equals(TrueCode)){
                    req.setAttribute("registInfo","验证码错误");
                    req.getRequestDispatcher("index.jsp").forward(req,resp);
                }
                else {
                    req.setAttribute("registInfo","用户名密码错误");
                    req.getRequestDispatcher("index.jsp").forward(req,resp);
                }
                break;
            case "hotel":
                if(hotel.check(id,password)&&verifycode.equals(TrueCode)){
                    req.getSession().setAttribute("hotelID",id);
                    req.getSession().setAttribute("hotelName",hotel.getHotelName(id));
                    Hotel ho = hotel.findByHotelId(id);
                    req.getSession().setAttribute("hotelPassword",ho.getPassword());
                    req.getSession().setAttribute("hotelPlace",ho.getHotelPlace());
                    resp.sendRedirect("/Final/hotelPage/index.jsp");
                }
                else if(!verifycode.equals(TrueCode)){
                    req.setAttribute("registInfo","验证码错误");
                    req.getRequestDispatcher("index.jsp").forward(req,resp);
                }
                else {
                    req.setAttribute("registInfo","用户名密码错误");
                    req.getRequestDispatcher("index.jsp").forward(req,resp);
                }
                break;
            default:
                req.setAttribute("registInfo","请选择登录身份");
                req.getRequestDispatcher("index.jsp").forward(req,resp);
                break;
        }
    }
}