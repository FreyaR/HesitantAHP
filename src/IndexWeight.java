import java.util.Arrays;
import java.util.List;

public class IndexWeight {
    private double[][] computeGeometricMean(List<List<double[]>> matrix){
        int row = matrix.size();
        double[][] res = new double[row][4];
        for (int i = 0; i < res.length; i++) {
            Arrays.fill(res[i],1.0);
        }
        for (int i = 0; i < matrix.size(); i++) {
            List<double[]> rows = matrix.get(i);
            for (int j = 0; j < rows.size(); j++) {
                res[i][0] *= Math.pow(rows.get(j)[0], 1.0/3);
                res[i][1] *= Math.pow(rows.get(j)[1], 1.0/3);
                res[i][2] *= Math.pow(rows.get(j)[2], 1.0/3);
                res[i][3] *= Math.pow(rows.get(j)[3], 1.0/3);
            }
        }
        return res;
    }
    private double[] computeOperatorAdd(double[][] matrix){
        int row = matrix.length;
        int col = matrix[0].length;
        double[] res = new double[4];
        for (int i = 0; i < col; i++) {
            double temp = 0;
            for (int j = 0; j < row; j++) {
                temp = temp + matrix[j][i] + temp * matrix[j][i];
            }
            res[i] = temp;
        }

        return res;
    }


    public double[][] getWeightMatrix(List<List<double[]>> matrix){
        int row = matrix.size();
        double[][] computeGeometricMean = computeGeometricMean(matrix);
        double[] operatorAdd = computeOperatorAdd(computeGeometricMean);
        for (int i = 0; i < operatorAdd.length; i++) {
            operatorAdd[i] = 1 / operatorAdd[i];
        }
        double[][] res = new double[row][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < row; j++) {
                res[j][i] = computeGeometricMean[j][i] * operatorAdd[i];
            }
        }
        return res;
    }

    public double[] getNormalizedWeight(double[][] weightMatrix){
        int row = weightMatrix.length;
        double[] res = new double[row];
        for (int i = 0; i < row; i++) {
            res[i] = (weightMatrix[i][0] + 2 * weightMatrix[i][1] + 2 * weightMatrix[i][2] + weightMatrix[1][3]) / 6;
            System.out.println(res[i]);
        }
        double sum = 0;
        for (int i = 0; i < res.length; i++) {
            sum += res[i];
        }
        for (int i = 0; i < res.length; i++) {
            res[i] /= sum;
        }
        return res;
    }

    public static void main(String[] args) {
        ComparisonMatrix matrix = new ComparisonMatrix();
        String[][] linguisticTerms = new String[][]{
                {"EE","Between EHI And WHI","Between EHI And ELI"},
                {"Between ELI And WLI","EE","Between ELI And WLI"},
                {"Between EHI And ELI","Between EHI And WHI","EE"}};
        List<List<double[]>> fuzzyComparisonMatrix = matrix.getFuzzyComparisonMatrix(linguisticTerms);
        for (List<double[]> comparisonMatrix : fuzzyComparisonMatrix) {
            for (double[] doubles : comparisonMatrix) {
                System.out.print(Arrays.toString(doubles));
            }
            System.out.println();
        }

        IndexWeight indexWeight = new IndexWeight();

        double[][] geometricMean = indexWeight.computeGeometricMean(fuzzyComparisonMatrix);
        for (int i = 0; i < geometricMean.length; i++) {
            for (int j = 0; j < geometricMean[0].length; j++) {
                System.out.print(geometricMean[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        double[] operatorAdd = indexWeight.computeOperatorAdd(geometricMean);
        for (int i = 0; i < operatorAdd.length; i++) {
            System.out.print(operatorAdd[i] + " ");
        }
        System.out.println();
        System.out.println();
        double[][] weightMatrix = indexWeight.getWeightMatrix(fuzzyComparisonMatrix);
        for (int i = 0; i < weightMatrix.length; i++) {
            for (int j = 0; j < weightMatrix[0].length; j++) {
                System.out.print(weightMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
//    private

}
