package flowers;

import java.util.ArrayList;

public class FlowerBucket {
    public double price;
    public ArrayList<Flower> flowers_bucket;

    public FlowerBucket(){
        this.flowers_bucket = new ArrayList<Flower>();
    }
    public void add_flowers(Flower flower) {
        price += flower.getPrice();
        flowers_bucket.add(flower);
    }

    public double getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "FlowerBucket{" +
                "price=" + price +
                ", flowers_bucket=" + flowers_bucket.toString() +
                '}';
    }
}

