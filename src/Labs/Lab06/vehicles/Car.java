package Labs.Lab06.vehicles;

public class Car {

    private String model;
    private float price;
    private float priceRoadworthy;
    private String tax;
    private String[] bodyType;
    private String transmission;
    private String seats;
    private String segment;
    private String introduction;
    private String end;

    public Car(){

    }

    public Car( Car other ) {
        this.model = other.model;
        this.price = other.price;
        this.priceRoadworthy = other.priceRoadworthy;
        this.tax = other.tax;
        this.bodyType = other.bodyType;
        this.transmission = other.transmission;
        this.seats = other.seats;
        this.segment = other.segment;
        this.introduction = other.introduction;
        this.end = other.end;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setPriceRoadworthy(float priceRoadworthy) {
        this.priceRoadworthy = priceRoadworthy;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public void setBodyType(String[] bodyType) {
        this.bodyType = bodyType;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getModel() {
        return "\n" + model;
    }

}

