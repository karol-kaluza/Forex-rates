package currencies;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ForexApp {

    public static final String FOREX_DB = "DAT_MT_EURUSD_M1_202011.csv.csv";

    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader(FOREX_DB);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        Scanner userInput = new Scanner(System.in);
        System.out.println("Podaj nazwę pliku:");
        String fileFromUser = userInput.nextLine();
        while (!fileFromUser.equals(FOREX_DB)) {
            System.out.println("Podany plik nie istnieje, podaj inną nazwę: ");
            fileFromUser = userInput.nextLine();
        }
        String firstLine = bufferedReader.readLine();
        System.out.println(firstLine);
    }
}
