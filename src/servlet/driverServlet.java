package servlet;

import dao.impl.OrderCarImpl;
import dao.impl.OrderHotelImpl;
import vo.OrderCar;
import vo.OrderHotel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value="/driver")
public class driverServlet extends HttpServlet {
    OrderCarImpl oh = new OrderCarImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderCar> orders = oh.findAll();
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("show.jsp").forward(req,resp);
    }
}
