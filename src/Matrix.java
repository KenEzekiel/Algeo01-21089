package src;

import java.util.*;
import java.lang.Math;
/*import java.io.*; */

public class Matrix {

    /*deklarasi row col*/
    static Scanner input = new Scanner(System.in);
    double[][] M;
    int row;
    int col;

    /* Konstruktor */
    public Matrix(int r, int c) {
        this.M = new double[r][c];
        this.row = r;
        this.col = c;
    }

    public static Matrix inputMatrix() {

        Scanner myObj = new Scanner(System.in);
        int choice;
        System.out.println("Masukkan baris matriks: ");
        String mInt = myObj.nextLine();
        System.out.println("Masukkan kolom matriks: ");
        String nInt = myObj.nextLine();

        int m = Integer.parseInt(mInt);
        int n = Integer.parseInt(nInt);

        Matrix matrix = new Matrix(m, n);

        System.out.println("Select matrix input");
        System.out.print("""
		        1. Keyboard
		        2. File
		        """);
        System.out.println("Masukan pilihan: ");
        String strchoice = myObj.nextLine();
        choice = Integer.parseInt(strchoice);

        switch (choice) {
            case 1 -> readMatrix(matrix, m, n);
            case 2 -> ReadFromFile.main(matrix, m, n);
        }
        return matrix;
    }

    public static void readMatrix(Matrix mat, int m, int n) {
        // Procedure to read matrix from user input
        // I.S. mat is defined
        // F.S. mat is filled with values of m x n
        // LOCAL DICTIONARY
        Matrix temp;
        Scanner myObj = new Scanner(System.in);
        int i, j;

        // ALGORITHM

        temp = new Matrix(m, n);

        System.out.println("Enter matrix: ");
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                String element = myObj.nextLine();
                temp.M[i][j] = Double.parseDouble(element);
            }
        }

        copyMatrix(temp, mat);
    }

    public static void printMatrix(Matrix mat){
        // Procedure to print matrix
        // I.S. mat is defined
        // F.S. mat is printed to the screen
        // LOCAL DICTIONARY
        int i, j;

        // ALGORITHM
        for (i = 0; i < mat.getRow(); i++) {
            for (j = 0; j < mat.getCol(); j++) {
                System.out.print(mat.M[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isSquare(Matrix mat) {
        // Function to determine whether a matrix is a square matrix or not
        // Square matrix : m x n where m = n

        return mat.getRow() == mat.getCol();
    }

    public static void copyMatrix(Matrix mat1, Matrix mat2) {
        // Procedure to copy a matrix content to another matrix
        // I.S. mat1 and mat2 are defined and with the same order and mat2 can be empty
        // F.S. mat1 is identical with mat2
        // LOCAL DICTIONARY
        int rowEff = mat1.getRow();
        int colEff = mat1.getCol();
        int i, j;
        // ALGORITHM
        for (i = 0; i < rowEff; i++) {
            for (j = 0; j < colEff; j++) {
                mat2.M[i][j] = mat1.M[i][j];
            }
        }

    }
    
    /* GETTER */
    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public double getELMT(int i, int j) {
        return (this.M[i][j]);
    }
    
    /* OPERANDS */
    public void switchRow(int i1, int i2) {
        /* KAMUS LOKAL */
        int j;
        double temp;
        /* ALGORITMA */
        for (j = 0; j < col; j++) {
          temp = this.M[i1][j];
          this.M[i1][j] = this.M[i2][j];
          this.M[i2][j] = temp;
        }
    }

    /* SETTER */
    public void setELMT(int i, int j, double value) {
        this.M[i][j] = value;
    }

    public void rowOperations(int i1, int i2, double k) {
        int j;

        for (j = 0; j < col; j++) {
        this.M[i1][j] -= k * this.M[i2][j];
        }
    }

    public static void changeZerovalue(Matrix m) {
        for (int i = 0; i < m.row; i++) {
        for (int j = 0; j < m.col; j++) {
            if (m.M[i][j] == -0.0) {
            m.M[i][j] = Math.abs(m.M[i][j]);
            }
        }
        }
    }
    
    /* Mengecek apakah elemen pada diagonal M bernilai 1 */
    public static boolean isDiagonalSatu(Matrix M) {

        int i;
        boolean check = true;

        i = 0;
        while (i < M.getRow() && check) {
            if (M.getELMT(i, i) != 1) {
                check = false;
            }
            i++;
        }
        return check;
    }

    /* Mengecek apakah semua elemen pada suatu baris bernilai 0 */
    public static boolean isRowZero(Matrix M, int row) {
        int j = 0;
        boolean check = true;

        while (j < M.getCol() - 1 && check) {
          if (M.getELMT(row, j) != 0) {
            check = false;
          }
          j++;
        }
        return check;
    }

    /* Mengecek apakah suatu baris kosong */
    public static boolean isRowEmpty(Matrix m, int i) {
        int count = 0;
        int idx = 0;

        while (count == 0 && idx < m.col) {
        if (m.M[i][idx] != 0) {
            count++;
        }
        idx++;
        }
        return (count == 0);
    }

    /* Mendapatkan pivot Matrix yang bernilai 1 */
    public static int getLeadingOne(Matrix M, int row) {
        /* KAMUS */
        int j = 0;
        int idxLead = -1;
        boolean found = false;

        // Iterasi mencari leading one di tiap baris
        while (j < M.getCol() && (found == false)) {
        if (M.getELMT(row, j) == 1) {
            idxLead = j;
            found = true;
        }
        j++;
        }
        return idxLead;
    }

    // Fungsi untuk menghasilkan array pecahan sebuah parametrik, contoh 1.00t
    // dipecah menjadi [1.00, t]
    public static String[] stripNonDigits(String x) {
        final String[] out = new String[2];

        final StringBuilder sb1 = new StringBuilder(x.length());
        final StringBuilder sb2 = new StringBuilder(x.length());

        for (int i = 0; i < x.length(); i++) {
            final char c = x.charAt(i);
            // Menggunakan ASCII value
            // Elemen yang diterima: ".", "-", dan angka
            if ((c == 45) || (c == 46) || (c > 47 && c < 58)) {
                out[0] = (sb1.append(c)).toString();
            }
            // Elemen yang diterima: semua huruf kecil
            if ((c > 96) && (c < 123)) {
                out[1] = (sb2.append(c)).toString();
            }
        }
        return (out);
    }

    /* Membentuk Matrix Koefisien */
    public static Matrix getMKoef(Matrix m) {

        Matrix mKoef = new Matrix(m.row, m.col - 1);
        int i, j;

        for (i = 0; i < mKoef.row; i++) {
            for (j = 0; j < mKoef.col; j++) {
                mKoef.M[i][j] = m.M[i][j];
        }
    }
    return mKoef;
  }

    /* Membentuk Matrix Konstanta */
    public static Matrix getMConst(Matrix m) {

        Matrix mConst = new Matrix(m.row, 1);
        int i;

        for (i = 0; i < mConst.row; i++) {
          mConst.M[i][0] = m.M[i][m.col - 1];
        }
        return mConst;
    }

    /* Membentuk Matrix Augmented */
    public static Matrix createMAug(Matrix koef, Matrix cons) {

    Matrix mAug = new Matrix(koef.getRow(), koef.getCol() + 1);

    for (int i = 0; i < koef.getRow(); i++) {
      for (int j = 0; j < mAug.getCol() - 1; j++) {
        mAug.M[i][j] = koef.M[i][j];
      }
    }
    for (int k = 0; k < mAug.getRow(); k++) {
        mAug.M[k][mAug.getCol() - 1] = cons.M[k][0];
    }
    return mAug;
  }

    /* Memindahkan baris kosong ke bawah*/
    public static void switchRowEmpty(Matrix m) {
        for (int i = 0; i < m.getRow(); i++) {
          if (isRowEmpty(m, i)) {
            for (int j = i + 1; j < m.getRow(); j++) {
              if (!isRowEmpty(m, j)) {
                m.switchRow(i, j);
              }
            }
          }
        }
    }

    /* Mengecheck apakah elemen dibawah elemen (i, j) kosong */
    public static boolean isUnderEmpty(Matrix m, int i, int j) {
        int count, iRow;
        count = 0;
        iRow = i + 1;
        while (count == 0 && iRow < m.getRow()) {
        if (m.M[iRow][j] != 0) {
            count++;
        }
        iRow++;
        }
        return (count == 0);
    }

    public static double determinanReduksiBaris(Matrix mat) {
        // Precondition: isSquare(mat) == true
        // Finding determinant with Row Reduction (Upper Triangle Matrix)
        // LOCAL DICTIONARY
        int rowEff = mat.getRow();
        int colEff = mat.getCol();
        int row = rowEff - 1;
        int col = colEff - 1;
        // temp is a temporary matrix to ignore the already zero-ed column
        Matrix temp = new Matrix(row, col);
        int i, j;
        double konst;

        // ALGORITHM
        // Base
        // Return the determinant of a 2x2 matrix
        if (rowEff == 2) {
            return (mat.M[0][0] * mat.M[1][1]) - (mat.M[0][1] * mat.M[1][0]);
        }
        // Recurrent
        else {
            // Multiplying the lower row from with a factor konst
            // (first element of the n row / first element of the first row) of the top row
            for (i = 1; i < rowEff; i++) {
                konst = mat.M[i][0] / mat.M[0][0];
                for (j = 1; j < colEff; j++) {
                    // Multiplying and saving the element
                    temp.M[i - 1][j - 1] = mat.M[i][j] - konst * mat.M[0][j];
                }
            }
            return mat.M[0][0] * determinanReduksiBaris(temp);
        }
    }

    public static double determinantUsingCofactorExpansion(Matrix M) {
        // Precondition: isSquare(M) == true
        // Finding determinant with cofactor expansion method
        // LOCAL DICTIONARY
        double determinant;
        Matrix submatrix;
        int i, j, k, sub_row, sub_col;

        // ALGORITHM
        if (M.getRow() == 1) {
            return M.getELMT(0, 0);
        }
        else {
            determinant = 0;

            for (i = 0; i < M.getRow(); i++) {
                submatrix = new Matrix(M.getRow() - 1, M.getCol() - 1);

                sub_row = 0;
                for (j = 0; j < M.getRow(); j++) {

                    sub_col = 0;
                    for (k = 1; k < M.getCol(); k++) {
                        if (j != i) {
                            if (j < i) {
                                submatrix.setELMT(sub_row, sub_col, M.getELMT(j, k));
                                sub_col += 1
                            }
                            else {
                                submatrix.setELMT(sub_row - 1, sub_col, M.getELMT(j, k));
                                sub_col += 1
                            }
                        }
                    }
                    sub_row += 1;
                }
                determinant += Math.pow(-1, i) * M.getELMT(i, 0) * determinantUsingCofactorExpansion(submatrix);
            }
            return determinant;
        }
    }

    // Finds the determinant of matrix using Gauss-Jordan Elimination
    public static Matrix findInverse(Matrix M) {



    }

}