// function registUser(w,h){
//     var left=(window.screen.width-w)/2;
//     var top=(window.screen.height-h)/2;
//     window.open("registUser.jsp", "用户注册", "height="+h+", width="+w+",top="+top+",left="+left+",toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
// };
// function registDriver(w,h){
//     var left=(window.screen.width-w)/2;
//     var top=(window.screen.height-h)/2;
//     window.open("registDriver.jsp", "司机注册", "height="+h+", width="+w+",top="+top+",left="+left+", toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
// }
function registUser(){
    window.open("registUser.jsp", "用户注册");
}
function registDriver(){
    window.open("registDriver.jsp", "司机注册");
}
function User(){
    $("#user").attr("style","color:red;text-decoration: none");
    $("#driver").attr("style","color:black;text-decoration: none");
    $("#admin").attr("style","color:black;text-decoration: none");
    $("#hotel").attr("style","color:black;text-decoration: none");
    $("#carteam").attr("style","color:black;text-decoration: none");

    $("#type").val("user");//给登录类型赋值
}
function Driver(){
    $("#user").attr("style","color:black;text-decoration: none");
    $("#driver").attr("style","color:red;text-decoration: none");
    $("#admin").attr("style","color:black;text-decoration: none");
    $("#hotel").attr("style","color:black;text-decoration: none");
    $("#carteam").attr("style","color:black;text-decoration: none");

    $("#type").val("driver");//给登录类型赋值
}
function Admin(){
    $("#user").attr("style","color:black;text-decoration: none");
    $("#driver").attr("style","color:black;text-decoration: none");
    $("#admin").attr("style","color:red;text-decoration: none");
    $("#hotel").attr("style","color:black;text-decoration: none");
    $("#carteam").attr("style","color:black;text-decoration: none");

    $("#type").val("admin");//给登录类型赋值
}
function Hotel(){
    $("#user").attr("style","color:black;text-decoration: none");
    $("#driver").attr("style","color:black;text-decoration: none");
    $("#admin").attr("style","color:black;text-decoration: none");
    $("#hotel").attr("style","color:red;text-decoration: none");
    $("#carteam").attr("style","color:black;text-decoration: none");

    $("#type").val("hotel");//给登录类型赋值
}
function Carteam(){
    $("#user").attr("style","color:black;text-decoration: none");
    $("#driver").attr("style","color:black;text-decoration: none");
    $("#admin").attr("style","color:black;text-decoration: none");
    $("#hotel").attr("style","color:black;text-decoration: none");
    $("#carteam").attr("style","color:red;text-decoration: none");

    $("#type").val("carteam");//给登录类型赋值
}
function changeone(){
    $("#verifycode").attr("src","verifycode.jsp?"+Math.random());
}
