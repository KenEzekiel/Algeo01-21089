package src;

public class RegresiLinier {

    /* Menghasilkan taksiran dari regresi data sampel */
    public static void regresiGandaSPL(Matrix M, Matrix X) {

      /* KAMUS LOKAL */
      String rumus, m;
      int i, j, k, l, idxCol;
      int n = X.getCol(); // Jumlah peubah x
      double temp;
      double taksiran; // index element
      Matrix mHasil; // Matrix setelah Normal Estimation Equation
      Matrix mHasilSPL; // Matrix hasil SPL
  
      /* ALGORITMA */
      // Normal Estimation Equation
      mHasil = new Matrix(n + 1, n + 2);
      for (i = 0; i < mHasil.getRow(); i++) {
        idxCol = i - 1;
        for (j = 0; j < M.getCol(); j++) {
          l = j + 1;
          temp = 0;
          for (k = 0; k < M.getRow(); k++) {
            if (i != 0) {
              temp += M.getELMT(k, j) * M.getELMT(k, idxCol);
            } else {
              temp += M.getELMT(k, j);
            }
          }
          if (j == 0 && i != 0) {
            mHasil.setELMT(i, j, mHasil.getELMT(j, i));
          }
          if (j == 0 && i == 0) {
            mHasil.setELMT(i, j, M.getRow());
          }
          mHasil.setELMT(i, l, temp);
        }
      }
  
      // SPL pada Matrix hasil Normal Estimation Equation
      mHasilSPL = SPL.inverseSPL(mHasil);

      // rumus polinom Y
      rumus = "Y = " + mHasilSPL.getELMT(0, 0);
      for (i = 1; i < mHasilSPL.getRow(); i++) {
        rumus += " + " + mHasilSPL.getELMT(i, 0) + " X" + i;
      }
  
      taksiran = mHasilSPL.getELMT(0, 0);
  
      for (i = 0; i < n; i++) {
        taksiran += X.getELMT(0, i) * mHasilSPL.getELMT(i + 1, 0);
      }
      
      // Output
      System.out.println("\nPersamaan Regresi yang terbentuk adalah: ");
      System.out.println(rumus);
      System.out.println();
      m = "Hasil taksiran regresi adalah: " + taksiran;
      System.out.println(m);
    }
}