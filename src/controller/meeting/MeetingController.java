package controller.meeting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import service.MeetingService;
import vo.Meeting;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/meeting")
public class MeetingController extends HttpServlet {
    private MeetingService meetingService;

    public MeetingController(){
        meetingService=new MeetingService();
        System.out.println("MeetingController构造");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String fun=request.getParameter("fun");
        PrintWriter out=response.getWriter();

        System.out.println("请求:"+fun);


        switch (fun){
            case "findAll":{
                findAll(request,out);
                break;
            }
            case "myCreate":{
                getCreate(request,out);
                break;
            }
            case "myJoin": {
                getJoin(request,out);
                break;
            }
            case "midQuery":{
                queryByMeetingId(request,out);
                break;
            }
            case "cidQuery":{
                queryByCreaterId(request,out);
                break;
            }
            case "nameQuery":{
                queryByMeetingName(request,out);
                break;
            }
            case "add":{
                add(request,out);
                break;
            }
            case "delete":{
                delete();
                break;
            }
            default: System.out.println("未知请求:"+fun);
        }
    }

    //全体会议
    private void findAll(HttpServletRequest request,PrintWriter out) throws JsonProcessingException {
        List<Meeting> meetingList=meetingService.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(meetingList);
        out.print(jsonStr);
    }

    //用户创建的会议
    private void getCreate(HttpServletRequest request,PrintWriter out) throws JsonProcessingException {
        String id=(String)request.getSession().getAttribute("userID");
        if(id.equals("null"))
            out.print("请先登录");
        else{
            List<Meeting> meetingList=meetingService.getCreatedMeetingByUserId(id);
            if(meetingList.size()==0)
                out.print(0);
            else{
                ObjectMapper mapper = new ObjectMapper();
                String jsonStr = mapper.writeValueAsString(meetingList);
                out.print(jsonStr);
            }
        }
    }

    //用户参加的会议
    private void getJoin(HttpServletRequest request,PrintWriter out) throws JsonProcessingException {
        String id=(String)request.getSession().getAttribute("userID");
        if(id.equals("null"))
            out.print("请先登录");
        else{
            List<Meeting> meetingList=meetingService.getJoinedMeetingByUserId(id);
            if(meetingList.size()==0)
                out.print(0);
            else{
                ObjectMapper mapper = new ObjectMapper();
                String jsonStr = mapper.writeValueAsString(meetingList);
                out.print(jsonStr);
            }
        }
    }

    //通过会议id查询
    private void queryByMeetingId(HttpServletRequest request,PrintWriter out) throws JsonProcessingException {
        String mid=request.getParameter("mid");
        List<Meeting> meetings=meetingService.getMeetingByMeetingId(mid);
        if(meetings.size()==0)
            out.print(0);
        else{
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(meetings);
            out.print(jsonStr);
        }
    }

    //通过发起人id查询
    private void queryByCreaterId(HttpServletRequest request,PrintWriter out) throws JsonProcessingException {
        String cid=request.getParameter("cid");
        List<Meeting> meetings=meetingService.getMeetingByUserId(cid);
        if(meetings.size()==0)
            out.print(0);
        else{
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(meetings);
            out.print(jsonStr);
        }
    }

    //通过会议名称查询
    private void queryByMeetingName(HttpServletRequest request,PrintWriter out) throws JsonProcessingException {
        String mname=request.getParameter("name");
        List<Meeting> meetings=meetingService.getMeetingByMeetingName(mname);
        if(meetings.size()==0)
            out.print(0);
        else{
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(meetings);
            out.print(jsonStr);
        }
    }

    /**
     * 添加会议
     * @param request
     * @param out
     */
    private void add(HttpServletRequest request,PrintWriter out){
        String uid=request.getParameter("uid");
        String name=request.getParameter("name");
        String part=request.getParameter("part");
        String time=request.getParameter("time");
        String place=request.getParameter("place");

        out.print(meetingService.insert(name,uid,part,time,place)?1:0);
    }

    private void delete(){

    }
}
