import java.util.*;

public class Phonebook {
    private HashMap<String, List<String>> directory = new HashMap<>();

    public void add(String surname, String phone) {
        if (surname == null || surname.isBlank()) {
            throw new IllegalArgumentException("Фамилия не может быть пустой");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Номер телефона не может быть пустым");
        }

        if (directory.containsKey(surname)) {
            List<String> phones = directory.get(surname);
            if (!phones.contains(phone)) {
                phones.add(phone);
            }
        } else {
            List<String> phones = new ArrayList<>();
            phones.add(phone);
            directory.put(surname, phones);
        }
    }

    public List<String> get(String surname) {
        if (surname == null || surname.isBlank()) {
            throw new IllegalArgumentException("Фамилия не может быть пустой");
        }

        if (directory.containsKey(surname)) {
            return Collections.unmodifiableList(directory.get(surname));
        } else {
            return Collections.emptyList();
        }
    }
}
