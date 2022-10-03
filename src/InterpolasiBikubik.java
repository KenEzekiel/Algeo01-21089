package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InterpolasiBikubik {
    //Matrix M;

    //Matrix F, X, A;

    public static void main(String[] args) {
        Matrix bikubik;
        String file = readFileName();
        Scanner fileScanner;
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                fileScanner = new Scanner(new FileReader(file));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File tidak ditemukan.");
                file = InterpolasiBikubik.readFileName();
            }
        }

        bikubik = InterpolasiBikubik.readBicubicFromFile(file);
        Scanner myObj = new Scanner(System.in);
        double x, y;

        x = getX(file);

        y = getY(file);

        System.out.println("Matriks bikubik: ");
        bikubik.displayMatrix();
        System.out.println("nilai x dan y: ");
        System.out.println(x + " " + y);

        Matrix func = readFFromFile(file);
        System.out.println("Matriks fungsi: ");
        func.displayMatrix();
        System.out.println("");

        Matrix X = new Matrix(16, 16);
        getMatrixX(X);
        System.out.println("Matriks X: ");
        X.displayMatrix();

        Matrix invX = Matrix.InverseDgnGauss(X);
        System.out.println("Matriks Inverse X: ");
        invX.displayMatrix();

        Matrix A = getMatrixA(func, invX);
        System.out.println("Matriks A: ");
        A.displayMatrix();
        System.out.println("");
        double hasil = getValue(A, x, y);

        System.out.print("""
                Bagaimana Anda ingin output dikeluarkan?
                
                1. Ditampilkan di Layar
                2. Disimpan di file
                """);
        int choice = Integer.parseInt(input.nextLine());
        while (true) {
            if (choice == 1 || choice == 2) {
                break;
            } else {
                System.out.println("Input salah");
                System.out.print("""
                Bagaimana Anda ingin output dikeluarkan?
                
                1. Ditampilkan di Layar
                2. Disimpan di file
                """);
                choice = Integer.parseInt(input.nextLine());
            }

        }

        switch (choice) {
            case 1 :
                System.out.println("Hasil : " + hasil);
                break;
            case 2 :
                String strhasil = Double.toString(hasil);
                InputOutput.saveFile(strhasil);
                break;
        }


    }

    public static void getMatrixX(Matrix XVar) {
        int x, y;
        int i = 0;

        for (y = -1; y <= 2; y++) {
            for (x = -1; x <= 2; x++) {
                XVar.setRow(i, getRowX(x, y));
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
                idx++;
            }
        }
        return row;
    }

    public static Matrix getMatrixA(Matrix Fungsi, Matrix invXVar) {
        Matrix AVar = invXVar.multiply_altern(invXVar, Fungsi);
        return AVar;
    }

    public static Matrix getMatrixF(Matrix M) {
        int i, j, idx;
        idx = 0;
        Matrix Fungsi = new Matrix(16, 1);

        for (j = 0; j < 16; j++) {
            for (i = 0; i < 16; i++) {
                Fungsi.M[idx][0] = M.M[i][j];
            }
        }
        return Fungsi;
    }

    public static double getValue(Matrix AVar, double x, double y) {
        Matrix XRow = new Matrix(1, AVar.getRow());
        XRow.M[0] = getRowX(x, y);
        Matrix hasil = new Matrix(1, 1);
        hasil = AVar.multiply_altern(XRow, AVar);

        return hasil.M[0][0];
    }

    public static String readFileName() {
        // DICTIONARY
        Scanner fileNameScanner = new Scanner(System.in);
        String fileName;

        // ALGORITHM
        while (true) {
            System.out.println("Masukkan nama file (dengan relative path yang sesuai dari path eksekusi program). Contoh: \"test/points.txt\":");
            try {
                fileName = fileNameScanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input salah.");
                fileNameScanner.next();
            }
        }

        return fileName;
    }

    public static Matrix readBicubicFromFile(String fileName) {
        // DICTIONARY
        Scanner fileScanner;
        //String fileName;
        int n, i, elementCount;
        double pointElement;
        Matrix M;

        // ALGORITHM
        //fileName = InterpolasiBikubik.readFileName();

        while (true) {
            try {
                fileScanner = new Scanner(new FileReader(fileName));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File tidak ditemukan.");
                fileName = InterpolasiBikubik.readFileName();
            }
        }

        M = new Matrix(4, 4);

        i = 0;
        while (fileScanner.hasNextLine() && i < 4) {
            pointElement = fileScanner.nextDouble();
            M.setELMT(i, 0, pointElement);
            pointElement = fileScanner.nextDouble();
            M.setELMT(i, 1, pointElement);
            pointElement = fileScanner.nextDouble();
            M.setELMT(i, 2, pointElement);
            pointElement = fileScanner.nextDouble();
            M.setELMT(i, 3, pointElement);
            i++;
        }

        return M;
    }

    public static Matrix readFFromFile(String fileName) {
        // DICTIONARY
        Scanner fileScanner;
        //String fileName;
        int n, i, elementCount;
        double pointElement;
        Matrix M;

        // ALGORITHM
        //fileName = InterpolasiBikubik.readFileName();

        while (true) {
            try {
                fileScanner = new Scanner(new FileReader(fileName));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File tidak ditemukan.");
                fileName = InterpolasiBikubik.readFileName();
            }
        }

        M = new Matrix(16, 1);

        i = 0;
        while (fileScanner.hasNextLine() && i < 16) {
            pointElement = fileScanner.nextDouble();
            M.setELMT(i, 0, pointElement);
            i++;
        }

        return M;
    }

    public static double getX(String fileName) {
        // DICTIONARY
        Scanner fileScanner;
        //String fileName;
        int i;
        double x;

        // ALGORITHM
        //fileName = InterpolasiBikubik.readFileName();

        while (true) {
            try {
                fileScanner = new Scanner(new FileReader(fileName));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File tidak ditemukan.");
                fileName = PolynomialInterpolation.readFileName();
            }
        }

        while (true) {
            try {
                fileScanner = new Scanner(new FileReader(fileName));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File tidak ditemukan");
                fileName = InterpolasiBikubik.readFileName();
            }
        }

        i = 0;
        while (fileScanner.hasNextLine() & i < 16) {
            fileScanner.next();
            i++;
        }
        x = fileScanner.nextDouble();

        return x;
    }

    public static double getY(String fileName) {
        // DICTIONARY
        Scanner fileScanner;
        //String fileName;
        int i;
        double y;

        // ALGORITHM
        //fileName = InterpolasiBikubik.readFileName();

        while (true) {
            try {
                fileScanner = new Scanner(new FileReader(fileName));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File tidak ditemukan.");
                fileName = PolynomialInterpolation.readFileName();
            }
        }

        while (true) {
            try {
                fileScanner = new Scanner(new FileReader(fileName));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File tidak ditemukan");
                fileName = InterpolasiBikubik.readFileName();
            }
        }

        i = 0;
        while (fileScanner.hasNextLine() & i < 17) {
            fileScanner.next();
            i++;
        }
        y = fileScanner.nextDouble();

        return y;
    }
}
