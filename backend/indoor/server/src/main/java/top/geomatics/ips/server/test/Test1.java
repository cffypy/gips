package top.geomatics.ips.server.test;

import top.geomatics.ips.server.service.PositionServiceImpl;

public class Test1 {
    public static void main(String[] args) {
        String json="[{\n" +
                "\t\"SSID\": \"d8:c7:c8:a7:79:28\",\n" +
                "\t\"Level\": 63.13333333333334,\n" +
                "\t\"BSSID\": \"hhh\"\n" +
                "}, {\n" +
                "\t\"SSID\": \"24:69:68:8a:29:c6\",\n" +
                "\t\"Level\": 43.63333333333333,\n" +
                "\t\"BSSID\": \"wuhan\"\n" +
                "}, {\n" +
                "\t\"SSID\": \"d8:24:bd:78:45:b4\",\n" +
                "\t\"Level\": 52.066666666666656,\n" +
                "\t\"BSSID\": \"chenfa\"\n" +
                "}]";
        PositionServiceImpl positionService=new PositionServiceImpl();
        positionService.calculatePosition(json);
    }
}
