package top.geomatics.ips.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.geomatics.ips.algorithms.PositioningResult;
import top.geomatics.ips.algorithms.knn.NearestNeighborAlgorithm;
import top.geomatics.ips.model.wlan.WLANMeasurement;

/**
 * @author chenfa
 * 基于wifi的室内定位算法
 */

@RestController
public class PositionController {

    @Autowired
    public NearestNeighborAlgorithm knn;

    @RequestMapping("/knn")
    public PositioningResult knn(@RequestParam WLANMeasurement measurements ){
        PositioningResult positioningResult=knn.calculatePosition(measurements);
        return positioningResult;
    }

}
