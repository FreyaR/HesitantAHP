public class Calculate {
    public double HFWA(double[] data, double[] weights){
        double sum = 1;
        int len = data.length;
        for (int i = 0; i < len; i++) {
            double tmp = Math.pow((1 - data[i]),weights[i]);
            sum *= tmp;
        }
        return 1 - sum;
    }
    public double HGWA(double[] data,double[] weights){
        double sum = 1;
        int len = data.length;
        for (int i = 0; i < len; i++) {
            double tmp = Math.pow(data[i],weights[i]);
            sum *= tmp;
        }
        return sum;
    }

}
