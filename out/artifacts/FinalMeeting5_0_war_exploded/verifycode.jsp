<%--
  Created by IntelliJ IDEA.
  User: qaj
  Date: 2020/12/4
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="javax.imageio.*" %>
<%@ page import="java.io.*" %>
<%@ page import="bean.VerifyCode"%>
<%	//����ҳ�治����
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
    response.reset();
    try{
        // ����֤�����SESSION
        //���� runVerifyCode(int i) ,��i�ĳ���Ҫ����֤��λ��
        VerifyCode VC = new VerifyCode();
        String  sVerifyCode = VC.runVerifyCode(4);
        session.setAttribute("VerifyCode",sVerifyCode);
        // ���ͼ��ҳ��
        //JPEGCodec.createJPEGEncoder(response.getOutputStream()).encode(VC.CreateImage(sVerifyCode));
        OutputStream outs = response.getOutputStream();
        ImageIO.write(VC.CreateImage(sVerifyCode),"JPEG",outs);
        //���OutputStream���outs������JspWriter���out����ĳ�ͻ����
        out.clear();
        out = pageContext.pushBody();

    }catch(Exception e){
        return ;
    }
%>
