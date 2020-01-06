package top.geomatics.ips.algorithms.coordinate;

import java.math.BigDecimal;

public class Convert {
    //计算第一偏心率
    public static double getE1(double a, double b) {
        double e1=Math.sqrt(a*a-b*b)/a;
        System.out.println("第一偏心率"+e1);
        return e1;
    }

    //计算第二偏心率
    public static double getE2(double a, double b) {
        double e2=Math.sqrt(a*a-b*b)/b;
        System.out.println("第二偏心率"+e2);
        return e2;
    }

    //计算扁率
    public static double getF(double a, double b) {
        double f=(a-b)/a;
        System.out.println("扁率"+f);
        return f;
    }

    //计算卯酉圈曲率半径N 长半轴a 短半轴b 大地纬度B
    public static double getN(double a, double b, double B[]) {
        double bb=convertCoordinate(B[0],B[1],B[2]);
        double e1=getE1(a,b);
        double W = Math.sqrt(1 - Math.pow(e1,2)* Math.pow(Math.sin(JTH(bb)),2));
        double N = a / W;
        System.out.println("卯酉圈曲率半径"+N);
        return N;
    }

    //将经纬度中分秒转换为度
    public static double convertCoordinate(double deg, double min, double sec) {
        return deg + min / 60.0 + sec / 3600.0;
    }

    //度转换为度分秒
    public static String convert2DFM(double degree){
        int du=(int)Math.floor(Math.abs(degree));    //获取整数部分
        double temp=getPoint(Math.abs(degree))*60;
        int fen=(int)Math.floor(temp); //获取整数部分
        double temp2=getPoint(temp)*60;
        int miao=(int)Math.floor(temp2);
        if(degree<0){
            return "-"+du+" "+fen+" "+miao;
        }
        return du+" "+fen+" "+miao;
    }

    //获取小数部分
    private static double getPoint(double num){
        int fInt = (int) num;
        BigDecimal b1 = new BigDecimal(Double.toString(num));
        BigDecimal b2 = new BigDecimal(Integer.toString(fInt));
        double dPoint = b1.subtract(b2).floatValue();
        return dPoint;
    }

    //角度转弧度
    public static double JTH(double angle) {
        return angle*Math.PI/180;
    }

    //弧度转角度
    public static double HTJ(double hudu) {
        return Math.toDegrees(hudu);
    }

    //求底点纬度方法
    public double dw(double a,double b,double X){
        double cc=2;
        return cc;
    }





}