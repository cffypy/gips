package top.geomatics.ips.algorithms.coordinate;

public class ConvertAlgorithm {
    //大地坐标转三维直角坐标
    //X=(N+h)*cos(lat)*cos(lon);
    //Y=(N+h)*cos(lat)*sin(lon);
    //Z=(N*(1-e2)+h)*sin(lat);
    public static double[] D2T(double a,double b,double N,double []B,double []L,double H){
        double bb=Convert.convertCoordinate(B[0],B[1],B[2]);
        double ll=Convert.convertCoordinate(L[0],L[1],L[2]);
        double e1=Convert.getE1(a,b);
        System.out.println("大地纬度"+bb);
        System.out.println("大地经度"+ll);
        double[] result=new double[3];
        result[0]=(N+H)*Math.cos(Convert.JTH(bb))*Math.cos(Convert.JTH(ll));
        result[1]=(N+H)*Math.cos(Convert.JTH(bb))*Math.sin(Convert.JTH(ll));
        result[2]=(N*(1-e1*e1)+H)*Math.sin(Convert.JTH(bb));
        return result;
    }

    //三维直角坐标转大地坐标
    public static double[] T2D(double a,double b,double X,double Y,double Z){
        double e1=Convert.getE1(a,b);
        //反三角函数得到的是弧度值，需要转为角度
        double L=Convert.HTJ(Math.atan(Y/X));
        System.out.println("经度"+Convert.convert2DFM(L));

        double N0=a;
        double H0=Math.sqrt(X*X+Y*Y+Z*Z)-Math.sqrt(a*b);
        double temp=Z/Math.sqrt(X*X+Y*Y);
        double temp2=e1*e1*N0/(N0+H0);
        double temp3=temp*(1/(1-temp2));
        double B0=Convert.HTJ(Math.atan(temp3));
        System.out.println("纬度"+Convert.convert2DFM(B0));
        System.out.println("N0 "+N0);
        System.out.println("H0 "+H0);
        System.out.println("B0 "+B0);

        double N1=a/Math.sqrt(1-e1*e1*Math.sin(Convert.JTH(B0)*Math.sin(Convert.JTH(B0))));
        double H1=Math.sqrt(X*X+Y*Y)/Math.cos(Convert.JTH(B0))-N1;
        double b11=Z*(1-e1*e1*N1/(N1+H1));
        double b1=b11/Math.sqrt(X*X+Y*Y)-1;
        double B1=Convert.HTJ(Math.atan(b1));
        System.out.println("N1 "+N1);
        System.out.println("H1 "+H1);
        System.out.println("B1 "+B1);

        double[] result=new double[3];
        System.out.println(result.length);
        result[0]=B0;
        result[1]=L;
        result[2]=H1;
        return result;
    }

    //大地坐标转高斯坐标
    public static double[] D2G(double a,double b,int dai,double []B,double []L){
        double bbb=Convert.convertCoordinate(B[0],B[1],B[2]);
        double lll=Convert.convertCoordinate(L[0],L[1],L[2]);
        System.out.println("纬度为"+bbb);
        System.out.println("经度为"+lll);
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

        double t=Math.tan(Convert.JTH(bbb));
        double u=e2*Math.cos(Convert.JTH(bbb));

        double X= a0*Math.sin(Convert.JTH(bbb))-a2*Math.sin(Convert.JTH(bbb))/2-a4*Math.sin(4*Convert.JTH(bbb))/4-
                a6*Math.sin(6*Convert.JTH(bbb))/6-a8*Math.sin(8*Convert.JTH(bbb))/8;
        System.out.println("哈哈X "+X);
        //根据所选择的分带方式，计算带号n和中央子午线经度L0
        double n;
        double L0;
        if(dai==3){
            n=Math.floor(bbb+1.5/3);
            L0=n*3;
        }
        else{
            n=Math.floor(bbb/6+1);
            L0=n*6-3;
        }
        //经差l
        double l = Math.abs(L0-lll);
        System.out.println("带号 "+n);
        System.out.println("中央子午线经度 "+L0);
        System.out.println("经差 "+l);
        double x=X+N*Math.sin(Convert.JTH(bbb))*Math.cos(Convert.JTH(bbb))*l*l/2+
                N*Math.sin(Convert.JTH(bbb))*Math.pow(Math.cos(Convert.JTH(bbb)),3)*(5-t*t+9*u*u+Math.pow(u,4))*Math.pow(l,4)/24+
                N*Math.sin(Convert.JTH(bbb))*Math.pow(Math.cos(Convert.JTH(bbb)),5)*(61-58*t*t+Math.pow(t,4))*Math.pow(l,6)/720;
        double y=N*l*Math.cos(Convert.JTH(bbb))+
                N*Math.pow(l,3)*Math.pow(Math.cos(Convert.JTH(bbb)),3)*(1-t*t+u*u)/6+
                N*Math.pow(l,5)*Math.pow(Math.cos(Convert.JTH(bbb)),5)*(5-18*t*t+Math.pow(t,4)+14*u*u-58*t*t*u*u)/120;
        System.out.println("xx "+x);
        System.out.println("yy "+y);

        double[] result=new double[2];
        result[0]=x;
        result[1]=y+500000+1000000*n;
        return result;
    }

    public static double[] G2D(double a,double b,int dai,double X,double Y){
        double e1=Convert.getE1(a,b);
        double e2=Convert.getE2(a,b);
        double f=Convert.getF(a,b);
//        double x=X;
//        double y=Y-n*1000000-500000;
        double d[]=new double[2];
        return d;
    }

    //正算
    public static double[] BLtoXY(double longitude,double latitude) {
        int ProjNo = 0;
        // 带宽
        int ZoneWide = 6;
        double longitude1, latitude1, longitude0, X0, Y0, xval, yval;
        double a, f, e2, ee, NN, T, C, A, M, iPI;
        // 3.1415926535898/180.0;
        iPI = 0.0174532925199433;
        // 54年北京坐标系参数
        a = 6378245.0;
        f = 1.0 / 298.3;

        // 80年西安坐标系参数
        // a=6378140.0;
        // f=1/298.257;
        ProjNo = (int) (longitude / ZoneWide);
        longitude0 = ProjNo * ZoneWide + ZoneWide / 2;
        longitude0 = longitude0 * iPI;

        // 经度转换为弧度
        longitude1 = longitude * iPI;
        // 纬度转换为弧度
        latitude1 = latitude * iPI;

        e2 = 2 * f - f * f;
        ee = e2 * (1.0 - e2);
        NN = a / Math.sqrt(1.0 - e2 * Math.sin(latitude1) * Math.sin(latitude1));
        T = Math.tan(latitude1) * Math.tan(latitude1);
        C = ee * Math.cos(latitude1) * Math.cos(latitude1);
        A = (longitude1 - longitude0) * Math.cos(latitude1);
        M = a * ((1 - e2 / 4 - 3 * e2 * e2 / 64 - 5 * e2 * e2 * e2 / 256) * latitude1 - (3 * e2 / 8 + 3 * e2 * e2 / 32 + 45 * e2 * e2 * e2 / 1024) * Math.sin(2 * latitude1) + (15 * e2 * e2 / 256 + 45 * e2 * e2 * e2 / 1024) * Math.sin(4 * latitude1) - (35 * e2 * e2 * e2 / 3072) * Math.sin(6 * latitude1));
        xval = NN * (A + (1 - T + C) * A * A * A / 6 + (5 - 18 * T + T * T + 72 * C - 58 * ee) * A * A * A * A * A / 120);
        yval = M + NN * Math.tan(latitude1) * (A * A / 2 + (5 - T + 9 * C + 4 * C * C) * A * A * A * A / 24 + (61 - 58 * T + T * T + 600 * C - 330 * ee) * A * A * A * A * A * A / 720);
        X0 = 1000000 * (ProjNo + 1) + 500000;
        Y0 = 0;
        xval = xval + X0;
        yval = yval + Y0;
        return new double[] { xval, yval };
    }

    //反算
    public static double[] XYtoBL(double X,double Y) {
        int ProjNo;
        int ZoneWide; // //带宽
        double[] output = new double[2];
        double longitude1, latitude1, longitude0, X0, Y0, xval, yval;// latitude0,
        double e1, e2, f, a, ee, NN, T, C, M, D, R, u, fai, iPI;
        iPI = 0.0174532925199433; // //3.1415926535898/180.0;
        a = 6378245.0; f = 1.0/298.3; //54年北京坐标系参数
        //a = 6378140.0;
        //f = 1 / 298.257; // 80年西安坐标系参数
        // 54年北京坐标系参数

        ZoneWide = 6; // //6度带宽
        ProjNo = (int) (X / 1000000); // 查找带号
        longitude0 = (ProjNo - 1) * ZoneWide + ZoneWide / 2;
        longitude0 = longitude0 * iPI; // 中央经线

        X0 = ProjNo * 1000000 + 500000;
        Y0 = 0;
        xval = X - X0;
        yval = Y - Y0; // 带内大地坐标
        e2 = 2 * f - f * f;
        e1 = (1.0 - Math.sqrt(1 - e2)) / (1.0 + Math.sqrt(1 - e2));
        ee = e2 / (1 - e2);
        M = yval;
        u = M / (a * (1 - e2 / 4 - 3 * e2 * e2 / 64 - 5 * e2 * e2 * e2 / 256));
        fai = u + (3 * e1 / 2 - 27 * e1 * e1 * e1 / 32) * Math.sin(2 * u) + (21 * e1 * e1 / 16 - 55 * e1 * e1 * e1 * e1 / 32) * Math.sin(4 * u) + (151 * e1 * e1 * e1 / 96) * Math.sin(6 * u) + (1097 * e1 * e1 * e1 * e1 / 512) * Math.sin(8 * u);
        C = ee * Math.cos(fai) * Math.cos(fai);
        T = Math.tan(fai) * Math.tan(fai);
        NN = a / Math.sqrt(1.0 - e2 * Math.sin(fai) * Math.sin(fai));
        R = a * (1 - e2) / Math.sqrt((1 - e2 * Math.sin(fai) * Math.sin(fai)) * (1 - e2 * Math.sin(fai) * Math.sin(fai)) * (1 - e2 * Math.sin(fai) * Math.sin(fai)));
        D = xval / NN;
        // 计算经度(Longitude) 纬度(Latitude)
        longitude1 = longitude0 + (D - (1 + 2 * T + C) * D * D * D / 6 + (5 - 2 * C + 28 * T - 3 * C * C + 8 * ee + 24 * T * T) * D * D * D * D * D / 120) / Math.cos(fai);
        latitude1 = fai - (NN * Math.tan(fai) / R) * (D * D / 2 - (5 + 3 * T + 10 * C - 4 * C * C - 9 * ee) * D * D * D * D / 24 + (61 + 90 * T + 298 * C + 45 * T * T - 256 * ee - 3 * C * C) * D * D * D * D * D * D / 720);
        // 转换为度 DD
        output[0] = longitude1 / iPI;
        output[1] = latitude1 / iPI;
        return output;
    }

}
