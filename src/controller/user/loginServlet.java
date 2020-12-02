package controller.user;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(true);

        String fun=request.getParameter("fun");

        if(fun.equals("login")){
            String id=request.getParameter("loginID");
            String pw=request.getParameter("loginPW");

            UserService userService=new UserService();

            if(userService.login(id,pw)){
                session.setAttribute("userID",id);
                session.setAttribute("userName",userService.getNameById(id));
                out.print(0);
                System.out.println("登录成功");
            }
            else{
                out.print(1);
                System.out.println("登陆失败");
            }
        }
        else if(fun.equals("exit")){
            Enumeration em=request.getSession().getAttributeNames();
            while(em.hasMoreElements()){
                request.getSession().removeAttribute(em.nextElement().toString());
            }
            response.sendRedirect("/ms/userPage/index.jsp");
        }
        else
            System.out.println("未知请求"+fun);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


}
