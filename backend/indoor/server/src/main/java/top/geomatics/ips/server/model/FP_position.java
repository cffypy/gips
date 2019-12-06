package top.geomatics.ips.server.model;

import lombok.Data;

/**
 * @author chenfa
 */
import java.util.List;

@Data
public class FP_position {
    private int Point_NO;
    private double PosLon;
    private double PosLat;
    private int NumOfSamples;
    private List<FP_info> FP_info;
}
