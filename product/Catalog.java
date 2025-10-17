package product;

public enum Catalog {
    TEA("Tea"),
    SODA("Soda"),
    JUICE("Juice"),
    MILK("Milk"),
    COFFEE("Coffee"),
    WATER("Water");

    private final String displayName;

    Catalog(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
