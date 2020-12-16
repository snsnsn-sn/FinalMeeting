package controller.hotel;

import service.HotelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateState")
public class updateStateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String time = request.getParameter("orderTime");
        int state =Integer.parseInt(request.getParameter("state"));
        String userId = request.getParameter("id");
        HotelService s = new HotelService();
        if(state == 3)
            s.deleteByIdAndTime(userId,time);
        else
            s.updateState(userId,time,state);
    }
}
