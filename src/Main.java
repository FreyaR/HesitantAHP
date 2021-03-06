import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ComparisonMatrix matrix = new ComparisonMatrix();
        MatrixConsistent matrixConsistent = new MatrixConsistent();
        IndexWeight indexWeight = new IndexWeight();
        DataSolve dataSolve = new DataSolve();
        Calculate calculate = new Calculate();
        //destructive
        String[][] linguisticTerms1 = new String[][]{
                {"EE","EE","ALI","ELI-WLI","ELI-WLI","ELI-WLI","VLI"},
                {"EE","EE","ALI","ELI-WLI","ELI-WLI","ELI-WLI","VLI"},
                {"AHI","AHI","EE","VHI","VHI","VHI","WHI"},
                {"EHI-WHI","EHI-WHI","VLI","EE","EE","EE","WLI"},
                {"EHI-WHI","EHI-WHI","VLI","EE","EE","EE","WLI"},
                {"EHI-WHI","EHI-WHI","VLI","EE","EE","EE","WLI"},
                {"VHI","VHI","WLI","WHI","WHI","WHI","EE"}};
        List<List<double[]>> fuzzyComparisonMatrix1 = matrix.getFuzzyComparisonMatrix(linguisticTerms1);
        for (List<double[]> comparisonMatrix : fuzzyComparisonMatrix1) {
            for (double[] doubles : comparisonMatrix) {
                System.out.print(Arrays.toString(doubles));
            }
            System.out.println();
        }
        double[][] crispyMatrix1 = matrix.getCrispyMatrix(fuzzyComparisonMatrix1);
        for (int i = 0; i < crispyMatrix1.length; i++) {
            for (int j = 0; j < crispyMatrix1[0].length; j++) {
                System.out.print(crispyMatrix1[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(matrixConsistent.ifConsistent(crispyMatrix1, crispyMatrix1.length));
        double[][] weightMatrix1 = indexWeight.getWeightMatrix(fuzzyComparisonMatrix1);
        for (int i = 0; i < weightMatrix1.length; i++) {
            for (int j = 0; j < weightMatrix1[0].length; j++) {
                System.out.print(weightMatrix1[i][j] + " ");
            }
            System.out.println();
        }
        double[] weight1 = indexWeight.getNormalizedWeight(weightMatrix1);
        for (double weight : weight1) {
            System.out.println(weight + " ");
        }
        System.out.println();
        //trace remove
        String[][] linguisticTerms2 = new String[][]{
                {"EE","VLI","VLI"},
                {"VHI","EE","EE"},
                {"VHI","EE","EE"},
        };

        List<List<double[]>> fuzzyComparisonMatrix2 = matrix.getFuzzyComparisonMatrix(linguisticTerms2);
        for (List<double[]> comparisonMatrix : fuzzyComparisonMatrix2) {
            for (double[] doubles : comparisonMatrix) {
                System.out.print(Arrays.toString(doubles));
            }
            System.out.println();
        }
        double[][] crispyMatrix2 = matrix.getCrispyMatrix(fuzzyComparisonMatrix2);
        for (int i = 0; i < crispyMatrix2.length; i++) {
            for (int j = 0; j < crispyMatrix2[0].length; j++) {
                System.out.print(crispyMatrix2[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(matrixConsistent.ifConsistent(crispyMatrix2, crispyMatrix2.length));
        double[][] weightMatrix2 = indexWeight.getWeightMatrix(fuzzyComparisonMatrix2);
        for (int i = 0; i < weightMatrix2.length; i++) {
            for (int j = 0; j < weightMatrix2[0].length; j++) {
                System.out.print(weightMatrix2[i][j] + " ");
            }
            System.out.println();
        }
        double[] weight2 = indexWeight.getNormalizedWeight(weightMatrix2);
        for (double weight : weight2) {
            System.out.println(weight + " ");
        }
        System.out.println();
        // code execution
        String[][] linguisticTerms3 = new String[][]{
                {"EE","AHI-VHI","VHI"},
                {"ALI-VLI","EE","EHI-EE"},
                {"VLI","ELI-EE","EE"},
        };

        List<List<double[]>> fuzzyComparisonMatrix3 = matrix.getFuzzyComparisonMatrix(linguisticTerms3);
        for (List<double[]> comparisonMatrix : fuzzyComparisonMatrix3) {
            for (double[] doubles : comparisonMatrix) {
                System.out.print(Arrays.toString(doubles));
            }
            System.out.println();
        }
        double[][] crispyMatrix3 = matrix.getCrispyMatrix(fuzzyComparisonMatrix3);
        for (int i = 0; i < crispyMatrix3.length; i++) {
            for (int j = 0; j < crispyMatrix3[0].length; j++) {
                System.out.print(crispyMatrix3[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(matrixConsistent.ifConsistent(crispyMatrix3, crispyMatrix3.length));

        double[][] weightMatrix3 = indexWeight.getWeightMatrix(fuzzyComparisonMatrix3);
        for (int i = 0; i < weightMatrix3.length; i++) {
            for (int j = 0; j < weightMatrix3[0].length; j++) {
                System.out.print(weightMatrix3[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        double[] weight3 = indexWeight.getNormalizedWeight(weightMatrix3);
        for (double weight : weight3) {
            System.out.println(weight + " ");
        }
        System.out.println();

        //PG
        String[][] linguisticTerms4 = new String[][]{
                {"EE","ELI-EE","ELI-WLI"},
                {"EHI-EE","EE","ELI-EE"},
                {"EHI-WHI","EHI-EE","EE"},
        };

        List<List<double[]>> fuzzyComparisonMatrix4 = matrix.getFuzzyComparisonMatrix(linguisticTerms4);
        for (List<double[]> comparisonMatrix : fuzzyComparisonMatrix4) {
            for (double[] doubles : comparisonMatrix) {
                System.out.print(Arrays.toString(doubles));
            }
            System.out.println();
        }
        double[][] crispyMatrix4 = matrix.getCrispyMatrix(fuzzyComparisonMatrix4);
        for (int i = 0; i < crispyMatrix4.length; i++) {
            for (int j = 0; j < crispyMatrix4[0].length; j++) {
                System.out.print(crispyMatrix4[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(matrixConsistent.ifConsistent(crispyMatrix4, crispyMatrix4.length));
        double[][] weightMatrix4 = indexWeight.getWeightMatrix(fuzzyComparisonMatrix4);
        for (int i = 0; i < weightMatrix4.length; i++) {
            for (int j = 0; j < weightMatrix4[0].length; j++) {
                System.out.printf("%.4f", weightMatrix4[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
        double[] pgWeight = indexWeight.getNormalizedWeight(weightMatrix4);
        for (double weight : pgWeight) {
            System.out.printf("%.4f",weight);
            System.out.print(" ");
        }
        System.out.println();

        //datasolve
        String[] s = new String[]{"FS","FS","SS-FS","SS","FS","SS","VS","FS","SW","SW","0.8","1","0.9"};
        double[][] envelopeVector = dataSolve.generateFuzzyEnvelopeVector(s);
        for (double[] num : envelopeVector) {
            for (double v : num) {
                System.out.printf("%.4f",v);
                System.out.print(" ");
            }
            System.out.println();
        }
        double[] crispyNumber = dataSolve.getCrispyNumber(envelopeVector);
        for (double v : crispyNumber) {
            System.out.printf("%.4f",v);
            System.out.print(" ");
        }
        System.out.println();
        //calculate data
        String[] s1 = {"FS", "FS", "SS-FS", "SS", "FS", "SS", "VS"};
        double[] crispyNumber1 = dataSolve.getCrispyNumber(dataSolve.generateFuzzyEnvelopeVector(s1));
        String[] s2 = {"FS","SW","SW"};
        double[] crispyNumber2 = dataSolve.getCrispyNumber(dataSolve.generateFuzzyEnvelopeVector(s2));
        String[] s3 = {"0.8","1","0.9"};
        double[] crispyNumber3 = dataSolve.getCrispyNumber(dataSolve.generateFuzzyEnvelopeVector(s3));
        System.out.println("pg1_hfwa:  "+calculate.HFWA(crispyNumber1, weight1));
        System.out.println("pg1_hgwa:  "+calculate.HGWA(crispyNumber1, weight1));

        System.out.println("pg2_hfwa:  "+calculate.HFWA(crispyNumber2, weight2));
        System.out.println("pg2_hgwa:  "+calculate.HGWA(crispyNumber2, weight2));

        System.out.println("pg3_hfwa:  "+calculate.HFWA(crispyNumber3, weight3));
        System.out.println("pg3_hgwa:  "+calculate.HGWA(crispyNumber3, weight3));

        double[] pgData_hfwa = new double[3];
        pgData_hfwa[0] = calculate.HFWA(crispyNumber1,weight1);
        pgData_hfwa[1] = calculate.HFWA(crispyNumber2,weight2);
        pgData_hfwa[2] = calculate.HFWA(crispyNumber3,weight3);

        double[] pgData_hgwa = new double[3];
        pgData_hgwa[0] = calculate.HGWA(crispyNumber1,weight1);
        pgData_hgwa[1] = calculate.HGWA(crispyNumber2,weight2);
        pgData_hgwa[2] = calculate.HGWA(crispyNumber3,weight3);

        System.out.println("atom_score_hfwa:  "+ calculate.HFWA(pgData_hfwa,pgWeight));
        System.out.println("atom_score_hgwa:  "+ calculate.HGWA(pgData_hgwa,pgWeight));



    }
}