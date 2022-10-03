package src;
import javax.swing.text.html.parser.Parser;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputOutput {
  /* ATRIBUT */
  private Scanner file;
  private String namaFile;
  static Scanner in = new Scanner(System.in); // Input String
  static Scanner sc = new Scanner(System.in); // Input integer/double

  /* KONSTRUKTOR */
  public InputOutput(String namaFile) {
    this.namaFile = namaFile;
  }

  /* CONVERTING METHODS */
  public static Matrix stringToMatrix(String in) {
    String[] inline = in.split("\n");
    double[][] data = new double[inline.length][inline[0].split(" ").length];
    for (int i = 0; i < inline.length; i++) {
        String[] temp = inline[i].split(" ");
        for (int j = 0; j < temp.length; j++) {
            data[i][j] = Double.parseDouble(temp[j]);
        }

    }
    Matrix out = new Matrix(data.length, data[0].length);
    out.inputDataFromDouble(data);
    return out;
    }  

    public static Matrix fileToMatrix(File inputFile) {
        String txt = "";
        try {
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                txt += scanner.nextLine() + "\n";
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stringToMatrix(txt);
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public static void stringToFile(String inp, String dir) {
        try {
            PrintWriter writer = new PrintWriter(dir);
            String[] inplines = inp.split("\n");
            for (String inpline : inplines) {
                writer.println(inpline);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static double[] stringToDoubleArray(String inp) {
        String[] tempArray = inp.split(" ");
        double[] res = new double[tempArray.length];
        for (int i = 0; i < tempArray.length; i++) {
            res[i] = Double.parseDouble(tempArray[i]);
        }
        return res;
    }

    /* BUKA TUTUP FILE */
    public void openFile() {
        try {
          this.file = new Scanner(new File(this.namaFile));
        } catch (Exception e) {
          System.out.println("Tidak ada nama File tersebut.");
          System.exit(0);
        }
    }

    public void closeFile() {
        file.close();
    } 

    /* READ FILE */
    public int readRow() {
        int row = 0;
        openFile();
        while (file.hasNextLine()) {
          row++;
          file.nextLine();
        }
        closeFile();
        return row;
    }

    public int readCol() {
        /* KAMUS LOKAL */
        int col = 0;
        /* ALGORITMA */
        openFile();
        Scanner lastline = new Scanner(file.nextLine());
        while (lastline.hasNextFloat()) {
          col++;
          lastline.nextFloat();
        }
        closeFile();
        return col;
    }

    public Matrix readFile() {
        /* KAMUS LOKAL */
        int i, j, m, n;
        Matrix M;
        /* ALGORITMA */
        m = readRow();
        n = readCol();
        M = new Matrix(m, n);
        openFile();
        for (i = 0; i < M.getRow(); i++) {
          for (j = 0; j < M.getCol(); j++) {
            M.setELMT(i, j, file.nextFloat());
          }
        }
        closeFile();
        return M;
    }

    /* SAVING */

    // UI Save
    public static void displaySave() {
    System.out.println();
    System.out.println("Apakah anda ingin melakukan save pada hasil ?");
    System.out.println("1. Ya");
    System.out.println("2. Tidak");
  }

    /* Tulis Matrix ke file */
    public static void writeMatrix(Matrix M, String namaFile) {
        int i, j;
        try {
          PrintWriter out = new PrintWriter(namaFile);
          for (i = 0; i < M.row; i++) {
            for (j = 0; j < M.col; j++) {
              out.print(M.getELMT(i, j));
              if (j != M.col - 1) {
                out.print(" ");
              }
            }
            out.println();
          }
          out.close();
        } catch (Exception e) {
          System.out.println("writeMatrix error");
        }
    }
    
    /* Save File: Matrix Inverse */
    public static void saveFileInverse(Matrix hasil) {
        int choice;
        String nama;
        PrintWriter out;

        try {
        displaySave();
        choice = in.nextInt();
        if (choice == 1) {
            System.out.print("Nama file (contoh: abc.txt): ");
            nama = sc.nextLine();
            out = new PrintWriter(new File("test/output/" + nama));
            writeMatrix(hasil, "test/output/" + nama);
            out.close();
        }
        } catch (Exception e) {
        System.out.println("saveFileInverse error");
        }
    }

    /* Save File: SPL */
    public static void saveFileSPL(Matrix hasil) {
        /* KAMUS LOKAL */
        int choice;
        String nama;
        PrintWriter out;
        /* ALGORITMA */
        try {
          displaySave();
          choice = in.nextInt();
          if (choice == 1) {
            System.out.print("Nama file (contoh: abc.txt): ");
            nama = sc.nextLine();
            out = new PrintWriter(new File("test/output/" + nama));
            for (int i = 0; i < hasil.getRow(); i++) {
              out.println("X" + (i + 1) + " = " + hasil.getELMT(i, 0));
            }
            out.close();
          }
        } catch (Exception e) {
          System.out.println("saveFileSPL error");
        }
    }

    /* Save File: Polinom */
    public static void saveFilePolinom(String m1, String m2) {
        /* KAMUS LOKAL */
        int choice;
        String nama;
        PrintWriter out;
        /* ALGORITMA */
        try {
          displaySave();
          choice = in.nextInt();
          if (choice == 1) {
            System.out.print("Nama file (contoh: abc.txt): ");
            nama = sc.nextLine();
            out = new PrintWriter(new File("test/output/" + nama));
            out.println("Persamaan polinom yang terbentuk: ");
            out.println(m1);
            out.println("\nHasil taksiran polinom: ");
            out.println(m2);
            out.close();
          }
        } catch (Exception e) {
          System.out.println("saveFilePolinom error");
        }
    }

    /* Save File: Regresi */
    public static void saveFileRegresi(String m1, String m2) {
        int choice;
        String nama;
        PrintWriter out;

        try {
          displaySave();
          choice = in.nextInt();
          if (choice == 1) {
            System.out.print("Nama file (contoh: abc.txt): ");
            nama = sc.nextLine();
            out = new PrintWriter(new File("test/output/" + nama));
            out.println("Persamaan regresi yang terbentuk: ");
            out.println(m1);
            out.println();
            out.println(m2);
            out.close();
          }
        } catch (Exception e) {
          System.out.println("saveFileRegresi error");
        }
    }
    /* Save File: Solusi Parametrik */
    public static void saveFileParametric(int N, String[] Eq) {
        /* KAMUS LOKAL */
        int choice;
        String nama;
        PrintWriter out;
        /* ALGORITMA */
        try {
          displaySave();
          choice = in.nextInt();
          if (choice == 1) {
            System.out.print("Nama file (contoh: abc.txt): ");
            nama = sc.nextLine();
            out = new PrintWriter(new File("test/output/" + nama));
            for (int i = 0; i < N; i++) {
              out.println("X" + (i + 1) + " = " + Eq[i]);
            }
            out.close();
          }
        } catch (Exception e) {
          System.out.println("saveFileSPL error");
        }
    }

    /* Save File: IN GENERAL */
    public static void saveFile(String m) {
        int choice;
        String nama;
        PrintWriter out;

        try {
          displaySave();
          choice = in.nextInt();
          if (choice == 1) {
            System.out.print("Nama file (contoh: abc.txt): ");
            nama = sc.nextLine();
            out = new PrintWriter(new File("test/output/" + nama));
            out.write(m);
            out.close();
          }
        } catch (Exception e) {
          System.out.println("saveFile error");
        }
    } 
}
