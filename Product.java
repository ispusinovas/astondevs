public class Product {
    private String name;
    private String productionData;
    private String manufacturer;
    private String countryOrigin;
    private double price;
    private boolean reservation;

    public Product(String name, String productionData, String manufacturer, String countryOrigin, double price, boolean reservation) {
        this.name = name;
        this.productionData = productionData;
        this.manufacturer = manufacturer;
        this.countryOrigin = countryOrigin;
        this.price = price;
        this.reservation = reservation;
    }

    public void productInformation() {
        System.out.println("Название: " + name);
        System.out.println("Дата производства: " + productionData);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна происхождения: " + countryOrigin);
        System.out.println("Цена: " + price);
        System.out.println("Состояние бронирования: " + reservation);
        System.out.println();
    }

};

