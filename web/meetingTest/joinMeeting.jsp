<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <input type="text" name="meetingName">会议名称
    <input type="submit" value="查找">
</form>
<div id="result">

</div>
</body>
</html>
<script src="static/js/common.js"></script>
<script>
    let form=document.forms[0];
    let a=null;
    form.addEventListener("submit",function () {
        let xmlHttp=new XMLHttpRequest();
        let div=document.getElementById("result");
        event.preventDefault();
        xmlHttp.open("post","/project/findMeetingServlet",true);

        xmlHttp.onreadystatechange=function () {
            if (xmlHttp.readyState == 4) {
                if ((xmlHttp.status >= 200 && xmlHttp.status < 300) || xmlHttp.status == 304) {
                    let lists=xmlHttp.responseText;
                    lists=JSON.parse(lists);
                    console.log(lists);
                    let str="<table>\n" +
                        "    <tr>\n" +
                        "        <th>会议名称</th>\n" +
                        "        <th>会议地点</th>\n" +
                        "        <th>参加人数</th>\n" +
                        "        <th>参加时间</th>\n" +
                        "    </tr>\n" +
                        "    <tr>";
                    for (let i of lists){
                        console.log(i);
                        str+="<td>会议名称："+i.meetingName+"</td>"+"<td>举办方："+i.host+"</td>"
                        +"<td>会议地点："+i.place+"</td>"+"<td>参加人数："+i.peopleCount+"</td>"
                        +"<td>参加时间："+i.time+"</td>"+"<td><a href='updateMeeting.jsp?meetingId='+'i.meetingId'>修改</a></td>"
                        +"<td><a href='/meeting/deleteMeeting?meetingId='+'i.meetingId'>删除</a></td>";
                    }
                    str+="</table>";
                    div.innerHTML=str;
                } else {
                    console.log("发生错误")
                }
            }
        }

        xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlHttp.send(serialize(form));

    },false);


</script>