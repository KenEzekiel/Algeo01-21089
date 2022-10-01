package src;

import java.io.File;
import java.util.Scanner;

public class InterpolasiBikubik {
    //Matrix M;

    //Matrix F, X, A;

    public static void main(String[] args) {
        Matrix bikubik = new Matrix(4, 4);
        bikubik = InputOutput.fileToMatrix(new File("./bikubik"));
        Scanner myObj = new Scanner(System.in);
        double x, y;

        System.out.println("enter x: ");
        String elementx = myObj.nextLine();
        x = Double.parseDouble(elementx);

        System.out.println("enter y: ");
        String elementy = myObj.nextLine();
        y = Double.parseDouble(elementy);

        Matrix func = new Matrix(16, 1);
        getMatrixF(func, bikubik);

        Matrix X = new Matrix(16, 16);
        getMatrixX(X);
        System.out.println("i");
        Matrix A = getMatrixA(func, X);
        System.out.println("h");
        double hasil = getValue(A, x, y);
        System.out.println("Hasil : " + hasil);
    }

    public static void getMatrixX(Matrix XVar) {
        int x, y;
        int i = 0;

        for (y = -1; y <= 2; y++) {
            for (x = -1; x <= 2; x++) {
                XVar.M[i] = getRowX(x, y);
                i++;
            }
        }
    }

    public static double[] getRowX(double x, double y) {
        double i, j;
        int idx = 0;
        double[] row = new double[16];

        for (j = 0; j <= 3; j++) {
            for (i = 0; i <= 3; i++) {
                row[idx] = Math.pow(x, i) * Math.pow(y, j);
            }
        }
        return row;
    }

    public static Matrix getMatrixA(Matrix Fungsi, Matrix XVar) {
        Matrix inv = Matrix.findInverseUsingAdjugate(XVar);
        Matrix AVar = Matrix.Multiply(inv, Fungsi);
        return AVar;
    }

    public static void getMatrixF(Matrix Fungsi, Matrix M) {
        int i, j, idx;
        idx = 0;

        for (j = 0; j < M.getCol(); j++) {
            for (i = 0; i < M.getRow(); i++) {
                Fungsi.M[idx][0] = M.M[i][j];
                idx++;
            }
        }
    }

    public static double getValue(Matrix AVar, double x, double y) {
        Matrix XRow = new Matrix(1, AVar.getRow());
        XRow.M[0] = getRowX(x, y);
        Matrix hasil = new Matrix(1, 1);
        hasil = Matrix.Multiply(XRow, AVar);

        return hasil.M[0][0];
    }
}
