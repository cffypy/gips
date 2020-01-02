package com.example.gxw.indoorlocation.util;

import android.os.Environment;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by peiyuyu on 2019/12/28.
 */

public class SaveCoordinatesUtil {
    private static Boolean flag = false;
    private static File file;

    /**
     * @param x        各方法得到的x坐标
     * @param y
     * @param fileType 需要保存的文件类型
     * @param isDelete 是不是删除之前手机根目录对应的文件
     * @return 填写是否成功
     * @throws IOException
     */
    public static Boolean save(Double x, Double y, String fileType, Boolean isDelete) throws IOException {
        File f = Environment.getExternalStorageDirectory();
        if ("wifi".equals(fileType)) {
            file = new File(f, "wifi.txt");
            write(file, x + "," + y + "\n", isDelete);
            flag = true;
        } else if ("pdr".equals(fileType)) {
            file = new File(f, "pdr.txt");
            write(file, x + "," + y + "\n", isDelete);
            flag = true;
        } else {
            file = new File(f, "ekf.txt");
            write(file, x + "," + y + "\n", isDelete);
            flag = true;
        }
        return flag;
    }

    public static void write(File file, String s, Boolean isDelete) throws IOException {
        if (file.exists()) {
            if (isDelete) {
                file.delete();
                file.createNewFile();
            }
        } else {
            file.createNewFile();
        }
        FileUtils.write(file, s, true);
    }

    public static void main(String[] args) {
        System.out.println("aaa" + "\n" + "bbb");
    }
}
