package controller.car;

import dao.impl.CarTeamImpl;
import dao.impl.DriverImpl;
import vo.CarTeam;
import vo.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Info")
public class changeInfo extends HttpServlet {
    CarTeamImpl ci = new CarTeamImpl();
    DriverImpl di = new DriverImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        switch(method){
            case "alterPassword":
                String pw0 = req.getParameter("pw0");
                String pw1 = req.getParameter("pw1");
                String teamid = (String) req.getSession().getAttribute("teamId");
                if(ci.findById(teamid).getPassword().equals(pw0)){
                    ci.updatePassword(teamid, pw1);
                    resp.getWriter().print(1);
                }
                else{
                    resp.getWriter().print(0);
                }
                break;
            case "alterDriverPassword":
                String Dpw0 = req.getParameter("pw0");
                String Dpw1 = req.getParameter("pw1");
                String driverid = ((Driver) req.getSession().getAttribute("driver")).getDriverId();
                if(di.findByDriverId1(driverid).getPassword().equals(Dpw0)){
                    di.updatePassword(driverid,Dpw1);
                    resp.getWriter().print(1);
                }
                else{
                    resp.getWriter().print(0);
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        //车队信息
        String TEAMPASSWORD =(String)req.getSession().getAttribute("teamPassword");
        String TEAMID =(String)req.getSession().getAttribute("teamId");
        String name = req.getParameter("alterCarName");
        //司机信息
        Driver driver = (Driver)req.getSession().getAttribute("driver");
        String DRIVERPASSWORD = driver.getPassword();
        switch(method){
            case "changeInfo":
                ci.deleteById(TEAMID);
                ci.insert(TEAMID,TEAMPASSWORD,name);
                req.getSession().setAttribute("teamName",name);
                resp.sendRedirect("/Final/carPage/carTeam/index.jsp");
                break;
            case "driverChangeInfo":
                String driverId = req.getParameter("alterID");
                di.deleteByDriverId(driverId);
                String password = ((Driver)req.getSession().getAttribute("driver")).getPassword();
                String phone = req.getParameter("alterPhone");
                int passengers = Integer.parseInt(req.getParameter("alterPassengers"));
                int state = Integer.parseInt(req.getParameter("alterState"));
                String drivername = req.getParameter("alterName");
                di.insert(driverId,password,phone,passengers,state,drivername);
                req.getSession().setAttribute("driver",di.findByDriverId1(driverId));
                resp.sendRedirect("/Final/OrderCar?method=dFindOrder");
                break;
        }
    }
}
