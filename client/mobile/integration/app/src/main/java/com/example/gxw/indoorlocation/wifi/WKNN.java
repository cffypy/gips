package com.example.gxw.indoorlocation.wifi;

import lombok.Data;

/**
 * Created by peiyuyu on 2019/12/24.
 */

@Data
public class WKNN {
    private Double POSX;
    private Double POSY;
    private Double distance;

    public static void main(String[] args) {
        System.out.println(0.65/Math.pow(6,0.25));
    }

}
