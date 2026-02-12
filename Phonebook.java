import java.util.*;

public class Phonebook {
    HashMap<String, List<String>> directory = new HashMap<>();

    public void add(String surname, String phone) {
        if (directory.containsKey(surname)) {
            List<String> phones = directory.get(surname);
            phones.add(phone);
        } else {
            List<String> phones = new ArrayList<>();
            phones.add(phone);
            directory.put(surname, phones);
        }
    }

    public void get(String surname) {
        if (directory.containsKey(surname)) {
            List<String> phones = directory.get(surname);
            for (String phone : phones) {
                System.out.println(phone);
            }
        } else {
            System.out.println("Нет записей");
        }
    }
}