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

//    private double[][] getWeightMatrix(double[][] matrix){
//
//    }
//
//    private
}
