package src;
import java.util.*;

public class UserInterface {
    static Scanner in = new Scanner(System.in); // Input String
    static Scanner sc = new Scanner(System.in); // Input integer/double    

    public static void displayTipeInput() {
        System.out.println();
        System.out.println("Pilih tipe masukan: ");
        System.out.println("1. Keyboard");
        System.out.println("2. File");
        System.out.print("Pilihan: ");
    }

    public static void main(String[] args) {
        System.out.println("Welcome to our main program!\n");

        boolean isRuning = true;

        while (isRuning){
            String message;
            String choice;
            int byk_row, byk_col, tipeInput, n, m;
            Matrix ab, a, hasilM, k;
            double hasil;
            InputOutput f;
            String temp;

            System.out.println();

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
            choice = in.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.println(" --SISTEM PERSAMAAN LINIER-- ");
                    System.out.print("""
                            1. Metode Eliminasi Gauss
                            2. Metode Eliminasi Gauss-Jordam
                            3. Metode Matriks Balikan
                            4. Metode Cramer
                            """);
                    System.out.println("Masukan pilihan: ");
                    choice = in.nextLine();
                    if (choice.equals("0")) {
                        break;
                    } else {
                        displayTipeInput();
                        tipeInput = sc.nextInt();

                        if (tipeInput == 1) {
                            System.out.print("Banyak Baris: ");
                            byk_row = sc.nextInt();
                            System.out.print("Banyak Kolom: ");
                            byk_col = sc.nextInt();
                            System.out.println("Input Matriks: ");
                            ab = new Matrix(byk_row, byk_col);
                            ab.readMatrix();
                        } else {
                            System.out.print("Masukkan path file: ");
                            String path = in.nextLine();
                            f = new InputOutput(path);
                            ab = f.readFile();
                        }

                        System.out.println();
                        switch (choice) {
                            case "1":
                                hasilM = SPL.getRowEchelon(ab);
                                SPL.GaussElimination(hasilM);
                                break;

                            case "2":
                                hasilM = SPL.elimGaussJordan(ab);
                                SPL.GaussElimination(hasilM);
                                break;

                            case "3":
                                if ((ab.getCol() - 1 != ab.getRow())) {
                                    temp = "Tidak ada invers karena tidak ada determinan";
                                    System.out.println(temp);
                                    InputOutput.saveFile(temp);
                                } else {
                                    hasil = Matrix.detKofaktor(Matrix.getMKoef(ab));
                                    if (hasil == 0) {
                                        temp = "Tidak ada invers karena determinan = 0";
                                        System.out.println(temp);
                                        InputOutput.saveFile(temp);
                                    } else {
                                        hasilM = SPL.inverseSPL(ab);
                                        System.out.println("Solusi Inverse: ");
                                        for (int i = 0; i < hasilM.getRow(); i++) {
                                            System.out.printf("X" + (i + 1) + " = %.6f", hasilM.getELMT(i, 0));
                                            System.out.println();
                                        }
                                        InputOutput.saveFileSPL(hasilM);
                                    }
                                }
                                break;

                            case "4":
                                if ((ab.getCol() - 1 != ab.getRow())) {
                                    temp = "Metode Cramer gagal karena tidak ada determinan";
                                    System.out.println(temp);
                                    InputOutput.saveFile(temp);
                                } else {
                                    hasil = Matrix.detKofaktor(Matrix.getMKoef(ab));
                                    if (hasil == 0) {
                                        temp = "Metode Cramer gagal karenaa determinan = 0";
                                        System.out.println(temp);
                                        InputOutput.saveFile(temp);
                                    } else {
                                        hasilM = SPL.Cramer(ab);
                                        System.out.println("Solusi Cramer: ");
                                        for (int i = 0; i < hasilM.getRow(); i++) {
                                            System.out.printf("X" + (i + 1) + " = " + hasilM.getELMT(i, 0));
                                            System.out.println();
                                        }
                                        InputOutput.saveFileSPL(hasilM);
                                    }
                                }
                                break;
                        }

                        System.out.println("Apakah anda ingin keluar?");
                        System.out.println("1. Ya");
                        System.out.println("0. Kembali ke menu utama");
                        System.out.print("Pilihan: ");
                        choice = in.nextLine();

                        switch (choice) {
                            case "1":
                                isRuning = false;
                                break;
                            case "0":
                                break;
                            default:
                                isRuning = false;
                                break;
                        }
                        break;
                    }
                }
                case "2" -> {
                    System.out.println();
                    System.out.println(" --DETERMINAN-- ");
                    System.out.println("1. Metode ekspansi kofaktor");
                    System.out.println("2. Metode reduksi baris");
                    System.out.println("0. Kembali ke menu utama");
                    System.out.print("Pilihan: ");
                    choice = in.nextLine();
                    if (choice.equals("0")) {
                        break;
                    } else {
                        displayTipeInput();
                        tipeInput = sc.nextInt();

                        if (tipeInput == 1) {
                            System.out.print("Masukkan N: ");
                            byk_row = sc.nextInt();
                            System.out.println("Matriks: ");
                            a = new Matrix(byk_row, byk_row);
                            a.readMatrix();
                        } else {
                            System.out.print("Masukkan path file: ");
                            String path = in.nextLine();
                            f = new InputOutput(path);
                            a = f.readFile();
                        }

                        switch (choice) {
                            case "1":
                                hasil = Matrix.detKofaktor(a);
                                System.out.println();

                                message = "Determinan Kofaktor = " + hasil;
                                System.out.println(message);
                                InputOutput.saveFile(message);
                                break;

                            case "2":
                                hasil = Matrix.determinanReduksiBaris(a);
                                System.out.println();
                                message = "Determinan Reduksi = " + hasil;
                                System.out.println(message);
                                InputOutput.saveFile(message);
                                break;
                        }

                        System.out.println("Apakah anda ingin keluar?");
                        System.out.println("1. Ya");
                        System.out.println("0. Kembali ke menu utama");
                        System.out.print("Pilihan: ");
                        choice = in.nextLine();

                        switch (choice) {
                            case "1":
                                isRuning = false;
                                break;
                            case "0":
                                break;
                            default:
                                isRuning = false;
                                break;
                        }
                        break;
                    }
                }
                case "3" -> {
                    System.out.println();
                    System.out.println(" --MATRIKS BALIKAN-- ");
                    System.out.println("1. Metode Gauss-Jordan");
                    System.out.println("2. Metode Adjoint");
                    System.out.println("0. Kembali ke menu utama");
                    System.out.print("Pilihan: ");
                    choice = in.nextLine();
                    if (choice.equals("0")) {
                        break;
                    } else {
                        displayTipeInput();
                        tipeInput = sc.nextInt();

                        if (tipeInput == 1) {
                            System.out.print("Masukkan N: ");
                            byk_row = sc.nextInt();
                            System.out.println("Matriks: ");
                            a = new Matrix(byk_row, byk_row);
                            a.readMatrix();
                        } else {
                            System.out.print("Masukkan path file: ");
                            String path = in.nextLine();
                            f = new InputOutput(path);
                            a = f.readFile();
                        }

                        System.out.println();
                        switch (choice) {
                            case "1":
                                hasil = Matrix.detKofaktor(a);
                                if (hasil == 0) {
                                    temp = "Tidak ada matrix balikan karena determinan = 0";
                                    System.out.println(temp);
                                    InputOutput.saveFile(temp);
                                } else {
                                    hasilM = Matrix.findInverseUsingAdjugate(a);
                                    System.out.println("Hasil Inverse Gauss-Jordan: ");
                                    hasilM.displayMatrix();
                                    InputOutput.saveFileInverse(hasilM);
                                }
                                break;

                            case "2":
                                hasil = Matrix.detKofaktor(a);
                                if (hasil == 0) {
                                    temp = "Tidak ada matrix balikan karena determinan = 0";
                                    System.out.println(temp);
                                    InputOutput.saveFile(temp);
                                } else {
                                    hasilM = Matrix.InverseDgnGauss(a);
                                    System.out.println("Hasil Inverse Adjoint: ");
                                    hasilM.displayMatrix();
                                    InputOutput.saveFileInverse(hasilM);
                                }
                                break;
                        }
                        System.out.println();
                        System.out.println("Apakah anda ingin keluar?");
                        System.out.println("1. Ya");
                        System.out.println("0. Kembali ke menu utama");
                        System.out.print("Pilihan: ");
                        choice = in.nextLine();

                        switch (choice) {
                            case "1":
                                isRuning = false;
                                break;
                            case "0":
                                break;
                            default:
                                isRuning = false;
                                break;
                        }
                        break;
                    }
                }
                case "4" -> {
                    PolynomialInterpolation.main();
                    System.out.println();
                    System.out.println("Apakah anda ingin keluar program?");
                    System.out.println("1. Ya");
                    System.out.println("0. Kembali ke menu utama");
                    System.out.print(">Masukan: ");
                    choice = in.nextLine();
                    switch (choice) {
                        case "1":
                            isRuning = false;
                            break;
                        case "0":
                            break;
                        default:
                            isRuning = false;
                            break;
                    }
                }
                case "5" -> {
                    InterpolasiBikubik.main(args);

                    System.out.println("Apakah anda ingin keluar?");
                    System.out.println("1. Ya");
                    System.out.println("0. Kembali ke menu utama");
                    System.out.print("Pilihan: ");
                    choice = in.nextLine();
                    switch (choice) {
                        case "1":
                            isRuning = false;
                            break;
                        case "0":
                            break;
                        default:
                            isRuning = false;
                            break;
                    }
                }
                case "6" -> {
                    displayTipeInput();
                    tipeInput = sc.nextInt();
                    if (tipeInput == 1) {
                        System.out.print("Masukkan jumlah peubah x: ");
                        n = sc.nextInt();
                        System.out.print("Masukkan jumlah data sampel: ");
                        m = sc.nextInt();
                        System.out.println("Masukkan data sampel: ");
                        a = new Matrix(m, n + 1);
                        a.readMatrix();
                        System.out.println("Masukkan nilai X yang akan ditaksir: ");
                        k = new Matrix(1, n);
                        k.readMatrix();
                    } else {
                        System.out.print("Masukkan path file: ");
                        String path = in.nextLine();
                        f = new InputOutput(path);
                        a = f.readFile();
                        k = new Matrix(1, a.getCol() - 1);
                        System.out.println("Masukkan nilai X yang akan ditaksir: ");
                        k.readMatrixRegresi(a.getCol() - 1);
                    }
                    RegresiLinear.regresiGandaSPL(a, k);
                    System.out.println("Apakah anda ingin keluar?");
                    System.out.println("1. Ya");
                    System.out.println("0. Kembali ke menu utama");
                    System.out.print("Pilihan: ");
                    choice = in.nextLine();
                    switch (choice) {
                        case "1":
                            isRuning = false;
                            break;
                        case "0":
                            break;
                        default:
                            isRuning = false;
                            break;
                    }
                }
                case "7" -> isRuning = false;
                default -> isRuning = false;
            }
                }
        System.out.println();
        System.out.println("==========================");
        System.out.println("      SAMPAI JUMPA ^^     ");
        System.out.println("==========================");
    }
}
