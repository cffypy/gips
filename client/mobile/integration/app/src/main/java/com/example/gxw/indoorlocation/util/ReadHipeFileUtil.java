package com.example.gxw.indoorlocation.util;

import com.example.gxw.indoorlocation.wifi.Hipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by peiyuyu on 2019/12/29.
 */

public class ReadHipeFileUtil {
    private static ReadHipeFileUtil readHipeFileUtil = new ReadHipeFileUtil();

    private ReadHipeFileUtil() {
    }

    public static ReadHipeFileUtil getInstance() {
        return readHipeFileUtil;
    }

    /**
     * 读取指纹库中的指纹
     *
     * @param file 指纹的位置
     * @return 返回指纹对应的集合
     */
    public List<Hipe> readFile(File file) {
        List<Hipe> hipe = null;
        if (file.exists()) {
            try {
                String jsonString = FileUtils.readFileToString(file, "utf-8");
                Gson gson = new Gson();
                hipe = gson.fromJson(jsonString, new TypeToken<List<Hipe>>() {
                }.getType());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return hipe;
    }


    public static void main(String[] args) {

    }

}
