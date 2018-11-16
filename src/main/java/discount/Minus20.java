package discount;

import flowers.Order;


public class Minus20 extends Discount {
    public Minus20() {    }

    public Minus20(Order order) {
        super(order);
    }

    @Override
    public double getPrice() {
        return super.getPrice() * 0.8;
    }

    public boolean process() {
        boolean status = getOrder().getPayment().process(getPrice());
        status = status && getOrder().getDelivery().prepare();
        return status;
    }
}
