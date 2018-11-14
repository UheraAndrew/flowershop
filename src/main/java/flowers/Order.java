package flowers;

import delivery.Delivery;
import lombok.Getter;
import lombok.Setter;
import payment.Payment;

import java.util.ArrayList;

@Getter
public class Order {
    @Setter
    private ArrayList<FlowerBucket> items;
    private Payment payment;
    private Delivery delivery;


    public Order() {
        items = new ArrayList<>();
    }

    public Order(Payment payment, Delivery delivery) {
        this();
        this.payment = payment;
        this.delivery = delivery;
    }

    public boolean process() {
        boolean status = payment.process(this.getPrice());
        status = status && delivery.prepare();
        return status;
    }

    public double getPrice() {
        double price = 0;
        for (FlowerBucket item : items) {
            price += item.getPrice();
        }
        return price;
    }

    public void addFlowerBucket(FlowerBucket fb) {
        items.add(fb);
    }
}
