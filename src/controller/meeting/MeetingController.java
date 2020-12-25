package controller.meeting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.impl.ImgImpl;
import service.MeetingService;
import vo.Meeting;
import vo.MeetingInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;

@WebServlet("/meeting")
public class MeetingController extends HttpServlet {
    private MeetingService meetingService;
    private ImgImpl imgImpl;

    public MeetingController() {
        meetingService = new MeetingService();
        imgImpl = new ImgImpl();
        System.out.println("MeetingController构造");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String fun = request.getParameter("fun");
        PrintWriter out = response.getWriter();

        switch (fun) {
            case "getImg": {
                getImg(request, out);
                break;
            }
            //查询系统全部会议
            case "findAll": {
                findAll(request, out);
                break;
            }
            //查询用户创建的会议
            case "myCreate": {
                getCreate(request, out);
                break;
            }
            //查询用户参加的会议
            case "myJoin": {
                getJoin(request, out);
                break;
            }
            //通过会议id查询会议
            case "midQuery": {
                queryByMeetingId(request, out);
                break;
            }
            //通过会议创建者id
            case "cidQuery": {
                queryByCreaterId(request, out);
                break;
            }
            //通过会议名称查询
            case "nameQuery": {
                queryByMeetingName(request, out);
                break;
            }
            //获取会议详细信息
            case "getInfo": {
                getMeetingInfo(request, out);
                break;
            }
            //修改会议基础信息
            case "alterBasic": {
                alterBasic(request, out);
                break;
            }
            //修改会议详细信息
            case "alterDetail": {
                alterDetail(request, out);
                break;
            }
            //添加会议
            case "add": {
                add(request, out);
                break;
            }
            //删除会议
            case "delete": {
                delete();
                break;
            }
            //获取会议参加者列表
            case "getParticipants": {
                getParticipants(request, out);
                break;
            }
            case "alterBrief": {
                alterBrief(request, out);
                break;
            }
            case "meetingDelete": {
                meetingDelete(request, out);
                break;
            }
            case "removeParticipant": {
                removeParticipant(request, out);
                break;
            }
            case "joinMeeting": {
                joinMeeting(request, out);
                break;
            }
            case "quitMeeting": {
                quitMeeting(request, out);
                break;
            }
            case "isJoin": {
                isJoin(request, out);
                break;
            }
            default:
                System.out.println("未知请求:" + fun);
        }
    }

    //全体会议
    private void findAll(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        List<Meeting> meetingList = meetingService.findAll();
        //递增排序
        meetingList.sort(new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return Integer.parseInt(o1.getMeetingId()) - Integer.parseInt(o2.getMeetingId());
            }
        });

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(meetingList);
        out.print(jsonStr);
    }

    //用户创建的会议 通过session查询
    private void getCreate(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        String id = (String) request.getSession().getAttribute("userID");
        if (id.equals("null"))
            out.print("请先登录");
        else {
            List<Meeting> meetingList = meetingService.getCreatedMeetingByUserId(id);
            if (meetingList.size() == 0)
                out.print(0);
            else {
                ObjectMapper mapper = new ObjectMapper();
                String jsonStr = mapper.writeValueAsString(meetingList);
                out.print(jsonStr);
            }
        }
    }

    //用户参加的会议
    private void getJoin(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        String id = (String) request.getSession().getAttribute("userID");
        if (id.equals("null"))
            out.print("请先登录");
        else {
            List<Meeting> meetingList = meetingService.getJoinedMeetingByUserId(id);
            if (meetingList.size() == 0)
                out.print(0);
            else {
                ObjectMapper mapper = new ObjectMapper();
                String jsonStr = mapper.writeValueAsString(meetingList);
                out.print(jsonStr);
            }
        }
    }

    //通过会议id查询
    private void queryByMeetingId(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        String mid = request.getParameter("mid");
        request.getSession().setAttribute("mid", mid);//存入session
        List<Meeting> meetings = meetingService.getMeetingByMeetingId(mid);
        if (meetings.size() == 0)
            out.print(0);
        else {
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(meetings);
            out.print(jsonStr);
        }
    }

    //通过发起人id查询
    private void queryByCreaterId(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        String cid = request.getParameter("cid");
        List<Meeting> meetings = meetingService.getMeetingByUserId(cid);
        if (meetings.size() == 0)
            out.print(0);
        else {
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(meetings);
            out.print(jsonStr);
        }
    }

    //通过会议名称查询
    private void queryByMeetingName(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        String mname = request.getParameter("name");
        List<Meeting> meetings = meetingService.getMeetingByMeetingName(mname);
        if (meetings.size() == 0)
            out.print(0);
        else {
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(meetings);
            out.print(jsonStr);
        }
    }

    /**
     * 添加会议
     *
     * @param request
     * @param out
     */
    private void add(HttpServletRequest request, PrintWriter out) {
        String uid = request.getParameter("uid");
        String name = request.getParameter("name");
        String part = request.getParameter("part");
        String time = request.getParameter("time");
        String place = request.getParameter("place");

        out.print(meetingService.insert(name, uid, part, time, place) ? 1 : 0);
    }

    /**
     * 查询会议详细信息 若mid已有信息,返回,否则新建初始信息插入数据库并返回
     *
     * @param request
     * @param out
     * @throws JsonProcessingException
     */
    private void getMeetingInfo(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        String mid = request.getParameter("mid");
        MeetingInfo i = meetingService.getMeetingInfoById(mid);
        ObjectMapper mapper = new ObjectMapper();

        if (i == null) {
            meetingService.insertInfo(mid, "未选择酒店", "", 0, "这里空空的,什么也没有", "");
            i = meetingService.getMeetingInfoById(mid);
        }
        String jsonStr = mapper.writeValueAsString(i);
        out.print(jsonStr);
    }

    private void delete() {

    }

    //修改会议的基本信息
    private void alterBasic(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        String mid = request.getParameter("mid");
        String name = request.getParameter("name");
        String place = request.getParameter("place");
        String time = request.getParameter("time");
        String state = request.getParameter("state");

        String s = (mid + name + place + time + state);
        out.print(meetingService.updateMeetingBasic(mid, name, time, place, state) ? 1 : 0);

    }

    //修改会议的详细信息
    private void alterDetail(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        String mid = request.getParameter("mid");
        String hotel = request.getParameter("hotel");
        String useCar = request.getParameter("useCar");
        String part = request.getParameter("part");


        MeetingInfo i = meetingService.getMeetingInfoById(mid);
        meetingService.deleteInfo(mid);
        if (meetingService.insertInfo(mid, hotel, i.getHotel_id(), Integer.parseInt(useCar), i.getMbrief(), part))
            out.print(1);
        else out.print(0);
    }

    //获取会议的参加者
    private void getParticipants(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        String mid = request.getParameter("mid");

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(meetingService.getParticipants(mid));
        out.print(jsonStr);
    }

    //获取会议的参加者
    private void alterBrief(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        String mid = request.getParameter("mid");
        String brief = request.getParameter("brief");

        out.print(meetingService.alterMeetingBrief(mid, brief));
    }

    //删除会议
    private void meetingDelete(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        String mid = request.getParameter("mid");
        meetingService.deleteMeeting(mid);
        out.print(1);
    }

    //删除与会者
    private void removeParticipant(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        String mid = request.getParameter("mid");
        String uid = request.getParameter("uid");
        meetingService.deleteParticipant(mid, uid);
        out.print(1);
    }

    //参加会议
    private void joinMeeting(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        String mid = request.getParameter("mid");
        String uid = (String) request.getSession().getAttribute("userID");
        String useCar = request.getParameter("useCar");
        String needHotel = request.getParameter("needHotel");

        String carPeople = request.getParameter("carPeople");
        String carTime = request.getParameter("carTime").replace("T"," ");
        String carPlace = request.getParameter("carPlace");

        String hotelPeople = request.getParameter("hotelPeople");
        String hotelTime = request.getParameter("hotelTime").replace("T"," ");

        //是否已报名
        if (!meetingService.isJoined(mid, uid)) {
            if (meetingService.joinMeeting(mid, uid)) {
                if (useCar.equals("1"))
                    meetingService.orderCar(uid, carPeople, carTime, carPlace);
                if (needHotel.equals("1"))
                    meetingService.orderHotel(uid, hotelPeople, hotelTime, mid);
                out.print(1);
            } else
                out.print(0);
        } else
            out.print(-1);
    }


    //退出会议
    private void quitMeeting(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        String mid = request.getParameter("mid");
        String uid = (String) request.getSession().getAttribute("userID");

        if (meetingService.quitMeeting(mid, uid)) {
            out.print(1);
        } else
            out.print(0);
    }

    //检查是否报名
    private void isJoin(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        String mid = request.getParameter("mid");
        String uid = (String) request.getSession().getAttribute("userID");

        //是否已报名
        if (meetingService.isJoined(mid, uid)) {
            out.print(1);
        } else
            out.print(0);
    }
    //获取会议图片
    private void getImg(HttpServletRequest request, PrintWriter out) throws JsonProcessingException {
        String mid = request.getParameter("mid");
        out.print(imgImpl.getImgPath(mid));
    }
}
