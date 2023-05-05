import java.util.Scanner;

public class MyBooking {
    private String name;
    private String surName;

    public MyBooking(String name, String surName) {
        Scanner scanner = new Scanner(System.in);

        while (name.isEmpty() || surName.isEmpty()) {
            if (name.isEmpty()) {
                System.out.print("Name: ");
                name = scanner.nextLine();
                while (!name.matches("[a-zA-Z]+")) {
                    System.out.println("Invalid input. Please enter a valid name (letters only):");
                    name = scanner.nextLine();
                }
            }
            if (surName.isEmpty()) {
                System.out.print("Surname: ");
                surName = scanner.nextLine();
                while (!surName.matches("[a-zA-Z]+")) {
                    System.out.println("Invalid input. Please enter a valid surname (letters only):");
                    surName = scanner.nextLine();
                }
            }
        }

        this.name = name;
        this.surName = surName;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surName;
    }

    public String getFullName() {
        return this.name + " " + this.surName;
    }

    public void printDetails() {
        System.out.println("Name: " + this.name);
        System.out.println("Surname: " + this.surName);
    }
}
