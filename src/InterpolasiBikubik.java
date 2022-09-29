package src;

public class InterpolasiBikubik {
    Matrix M;

    Matrix F, X, A;

    public static void main(String[] args) {

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

    public static void getMatrixA(Matrix Fungsi, Matrix XVar, Matrix AVar) {
        AVar = Matrix.Multiply(Matrix.inverseSPL(XVar), Fungsi);
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
        Matrix XRow = new Matrix(1, AVar.getCol());
        XRow.M[0] = getRowX(x, y);
        Matrix F = Matrix.Multiply(XRow, AVar);

        return F.M[0][0];
    }
}
