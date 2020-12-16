package controller.hotel;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.HotelService;
import vo.OrderHotel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/showOrder")
public class showOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String name = (String)request.getSession().getAttribute("hotelName");
        //PrintWriter pw = response.getWriter();
        //pw.write(name);
        HotelService s = new HotelService();
        List<OrderHotel> list = s.findByHotelName(name);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(list);
        PrintWriter out = response.getWriter();
        out.write(jsonStr);
    }
}
