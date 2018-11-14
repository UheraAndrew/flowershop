package discount;

import flowers.FlowerBucket;
import flowers.Order;


public class Discount extends Order {
    private Order order;

    public Order getOrder() {
        return order;
    }

    public Discount(){}
    public Discount(Order order) { this.order = order;}

    public boolean process() {
        return order.process();
    }
    public double getPrice() {
        return order.getPrice();
    }

    public void addFlowerBucket(FlowerBucket fb) { order.addFlowerBucket(fb); }
}
