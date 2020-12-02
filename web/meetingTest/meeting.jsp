<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        input{
            margin-left: 5px;
        }
        form{
            width: 300px;
        }
    </style>
</head>
<body>
<form action="/ms/meeting/createMeeting" method="post">
    <fieldset>
        <legend>填写信息</legend>
        会议名称：<input name="meetingName" type="text" required autocomplete="off"><br/>
        举办单位：<input name="meetingHost" type="text" required autocomplete="off" ><br/>
        举办地点：<input type="text" name="meetingPlace" required autocomplete="off"><br/>
        举办时间：<input type="date" name="meetingDate" id="date" required autocomplete="off" value="2020-11-9"><br/>
        酒店选择：
        <select>
            <option>糖葫芦酒店</option>
        </select><br/>
        车队选择：
        <select>
            <option>糖葫芦车队</option>
        </select><br/>
    </fieldset>
    <fieldset>
        <legend>填写要求</legend>
        所属单位：<input type="text" name="part" ><br/>
        是否批准：<input type="radio" id="yes" name="flag"><label for="yes">是</label>
                <input type="radio" id="no" name="flag"><label for="no">否</label><br/>
        人数限制：<input name="peopleCount" type="number" required autocomplete="off"><br/>
    </fieldset>
    <pre>
        <input type="submit" value="提交">      <input type="reset" value="重置">
    </pre>
</form>
</body>
</html>
<script src="static/js/meeting.js"></script>
<script>
    window.onload=function () {
        setDate();
    }
</script>
