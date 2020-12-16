package controller.hotel;



import dao.impl.HotelImpl;
import vo.Hotel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/changeHotelInfo")
public class changeInfoServlet extends HttpServlet {
    HotelImpl ho = new HotelImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();

        String fun = null;
        fun = req.getParameter("fun");
        switch (fun){
            case "alterPassword":
                alterPassword(req, out);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String fun = req.getParameter("method");
        String HOTELPASSWORD =(String)req.getSession().getAttribute("hotelPassword");
        String HOTELID =(String)req.getSession().getAttribute("hotelID");
        String ID = req.getParameter("alterID");
        String name = req.getParameter("alterHotel");
        String place = req.getParameter("alterPlace");

        String password = (String)req.getSession().getAttribute("hotelPassword");
        switch(fun){
            case "changeInfo":
                ho.deleteById(ID);
                ho.insert(ID,password,name,place);
                req.getSession().setAttribute("hotelName",name);
                req.getSession().setAttribute("hotelPlace",place);
                resp.sendRedirect("/Final/hotelPage/index.jsp");
                break;
        }
    }

    private void alterPassword(HttpServletRequest request, PrintWriter out) {
        String pw0 = request.getParameter("pw0");
        String pw1 = request.getParameter("pw1");
        String uid = (String) request.getSession().getAttribute("hotelID");
        if(ho.findByHotelId(uid).getPassword().equals(pw0)){
            if (ho.updatePassword(uid, pw1))
                out.print(1);
            else
                out.print(0);
        }
        else{
            out.print(0);
        }

    }
}
