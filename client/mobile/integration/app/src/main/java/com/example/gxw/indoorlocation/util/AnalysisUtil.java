package com.example.gxw.indoorlocation.util;

import com.example.gxw.indoorlocation.wifi.Hipe;
import com.example.gxw.indoorlocation.wifi.Wifi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by peiyuyu on 2020/1/2.
 */

public class AnalysisUtil {
    private static AnalysisUtil analysisUtil = new AnalysisUtil();
    public String f1 = "C:\\Users\\peiyuyu\\Desktop\\hipe0.txt";
    public String f2 = "C:\\Users\\peiyuyu\\Desktop\\hipe1.txt";

    private AnalysisUtil() {
    }

    public static AnalysisUtil getInstance() {
        return analysisUtil;
    }

    private List<Hipe> merge(String f1, String f2) throws IOException {
        File file1 = new File(f1);
        File file2 = new File(f2);
        String json1 = FileUtils.readFileToString(file1, "utf-8");
        String json2 = FileUtils.readFileToString(file2, "utf-8");
        Gson gson = new Gson();
        List<Hipe> hipes1 = gson.fromJson(json1, new TypeToken<List<Hipe>>() {
        }.getType());
        List<Hipe> hipes2 = gson.fromJson(json2, new TypeToken<List<Hipe>>() {
        }.getType());
        hipes1.addAll(hipes2);
        return hipes1;
    }

    public static void main(String[] args) {
        AnalysisUtil instance = AnalysisUtil.getInstance();
        File file = new File("C:\\Users\\peiyuyu\\Desktop\\finger.xls");
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet wifiSheet = workbook.createSheet();
        writeHeadLine(wifiSheet);
        try {
            List<Hipe> hipes = instance.merge(instance.f1, instance.f2);
            int i = 1;
            for (Hipe hipe : hipes) {
                for (Wifi wifi : hipe.getFPinfo()) {
                    HSSFRow row = wifiSheet.createRow(i);
                    row.createCell(0).setCellValue(wifi.getBssid());
                    row.createCell(1).setCellValue(hipe.getPOSX());
                    row.createCell(2).setCellValue(hipe.getPOSY());
                    row.createCell(3).setCellValue(wifi.getDirection());
                    row.createCell(4).setCellValue(wifi.getRSSI());
                    row.createCell(5).setCellValue(wifi.getDater());
                    i++;
                }
            }
            workbook.write(file);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private static void writeHeadLine(HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("mac");
        row.createCell(1).setCellValue("X");
        row.createCell(2).setCellValue("Y");
        row.createCell(3).setCellValue("direction");
        row.createCell(4).setCellValue("level");
        row.createCell(5).setCellValue("time");
    }

}

