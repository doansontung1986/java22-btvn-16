package statics;

public enum CustomerType {
    PERSONAL("Cá nhân"),
    GROUP("Tập thể"),
    CORPORATION("Doanh nghiệp");
    public String type;

    CustomerType(String type) {
        this.type = type;
    }
}
