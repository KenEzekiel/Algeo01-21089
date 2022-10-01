package src;

import java.io.File;
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
        this.row = r;
        this.col = c;
        this.M = new double[r][c];
    }


    public static Matrix inputMatrix() {

        Scanner myObj = new Scanner(System.in);
        int choice;
        System.out.println("Masukkan jumlah baris matriks: ");
        String mInt = myObj.nextLine();
        System.out.println("Masukkan jumlah kolom matriks: ");
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
            case 1 :
                matrix = inputMatrixFromKeyboard();
                break;
            case 2 :
                System.out.println("input file name: ");
                File file = new File(myObj.nextLine());
                matrix = InputOutput.fileToMatrix(file);
                break;
        }
        return matrix;
    }

    public static Matrix inputSquareMatrix() {

        Scanner myObj = new Scanner(System.in);
        int choice;
        System.out.println("Masukkan n: ");
        String nInt = myObj.nextLine();

        int n = Integer.parseInt(nInt);

        Matrix matrix = new Matrix(n, n);

        System.out.println("Select matrix input");
        System.out.print("""
		        1. Keyboard
		        2. File
		        """);
        System.out.println("Masukan pilihan: ");
        String strchoice = myObj.nextLine();
        choice = Integer.parseInt(strchoice);

        switch (choice) {
            case 1 :
                matrix = inputMatrixFromKeyboard();
                boolean check = (matrix.getRow() == n) && (matrix.getCol() == n);
                while (!check) {
                    System.out.println("Masukan tidak valid");
                    matrix = inputMatrixFromKeyboard();
                    check = (matrix.getRow() == n) && (matrix.getCol() == n);
                }
                break;
            case 2 :
                System.out.println("input file name: ");
                File file = new File(myObj.nextLine());
                matrix = InputOutput.fileToMatrix(file);
                break;
        }
        return matrix;
    }
    public static Matrix inputMatrixFromKeyboard() {
        boolean loop = true;
        Scanner in = new Scanner(System.in);
        String data;
        String out = "";

        while (loop) {
            data = in.nextLine();
            if (data.isEmpty()) {
                loop = false;
            } else {
                out += data + "\n";
            }
        }
        return InputOutput.stringToMatrix(out);
    }


    public final void inputDataFromDouble(double[][] data) {
        // Function to set the data of a matrix
        this.row = data.length;
        this.col = data.length > 0 ? data[0].length : 0;
        this.M = data;
    }

    public static void readMatrixPerLine(Matrix mat, int m, int n) {
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

    public static Matrix readMatrixPerRow() {
        int n = 0;
        int m = 0;
        double[][] newmat = new double[n][n];
        Scanner in = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            n++;
            String[] data = in.nextLine().split(" ");
            double[] numbers = new double[data.length];
            m = data.length;
            System.out.println(m);
            if (m == 1) {
                System.out.println("Kosong");
                loop = false;
                n--;
                break;
            }
            for (int i = 0; i < data.length; i++) {
                    numbers[i] = Double.parseDouble(data[i]);
            }
            newmat = new double[n][data.length];
            newmat[n-1] = numbers;
            System.out.println(Arrays.toString(numbers));
        }
        Matrix hasil = new Matrix(n, m);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                hasil.M[i][j] = newmat[i][j];
            }
        }
        return hasil;
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
        // M2 merupakan hasil copy matrix M1

        int rowEff = mat1.getRow();
        int colEff = mat1.getCol();
        int i, j;

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

    public static Matrix Multiply(Matrix A, Matrix B) {
        /*LOCAL DICTIONARY*/
        Matrix C;
        int i, j, k;
        /*ALGORITHM*/
        C = new Matrix(A.getRow(), B.getCol());

        for (i = 0; i < C.getRow(); i++)
        {
            for (j = 0; j < C.getCol(); j++)
            {
                C.M[i][j] = 0;
                for (k = 0; k < A.getCol(); k++)
                {
                    C.M[i][j] += A.M[i][k] * B.M[k][j];
                }
            }
        }
        return C;
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

    public static void changeZeroval(Matrix m) {
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

    public int Size() {
        /* ALGORITMA */
        return (this.row * this.col);
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

    /* Creates identity matrix based on the order of matrix parameter (orderOfMatrix)*/
    public static Matrix createIdentityMatrix(int orderOfMatrix) {
        int i, j;
        Matrix identityMatrix;

        identityMatrix = new Matrix(orderOfMatrix, orderOfMatrix);

        for (i = 0; i < orderOfMatrix; i++) {
            for (j = 0; j < orderOfMatrix; j++) {
                if (i == j) {
                    identityMatrix.setELMT(i, j, 1);
                }
                else {
                    identityMatrix.setELMT(i, j, 0);
                }
            }
        }

        return identityMatrix;
    }

    public static Matrix transpose(Matrix M) {
        /* KAMUS LOKAL */
        int i, j;
        Matrix Mout = new Matrix(M.getRow(), M.getCol());
        /* ALGORITMA */
        for (i = 0; i < M.getRow(); i++) {
          for (j = 0; j < M.getCol(); j++) {
            Mout.setELMT(j, i, M.getELMT(i, j));
          }
        }
        return Mout;
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

    /* Returns the determinant of parameter Matrix M using confactor expansion method */
    public static double findDeterminantUsingCofactorExpansion(Matrix M) {
        // Precondition: isSquare(M) == true
        // Finding determinant with cofactor expansion method
        // DICTIONARY
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
                        if (j < i) {
                            submatrix.setELMT(sub_row, sub_col, M.getELMT(j, k));
                            sub_col += 1;
                        }
                        else if (j > i) {
                            submatrix.setELMT(sub_row - 1, sub_col, M.getELMT(j, k));
                            sub_col += 1;
                        }
                    }
                    sub_row += 1;
                }
                determinant += Math.pow(-1, i) * M.getELMT(i, 0) * findDeterminantUsingCofactorExpansion(submatrix);
            }
            return determinant;
        }
    }

    /* Returns the inverse matrix of parameter Matrix M with the formula (1/det(M)) * Adjugate(M) */
    public static Matrix findInverseUsingAdjugate(Matrix M) {
        // DICTIONARY
        Matrix adjugate, inverse;
        double determinant;
        int i, j;

        // ALGORITHM
        determinant = findDeterminantUsingCofactorExpansion(M);
        adjugate = transpose(findCofactorMatrix(M));
        inverse = new Matrix(M.getRow(), M.getCol());

        for (i = 0; i < M.getRow(); i++) {
            for (j = 0; j < M.getCol(); j++) {
                inverse.setELMT(i, j, (1 / determinant) * adjugate.getELMT(i, j));
            }
        }

        return inverse;
    }

    /* Returns the cofactor matrix of the parameter Matrix M */
    public static Matrix findCofactorMatrix(Matrix M) {
        // DICTIONARY
        Matrix submatrix, cofactorMatrix;
        int i, j, k, l;

        // ALGORITHM
        cofactorMatrix = new Matrix(M.getRow(), M.getCol());

        for (i = 0; i < M.getRow(); i++) {
            for (j = 0; j < M.getCol(); j++) {

                submatrix = new Matrix(M.getRow() - 1, M.getCol() - 1);
                for (k = 0; k < M.getRow(); k++) {

                    for (l = 0; l < M.getCol(); l++) {
                        if (k < i && l < j) {
                            submatrix.setELMT(k, l, M.getELMT(k, l));
                        }
                        else if (k < i && l > j) {
                            submatrix.setELMT(k, l - 1, M.getELMT(k, l));
                        }
                        else if (k > i && l < j) {
                            submatrix.setELMT(k - 1, l, M.getELMT(k, l));
                        }
                        else if (k > i && l > j) {
                            submatrix.setELMT(k - 1, l - 1, M.getELMT(k, l));
                        }
                    }
                }

                cofactorMatrix.setELMT(i, j, Math.pow(-1, (i + j)) * findDeterminantUsingCofactorExpansion(submatrix));
            }
        }

        return cofactorMatrix;
    }

    public void setIdentitas() {
        // Mengubah matriks menjadi matriks identitas
        for (int i = 0; i < row; i++) {
          for (int j = 0; j < col; j++) {
            if (i == j) {
              this.M[i][j] = 1.0f;
            } else {
              this.M[i][j] = 0.0f;
            }
          }
        }
    }

    public void divRow(int i, double k) 
    // membagi elemen pada row dgn konstanta k
    {
        /* KAMUS LOKAL */
        int j;
        /* ALGORITMA */
        for (j = 0; j < col; j++) {
          this.M[i][j] /= k;
        }
    }

    public void operationRow(int i1, int i2, double k) {
        /* KAMUS LOKAL */
        int j;
        /* ALGORITMA */
        for (j = 0; j < col; j++) {
          this.M[i1][j] -= k * this.M[i2][j];
        }
      }

  public static Matrix InverseDgnGauss(Matrix M) {
    /* KAMUS LOKAL */
    int i, j, k;
    Matrix Mkiri = new Matrix(M.getRow(), M.getCol());
    Matrix Mkanan = new Matrix(M.getRow(), M.getCol());

    /* ALGORITMA */
    copyMatrix(M, Mkiri);
    copyMatrix(M, Mkanan);
    // Matrix kanan dijadikan matrix identitas
    Mkanan.setIdentitas();
    for (i = 0; i < Mkiri.getRow(); i++) {
      // Melakukan pertukaran jika elemen diagonal ada yang 0
      if (Mkiri.getELMT(i, i) == 0) {
        boolean check = true;
        k = i + 1;
        while (k < Mkiri.getRow() && check) {
          if (Mkiri.getELMT(k, i) != 0) {
            Mkanan.switchRow(i, k);
            Mkiri.switchRow(i, k);
            check = false;
          }
        }
      }

      // Melakukan pembagian koefisien jika pivot != 1
      if (Mkiri.getELMT(i, i) != 1) {
        Mkanan.divRow(i, Mkiri.getELMT(i, i));
        Mkiri.divRow(i, Mkiri.getELMT(i, i));
      }
      // Melakukan OBE pada pivot
      for (j = 0; j < Mkiri.getRow(); j++) {
        if (Mkiri.getELMT(j, i) != 0 && i != j) {
          Mkanan.operationRow(j, i, Mkiri.getELMT(j, i));
          Mkiri.operationRow(j, i, Mkiri.getELMT(j, i));
        }
      }
    }
    // Menghilangkan -0.0
    Matrix.changeZeroval(Mkanan);
    return Mkanan;
  }

  public static Matrix findInverse(Matrix M) {

    return M;
}

public static Matrix inverseSPL(Matrix xVar) {
    return null;
}
      
}
