package src;
import javax.swing.text.html.parser.Parser;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputOutput {
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
}
