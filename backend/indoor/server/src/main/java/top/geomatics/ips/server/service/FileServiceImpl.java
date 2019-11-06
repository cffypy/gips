package top.geomatics.ips.server.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @author chenfa
 * 文件接口实现类
 */
@Service
public class FileServiceImpl implements FileService {
    //单个文件上传
    @Override
    public String fileUpload(MultipartFile file) {
        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();
        long size = file.getSize();
        System.out.println(fileName + "-->" + size);

        String path = "C:/test" ;
        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "true";
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
    }

    //多个文件上传
    @Override
    public String multifileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");
        if(files.isEmpty()){
            return "false";
        }

        String path = "C:/test" ;
        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();
            long size = file.getSize();
            System.out.println(fileName + "-->" + size);

            if(file.isEmpty()){
                return "false";
            }else{
                File dest = new File(path + "/" + fileName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e) {
                    e.printStackTrace();
                    return "false";
                }
            }
        }
        return "true";
    }

    @Override
    public String download(HttpServletResponse response) throws UnsupportedEncodingException {
        String filename="1.xls";
        String filePath = "C:\\Users\\chenfa\\Desktop" ;
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filename,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(filename+" 下载成功");
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
