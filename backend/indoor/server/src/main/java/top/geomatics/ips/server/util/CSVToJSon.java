package top.geomatics.ips.server.util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;


/**
 * @author chenfa
 * csv转json
 */
public class CSVToJSon {

    private List<String> stringToList(String s, String sep) {
        if (s == null) {
            return null;
        }

        String[] parts = s.split(sep);
        return Arrays.asList(parts);
    }

    private String stringToJson(List<String> header, List<String> lineData) throws Exception {

        if (header == null || lineData == null) {
            throw new Exception("输入不能为null");
        } else if (header.size() != lineData.size()) {
            throw new Exception("表头个数和数据列个数不等。");
        }

        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("{ ");
        for (int i = 0; i < header.size(); i++) {
            String mString = String.format("\"%s\":\"%s\"", header.get(i), lineData.get(i));
            sBuilder.append(mString);

            if (i != header.size() - 1) {
                sBuilder.append(", ");
            }
        }
        sBuilder.append(" }");

        return sBuilder.toString();
    }

    public void ConvertToJson(String filePath, String outPutPath) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GB2312"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPutPath), "GB2312"));

        try {
            String sep = ",";
            String headerStr = reader.readLine();

            if (headerStr.trim().isEmpty()) {
                System.out.println("表格头不能为空");
                return;
            }

            List<String> header = stringToList(headerStr, sep);
            String line;
            int lineCnt = 1;
            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    System.out.println("第" + lineCnt + "行为空，已跳过");
                    continue;
                }

                List<String> lineData = stringToList(line, sep);

                if (lineData.size() != header.size()) {
                    String mString = String.format("第%d行数据列和表头列个数不一致\r\n%s", lineCnt, line);
                    System.err.println(mString);
                    break;
                }

                String jsonStr = stringToJson(header, lineData);
                writer.write(jsonStr);
                writer.write("\r\n");

                lineCnt++;
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String filePath = "data.csv";
        String outPutPath = "data.json";
        CSVToJSon csvToJSon = new CSVToJSon();
        csvToJSon.ConvertToJson(filePath, outPutPath);
        System.out.println("处理完成");
    }

}