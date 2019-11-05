package top.geomatics.ips.server.model;


import lombok.Data;

/**
 * @author chenfa
 */
@Data
public class FP_info {

    private String MAC;
    private double meanRssi;
    private double stdRssi;
    private int minRssi;
    private int minRange;
    private int maxRange;
    private double ZeroProb;
    private int NumOfSamples;
    private int NumOfZeroSamples;
    private int NumStrongerthanMin;
    private int NumWeakerthanMax;

}
