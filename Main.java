public class Main {
    public static void main(String[] args) {
        Product[] products = new Product[5];

        products[0] = new Product("Стол круглый", "08.05.2025", "Белорусская мебель", "Белорусь", 25000, false);
        products[1] = new Product("Стул деревянный", "02.12.2023", "Hoff", "Россия", 4000, true);
        products[2] = new Product("Полка подвисная", "23.02.2022", "Оби", "Россия", 1500, false);
        products[3] = new Product("Стол компьютерный", "14.11.2024", "Karera", "Китай", 14000, true);
        products[4] = new Product("Шкаф", "05.06.2024", "Лазурит", "Россия", 50000, false);

        for (int i = 0; i < products.length; i++) {
            products[i].productInformation();
        }
    }
}
