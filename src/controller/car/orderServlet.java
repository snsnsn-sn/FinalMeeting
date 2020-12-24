package controller.car;

import dao.impl.DriverImpl;
import dao.impl.OrderCarImpl;
import dao.impl.PickUserImpl;
import vo.Driver;
import vo.OrderCar;
import vo.PickUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/OrderCar")
public class orderServlet extends HttpServlet {
    OrderCarImpl oc = new OrderCarImpl();
    DriverImpl di = new DriverImpl();
    PickUserImpl pk = new PickUserImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        switch(method){
            case "dFindOrder":
                Driver d = (Driver)req.getSession().getAttribute("driver");
                req.getSession().setAttribute("picklist",pk.findByDriverId(d.getDriverId()));
                resp.sendRedirect("/Final/carPage/drive/index.jsp");
                break;
            case "findOrder":
                req.getSession().setAttribute("carlist",oc.findAll());
                resp.sendRedirect("/Final/carPage/orderCharge/showOrders.jsp");
                break;
            case "findDrivers":
                req.getSession().setAttribute("userId",req.getParameter("uid"));
                req.getSession().setAttribute("deadline",req.getParameter("deadline"));
                req.getSession().setAttribute("driverList",di.findAll());
                resp.getWriter().print(1);
                break;
            case "refuse":
                String userid = req.getParameter("uid");
                String deadline = req.getParameter("deadline");
                oc.updateState(userid,deadline,1);
                resp.getWriter().print(1);
                break;
            case "deleteOrder":
                String userid1 = req.getParameter("uid");
                String deadline1 = req.getParameter("deadline");
                oc.deleteByIdAndTime(userid1,deadline1);
                resp.getWriter().print(1);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
