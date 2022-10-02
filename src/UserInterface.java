package src;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.*;

public class UserInterface {
    static Scanner in = new Scanner(System.in);
    static Scanner sc = new Scanner(System.in);
    static String temp;
    public static void main(String[] args) {
        Matrix matrix;
        int m, n;
        System.out.println("Welcome to our main program!\n");
        mainMenu();
    }


    public static void mainMenu() {
        Scanner myObj = new Scanner(System.in);
        int choice;
        System.out.println("MAIN MENU");
        System.out.print("""
                1. Sistem Persamaan Linier
                2. Determinan
                3. Matriks Balikan
                4. Intepolasi Polinom
                5. Interpolasi Bicubic
                6. Regresi linier berganda
                7. Keluar
                """);
        System.out.println("Masukan pilihan: ");
        String strchoice = myObj.nextLine();
        choice = Integer.parseInt(strchoice);

        switch (choice) {
            case 1 -> sistemPersamaanLinierMenu();
            case 2 -> determinanMenu();
            case 3 -> inversMenu();
            case 4 -> interPolinomMenu();
            case 5 -> interBicubicMenu();
            case 6 -> regLinearBergandaMenu();
        }
    }

    public static void sistemPersamaanLinierMenu() {
        Scanner myObj = new Scanner(System.in);
        int choice;
        Matrix mat = null;
        InputOutput f;
        System.out.print("""
                Masukan pilihan input:
                1. Keyboard
                2. File
                """);
        String strchoice = myObj.nextLine();
        choice = Integer.parseInt(strchoice);
        switch (choice) {
            case 1 : 
                mat = Matrix.inputMatrixSPLFromKeyboard();
                break;
            case 2 :
                System.out.print("Masukkan path file: ");
                String path = myObj.nextLine();
                f = new InputOutput(path);
                mat = f.readFile();
                break;
        }

        System.out.println("SISTEM PERSAMAAN LINIER");
        System.out.print("""
                1. Metode Eliminasi Gauss
                2. Metode Eliminasi Gauss-Jordam
                3. Metode Matriks Balikan
                4. Metode Cramer
                """);
        System.out.println("Masukan pilihan: ");
        strchoice = myObj.nextLine();
        choice = Integer.parseInt(strchoice);
        switch(choice) {
            case 1 -> SPL.GaussElimination(mat);
            case 2 -> SPL.elimGaussJordan(mat);
            case 3 -> SPL.inverseSPL(mat);
            case 4 -> SPL.Cramer(mat);
        }
    }

    public static void determinanMenu() {
        Scanner in = new Scanner(System.in);
        Matrix mat = Matrix.inputSquareMatrix();
        String output;

        Matrix.printMatrix(mat);
        if (Matrix.isSquare(mat)) {
            Scanner myObj = new Scanner(System.in);
            int choice;
            System.out.println("DETERMINAN MATRIKS");
            System.out.print("""
                    1. Metode Reduksi Baris
                    2. Metode Ekspansi Kofaktor
                    """);
            System.out.println("Masukan pilihan: ");
            String strchoice = myObj.nextLine();
            choice = Integer.parseInt(strchoice);
            switch (choice) {
                case 1:
                    double det = Matrix.determinanReduksiBaris(mat);
                    output = "Determinan reduksi baris adalah %f" + det;
                    System.out.printf(output);
                    InputOutput.saveFile(output);
                    break;
                case 2:
                    double determinant = Matrix.findDeterminantUsingCofactorExpansion(mat);
                    output = "Determinan dari matriks adalah %f" + determinant;
                    System.out.printf(output);
                    InputOutput.saveFile(output);
                    break;
            }
        } else {
            System.out.println("Matriks bukan matriks persegi");
        }
    }

    public static void inversMenu() {
        Matrix mat = Matrix.inputMatrix();
        if (mat.isSquare(mat)) {
            Scanner myObj = new Scanner(System.in);
            int choice;
            System.out.println("INVERS MATRIKS");
            System.out.print("""
                    1. Metode Reduksi Baris
                    2. Metode Ekspansi Kofaktor
                    """);
            System.out.println("Masukan pilihan: ");
            String strchoice = myObj.nextLine();
            choice = Integer.parseInt(strchoice);
            switch (choice) {
                case 1:
                    Matrix hasilM;
                    double det = Matrix.findDeterminantUsingCofactorExpansion(mat);
                    //System.out.println(det);
                    if (det == 0) {
                        temp = "Tidak ada invers karena determinannya 0";
                        System.out.println(temp);
                        InputOutput.saveFile(temp);
                    }
                    else{
                        hasilM = Matrix.InverseDgnGauss(mat);
                        System.out.println("Hasil Inverse Gauss-Jordan: ");
                        hasilM.displayMatrix();
                        InputOutput.saveFileInverse(hasilM);
                    }
                    break;
                case 2:
                    Matrix mHasil;
                    double detCof = Matrix.findDeterminantUsingCofactorExpansion(mat);
                    if (detCof == 0) {
                        temp = "Tidak ada invers karena determinannya 0";
                        System.out.println(temp);
                        InputOutput.saveFile(temp);
                    }
                    else {
                        mHasil = Matrix.findInverseUsingAdjugate(mat);
                        System.out.println("Hasil Inverse Adjoint: ");
                        mHasil.displayMatrix();
                        InputOutput.saveFileInverse(mHasil);
                    }
                    System.out.println("");
                    break;
            }
        } 
        else {
            temp = "Matriks bukan matriks persegi";
            System.out.println(temp);
            InputOutput.saveFile(temp);
        }
    }

    public static void interPolinomMenu() {
        PolynomialInterpolation.main();
    }

    public static void interBicubicMenu() {
        Scanner myObj = new Scanner(System.in);
        int choice;
        System.out.println("SISTEM PERSAMAAN LINIER");
        System.out.print("""
                1. 
                """);
        System.out.println("Masukan pilihan: ");
        String strchoice = myObj.nextLine();
        choice = Integer.parseInt(strchoice);
        switch(choice) {

        }
    }
    public static void displayType() {
        System.out.println();
        System.out.println("Pilih tipe masukan");
        System.out.println("1. Keyboard");
        System.out.println("2. File");
        System.out.print("Masukkan: ");
      }
    public static void regLinearBergandaMenu() {
        //int choice;
        int m, n, type;
        Matrix a, k;
        InputOutput f;

        displayType();
        type = sc.nextInt();
        if (type == 1) {
            System.out.print(">Masukkan jumlah peubah x: ");
            n = sc.nextInt();
            System.out.print(">Masukkan jumlah data sampel: ");
            m = sc.nextInt();
            System.out.println(">Masukkan data sampel: ");
            a = new Matrix(m, n + 1);
            a.readMatrix();
            System.out.println(">Masukkan nilai X yang akan ditaksir: ");
            k = new Matrix(1, n);
            k.readMatrix();
        } 
        else {
            System.out.print("Masukkan path file: ");
            String path = in.nextLine();
            f = new InputOutput(path);
            a = f.readFile();
            k = new Matrix(1, a.getCol() - 1);
            System.out.println("Masukkan nilai X yang akan ditaksir: ");
            k.readMatrixRegresi(a.getCol() - 1);
        }
        RegresiLinier.regresiGandaSPL(a, k);
    }
    private static int nextInt() {
        return 0;
    }
}
