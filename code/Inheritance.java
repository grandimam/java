public class Account {
    public void run() {
        System.out.println("Hello");
    }
}

public class Person extends Account {

    // we are overriding the parent method
    public void run() {
        System.out.println("Hi");
    }
}