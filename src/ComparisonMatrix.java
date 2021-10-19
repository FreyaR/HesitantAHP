import java.util.*;
import java.util.regex.Pattern;

public class ComparisonMatrix {
    Map<String, double[]> LingusticTermTriNumber = new HashMap<String, double[]>(){
        {
            put("AHI", new double[]{7.0000, 9.0000, 9.0000});
            put("VHI", new double[]{5.0000, 7.0000, 9.0000});
            put("ESHI", new double[]{3.0000, 5.0000, 7.0000});
            put("WHI", new double[]{1.0000, 3.0000, 5.0000});
            put("EHI", new double[]{1.0000, 1.0000, 3.0000});
            put("EE", new double[]{1.0000, 1.0000, 1.0000});
            put("ELI", new double[]{0.3300, 1.0000, 1.0000});
            put("WLI", new double[]{0.2000, 0.3300, 1.0000});
            put("ESLI", new double[]{0.1400, 0.2000, 0.3300});
            put("VLI", new double[]{0.1100, 0.1400, 0.2000});
            put("ALI", new double[]{0.1100, 0.1100, 0.1400});
        }
    };

    enum LingusticTerms {
        ALI,
        VLI,
        ESLI,
        WLI,
        ELI,
        EE,
        EHI,
        WHI,
        ESHI,
        VHI,
        AHI
    }
    public double[] generateEvenlope(String s){

        String pattern = "Between.*And.*";
        if (s.equals("EE")){
            return new double[]{1.0000, 1.0000, 1.0000, 1.0000};
        }
        else if (Pattern.matches(pattern,s)){
            String[] s1 = s.split(" ");

            int i = LingusticTerms.valueOf(s1[1]).ordinal();
            int j = LingusticTerms.valueOf(s1[3]).ordinal();
            if (i > j){
                int tmp = i;
                i = j;
                j = tmp;
            }
            double[] L = LingusticTermTriNumber.get(LingusticTerms.values()[i].toString());
            double[] R = LingusticTermTriNumber.get(LingusticTerms.values()[j].toString());
            double a = L[0];
            double d = R[2];
            List<Double> tempC = new ArrayList<>();
            for (int k = j; k >= i ; k--) {
                tempC.add(LingusticTermTriNumber.get(LingusticTerms.values()[k].toString())[1]);
            }
            List<Double> tempB = new ArrayList<>();
            for (int k = i; k <= j ; k++) {
                tempB.add(LingusticTermTriNumber.get(LingusticTerms.values()[k].toString())[1]);
            }
            int g = LingusticTerms.values().length - 1;
            double a1 = ((j-i) - 1d) / (g - 1d);
            double a2 = (g - (j-i)) / (g - 1d);

            double b = 0;
            double c = 0;
            int delta = 0;
            if (j == i + 1){
                b = LingusticTermTriNumber.get(LingusticTerms.values()[i].toString())[1];
                c = LingusticTermTriNumber.get(LingusticTerms.values()[i+1].toString())[1];
                return new double[]{a,b,c,d};
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
            return new double[]{a,b,c,d};

        }
        return null;
    }

    public List<List<double[]>> getFuzzyComparisonMatrix(String[][] linguisticMatrix){
        List<List<double[]>> res = new ArrayList<>();
        int row = linguisticMatrix.length;
        int col = linguisticMatrix[0].length;
        for (int i = 0; i < row; i++) {
            List<double[]> rowData = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                rowData.add(generateEvenlope(linguisticMatrix[i][j]));
            }
            res.add(rowData);
        }
        return res;
    }

    public double[][] getCrispyMatrix(List<List<double[]>> matrix){
        int row = matrix.size();
        int col = matrix.get(0).size();
        double[][] res = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                double[] numbers = matrix.get(i).get(j);
                double num = (numbers[0] + 2 * numbers[1] + 2 * numbers[2] + numbers[3]) / 6;
                res[i][j] = num;
            }
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
    public static void main(String[] args) {
        ComparisonMatrix matrix = new ComparisonMatrix();
        String[][] linguisticTerms = new String[][]{
                {"EE","Between WLI And EE","Between WLI And EE"},
                {"Between WLI And EE","EE","Between WLI And EE"},
                {"Between WLI And EE","Between WLI And EE","EE"}};
        List<List<double[]>> fuzzyComparisonMatrix = matrix.getFuzzyComparisonMatrix(linguisticTerms);
        for (List<double[]> comparisonMatrix : fuzzyComparisonMatrix) {
            for (double[] doubles : comparisonMatrix) {
                System.out.print(Arrays.toString(doubles));
            }
            System.out.println();
        }
        double[][] crispyMatrix = matrix.getCrispyMatrix(fuzzyComparisonMatrix);
        for (int i = 0; i < crispyMatrix.length; i++) {
            for (int j = 0; j < crispyMatrix[0].length; j++) {
                System.out.print(crispyMatrix[i][j] + " ");
            }
            System.out.println();
        }
        MatrixConsistent matrixConsistent = new MatrixConsistent();
        System.out.println(matrixConsistent.ifConsistent(crispyMatrix, crispyMatrix.length));
    }
}
