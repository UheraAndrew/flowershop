package payment;

public class Privat24Payment implements Payment {

    public boolean process(double price) {
        System.out.println("Paid with Privat24...\n You paid +"+price);
        return true;
    }

}
