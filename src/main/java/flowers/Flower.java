package flowers;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Flower {
    private Color color;
    private double lenghtOfStem;
    private double price;
    private FlowerType type;
    private Odor odor;
    private ArrayList<String> specialAttributes;
    private Country countryOfOrigin;

    public Flower(Color color, double lenghtOfStem, double price, FlowerType type, Odor odor, ArrayList<String> specialAttributes, Country countryOfOrigin) {
        this.color = color;
        this.lenghtOfStem = lenghtOfStem;
        this.price = price;
        this.type = type;
        this.odor = odor;
        this.specialAttributes = specialAttributes;
        this.countryOfOrigin = countryOfOrigin;
    }
//    різні квіточки color, length of the stem, odor, country of origin, price, SPECIAL ATTRIBUTES

    @Override
    public String toString() {
        return "Flower{" +
                "type = " + type +
                ", color=" + color +
                ", lenghtOfStem=" + lenghtOfStem +
                ", price=" + price +
                ", odor=" + odor +
                ", countryOfOrigin=" + countryOfOrigin +
                ", specialAttributes=" + specialAttributes +
                '}';
    }
}