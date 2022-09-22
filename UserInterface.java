import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        System.out.println("Welcome to our main program");
        mainMenu();
    }

    public static void mainMenu() {
        Scanner myObj = new Scanner(System.in);
        int choice;
        System.out.println("MAIN MENU");
        System.out.print("1. Sistem Persamaan Linier\n" +
                        "2. Determinan\n" +
                        "3. Matriks Balikan\n" +
                        "4. Intepolasi Polinom\n" +
                        "5. Interpolasi Bicubic\n" +
                        "6. Regresi linier berganda\n" +
                        "7. Keluar\n");
        System.out.println("Masukan pilihan: ");
        String strchoice = myObj.nextLine();
        choice = Integer.parseInt(strchoice);

        switch (choice) {
            case 1:
                System.out.println("Hi");
                break;
        }
    }
}
