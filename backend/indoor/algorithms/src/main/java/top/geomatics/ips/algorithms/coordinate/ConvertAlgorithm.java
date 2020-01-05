package top.geomatics.ips.algorithms.coordinate;

public class ConvertAlgorithm {
    //大地坐标求三维直角坐标
    //X=(N+h)*cos(lat)*cos(lon);
    //Y=(N+h)*cos(lat)*sin(lon);
    //Z=(N*(1-e2)+h)*sin(lat);
    public static double[] D2T(double a,double b,double n,double []B,double []L,double H){
        double bb=Convert.convertCoordinate(B[0],B[1],B[2]);
        double ll=Convert.convertCoordinate(L[0],L[1],L[2]);
        double e=Convert.getE1(a,b);
        System.out.println("大地纬度"+bb);
        System.out.println("大地经度"+ll);
        double[] result=new double[3];
        System.out.println(result.length);
        result[0]=(n+H)*Math.cos(Convert.JTH(bb))*Math.cos(Convert.JTH(ll));
        result[1]=(n+H)*Math.cos(Convert.JTH(bb))*Math.sin(Convert.JTH(ll));
        result[2]=(n*(1-e*e)+H)*Math.sin(Convert.JTH(bb));
        return result;
    }

    //三维直角求大地坐标
    public static double[] T2D(double a,double b,double X,double Y,double Z){
        double N0=a;
        double H0=Math.sqrt(X*X+Y*Y+Z*Z);


        double L=Math.atan(Y/X);
        double H=Math.sqrt(X*X+Y*Y);
        double bb=Z/Math.sqrt(X*X+Y*Y);
        double B=Math.atan(bb);

        double[] result=new double[3];
        System.out.println(result.length);
        return result;
    }

    //求底点纬度方法
    public double[] BftNMn(double a,double b,double X){
        double [] aa={1,2};
        return aa;
    }

    //大地坐标转为高斯坐标
    public static double[] D2G(double a,double b,int dai,double []B,double []L,int system){
        double e1=Convert.getE1(a,b);
        double e2=Convert.getE2(a,b);
        double f=Convert.getF(a,b);
        double N=Convert.getN(a, b, B);
        double m0=a*(1-e1*e1);
        double m2=3/2*e1*e1*m0;
        double m4=5/4*e1*e1*m2;
        double m6=7/6*e1*e1*m4;
        double m8=9/8*e1*e1*m6;
        double a0=m0+1/2*m2+3/8*m4+5/16*m6+35/128*m8;
        double a2=1/2*m2+1/2*m4+15/32*m6+7/16*m8;
        double a4=1/8*m4+3/16*m6+7/32*m8;
        double a6=1/8*m4+3/16*m6+7/32*m8;
        double a8=1/128*m8;
        double bb=Convert.convertCoordinate(B[0],B[1],B[2]);
        double ll=Convert.convertCoordinate(L[0],L[1],L[2]);
        double t=Math.tan(bb);
        double xx=e2*Math.cos(bb);

        //根据所选择的分带方式，计算带号n和经差L0
        double n;
        double L0;
        if(dai==3){
            n=Math.floor((b+1.5)/3);
            L0=n*3;
        }
        else{
            n=Math.floor((b/6))+1;
            L0=n*6-3;
        }
        System.out.println(n);
        System.out.println(L0);


        double d[]=new double[2];
        return d;

    }

    public static double[] G2D(double a,double b,int dai,double X,double Y,int system){
        double e1=Convert.getE1(a,b);
        double e2=Convert.getE2(a,b);
        double f=Convert.getF(a,b);

        double d[]=new double[2];
        return d;

    }

}
