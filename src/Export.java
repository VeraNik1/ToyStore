package src;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Export {
    public static void savePrizeListToTxt(ToysPrizeList toysPrizesList) {
        if (toysPrizesList.size() == 0) {
            System.out.println("Prize list is empty. Please, choose toys to add for lottery.");
            return;
        }
        String filename = KeyScanner.getText("Input prize list name (press enter to cancel): ");
        if (filename == null || filename.isBlank() || filename.isEmpty()) {
            System.out.println("Canceled");
        }
        StringBuilder text = new StringBuilder("Toys list for lottery:\n");
        int n = 1;
        for (Customer name : toysPrizesList.getPrize_list().keySet()) {
            Toy toy = toysPrizesList.getPrize_list().get(name);
            text.append(String.format("%d winner %s - prize: id %d - name %s \n",
                    n++, name.get_name(), toy.getId(), toy.getName()));
        }
        try {
            assert filename != null;
            try (java.io.FileWriter writer = new java.io.FileWriter(filename, false)) {

                writer.write(text.toString());
                writer.flush();
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
    public static void CSVExportStore(ToyStore store) throws IOException {
            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyyMMdd-hh-mm-ss");
            String fileStore = "StoreData" + formatForDateNow.format(dateNow) + ".csv";
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileStore))) {
                int n = 0;
                for (Toy toy : store.toys_list) {
                    n++;
                    writer.printf("%d;%s;%d;%d\n", n, toy.getName(), toy.getAmount(), toy.getWeight());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public static void CSVExportWinners(ToysPrizeList list) throws IOException {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyyMMdd-hh-mm-ss");
        String fileStore = "StoreData" + formatForDateNow.format(dateNow) + ".csv";
        String fileWinners = "PrizeList" + formatForDateNow.format(dateNow) + ".csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileWinners))) {
            int k = 0;
            for (Customer name: list.getPrize_list().keySet()) {
                Toy toy = list.getPrize_list().get(name);
                k++;
                writer.printf("%d;%s;%d;%s\n", k, name.get_name(), toy.getId(), toy.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();

            }
        }
    }

