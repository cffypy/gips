package top.geomatics.ips.server.service;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import top.geomatics.ips.server.model.ScanInfo;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    @Override
    public void save(String json) {
        List<ScanInfo> scaninfos = JSON.parseArray(json, ScanInfo.class);
        System.out.println(scaninfos.size());
        for (int i = 0; i <scaninfos.size() ; i++) {
            scaninfos.get(i).getBSSID();
            scaninfos.get(i).getLevel();
            scaninfos.get(i).getSSID();
        }

    }
}
