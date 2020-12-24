package servlet;

import dao.impl.DriverImpl;
import dao.impl.HotelImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/login")
public class loginServlet extends HttpServlet {
    DriverImpl driver = new DriverImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        if(driver.check(id,password)){
            resp.sendRedirect("driver");
        }
        else {
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }
    }
}
