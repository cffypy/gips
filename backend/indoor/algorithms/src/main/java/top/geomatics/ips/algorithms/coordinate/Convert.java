package top.geomatics.ips.algorithms.coordinate;

public class Convert {
    //卯酉圈曲率半径N 长半轴a 短半轴b 大地纬度B
    public static double getN(double a, double b, double B[]) {
        double bb=ConvertCoordinate(B[0],B[1],B[2]);
        //第一偏心率e1
        double e1=Convert.getE(a,b);
        double W = Math.sqrt(1 - Math.pow(e1,2)* Math.pow(Math.sin(bb),2));
        double N = a / W;
        System.out.println("卯酉圈曲率半径"+N);
        return N;
    }

    //计算偏心率
    public static double getE(double a, double b) {
        double e=Math.sqrt(a*a-b*b)/a;
        System.out.println("第一偏心率"+e);
        return e;
    }

    //度分秒转换为度 通过ConvertCoordinate函数将经纬度中分秒转换为度。
    public static double ConvertCoordinate(double deg, double min, double sec) {
        return deg + min / 60.0 + sec / 3600.0;
    }

    //角度转弧度
    public double JTH(String angle) {
        double a=11;
        return a;
    }

    //弧度转角度
    public double[] HTJ(double hudu) {
        double []a={1,2};
        return a;
    }

    //大地坐标求三维直角坐标方法
//    X=(N+h)*cos(lat)*cos(lon);
//    Y=(N+h)*cos(lat)*sin(lon);
//    Z=(N*(1-e2)+h)*sin(lat);
    public static double[] XYZ(double a,double b,double n,double []B,double []L,double H){
        double bb=ConvertCoordinate(B[0],B[1],B[2]);
        double ll=ConvertCoordinate(L[0],L[1],L[2]);
        double e=Convert.getE(a,b);
        System.out.println("大地纬度"+bb);
        System.out.println("大地经度"+ll);
        double[] result=new double[3];
        System.out.println(result.length);
        result[0]=(n+H)*Math.cos(bb)*Math.cos(ll);
        result[1]=(n+H)*Math.cos(bb)*Math.sin(ll);
        result[2]=(n*(1-e*e)+H)*Math.sin(bb);
        return result;
    }

    //三维直角求大地坐标方法
    public double[] BLH(double X,double Y,double Z,double e2,double B0,double H0,double N){
        double []a={1,2};
        return a;
    }

    //求底点纬度方法
    public double[] BftNMn(double a,double b,double X){
        double [] aa={1,2};
        return aa;
    }

    //大地坐标转为高斯坐标
    public static double[] gaosi(double []B,double []L,int f,int system){
        double b=ConvertCoordinate(B[0],B[1],B[2]);
        double l=ConvertCoordinate(L[0],L[1],L[2]);
        //根据所选择的分带方式，计算带号 和经差
        double n;
        double L0;
        if(f==3){
            n=(b+1.5)/3;
            L0=n*3;
        }
        else{
            n=b/6+1;
            L0=n*6-3;
        }
        System.out.println(n);
        System.out.println(L0);

        double d[]=new double[2];
        return d;
    }


}