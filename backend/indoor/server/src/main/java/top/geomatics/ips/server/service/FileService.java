package top.geomatics.ips.server.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @author chenfa
 * 文件接口
 */
public interface FileService {

    public String fileUpload(MultipartFile file);

    public String multifileUpload(HttpServletRequest request);

    public String download(HttpServletResponse response) throws UnsupportedEncodingException;

}
