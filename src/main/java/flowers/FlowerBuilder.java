package flowers;

import java.util.ArrayList;

public class FlowerBuilder {

    private Color color;
    private double lenghtOfStem;
    private double price;
    private FlowerType type;
    private Odor odor;
    private ArrayList<String> specialAttributes;
    private Country countryOfOrigin;


    public FlowerBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public FlowerBuilder setLenghtOfStem(double lenghtOfStem) {
        this.lenghtOfStem = lenghtOfStem;
        return this;
    }

    public FlowerBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public FlowerBuilder setType(FlowerType type) {
        this.type = type;
        return this;
    }

    public FlowerBuilder setOdor(Odor odor) {
        this.odor = odor;
        return this;
    }

    public FlowerBuilder setSpecialAttributes(ArrayList<String> specialAttributes) {
        this.specialAttributes = specialAttributes;
        return this;
    }

    public FlowerBuilder setCountryOfOrigin(Country countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
        return this;
    }

    public Flower build() {
        if (this.color == null || this.type == null || this.odor == null ||
                this.countryOfOrigin == null || this.price == 0 || this.lenghtOfStem == 0) {
            throw new ExceptionInInitializerError();
        }
        if (this.specialAttributes == null) {
            this.specialAttributes = new ArrayList<String>();
        }
        return new Flower(this.color, this.lenghtOfStem, this.price, this.type, this.odor, this.specialAttributes, this.countryOfOrigin);
    }

}
