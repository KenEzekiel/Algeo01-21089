package src;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PolynomialInterpolation {

    /* The main procedure. Called to the main program */
    public static void main() {
        // DICTIONARY
        Scanner choiceScanner, xScanner;
        int choice;
        double x, estimation;
        String equationString, estimationString, saveString;
        Matrix pointsMatrix, systemOfLinearEquations, solutionMatrix;

        // ALGORITHM
        System.out.println("INTERPOLASI POLINOMIAL");
        System.out.print("""
                Pilih metode input:
                1. Keyboard
                2. File
                """);

        choiceScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Masukkan pilihan:");
            try {
                choice = choiceScanner.nextInt();
                checkChoiceBetweenOneOrTwo(choice);
                break;
            }
            // Handles if the input is not an integer
            catch (InputMismatchException e) {
                System.out.println("Input salah.");
                choiceScanner.next();
            }
            // Handles if the input is an integer, but not 1 or 2
            catch (IOException e) {
                System.out.println("Input salah.");
            }
        }

        pointsMatrix = new Matrix(1, 1);

        switch (choice) {
            case 1 -> pointsMatrix = readPointsFromKeyboard();
            case 2 -> pointsMatrix = readPointsFromFile();
        }

        systemOfLinearEquations = createSystemOfLinearEquations(pointsMatrix);
        solutionMatrix = findSolutionMatrix(systemOfLinearEquations);

        System.out.print("Persamaan interpolasi polinomial adalah:\n");
        equationString = getPolynomialInterpolationEquation(solutionMatrix);
        System.out.println(equationString);

        xScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Masukkan nilai x yang ingin diinterpolasi ke dalam persamaan polinomial:");
            try {
                x = xScanner.nextDouble();
                break;
            }
            catch (InputMismatchException e) {
                System.out.println("Input salah.");
                xScanner.next();
            }
        }

        estimation = interpolate(solutionMatrix, x);

        estimationString = String.format("Estimasi dari f(%f) adalah %f\n", x, estimation);

        System.out.println(estimationString);

        saveString = String.format("%s\n%s", equationString, estimationString);

        InputOutput.saveFile(saveString);
    }

    /* Procedure to check if the parameter choice is 1 or 2.
    If not, it throws an IOException */
    public static void checkChoiceBetweenOneOrTwo(int choice) throws IOException {
        if (choice != 1 && choice != 2) {
            throw new IOException("Wrong choice.");
        }
    }

    /* Function to read n from keyboard input and returns it as an integer */
    public  static int readN() {
        // DICTIONARY
        Scanner nScanner = new Scanner(System.in);
        int n;

        // ALGORITHM
        while (true) {
            System.out.println("Masukkan nilai n:");
            try {
                n = nScanner.nextInt();
                break;
            }
            catch (InputMismatchException e) {
                System.out.println("Input salah.");
                nScanner.next();
            }
        }

        return n;
    }

    /* Function to read file name from keyboard and returns it as a string */
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

    /* Function to read points (x0, y0), (x1, y1), ..., (xn, yn)
    from keyboard input and returns a matrix containing them */
    public static Matrix readPointsFromKeyboard() {
        // DICTIONARY
        Scanner pointScanner = new Scanner(System.in);
        Matrix M;
        int n, i;
        double pointElement;

        // ALGORITHM
        n = PolynomialInterpolation.readN();
        M = new Matrix(n + 1, 2);

        i = 0;
        while (i <= n) {
            System.out.printf("Masukkan titik (x%d, y%d) dengan absis dan ordinat terpisah spasi:\n", i, i);
            try {
                pointElement = pointScanner.nextDouble();
                M.setELMT(i, 0, pointElement);
                pointElement = pointScanner.nextDouble();
                M.setELMT(i, 1, pointElement);
                i++;
            }
            catch (InputMismatchException e) {
                System.out.println("Input salah.");
                pointScanner.next();
            }
        }

        return M;
    }

    /* Function to read points (x0, y0), (x1, y1), ..., (xn, yn)
    from a file and returns a matrix containing them */
    public static Matrix readPointsFromFile() {
        // DICTIONARY
        Scanner fileScanner;
        String fileName;
        int n, i, elementCount;
        double pointElement;
        Matrix M;

        // ALGORITHM
        fileName = PolynomialInterpolation.readFileName();

        while (true) {
            try {
                fileScanner = new Scanner(new FileReader(fileName));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File tidak ditemukan.");
                fileName = PolynomialInterpolation.readFileName();
            }
        }

        elementCount = 0;
        while (fileScanner.hasNextLine()) {
            fileScanner.next();
            elementCount++;
        }

        n = (elementCount / 2) - 1;

        M = new Matrix(n + 1, 2);

        while (true) {
            try {
                fileScanner = new Scanner(new FileReader(fileName));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File tidak ditemukan");
                fileName = PolynomialInterpolation.readFileName();
            }
        }

        i = 0;
        while (fileScanner.hasNextLine()) {
            pointElement = fileScanner.nextDouble();
            M.setELMT(i, 0, pointElement);
            pointElement = fileScanner.nextDouble();
            M.setELMT(i, 1, pointElement);
            i++;
        }

        return M;
    }

    /* Function to create a matrix that contains the system of linear equations
    that are obtained from points substituted into polynomial equation.
    The parameter inputMatrix is the matrix containing user-inputted points*/
    public static Matrix createSystemOfLinearEquations(Matrix inputMatrix) {
        // DICTIONARY
        Matrix M;
        int i, j, n;

        // ALGORITHM
        n = inputMatrix.getRow() - 1;
        M = new Matrix(inputMatrix.getRow(), n + 2);
        for (i = 0; i < M.getRow(); i++) {
            for (j = 0; j < M.getCol(); j++) {
                if (j == M.getCol() - 1) {
                    M.setELMT(i, j, inputMatrix.getELMT(i, 1));
                }
                else {
                    M.setELMT(i, j, Math.pow(inputMatrix.getELMT(i, 0), j));
                }
            }
        }

        return M;
    }

    /* Returns a matrix containing solution of the system of linear equations */
    public static Matrix findSolutionMatrix(Matrix systemOfLinearEquations) {
        // DICTIONARY
        Matrix solutionMatrix;

        // ALGORITHM
        solutionMatrix = SPL.elimGaussJordan(systemOfLinearEquations);
        solutionMatrix = Matrix.getMConst(solutionMatrix);

        return solutionMatrix;
    }

    /* Prints the polynomial interpolation equation to the screen.
    Parameter systemOfLinearEquations is a matrix containing the system of linear equations */
    public static String getPolynomialInterpolationEquation(Matrix solutionMatrix) {
        // DICTIONARY
        String equation;
        int i, j, n;

        // ALGORITHM
        equation = "";
        n = solutionMatrix.getRow() - 1;

        equation += String.format("f(x) =");
        for (i = solutionMatrix.getRow() - 1; i >= 0; i--) {
            for (j = 0; j < solutionMatrix.getCol(); j++) {
                if (solutionMatrix.getELMT(i, j) < 0) {
                    equation += String.format(" - %fx^%d", - solutionMatrix.getELMT(i, j), n);
                }
                else {
                    if (i == 0) {
                        equation += String.format(" + %f", solutionMatrix.getELMT(i, j));
                    }
                    else if (i == 1) {
                        equation += String.format(" + %fx", solutionMatrix.getELMT(i, j));
                    }
                    else if (i == solutionMatrix.getRow() - 1) {
                        equation += String.format(" %fx^%d", solutionMatrix.getELMT(i, j), n);
                    }
                    else {
                        equation += String.format(" + %fx^%d", solutionMatrix.getELMT(i, j), n);
                    }
                }
            }
            n--;
        }

        return equation;
    }

    /* Returns the estimation of value x if interpolated to the polynomial interpolation equation */
    public static double interpolate(Matrix solutionMatrix, double x) {
        // DICTIONARY
        int i, j;
        double estimation;

        // ALGORITHM
        estimation = 0;
        for (i = 0; i < solutionMatrix.getRow(); i++) {
            for (j = 0; j < solutionMatrix.getCol(); j++) {
                estimation += solutionMatrix.getELMT(i, j) * Math.pow(x, i);
            }
        }

        return estimation;
    }
}