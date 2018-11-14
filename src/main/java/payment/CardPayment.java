package payment;

public class CardPayment implements Payment {

    public boolean process(double price) {

        System.out.println("Paid with CardPayment...\n You paid +"+price);
        return true;
    }

}
