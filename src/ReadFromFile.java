package src;

import java.io.File;
import java.util.*;
import java.lang.*;
import java.io.*;

public class ReadFromFile {
    public static void main(Matrix mat, int m, int n) {
        // Read a matrix from a txt file named array.txt
        try {
            Scanner input = new Scanner(new File("src/array.txt"));
            while (input.hasNextLine()) {
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        try{//    System.out.println("number is ");
                            mat.M[i][j] = input.nextInt();
                        }
                        catch (java.util.NoSuchElementException e) {
                            e.printStackTrace();
                        }
                    }
                }
                // Print the input matrix
                System.out.println("Matrix : ");
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print(mat.M[i][j] + " ");
                    }
                    System.out.println("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
