public class MatrixConsistent {
    double[] RI = new double[]{0,0,0.52,0.89,1.12,1.26,1.36,1.41,1.46,1.49};
    private double[] getNormalizedWeight(double[][] matrix){
        int row = matrix.length;
        int col = matrix[0].length;
        double[] res = new double[row];
        for (int i = 0; i < row; i++) {
            double weight = 1;
            for (int j = 0; j < col; j++) {
                weight *= matrix[i][j];
            }
            weight = Math.pow(weight,1.0/col);
            res[i] = weight;
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
    private double[] mergeCol(double[][] matrix){
        int row = matrix.length;
        int col = matrix[0].length;
        double[] res = new double[col];
        for (int i = 0; i < col; i++) {
            double sum = 0;
            for (int j = 0; j < row; j++) {
                sum += matrix[j][i];
            }
            res[i] = sum;
        }
        return res;
    }
    private double getMaxEigenNumber(double[] normalizedWeightMatrix, double[] mergeColMatrix){
        double res = 0;
        for (int i = 0; i < mergeColMatrix.length; i++) {
            res += normalizedWeightMatrix[i] * mergeColMatrix[i];
        }
        return res;
    }
    public boolean ifConsistent(double[][] matrix, int n){
        double[] normalizedWeightMatrix = getNormalizedWeight(matrix);
        double[] mergeColMatrix = mergeCol(matrix);
        double maxEigenNumber = getMaxEigenNumber(normalizedWeightMatrix, mergeColMatrix);
        double CI = (maxEigenNumber - n) / (n - 1);
        double CR = CI / RI[n-1];
        if (CR <= 0.1){
            return true;
        }else {
            return false;
        }
    }
    public static void main(String[] args) {
        MatrixConsistent matrixConsistent = new MatrixConsistent();
        double[][] matrix = new double[][]{{1, 2, 6},{1.0/2.0, 1, 4},{1.0/6.0, 1.0/4.0, 1}};

        double[] normalizedWeight = matrixConsistent.getNormalizedWeight(matrix);
        for (int i = 0; i < normalizedWeight.length; i++) {
            System.out.println(normalizedWeight[i]);
        }
        double[] mergeCol = matrixConsistent.mergeCol(matrix);
        for (int i = 0; i < mergeCol.length; i++) {
            System.out.println(mergeCol[i]);
        }
        double maxEigenNumber = matrixConsistent.getMaxEigenNumber(normalizedWeight, mergeCol);
        System.out.println(matrixConsistent.getMaxEigenNumber(normalizedWeight, mergeCol));
        System.out.println(matrixConsistent.ifConsistent(matrix, 3));

    }
}
