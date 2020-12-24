package controller.car;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.impl.DriverImpl;
import dao.impl.OrderCarImpl;
import dao.impl.PickUserImpl;
import service.DriverService;
import vo.Driver;
import vo.PickUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

@WebServlet("/driver")
public class DriverController extends HttpServlet {
    OrderCarImpl oc = new OrderCarImpl();
    DriverImpl dr = new DriverImpl();
    PickUserImpl pk = new PickUserImpl();
    private DriverService driverService;
    public DriverController() {
        driverService=new DriverService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        switch(method){
            case "driverAssign":
                String userId = req.getParameter("uid");
                String driverId = req.getParameter("did");
                String pickTime = req.getParameter("pickTime");
                String place = req.getParameter("place");
                if(place==""){
                    resp.getWriter().print(2);
                }
                else if(userId==""){
                    resp.getWriter().print(3);
                }
                else{
                    oc.updateState(userId,pickTime,2);
                    dr.updateState(driverId,1);
                    pk.insert(userId,driverId, pickTime,place);
                    resp.getWriter().print(1);
                }
                break;
            case "driverCancelAssign":
                String driverId1 = req.getParameter("did");

                break;
            case "findDrivers":
                req.getSession().setAttribute("userId",req.getParameter("uid"));
                req.getSession().setAttribute("deadline",req.getParameter("deadline"));
                req.getSession().setAttribute("driverList",dr.findAll());
                resp.sendRedirect("/Final/carPage/carTeam/driverControl.jsp");
                break;
            case "complete":
                pk.deleteByUidAndDid(req.getParameter("uid"),req.getParameter("did"));//PickUser表要删掉
                dr.updateState(req.getParameter("did"),0);//变为空闲
                oc.updateState(req.getParameter("uid"),req.getParameter("pickTime"),3);
                resp.getWriter().print(1);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("UTF-8");

       String method = request.getParameter("method");
       switch(method){
           case "findOrder":
               break;

       }
    }
    //全部的司机
    private void findAll(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        List<Driver> List=driverService.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(List);
        out.print(jsonStr);
    }
    //获得司机
    public void getDriver(HttpServletRequest request, PrintWriter out)throws IOException{
        Driver d=driverService.getDriver((String) request.getSession().getAttribute("driverID"));
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(d);
        out.print(jsonStr);
    }
    //改密码
    public void alterPassword(HttpServletRequest request, PrintWriter out){
        String pw0 = request.getParameter("pw0");
        String pw1 = request.getParameter("pw1");
        String id = (String) request.getSession().getAttribute("driverID");
        if(driverService.alterPassword(id,pw0,pw1))
            out.print(1);
        else
            out.print(0);
    }
    //id不存在,注册；存在不可用
    public void checkID(HttpServletRequest request, PrintWriter out)throws IOException{
        String id = request.getParameter("id");
        if(id.equals(""))
            out.print("id不能为空");
        else if (!driverService.id_exit(id)){
            out.print("<p class=\"text-success\">可以注册</p>");
        }
        out.print("<p class=\"text-danger\">该id不可用</p>");
    }
    //删除用户
    public void delete(HttpServletRequest request, PrintWriter out){
        String id=request.getParameter("id");
        driverService.delete(id);
        if(!driverService.id_exit(id))
            out.print(1);
        else  out.print(0);
    }
    //添加
    public void add(HttpServletRequest request, PrintWriter out)throws IOException{
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String pw = request.getParameter("password");
        String phone = request.getParameter("phone");
        int passengers=Integer.parseInt(request.getParameter("passengers"));
        int state=0;
        Driver d=new Driver(id,pw,phone,passengers,state,name);
        if(!driverService.id_exit(id)){
            driverService.insert(id,pw,phone,passengers,state,name);
            out.print(1);
        }
        else out.print("该账号存在，不可用");
    }
    public void getList(HttpServletRequest request, PrintWriter out) throws ParseException, JsonProcessingException {
        String id=request.getParameter("id");
        if(driverService.id_exit(id)){
            List<PickUser> list= driverService.getPickUsersList(id);
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(list);
            out.print(jsonStr);
        }
        out.print("该账户不存在");
    }

}
