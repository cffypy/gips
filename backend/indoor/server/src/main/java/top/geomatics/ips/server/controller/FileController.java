package top.geomatics.ips.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.geomatics.ips.server.service.FileServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Api(value = "/file", tags = "文件传输服务")
@Controller
public class FileController {

    @Autowired
    public FileServiceImpl fileService;

    @ApiOperation(value = "单文件上传", notes = "单文件上传")
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam MultipartFile file){
        return fileService.fileUpload(file);
    }

    @ApiOperation(value = "多文件上传", notes = "多文件上传")
    @PostMapping("/multifileUpload")
    public @ResponseBody String multifileUpload(HttpServletRequest request){
        return fileService.multifileUpload(request);
    }

    @ApiOperation(value = "文件下载", notes = "文件下载")
    @RequestMapping("/download")
    public String downLoad(HttpServletResponse response) throws UnsupportedEncodingException {
        return fileService.download(response);
    }
}
