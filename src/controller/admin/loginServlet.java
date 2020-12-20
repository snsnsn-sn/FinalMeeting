package controller.admin;

import service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/adminLogin")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        String fun = request.getParameter("fun");

        if (fun.equals("login")) {
            String id = request.getParameter("loginID");
            String pw = request.getParameter("loginPW");

            AdminService AdminService = new AdminService();

            if (AdminService.login(id, pw)) {
                session.setAttribute("AdminID", id);
                session.setAttribute("AdminName", AdminService.getNameById(id));
                out.print(0);
            } else {
                out.print(1);
            }
        } else if (fun.equals("exit")) {
            Enumeration em = request.getSession().getAttributeNames();
            while (em.hasMoreElements()) {
                request.getSession().removeAttribute(em.nextElement().toString());
            }
            response.sendRedirect("/ms/AdminPage/index.jsp");
        } else
            System.out.println("未知请求" + fun);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
