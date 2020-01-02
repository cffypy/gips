package com.example.gxw.indoorlocation.wifi;

import java.util.List;

import lombok.Data;

/**
 * Created by peiyuyu on 2019/12/24.
 */

@Data
public class Hipe {
    private Integer POSNum;
    private Double POSX;
    private Double POSY;
    private List<Wifi> FPinfo;
}
