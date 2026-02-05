public class MainAnimal {
    public static void main(String[] args) {
        Bowl bowl = new Bowl(32);

        Cat[] cats = {
                new Cat("Кокос"),
                new Cat("Юки"),
                new Cat("Мася"),
                new Cat("Джессика")
        };

        for (Cat cat : cats) {
            cat.eat(bowl, 17);
        }

        for (Cat cat : cats) {
            System.out.println(cat.infoName() + ": " + (cat.infoSatiety() ? "сыт" : "голоден"));
        }
    }
}