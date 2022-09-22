import java.util.Scanner;

public class MatrixOperations {



    public static void readMatrix(float[][] mat) {
        // Procedure to read matrix from user input
        // I.S. mat is defined
        // F.S. mat is filled with values of m x n
        // LOCAL DICTIONARY
        float[][] temp;
        Scanner myObj = new Scanner(System.in);
        int m, n;
        int i, j;


        // ALGORITHM
        System.out.println("Enter row: ");
        String row = myObj.nextLine();
        m = Integer.parseInt(row);

        System.out.println("Enter col: ");
        String col = myObj.nextLine();
        n = Integer.parseInt(col);

        temp = new float[m][n];

        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                String element = myObj.nextLine();
                temp[i][j] = Float.parseFloat(element);
            }
        }

        copyMatrix(temp, mat);
    }

    public static void printMatrix(float[][] mat){
        // Procedure to print matrix
        // I.S. mat is defined
        // F.S. mat is printed to the screen
        // LOCAL DICTIONARY
        int i, j;


        // ALGORITHM
        for (i = 0; i < mat.length; i++) {
            for (j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isSquare(float[][] mat) {
        // Function to determine whether a matrix is a square matrix or not
        // Square matrix : m x n where m = n

        return mat.length == mat[0].length;
    }

    public static void copyMatrix(float[][] mat1, float[][] mat2) {
        // Procedure to copy a matrix content to another matrix
        // I.S. mat1 and mat2 are defined and with the same order and mat2 can be empty
        // F.S. mat1 is identical with mat2
        // LOCAL DICTIONARY
        int rowEff = mat1.length;
        int colEff = mat1[0].length;
        int i, j;
        // ALGORITHM
        for (i = 0; i < rowEff; i++) {
            for (j = 0; j < colEff; j++) {
                mat2[i][j] = mat1[i][j];
            }
        }

    }

    public static float determinanReduksiBaris(float[][] mat) {
        // Precondition: isSquare(mat) == true
        // Finding determinant with Row Reduction (Upper Triangle Matrix)
        // LOCAL DICTIONARY
        int rowEff = mat.length;
        int colEff = mat[0].length;
        int row = rowEff - 1;
        int col = colEff - 1;
        // temp is a temporary matrix to ignore the already zero-ed column
        float[][] temp = new float[row][col];
        int i, j;
        float konst;

        // ALGORITHM
        // Base
        // Return the determinant of a 2x2 matrix
        if (rowEff == 2) {
            return (mat[0][0] * mat[1][1]) - (mat[0][1] * mat[1][0]);
        }
        // Recurrent
        else {
            // Multiplying the lower row from with a factor konst
            // (first element of the n row / first element of the first row) of the top row
            for (i = 1; i < rowEff; i++) {
                konst = mat[i][0] / mat[0][0];
                for (j = 1; j < colEff; j++) {
                    // Multiplying and saving the element
                    temp[i - 1][j - 1] = mat[i][j] - konst * mat[0][j];
                }
            }
            return mat[0][0] * determinanReduksiBaris(temp);
        }
    }

}
