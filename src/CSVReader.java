package src;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
    public static ToyStore toyStoreImport(String csvFile) {
        ToyStore store = new ToyStore();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // delimiter = ;
                String[] temp = line.split(cvsSplitBy);
                store.add(new Toy(temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3])));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return store;
    }
    public static ArrayList<Customer> customerList(String csvFile) {
        ArrayList<Customer> contestants = new ArrayList<>();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // delimiter = ;
                String[] temp = line.split(cvsSplitBy);
                contestants.add(new Customer(temp[0], Integer.parseInt(temp[1])));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return contestants;
    }
}