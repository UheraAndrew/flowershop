import delivery.Delivery;
import delivery.UkrPoshtaDelivery;
import discount.Minus20;
import flowers.*;
import payment.CardPayment;
import payment.Payment;
import payment.Privat24Payment;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class FlowerStore {
    ArrayList<FlowerBucket> defaultBuckets;
    HashMap<String, Payment> availablePaiment;
    HashMap<String, Delivery> availableDelivery;
    ArrayList<Flower> availableFlowers;
    HashMap<String, Constructor<? extends Order>> discounts;

    public FlowerStore() {
        this.availableFlowers = new ArrayList<>();
        this.defaultBuckets = new ArrayList<>();
        this.availablePaiment = new HashMap<>();
        this.availableDelivery = new HashMap<>();
    }

    public FlowerStore(ArrayList<FlowerBucket> defaultBuckets, HashMap<String, Payment> availablePaiment,
                       HashMap<String, Delivery> availableDelivery, ArrayList<Flower> availableFlowers,
                       HashMap<String, Constructor<? extends Order>> discounts) {
        this.defaultBuckets = defaultBuckets;
        this.availablePaiment = availablePaiment;
        this.availableDelivery = availableDelivery;
        this.availableFlowers = availableFlowers;
        this.discounts = discounts;
    }

    public static FlowerStore createTypicalStore() throws NoSuchMethodException {
        Flower flower1 = new FlowerBuilder().setColor(Color.Blue).setCountryOfOrigin(Country.UKRAINE).
                setPrice(1).setLenghtOfStem(0.5).setOdor(Odor.Good).setType(FlowerType.Tulip).build();
        Flower flower2 = new FlowerBuilder().setColor(Color.Red).setCountryOfOrigin(Country.UKRAINE).
                setPrice(10).setLenghtOfStem(0.5).setOdor(Odor.Good).setType(FlowerType.Rose).build();
        Flower flower3 = new FlowerBuilder().setColor(Color.White).setCountryOfOrigin(Country.UKRAINE).
                setPrice(100).setLenghtOfStem(0.5).setOdor(Odor.Good).setType(FlowerType.Chamomile).build();
        Flower flower4 = new FlowerBuilder().setColor(Color.Purple).setCountryOfOrigin(Country.UKRAINE).
                setPrice(1000).setLenghtOfStem(0.5).setOdor(Odor.Good).setType(FlowerType.Rose).build();

        HashMap<String, Constructor<? extends Order>> discounts = new HashMap<>();
        discounts.put("minus20%", Minus20.class.getConstructor(Order.class));


        ArrayList<Flower> availableFlovers = new ArrayList<>();
        availableFlovers.add(flower1);
        availableFlovers.add(flower2);
        availableFlovers.add(flower3);
        availableFlovers.add(flower4);

        FlowerBucket bucket1 = new FlowerBucket();
        bucket1.add_flowers(flower2);
        bucket1.add_flowers(flower4);
        bucket1.add_flowers(flower2);

        FlowerBucket bucket2 = new FlowerBucket();
        bucket2.add_flowers(flower3);
        bucket2.add_flowers(flower3);
        bucket2.add_flowers(flower3);

        FlowerBucket bucket3 = new FlowerBucket();
        bucket3.add_flowers(flower1);
        bucket3.add_flowers(flower1);
        bucket3.add_flowers(flower1);

        FlowerBucket bucket4 = new FlowerBucket();
        bucket4.add_flowers(flower1);
        bucket4.add_flowers(flower2);
        bucket4.add_flowers(flower3);
        bucket4.add_flowers(flower4);


        ArrayList<FlowerBucket> defaultBuckets = new ArrayList<>();
        defaultBuckets.add(bucket1);
        defaultBuckets.add(bucket2);
        defaultBuckets.add(bucket3);
        defaultBuckets.add(bucket4);

        HashMap<String, Payment> payment = new HashMap<>();
        payment.put("privat24", new Privat24Payment());
        payment.put("card", new CardPayment());

        HashMap<String, Delivery> delivery = new HashMap<>();
        delivery.put("UkrPoshta", new UkrPoshtaDelivery());

        return new FlowerStore(defaultBuckets, payment, delivery, availableFlovers, discounts);
    }

    public Order askAboutDiscount(Delivery delivery, Payment payment) throws IllegalAccessException, InstantiationException {
        String answer;
        Scanner scan = new Scanner(System.in);
        Order order = new Order(payment, delivery);

        do {
            System.out.println("do you have any discounts? [y, n]");
            answer = scan.nextLine().trim();
        }
        while (!answer.equals("y") && !answer.equals("n"));
        System.out.println(answer);
        if (answer.equals("y")) {
            Set<String> posibleAnswers = this.discounts.keySet();
            do {
                System.out.println("whose delivery services you will want to use?[" + posibleAnswers.toString() + "]");
                answer = scan.nextLine();
            } while (!posibleAnswers.contains(answer));

            try {
                order = this.discounts.get(answer).newInstance(order);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        return order;
    }

    public Delivery askAboutDelivery() {
        Scanner scan = new Scanner(System.in);
        String answer;

        Set<String> posibleAnswers = this.availableDelivery.keySet();
        do {
            System.out.println("whose delivery services you will want to use?[" + posibleAnswers.toString() + "]");
            answer = scan.nextLine();
        } while (!posibleAnswers.contains(answer));
        return this.availableDelivery.get(answer);
    }

    public Payment askAboutPayment() {
        String answer;
        Scanner scan = new Scanner(System.in);
        Set<String> posibleAnswers = this.availablePaiment.keySet();
        do {
            System.out.println("whose delivery services you whant to use?[" + posibleAnswers.toString() + "]");
            answer = scan.nextLine();
        } while (!posibleAnswers.contains(answer));
        return this.availablePaiment.get(answer);

    }

    public FlowerBucket createBucket() {
        Scanner scan = new Scanner(System.in);
        String answer;

        FlowerBucket bucket = new FlowerBucket();
        while (Boolean.TRUE) {
            int result = -4;
            System.out.println("We have next available flowers\n" + availableFlowers.toString());
            do {
                System.out.println("which one index from 0 to " + (this.availableFlowers.size() - 1) + " including");
                try {
                    result = Integer.parseInt(scan.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Uncorect input");
                }
            } while (0 > result || result >= this.availableFlowers.size());

            bucket.add_flowers(availableFlowers.get(result));

            System.out.println("One more flower? [Y, N]");
            do {
                answer = scan.nextLine();
            } while (!answer.equals("Y") && !answer.equals("N"));
            if (answer.equals("N")) {
                break;
            }
        }
        return bucket;

    }

    public FlowerBucket chooseDefaultBucket() {
        System.out.println("We have some default buckets. They are here:\n" + this.defaultBuckets.toString());
        Scanner scan = new Scanner(System.in);
        int result;
        do {
            System.out.println("which one index from [0, " + (this.defaultBuckets.size() - 1) + "] including");
            result = Integer.parseInt(scan.nextLine());
        } while (0 > result || result > this.defaultBuckets.size());
        return defaultBuckets.get(result);
    }

    public void startConversation() {
        Delivery delivery = askAboutDelivery();
        Payment payment = askAboutPayment();
        Order order = null;
        try {
            order = askAboutDiscount(delivery, payment);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        String answer;
        Scanner scan = null;
        scan = new Scanner(System.in);

//        try {
//            scan = new Scanner(new File("src/main/java/in2.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


        while (Boolean.TRUE) {
            System.out.println("Will you buy default or create your own. [default, own]");
            while (!(answer = scan.nextLine().trim()).equals("default") && !answer.equals("own")) {
                System.out.println("Possible answer are: [default, own]");
            }

            if (answer.equals("default")) {
                order.addFlowerBucket(chooseDefaultBucket());
            } else {
                order.addFlowerBucket(createBucket());
            }
            System.out.println("One more bucket?[y, n]");
            do {
                answer = scan.nextLine();
            } while (!answer.equals("y") && !answer.equals("n"));

            if (answer.equals("n")) {
                break;
            }
        }

        if (order.process()) {
            System.out.println("Done");
        } else {
            System.out.println("Error");
        }


    }
}
