package src;


import java.util.*;
public class LotteryFunction {
    public static void view_toy(Toy toy) {
        System.out.printf("Name: %s\n", toy.getName());
        System.out.printf("Weight: %d\n", toy.getWeight());
    }
    public static void view_list(ToysPrizeList toysPrizesList) {
        System.out.printf("In the list of prizes %d toys:\n", toysPrizesList.size());
        if (toysPrizesList.size() != 0) {
            int n = 1;
            for (Customer name: toysPrizesList.getPrize_list().keySet()) {
                Toy toy = toysPrizesList.getPrize_list().get(name);
                if (toy != null) {
                    System.out.printf("%d winner %s - id %d name %s;\n", n++, name.get_name(), toy.getId(), toy.getName());
                }
            }
        }
    }
    public static ToysPrizeList make_list_toys(ToyStore store, ArrayList<Customer> customers, ToysPrizeList toysPrizesList) {
        int amount = 0;
        while (true) {
            System.out.printf("The toy store has %d toys.\n", store.size());
            try {
                amount = Integer.parseInt(KeyScanner.getText("How many toys you want to give/add to lottery?: "));
                if (customers.size() == 0){
                    System.out.println("There is no any customers for lottery! Canceled");
                    return toysPrizesList;}
                else if (amount == 0) {
                    System.out.println("You need to input a number > 0");
                } else if (amount > store.size()) {
                    System.out.println("There aren't so many toys in the store!");}
                  else if (amount > customers.size())     {
                      System.out.printf("There aren't so many unique customers for lottery! " +
                              "You need to choose amount =< %d\n", customers.size());}

                  else break;}
             catch (NumberFormatException e) {
                System.out.println("Wrong input. You need to input a number > 0");
            }
        }
        Random rnd = new Random();
        for (int i = 0; i < amount; i++) {
            boolean fl = true;
            while (fl) {
                int winner_index = rnd.nextInt(customers.size());

                int marker = rnd.nextInt(99) + 1;
                int id = store.get_ID_by_weight(marker, toysPrizesList);
                if (id != 0) {
                    toysPrizesList.add(customers.get(winner_index), store.get_toy_by_id(id));
                    customers.remove(winner_index);
                    store.decreaseAmount(id);
                    fl = false;
                }
            }
        }
        return toysPrizesList;
    }


}
