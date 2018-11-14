package delivery;

public class UkrPoshtaDelivery extends Delivery {
    public boolean prepare() {
        System.out.println("Delivering...");
        return true;
    }
}
