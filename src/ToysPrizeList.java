package src;

import java.util.HashMap;

public class ToysPrizeList {
    public HashMap<Customer, Toy> getPrize_list() {
        return prize_list;
    }
    private HashMap<Customer, Toy> prize_list = new HashMap<>();
      public void add(Customer name, Toy toy) {
        if (prize_list.size() == 0 || !this.prize_list.containsKey(name)) {
        prize_list.put(name, toy);}
    }

    public int size() {
         if (!prize_list.isEmpty()){
          return prize_list.size();
        }
          return 0;
        }
    public int count_equal_toys(Toy toy){
        int counter = 0;
        if (!prize_list.isEmpty()){
        for (Toy item: prize_list.values()) {
            if (toy.equals(item)) counter++;
        }}
        return counter;
    }

}

