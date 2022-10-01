package src;

import java.util.Scanner;
import java.util.*;

public class UserInterface {
    static Scanner in = new Scanner(System.in);
    static Scanner sc = new Scanner(System.in);
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
        Matrix mat = Matrix.inputMatrix();
        Scanner myObj = new Scanner(System.in);
        int choice;
        System.out.println("SISTEM PERSAMAAN LINIER");
        System.out.print("""
                1. Metode Eliminasi Gauss
                2. Metode Eliminasi Gauss-Jordam
                3. Metode Matriks Balikan
                4. Metode Cramer
                """);
        System.out.println("Masukan pilihan: ");
        String strchoice = myObj.nextLine();
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
                    System.out.printf("Determinan dari matriks adalah %f", det);
                    break;
                case 2:
                    double determinant = Matrix.findDeterminantUsingCofactorExpansion(mat);
                    System.out.printf("Determinan dari matriks adalah %f", determinant);
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
                    double det = Matrix.determinanReduksiBaris(mat);
                    System.out.println(det);
                    break;
                case 2:
                    System.out.println("");
                    break;
            }
        } else {
            System.out.println("Matriks bukan matriks persegi");
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
