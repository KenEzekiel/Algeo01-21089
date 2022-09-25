import java.io.File;
import java.util.*;
import java.lang.*;
import java.io.*;

public class ReadFromFile {
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(new File("array.txt"));
            int m = 3;
            int n = 5;
            int[][] a = new int[m][n];
            while (input.hasNextLine()) {
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        try{//    System.out.println("number is ");
                            a[i][j] = input.nextInt();
                            System.out.println("number is "+ a[i][j]);
                        }
                        catch (java.util.NoSuchElementException e) {
                            // e.printStackTrace();
                        }
                    }
                }         //print the input matrix
                System.out.println("The input sorted matrix is : ");
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.println(a[i][j]);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
