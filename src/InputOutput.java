package src;
import javax.swing.text.html.parser.Parser;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputOutput {
    private Scanner file;
    private String fileName;

    public InputOutput(String path) {
    }

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
    public void openFile() {
        try {
          this.file = new Scanner(new File(this.fileName));
        } catch (Exception e) {
          System.out.println("Tidak ada nama File tersebut.");
          System.exit(0);
        }
      }
    
      /* Menutup file */
      public void closeFile() {
        file.close();
      }
    public int readRow() {
        /* KAMUS LOKAL */
        int row = 0;
        /* ALGORITMA */
        openFile();
        while (file.hasNextLine()) {
          row++;
          file.nextLine();
        }
        closeFile();
        return row;
      }
    
      /* Menghasilkan banyak kolom Matrix dalam file .txt */
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
}
