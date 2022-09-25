package src;

import java.io.File;
import java.util.*;
import java.lang.*;
import java.io.*;

public class ReadFromFile {
    public static void main(float[][] mat) {
        // Read a matrix from a txt file named array.txt
        try {
            Scanner input = new Scanner(new File("src/array.txt"));
            int m = 3;
            int n = 5;
            int[][] a = new int[m][n];
            while (input.hasNextLine()) {
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        try{//    System.out.println("number is ");
                            a[i][j] = input.nextInt();
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
                        System.out.print(a[i][j] + " ");
                    }
                    System.out.println("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
