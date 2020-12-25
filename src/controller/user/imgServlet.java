package controller.user;

import dao.impl.ImgImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/imgServlet")
public class imgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("imgServlet");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String mid = (String) request.getSession().getAttribute("mid");

        //设置单个文件大小限制
        upload.setFileSizeMax(1024 * 1024 * 10);
        //设置所有文件总和大小限制
        upload.setSizeMax(1024 * 1024 * 30);

        try {
            List<FileItem> list = upload.parseRequest(request);
            System.out.println(list);
            for (FileItem fileItem : list) {
                System.out.println(fileItem.getFieldName());
                if (!fileItem.isFormField() && fileItem.getName() != null && !"".equals(fileItem.getName())) {
                    String filName = fileItem.getName();
                    //利用UUID生成伪随机字符串，作为文件名避免重复
                    String uuid = UUID.randomUUID().toString();
                    //获取文件后缀名
                    String suffix = filName.substring(filName.lastIndexOf("."));

                    //获取文件上传目录路径，在项目部署路径下的upload目录里。若想让浏览器不能直接访问到图片，可以放在WEB-INF下
                    String uploadPath = request.getSession().getServletContext().getRealPath("/meetingImg");

                    File file = new File(uploadPath);
                    file.mkdirs();
                    //写入文件到磁盘，该行执行完毕后，若有该临时文件，将会自动删除
                    fileItem.write(new File(uploadPath, uuid + suffix));
                    ImgImpl img = new ImgImpl();
                    img.deleteImg(mid);
                    img.uploadImg(mid, "/Final/meetingImg/" + uuid + suffix);
                    System.out.println(uploadPath + "存入");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
