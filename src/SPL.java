package src;
public class SPL {

    /* -------- SPL GAUSS -------- */
    /* Menghasilkan Matrix Echelon */
    public static Matrix getRowEchelon(Matrix M) {

        /* KAMUS */
        int i, j, k, l, m, switchCol;
        int idxColMain = 0; // set = 0, jika element pertama bukan 1 maka akan diganti

        Matrix mHasil = new Matrix(M.getRow(), M.getCol());
        boolean check, divisorCheck, opRowCheck1 = true, opRowCheck2, switchCheck;
        double divisor, kVal;
        
        /* ALGORITMA */
        // copy Matriks M ke Matriks M1
        M.copyMatrix_altern(mHasil);

        for (i = 0; i < M.getRow(); i++) {
            switchCol = 0;
            switchCheck = true;
            while (switchCol < mHasil.getCol() - 1 && switchCheck) {
                if (mHasil.getELMT(i, switchCol) == 0 || mHasil.getELMT(i, switchCol) != 1) {

                    check = true;
                    //  baris selanjutnya
                    k = i + 1;
                    while ((k < mHasil.getRow()) && (check)) {
                        // mencari elemen yang bisa untuk ditukar agar elemen di diagonal tidak 0 atau bernilai 1
                        if (mHasil.getELMT(k, switchCol) != 0 || mHasil.getELMT(k, switchCol) == 1) {

                            // Tukar baris ke-i dengan baris ke-k
                            mHasil.switchRow(i, k);

                            // false agar penukaran hanya sekali dan keluar dari loop
                            check = false;
                            switchCheck = false;
                        }
                        k++; //ulangi looping untuk baris lainnya
                    }
                } else {
                    switchCheck = false;
                }
                switchCol++;
            }

            // mencari 1 utama, tidak perlu diperiksa apabila sudah bernilai 1
            // run setelah sisa baris dibawah 1 utama sudah 0
            divisor = 1;
            divisorCheck = false;
            opRowCheck1 = true;
            j = 0;
            while (j < M.getCol() - 1 && (divisorCheck == false) && opRowCheck1) {
                if ((mHasil.getELMT(i, j) != 0)) {

                    // Set pembagi ke angka yang akan dijadikan 1 utama
                    divisor = mHasil.getELMT(i, j);
                    // idxColMain ke j = letak kolom 1 utama
                    idxColMain = j;
                    // check = true agar tidak di loop dan menghasilkan pembagi berbeda
                    divisorCheck = true;
                    // opRowFlag1 = false sehingga tidak run apabila element dibawah 1 utama belum 0
                    opRowCheck1 = false;

                } //asumsi sudah 1
                else if (mHasil.getELMT(i, j) == 1) {
                    idxColMain = j;
                    divisorCheck = false;
                }
                j++;

            }

            // membagi sisa baris yang ada 1 utama
            for (j = 0; j < mHasil.getCol(); j++) {
                mHasil.setELMT(i, j, (mHasil.getELMT(i, j) / divisor));
            }

            // looping pengubahan baris dibawah 1 utama sehingga menjadi 0
            for (l = i + 1; l < mHasil.getRow(); l++) {
                opRowCheck2 = true;
                m = idxColMain;

                // operationRow dilakukan jika element dibawah 1 utama belum 0
                while (m < mHasil.getCol() - 1 && opRowCheck2) {
                    if (mHasil.getELMT(l, m) == 0) {
                        opRowCheck2 = false;
                    } 
                    else if (mHasil.getELMT(l, m) != 0) {
                        kVal = (mHasil.getELMT(l, m) / mHasil.getELMT(i, m));
                        mHasil.rowOperations(l, i, kVal);
                        opRowCheck2 = false;
                    }
                    m++;
                }
            }
        }
        Matrix.changeZeroval(mHasil);
        return mHasil;
    }

    /* Menghasilkan Matrix Echelon Tereduksi*/
    public static Matrix getReducedRowEchelon_ElimGaussJordan2(Matrix M) {
        
        Matrix mHasil = new Matrix(M.getRow(), M.getCol());
        M.copyMatrix_altern(mHasil);

        int lead = 0;
        int i, r;

        for (r = 0; r < mHasil.getRow() - 1; r++){
            if (mHasil.getCol() <= lead){
                break;
            }
            i = r;
            while (mHasil.getELMT(i, lead) == 0 ){
                i = i + 1;
                if (i == mHasil.getRow()){
                    i = r;
                    lead = lead + 1;
                    if (lead == mHasil.getCol()) {
                        break;
                    }
                }
            }
            M.switchRow(i, r);
            if (mHasil.getELMT(r, lead) != 0){
                mHasil.divRow(r, lead);
            }
            else {
                for (i = 0; i < mHasil.getRow(); i++){
                    if (i != r){
                        mHasil.rowOperations(i, i, mHasil.getELMT(r, lead));
                    }
                }
            }
            lead = lead + 1;
        }
        return mHasil;
    }

    /* Menghasilkan solusi SPL dengan metode Gauss */
    public static void GaussElimination(Matrix M) {

        int i;

        if (Matrix.isDiagonalSatu(M) && (M.getRow() == M.getCol() - 1)) {
            getGaussSolutions(M);
        } 
        else if (Matrix.isDiagonalSatu(M) && (M.getRow() != M.getCol() - 1)) {
            getGaussSolutions(M);
        } 
        else {
            i = M.getRow() - 1;

            // jika semua syarat terpenuhi
            while ((i > 0) && (Matrix.isRowZero(M, i)) && (M.getELMT(i, M.getCol() - 1) == 0)) {
                i--;
            }

            if (Matrix.isRowZero(M, i) && (M.getELMT(i, M.getCol() - 1) != 0)) {
                String m = "SPL tidak memiliki solusi";
                System.out.println(m);
                InputOutput.saveFile(m);
            } else {
                getGaussSolutions(M);
            }
        }
    }

    /* Solusi SPL PARAMETRIK dengan metode Gauss */
    public static void getGaussSolutions(Matrix M) {
        /* KAMUS */
        int state[] = new int[M.getCol() - 1];
        /*
         * isi array = 0: solusi undefined, belum diinisialisasi
         * isi array = 1: solusi tersebut eksak, contoh x1 = 3
         * isi array = 2: solusi tersebut parametrik, contoh x3 = t
         * isi array = 3: solusi tersebut gabungan eksak dan parametrik,
         *                contoh: x2 = x3 + 5 = t + 5
         */

        // Array yang menyimpan solusi dalam bentuk eksak
        double result[] = new double[M.getCol() - 1];

        // Array yang menyimpan solusi dalam bentuk string
        String ArrSolString[] = new String[M.getCol() - 1];

        // Array untuk menangani bagian parametrik dibawah
        String[] answer = new String[2];
        int i, j, k;
        double constVal, tempValue = 0.0;
        String constParam;
        char variabel = 'p';

        /* ALGORITMA */
        // Hitung dari paling bawah ke atas
        for (i = M.getRow() - 1; i >= 0; i--) {

            // skip baris yg 0 semua
            if (Matrix.isRowEmpty(M, i)) {
                continue;
            }

            // index kolom yg ada 1 utama
            j = Matrix.getLeadingOne(M, i);
            k = j;

            // asumsi solusi eksak
            state[k] = 1;
            j++;

            // konstanta di kolom terakhir
            constVal = M.getELMT(i, M.getCol() - 1);

            // Start looping nilai eksak untuk constVal
            while (j < M.getCol() - 1) {

                // Skip yang isinya 0
                if (M.getELMT(i, j) != 0) {

                    // solusi bukan eksak
                    if (state[j] != 1) {
                        state[k] = 3;
                    }
                    // solusi eksak
                    if (state[j] == 1) {
                        constVal -= M.getELMT(i, j) * result[j];
                    } else if (state[j] == 0) {
                        state[j] = 2; // set state menjadi bentuk variabel parametrik
                        ArrSolString[j] = String.valueOf(variabel); // set variabel berbentuk huruf ke array ArrSolString
                        variabel++;
                    }
                }
                j++;
            }

            result[k] = constVal;
            // Inisialisasi string constParam
            if (constVal != 0 || state[k] == 1) {
                constParam = constVal + "";
            } else {
                constParam = "";
            }

            j = k + 1;

            /* VARIABEL KE OUTPUT */
            if (state[k] == 3) {
                while (j < M.getCol() - 1) {

                    // Skip yang isinya 0
                    if (M.getELMT(i, j) != 0) {

                        if (state[j] == 2) {
                            // nilai positif berubah tanda menjadi negatif saat melewati '='
                            if (M.getELMT(i, j) > 0) {

                                // koefisien 1 tidak perlu ditulis
                                if (Math.abs(M.getELMT(i, j)) == 1) {
                                    constParam += "-" + ArrSolString[j] + "";
                                } else {
                                    constParam += "-" + Math.abs(M.getELMT(i, j)) + ArrSolString[j];
                                }

                            } else { // nilai negatif berubah tanda menjadi positif saat melewati '='
                                if (Math.abs(M.getELMT(i, j)) == 1) {
                                    constParam += "+" + ArrSolString[j] + "";
                                } else {
                                    constParam += "+" + Math.abs(M.getELMT(i, j)) + ArrSolString[j];
                                }
                            }
                        } else if (state[j] == 3) {
                            if (M.getELMT(i, j) > 0) {
                                if (Math.abs(M.getELMT(i, j)) == 1) {
                                    constParam += "-" + "(" + ArrSolString[j] + ")";
                                } else {

                                    try {
                                        answer = Matrix.stripNonDigits(ArrSolString[j]);
                                        tempValue = (M.getELMT(i, j)) * (Float.parseFloat(answer[0]));
                                        if (tempValue > 0) {
                                            constParam += "+" + Math.abs(tempValue) + answer[1];
                                        } else {
                                            constParam += "-" + Math.abs(tempValue) + answer[1];
                                        }
                                        
                                    } catch (Exception e) {
                                        constParam += "-" + Math.abs(M.getELMT(i, j)) + "(" + ArrSolString[j] + ")";
                                    }
                                }
                            } else {
                                if (Math.abs(M.getELMT(i, j)) == 1) {
                                    constParam += "+" + "(" + ArrSolString[j] + ")";
                                } else {
                                    try {
                                        answer = Matrix.stripNonDigits(ArrSolString[j]);
                                        tempValue = (M.getELMT(i, j)) * (-1) * (Float.parseFloat(answer[0]));
                                        if (tempValue > 0) {
                                            constParam += "+" + Math.abs(tempValue) + answer[1];
                                        } else {
                                            constParam += "-" + Math.abs(tempValue) + answer[1];
                                        }
                                    } catch (Exception e) {
                                        constParam += "+" + Math.abs(M.getELMT(i, j)) + "(" + ArrSolString[j] + ")";
                                    }

                                }
                            }
                        }
                    }
                    j++;
                }
            }
            ArrSolString[k] = constParam;
        }

        // Final check null
        for (i = 0; i < M.getCol() - 1; i++) {
            if (state[i] == 0) {
                state[i] = 2;
                ArrSolString[i] = String.valueOf(variabel);
                variabel++;
            }
        }

        System.out.println("Solusi SPL: ");
        for (i = 0; i < M.getCol() - 1; i++) {
            System.out.println("X" + (i + 1) + " = " + ArrSolString[i]);
        }
        InputOutput.saveFileParametric(M.getCol() - 1, ArrSolString);
    }

    /* --------  GAUSS JORDAN -------- */
    public static Matrix elimGaussJordan(Matrix m) {
        Matrix mHasil, mKoef, mConst;

        mKoef = Matrix.getMKoef(m);
        mConst = Matrix.getMConst(m);
        mHasil = new Matrix(m.row, m.col);
        mHasil = Matrix.createMAug(mKoef, mConst);

        int j, k;
        int x;  //x = kolom pivot
        int pivotRow = 0;
        int pivotCol = 0;
        boolean check;
        boolean isUnderEmpty;

        while (pivotRow < m.row && pivotCol < m.col - 1) {
            check = false;

            // Jika elemen pivot bernilai nol, maka baris ditukar
            if (mKoef.M[pivotRow][pivotCol] == 0) {
                isUnderEmpty = Matrix.isUnderEmpty(Matrix.createMAug(mKoef, mConst), pivotRow, pivotCol);

                if (!isUnderEmpty) {
                    for (j = pivotRow + 1; j < mKoef.row; j++) {
                        if ((mKoef.M[j][pivotCol]) > (mKoef.M[pivotRow][pivotCol])) {
                            mKoef.switchRow(pivotRow, j);
                            mConst.switchRow(pivotRow, j);        
                            break;
                        }
                    }
                } else {
                    check = true;
                }
            }

            // ada baris kosong
            if (Matrix.isRowEmpty(Matrix.createMAug(mKoef, mConst), pivotRow)) {
                pivotRow++;
                pivotCol++;
                continue;
            }

            // posisi pivot berganti
            if (check) {
                pivotCol++;
                continue;
            }

            double divider;
            divider = mKoef.M[pivotRow][pivotCol];

            // mendapatkan satu utama suatu baris,
            for (j = pivotCol; j < mKoef.col; j++) {
                mKoef.M[pivotRow][j] = mKoef.M[pivotRow][j] / divider;
            }
            mConst.M[pivotRow][0] = mConst.M[pivotRow][0] / divider;

            // agar elemen-elemen diatas satu utama bernilai 0
            for (k = 0; k < mKoef.row; k++) {
                if (k == pivotRow || mKoef.M[k][pivotCol] == 0) {
                    continue;
                }
                double multiplier;
                multiplier = mKoef.M[k][pivotCol];

                for (x = pivotCol; x < mKoef.col; x++) {
                    mKoef.M[k][x] = mKoef.M[k][x] - (mKoef.M[pivotRow][x] * multiplier);
                }
                mConst.M[k][0] = mConst.M[k][0] - (multiplier * mConst.M[pivotRow][0]);
            }
            pivotRow++;
            pivotCol++;
        }

        mHasil = Matrix.createMAug(mKoef, mConst);

        Matrix.switchRowEmpty(mHasil);
        Matrix.changeZeroval(mHasil);
        mHasil.displayMatrix();

        return mHasil;
    }    

    /* -------- INVERS -------- */
    public static Matrix inverseSPL(Matrix M) {

        Matrix kons = new Matrix(M.getRow(), 1);
        Matrix koef = new Matrix(M.getRow(), M.getCol() - 1);
        Matrix mHasil;

        kons = Matrix.getMConst(M);
        koef = Matrix.getMKoef(M);
        koef = Matrix.InverseDgnGauss(koef);
        mHasil = M.multiply_altern(koef, kons);
        return mHasil;
    }

    public static Matrix Cramer(Matrix M) {
        /* KAMUS LOKAL */
        double detCramer, detM;
        Matrix mHasil, koef;
        int k, i;

        /* ALGORITMA */
        mHasil = new Matrix(M.getRow(), 1);
        koef = Matrix.getMKoef(M);
        detM = Matrix.detKofaktor(koef);

        for (k = 0; k < M.getCol() - 1; k++) {
            koef = Matrix.getMKoef(M);
            for (i = 0; i < M.getRow(); i++) {
                koef.setELMT(i, k, M.getELMT(i, M.getCol() - 1));
            }
            detCramer = Matrix.detKofaktor(koef);
            mHasil.setELMT(k, 0, detCramer / detM);
        }
        return mHasil;
    }
}
