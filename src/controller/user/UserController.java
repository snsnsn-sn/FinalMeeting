package controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.UserService;
import vo.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private UserService userService;

    public UserController(){
        userService=new UserService();
        System.out.println("UserController构造");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();

        String fun=null;
        fun=request.getParameter("fun");
        System.out.println("功能请求:"+fun);

        switch (fun){
            case "findAll":{
                findAll(request,out);
                break;
            }
            case "checkID":{
                checkID(request,out);
                break;
            }
            case "delete":{
                delete(request,out);
                break;
            }
            case "add":{
                add(request,out);
                break;
            }
            case "getPart":{
                getPart(request,out);
                break;
            }
            default:{
                System.out.println("未知请求:"+fun);
            }
        }
    }


    /**
     * 接受前端AJAX刷新请求,发送jsonStr格式的用户列表
     * @param request
     * @param out
     * @throws IOException
     */
    private void findAll(HttpServletRequest request, PrintWriter out) throws IOException {
        List<User> userList=userService.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(userList);
        out.print(jsonStr);
        System.out.println(jsonStr);
    }


    private void getPart(HttpServletRequest request, PrintWriter out) {
        String id= (String) request.getSession().getAttribute("userID");
        System.out.println("查询用户"+id+"所属单位:");
        String part=userService.getPartById(id);
        out.print(part!=null?part:0);
    }

    /**
     * 检查id是否可用 返回checkSpan的html内容
     * @param request
     * @param out
     * @throws IOException
     */
    public void checkID(HttpServletRequest request, PrintWriter out) throws IOException {
        String id=request.getParameter("id");
        System.out.println("待检测id:"+id);

        if(id=="")
            out.print("id不可为空");
        else if(!userService.id_exist(id))
        {
            System.out.println("可以使用");
            out.print("<p class=\"text-success\">可以注册</p>");
        }
        else{
            out.print("<p class=\"text-danger\">该id不可用</p>");
            System.out.println("不可用");
        }

    }

    /**
     * 删除用户 返回 1:删除成功  0:删除失败
     * @param request
     * @param out
     */
    private void delete(HttpServletRequest request, PrintWriter out){
        String id=request.getParameter("did");
        System.out.println("请求删除用户 id:"+id);
        if(userService.delete(id))
            out.print(1);
        else
            out.print(0);
    }

    /**
     * 添加用户 返回1:添加成功 0:添加失败
     * @param request
     * @param out
     * @throws IOException
     */
    public void add(HttpServletRequest request, PrintWriter out) throws IOException {
        String id=request.getParameter("aid");
        String name=request.getParameter("aname");
        String pw=request.getParameter("apw");
        String phone=request.getParameter("aphone");
        String part=request.getParameter("apart");

        User u=new User(id,name,pw,phone,part);
        System.out.println(u);

        if(!userService.id_exist(id))  //id不存在,可添加
            out.print(userService.insert(id,name,pw,phone,part)?1:0);
    }
}

