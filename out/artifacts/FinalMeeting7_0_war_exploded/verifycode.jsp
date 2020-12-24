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
<%	//设置页面不缓存
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
    response.reset();
    try{
        // 将认证码存入SESSION
        //调用 runVerifyCode(int i) ,把i改成所要的验证码位数
        VerifyCode VC = new VerifyCode();
        String  sVerifyCode = VC.runVerifyCode(4);
        session.setAttribute("VerifyCode",sVerifyCode);
        // 输出图象到页面
        //JPEGCodec.createJPEGEncoder(response.getOutputStream()).encode(VC.CreateImage(sVerifyCode));
        OutputStream outs = response.getOutputStream();
        ImageIO.write(VC.CreateImage(sVerifyCode),"JPEG",outs);
        //解决OutputStream类的outs对象与JspWriter类的out对象的冲突问题
        out.clear();
        out = pageContext.pushBody();

    }catch(Exception e){
        return ;
    }
%>
