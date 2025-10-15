package product;

public enum Size {
    NORMAL(0),       // ไม่มีบวกเพิ่ม
    LARGE(10),        // +5 บาท
    LARGEST(20);       // + 10 บาท

    private final double extraPrice;

    Size(double extraPrice) {
        this.extraPrice = extraPrice;
    }

    public double getExtraPrice() {
        return extraPrice;
    }
}
