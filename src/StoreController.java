package src;

import java.io.IOException;
import java.util.ArrayList;
public class StoreController {

    private ToyStore toysBox;
    private ToysPrizeList toysPrizesList = new ToysPrizeList();

    private ArrayList<Customer> contestants = CSVReader.customerList("Customers.csv");
    public StoreController() throws InterruptedException, IOException {
        toysBox = CSVReader.toyStoreImport("StoreData.csv");
        main_MenuLoop();
    }

    private void main_MenuLoop() throws InterruptedException, IOException {
        // главное меню
        Menu menu = new Menu();
        menu.add("1", "Show the list of available for lottery toys in the store");
        menu.add("2", "Show result of lottery - the prize list");
        menu.add("3", "Add new toys in the Toy store");
        menu.add("4", "Add toys to the store by id");
        menu.add("5", "Change \"weight\" for the toy");
        menu.add("6", "Add winners to the random prize list");
        menu.add("7", "Write prize list to txt-file");
        menu.add("Q", "Exit");
        while (true) {
            switch (menu.run()) {
                case "1":
                    ToyStore.viewStore(this.toysBox);
                    break;
                case "2":
                    LotteryFunction.view_list(this.toysPrizesList);
                    break;
                case "3":
                    ToyStore.add_toys_to_store(this.toysBox);
                    break;
                case "4":
                    ToyStore.add_toys_by_id(this.toysBox);
                    break;
                case "5":
                    ToyStore.edit_Toys_weight(this.toysBox);
                    break;
                case "6":
                    toysPrizesList = LotteryFunction.make_list_toys(this.toysBox, contestants, toysPrizesList);
                    LotteryFunction.view_list(toysPrizesList);
                    break;
                case "7":
                    Export.savePrizeListToTxt(this.toysPrizesList);
                    break;
                case "Q":
                    Export.CSVExportStore(this.toysBox);
                    Export.CSVExportWinners(this.toysPrizesList);
                    return;
            }
            System.out.println();
        }
    }
}
