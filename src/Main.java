import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ComparisonMatrix matrix = new ComparisonMatrix();
        String[][] linguisticTerms = new String[][]{
                {"EE","EE","ALI","ELI-WLI","ELI-WLI","ELI-WLI","VLI"},
                {"EE","EE","ALI","ELI-WLI","ELI-WLI","ELI-WLI","VLI"},
                {"AHI","AHI","EE","ESLI","ESLI","ESLI","ELI"},
                {"EHI-WHI","EHI-WHI","ESHI","EE","EE","EE","WLI"},
                {"EHI-WHI","EHI-WHI","ESHI","EE","EE","EE","WLI"},
                {"EHI-WHI","EHI-WHI","ESHI","EE","EE","EE","WLI"},
                {"VHI","VHI","EHI","WHI","WHI","WHI","EE"}};
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
