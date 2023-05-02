package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
public class Menu {
    HashMap<String, String> menu_list;

    public Menu() {
        this.clean();
    }
    /**
     * Add menu options
     *
     * @param a           - an option
     * @param description
     */
    public void add(String a, String description) {
        menu_list.put(a, description);
    }

    /**
     * clean menu list
     */
    public void clean() {
        menu_list = new HashMap<>();
    }

    /**
     * print to console
     */
    public void print() {
        ArrayList<String> menu_items = new ArrayList<String>(menu_list.keySet());
        Collections.sort(menu_items);
        System.out.println("Choose the option");
        for (String a : menu_items) {
            System.out.printf("%4s - %s\n", a, menu_list.get(a));
        }

    }

    /**
     * Main loop. Output menu options, waiting user answer and its check.
     *
     * @return - return user choice (String)
     */
    public String run() {
        String key;
        while (true) {
            this.print();
            System.out.print(" >>>  ");
            key = KeyScanner.getText().toUpperCase();
            System.out.println();
            if (menu_list.get(key) != null) {
                return key;
            }
        }
    }

}
