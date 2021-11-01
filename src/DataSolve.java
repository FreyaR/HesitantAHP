import com.sun.nio.file.SensitivityWatchEventModifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DataSolve {
    Map<String, double[]> LingusticTermTriNumber = new HashMap<>(){
        {
            put("AS",new double[]{0.875,1,1.125});
            put("VS",new double[]{0.75,0.875,1});
            put("FS",new double[]{0.625,0.75,0.875});
            put("SS",new double[]{0.5,0.625,0.75});
            put("SW",new double[]{0.375,0.5,0.625});
            put("FW",new double[]{0.25,0.375,0.5});
            put("VW",new double[]{0.125,0.25,0.375});
            put("AW",new double[]{0,0.125,0.25});
            put("NO",new double[]{0,0,0});
        }
    };

    enum LingusticDegreeTerms {
        AS,
        VS,
        FS,
        SS,
        SW,
        FW,
        VW,
        AW,
        NO
    }

    private boolean isContains(String s){
        for (LingusticDegreeTerms terms : LingusticDegreeTerms.values()) {
            if (terms.name().equals(s)){
                return true;
            }
        }
        return false;
    }
    public double[][] generateFuzzyEnvelopeVector(String[] data){
        ComparisonMatrix matrix = new ComparisonMatrix();
        int len = data.length;
        double[][] res = new double[len][4];
        String pattern = ".*-.*";
        String[] s1 = new String[2];
        for (int m = 0; m < len; m++) {
            if (Pattern.matches(pattern,data[m])){
                s1 = data[m].split("-");
            }
            else if(isContains(data[m])){
                s1[0] = data[m];
                s1[1] = data[m];
            }
            else if (!isContains(data[m])){
                double tmp = Double.parseDouble(data[m]);
                res[m] = new double[]{tmp,tmp,tmp,tmp};
                continue;
            }
            int i = LingusticDegreeTerms.valueOf(s1[0]).ordinal();
            int j = LingusticDegreeTerms.valueOf(s1[1]).ordinal();
            if (i > j){
                int tmp = i;
                i = j;
                j = tmp;
            }
            double[] L = LingusticTermTriNumber.get(LingusticDegreeTerms.values()[i].toString());
            double[] R = LingusticTermTriNumber.get(LingusticDegreeTerms.values()[j].toString());
            double a = L[0];
            double d = R[2];
            List<Double> tempC = new ArrayList<>();
            for (int k = j; k >= i ; k--) {
                tempC.add(LingusticTermTriNumber.get(LingusticDegreeTerms.values()[k].toString())[1]);
            }
            List<Double> tempB = new ArrayList<>();
            for (int k = i; k <= j ; k++) {
                tempB.add(LingusticTermTriNumber.get(LingusticDegreeTerms.values()[k].toString())[1]);
            }
            int g = LingusticDegreeTerms.values().length - 1;
            double a1 = ((j-i) - 1d) / (g - 1d);
            double a2 = (g - (j-i)) / (g - 1d);

            double b = 0;
            double c = 0;
            int delta = 0;
            if (j == i + 1){
                b = LingusticTermTriNumber.get(LingusticDegreeTerms.values()[i].toString())[1];
                c = LingusticTermTriNumber.get(LingusticDegreeTerms.values()[i+1].toString())[1];
                res[m] =  new double[]{a,b,c,d};
                continue;
            }
            else if ((i + j) % 2 == 0){
                delta = (i + j) / 2;

            }
            else if ((i + j) % 2 == 1){
                delta = (i + j + 1) / 2;
            }
            double[] w1 = initWeightOne(a2, j - delta + 1);
            double[] w2 = initWeightTwo(a1, j - delta + 1);

            for (int k = 0; k <= j - delta; k++) {
                b += tempB.get(k) * w2[k];
            }
            for (int k = 0; k <= j - delta; k++) {
                c += tempC.get(k) * w1[k];
            }
            res[m] = new double[]{a,b,c,d};
        }
        return res;
    }
    private double[] initWeightOne(double init,int length){
        double[] res = new double[length];
        res[0] = init;
        res[length - 1] = Math.pow(1 - init, length - 1);
        for (int i = 1; i < length - 1; i++){
            res[i] = init * Math.pow(1 - init, i);
        }
        return res;
    }

    private double[] initWeightTwo(double init, int length){
        double[] res = new double[length];
        res[0] = Math.pow(init, length - 1);
        res[length - 1] = 1 - init;
        for (int i = 1; i < length - 1; i++) {
            res[i] = (1 - init) * Math.pow(init, length - i - 1);
        }
        return res;
    }

    public double[] getCrispyNumber(double[][] matrix){
        int len  = matrix.length;
        double[] res = new double[len];
        for (int i = 0; i < len; i++) {
            res[i] = (matrix[i][0] + 2 * matrix[i][1] + 2 * matrix[i][2] + matrix[i][3]) / 6;
        }
        return res;
    }
}
