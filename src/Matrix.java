package src;

import java.util.*;

public class Matrix {
    /* -- ATRIBUT -- */
    static Scanner input = new Scanner(System.in);
    double[][] M;
    int row;
    int col;

    /* -- KONSTRUKTOR -- */
    public Matrix(int r, int c) {
        this.row = r;
        this.col = c;
        this.M = new double[r][c];
    }        
    /* ======== GETTER ======== */
    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public double getELMT(int i, int j) {
        return (this.M[i][j]);
    }

    /* ======== SETTER ======== */
    public void setELMT(int i, int j, double value) {
        this.M[i][j] = value;
    }

    public void setIdentitas() {
        // Mengubah matriks menjadi matriks identitas
        for (int i = 0; i < row; i++) {
          for (int j = 0; j < col; j++) {
            if (i == j) {
              this.M[i][j] = 1.0f;
            } 
            else {
              this.M[i][j] = 0.0f;
            }
          }
        }
    }

    /* -- IO -- */
    public void readMatrix() {
        /* KAMUS LOKAL */
        int i, j;
        /* ALGORITMA */
        for (i = 0; i < this.row; i++) {
          for (j = 0; j < this.col; j++) {
           this.M[i][j] = input.nextDouble();
          }
        }
    }

    public void readMatrixRegresi(int N) {
        /* KAMUS LOKAL */
        int i;
        /* ALGORITMA */
        for (i = 0; i < N; i++) {
          System.out.print(">X" + (i + 1) + ": ");
          this.M[0][i] = input.nextDouble();
        }
    }

    public void displayMatrix() {
        int i, j;

        for (i = 0; i < this.row; i++) {
          for (j = 0; j < this.col; j++) {
            System.out.print(M[i][j]);
            if (j != this.col - 1) {
              System.out.print(" ");
            }
          }
          System.out.println();
        }
        System.out.println();
    }

    public final void inputDataFromDouble(double[][] data) {
        // Function to set the data of a matrix
        this.row = data.length;
        this.col = data.length > 0 ? data[0].length : 0;
        this.M = data;
    }

    /* -- OPERATIONS -- */
    public int Size() {
        return (this.row * this.col);
    }

    public void operationRow(int i1, int i2, double k) {
        int j;
        for (j = 0; j < col; j++) {
          this.M[i1][j] -= k * this.M[i2][j];
        }
    }

    public void divRow(int i, double k) 
    // membagi elemen pada row dgn konstanta k
    {
        int j;
        for (j = 0; j < col; j++) {
          this.M[i][j] /= k;
        }
    }

    public void switchRow(int i1, int i2) {
        int j;
        double temp;

        for (j = 0; j < col; j++) {
          temp = this.M[i1][j];
          this.M[i1][j] = this.M[i2][j];
          this.M[i2][j] = temp;
        }
    }

    public void copyMatrix_altern(Matrix outM) {

        int i, j;

        for (i = 0; i < outM.getRow(); i++) {
          for (j = 0; j < outM.getCol(); j++) {
            outM.setELMT(i, j, this.getELMT(i, j));
          }
        }
      }

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

    public static void changeZeroval(Matrix m) {
        for (int i = 0; i < m.row; i++) {
            for (int j = 0; j < m.col; j++) {
                if (m.M[i][j] == -0.0) {
                m.M[i][j] = Math.abs(m.M[i][j]);
                }
            }
        }
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

    public static Matrix createMAug(Matrix koef, Matrix cons) {

        Matrix mAug = new Matrix(koef.getRow(), koef.getCol() + 1);
    
        for (int i = 0; i < koef.getRow(); i++) {
          for (int j = 0; j < mAug.getCol() - 1; j++) {
            mAug.M[i][j] = mAug.M[i][j];
          }
        }
        for (int k = 0; k < mAug.getRow(); k++) {
            mAug.M[k][mAug.getCol() - 1] = mAug.M[k][0];
        }
        return mAug;
    }
    public static Matrix getMConst(Matrix m) {

        Matrix mConst = new Matrix(m.row, 1);
        int i;

        for (i = 0; i < mConst.row; i++) {
            mConst.M[i][0] = m.M[i][m.col - 1];
        }
        return mConst;
    }
    public static Matrix Multiply(Matrix A, Matrix B) {
        /*LOCAL DICTIONARY*/
        Matrix C;
        int i, j, k;
        /*ALGORITHM*/
        C = new Matrix(A.getRow(), B.getCol());

        for (i = 0; i < C.getRow(); i++){
            for (j = 0; j < C.getCol(); j++){
                C.M[i][j] = 0;
                for (k = 0; k < A.getCol(); k++){
                    C.M[i][j] += C.M[i][k] * C.M[k][j];
                }
            }
        }
        return C;
    }

    public Matrix multiply_altern(Matrix m1, Matrix m2) {
        /* KAMUS LOKAL */
        int i, j, k = 0;
        Matrix mHasil = new Matrix(m1.getRow(), m2.getCol());
        double temp = 0;
        /* ALGORITMA */
        for (i = 0; i < m1.getRow(); i++) {
          for (j = 0; j < m2.getCol(); j++) {
            temp = 0;
            for (k = 0; k < m1.getCol(); k++) {
              temp += m1.getELMT(i, k) * m2.getELMT(k, j);
            }
            mHasil.setELMT(i, j, temp);
          }
        }
        return mHasil;
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

    public void rowOperations(int i1, int i2, double k) {
        int j;

        for (j = 0; j < col; j++) {
            this.M[i1][j] -= k * this.M[i2][j];
        }
    }

    /* -- BOOL FUNCTION CHECKING -- */
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

    /* DETERMINAN */
    public static double determinanReduksiBaris(Matrix mat) {
        int rowEff = mat.getRow();
        int colEff = mat.getCol();
        int row = rowEff - 1;
        int col = colEff - 1;

        Matrix temp = new Matrix(row, col);
        int i, j;
        double konst;

        // Base
        // Matrix 2x2
        if (rowEff == 2) {
            return (mat.M[0][0] * mat.M[1][1]) - (mat.M[0][1] * mat.M[1][0]);
        }
        else {
            for (i = 1; i < rowEff; i++) {
                konst = mat.M[i][0] / mat.M[0][0];
                for (j = 1; j < colEff; j++) {
                    mat.M[i - 1][j - 1] = mat.M[i][j] - konst * mat.M[0][j];
                }
            }
            return mat.M[0][0] * determinanReduksiBaris(temp);
        }
    }

    public static double detKofaktor(Matrix M) {
        int i, j, k;
        int iCol, iRow;
        double hasil;
        Matrix mKof = new Matrix(M.getRow() - 1, M.getCol() - 1);

        hasil = 0;

        if (M.Size() == 1) {
          // Basis untuk matrix 1x1
          hasil = M.getELMT(0, 0);
        } else if (M.Size() == 4) {
          // Basis untuk matrix 2x2;
          hasil = (M.getELMT(0, 0) * M.getELMT(1, 1)) - (M.getELMT(0, 1) * M.getELMT(1, 0));
        } else {
          // submatrix kofaktor
          for (k = 0; k < M.getCol(); k++) {
            iRow = 0;
            for (i = 1; i < M.getRow(); i++) {
              iCol = 0;
              for (j = 0; j < M.getCol(); j++) {
                if (j != k) {
                  mKof.setELMT(iRow, iCol, M.getELMT(i, j));
                  iCol++;
                }
              }
              iRow++;
            }
            // urutan + - kofaktor
            if (k % 2 == 0) {
              hasil += M.getELMT(0, k) * detKofaktor(mKof);
            } else {
              hasil += -1 * M.getELMT(0, k) * detKofaktor(mKof);
            }
          }
        }
        return hasil;
    }

    /* INVERS */
    public static Matrix InverseDgnGauss(Matrix M) {
        int i, j, k;
        Matrix Mkiri = new Matrix(M.getRow(), M.getCol());
        Matrix Mkanan = new Matrix(M.getRow(), M.getCol());
    
        M.copyMatrix_altern(Mkiri);
        M.copyMatrix_altern(Mkanan);

        // Matrix kanan matrix identitas
        Mkanan.setIdentitas();
        for (i = 0; i < Mkiri.getRow(); i++) {
          // pertukaran jika elemen diagonal ada yang 0
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
    
          if (Mkiri.getELMT(i, i) != 1) {
            Mkanan.divRow(i, Mkiri.getELMT(i, i));
            Mkiri.divRow(i, Mkiri.getELMT(i, i));
          }

          // OBE pada pivot
          for (j = 0; j < Mkiri.getRow(); j++) {
            if (Mkiri.getELMT(j, i) != 0 && i != j) {
              Mkanan.operationRow(j, i, Mkiri.getELMT(j, i));
              Mkiri.operationRow(j, i, Mkiri.getELMT(j, i));
            }
          }
        }

        Matrix.changeZeroval(Mkanan);
        return Mkanan;
    }

    /* KOFAKTOR */
    public static Matrix findCofactorMatrix(Matrix M) {

        Matrix submatrix, cofactorMatrix;
        int i, j, k, l;

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
                cofactorMatrix.setELMT(i, j, Math.pow(-1, (i + j)) * detKofaktor(submatrix));
            }
        }
        return cofactorMatrix;
    }

    public static Matrix findInverseUsingAdjugate(Matrix M) {
        Matrix adjugate, inverse;
        double determinant;
        int i, j;

        determinant = detKofaktor(M);
        adjugate = transpose(findCofactorMatrix(M));
        inverse = new Matrix(M.getRow(), M.getCol());

        for (i = 0; i < M.getRow(); i++) {
            for (j = 0; j < M.getCol(); j++) {
                inverse.setELMT(i, j, (1 / determinant) * adjugate.getELMT(i, j));
            }
        }
        return inverse;
    }
}