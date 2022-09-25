package src;

import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        float[][] matrix;
        System.out.println("Welcome to our main program");
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

        }
    }

    public static void determinanMenu() {
        float[][] matrix = new float[5][5];
        Scanner myObj = new Scanner(System.in);
        int choice;
        System.out.println("SISTEM PERSAMAAN LINIER");
        System.out.print("""
                1. Metode Reduksi Baris
                2. Metode Ekspansi Kofaktor
                """);
        System.out.println("Masukan pilihan: ");
        String strchoice = myObj.nextLine();
        choice = Integer.parseInt(strchoice);
        switch(choice) {
            case 1 -> Matrix.determinanReduksiBaris(matrix);
            case 2 -> System.out.println("");
        }
    }

    public static void inversMenu() {
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

        }
    }

    public static void interPolinomMenu() {
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

        }
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

    public static void regLinearBergandaMenu() {
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
}
