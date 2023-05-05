import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MovieTicketBooking extends MovieDetail {
    private ArrayList<String> movies;
    private String[][] seats;
    private int selectedMovieIndex;
    private int selectedSeatRow;
    private int selectedSeatColumn;

    
    public MovieTicketBooking(ArrayList<String> movies, int rows, int columns) {
        
        this.movies = movies;
        this.seats = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seats[i][j] = "O";
            }
        }
    }

    public void displayMovies() {
        System.out.println("Available movies:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(i + ". " + movies.get(i));
        }
    }

    public void selectMovie(int index) {
        while (index < 0 || index >= movies.size()) {
            System.out.println("Invalid movie index");
            System.out.print("Please enter a valid movie index: ");
            Scanner scanner = new Scanner(System.in);
            index = scanner.nextInt();
        }
        this.selectedMovieIndex = index;
        System.out.println("Selected movie: " + movies.get(selectedMovieIndex));
        System.out.println("");
    }
   
    public void displaySeats() {
        System.out.println("Available seats:");
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void selectSeat(int row, int column) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                if (row < 0 || row >= seats.length || column < 0 || column >= seats[0].length) {
                    throw new IllegalArgumentException("Invalid seat");
                }
                if (!seats[row][column].equals("O")) {
                    throw new IllegalArgumentException("Seat already booked");
                }
                seats[row][column] = "X";
                this.selectedSeatRow = row;
                this.selectedSeatColumn = column;
                System.out.println("Selected seat: " + (row+1) + "-" + (column+1));
                System.out.println("The seat that has been selected :");
                for (int i = 0; i < seats.length; i++) {
                    for (int j = 0; j < seats[0].length; j++) {
                        System.out.print(seats[i][j] + " ");
                    }
                    System.out.println();
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                displaySeats();
                System.out.print("Please select a valid seat (row column): ");
                try {
                    row = scanner.nextInt() - 1;
                    column = scanner.nextInt() - 1;
                    scanner.nextLine(); // consume newline character
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input. Please enter row and column as integers.");
                    scanner.nextLine(); // consume newline character
                    row = -1;
                    column = -1;
                } catch (NoSuchElementException ex) {
                    System.out.println("Input not found. Please try again.");
                    scanner.nextLine(); // consume newline character
                    row = -1;
                    column = -1;
                } catch (IllegalStateException ex) {
                    System.out.println("Scanner is closed. Please try again.");
                    return;
                }
            }
        }
    }




    //old version
//    public void selectSeat(int row, int column) {
//        if (row < 0 || row >= seats.length || column < 0 || column >= seats[0].length) {
//            throw new IllegalArgumentException("Invalid seat");
//        }
//        if (!seats[row][column].equals("O")) {
//            throw new IllegalArgumentException("Seat already booked");
//        }
//        seats[row][column] = "X";
//        this.selectedSeatRow = row;
//        this.selectedSeatColumn = column;
//        System.out.println("Selected seat: " + (row+1) + "-" + (column+1));
//        System.out.println("The seat that has been selected :");
//        for (int i = 0; i < seats.length; i++) {
//            for (int j = 0; j < seats[0].length; j++) {
//                System.out.print(seats[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }



    public void makePayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter credit card number: ");
        String creditCardNumber = scanner.nextLine();
        System.out.print("Enter expiry date (mm/yy): ");
        String expiryDate = scanner.nextLine();
        System.out.print("Enter security code: ");
        String securityCode = scanner.nextLine();

        double cost = 10.0; // $10.00
        System.out.println("Total cost: $" + cost);

        System.out.println("Processing payment...");
        // Simulate payment processing
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Payment successful!");
        System.out.println("Ticket confirmation:");
        System.out.println("Movie: " + movies.get(selectedMovieIndex));
        System.out.println("Seat: " + (selectedSeatRow+1) + "-" + (selectedSeatColumn+1));
        System.out.println("Total cost: $" + cost);
    }

    public static void displayMovieInformation() {
        MovieDetail[] movies = getMovieDetails();
        
        System.out.println("");
        System.out.println("=====List of the movies=====");
        for (int i = 0; i < movies.length; i++) {
            System.out.println((i+1) + ". " + movies[i].getMovieName());
        }
        Scanner input = new Scanner(System.in);
        int choice = 0;
        boolean validInput = false;

        do {
            System.out.print("Choose a movie (Enter the number of the movie): ");

            try {
                choice = input.nextInt();

                if (choice < 1 || choice > movies.length) {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + movies.length + ".");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine(); // consume the invalid input
            }
        } while (!validInput);

        MovieDetail selectedMovie = movies[choice-1];

        System.out.println("------ Movie Information ------");
        selectedMovie.printDetails();
    }

    public static MovieDetail[] getMovieDetails() {
        MovieDetail[] movies = new MovieDetail[6];

        movies[0] = new MovieDetail("The Shawshank Redemption", "Frank Darabont", "Drama", 142);
        movies[1] = new MovieDetail("The Godfather", "Francis Ford Coppola", "Crime/Drama", 175);
        movies[2] = new MovieDetail("The Dark Knight", "Christopher Nolan", "Action/Drama", 152);
        movies[3] = new MovieDetail("Avatar 2", "James Cameron", "Science Fiction/Action", 160);
        movies[4] = new MovieDetail("Avengers Endgame", "Anthony Russo, Joe Russo", "Superhero/Action", 182);
        movies[5] = new MovieDetail("Lion King", "Jon Favreau", "Musical/Drama", 118);

        
        return movies;
    }



    public static void main(String[] args) {
        ArrayList<String> movies = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to E-Booking!");
        System.out.println("Please choose an option:");
        System.out.println("1. Display movie information");
        System.out.println("2. Book a ticket");

        Scanner scanner1 = new Scanner(System.in);

        int choice = 0;

        while (choice == 0) {
            System.out.print("Your choice: ");

            if (scanner1.hasNextInt()) { // check if input is an integer
                choice = scanner1.nextInt();
                if (choice < 1 || choice > 2) {
                    System.out.println("Invalid input. Please enter a valid choice.");
                    choice = 0;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid choice.");
                scanner1.nextLine(); // consume the invalid input
            }
        }

        if (choice == 1) {
            displayMovieInformation();
            // display movie information
            // call the displayMovieInformation() method or write the code to display the information here
            System.out.println("Press 1 to book a ticket or 2 to exit.");
            int choice1 = 0;
            while (choice1 == 0) {
                System.out.print("Your choice: ");
                if (scanner1.hasNextInt()) {
                    choice1 = scanner1.nextInt();
                    if (choice1 < 1 || choice1 > 2) {
                        System.out.println("Invalid input. Please enter a valid choice.");
                        choice1 = 0;
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid choice.");
                    scanner1.nextLine();
                }
            }
            if (choice1 == 1) {
                // book a ticket
                bookTicket();
                    } else if (choice1 == 2) {
                        // exit
                        System.out.println("Thank you for visiting!");
                        System.exit(0);
                    } else {
                        System.out.println("Invalid choice.");
                    }

                    

                } else if (choice == 2) {
                    // book a ticket
                    // call the bookTicket() method or write the code to book a ticket here

                } else {
                    System.out.println("Invalid choice");
                    choice = 0;
                }
            
        System.out.println("");
        movies.add("The Shawshank Redemption");
        movies.add("The Godfather");
        movies.add("The Dark Knight");
        movies.add("Avatar2");
        movies.add("Avengers Endgame");
        movies.add("Lion King");
        int rows = 5;
        int columns = 5;
        MovieTicketBooking booking = new MovieTicketBooking(movies, rows, columns);
        
       

        Scanner scanner11 = new Scanner(System.in);
        int selectedMovieIndex;
        int selectedSeatRow;
        int selectedSeatColumn;

        // Display available movies and prompt user to select a movie
        int selectedMovieIndex1;

        do {
            booking.displayMovies();
            System.out.println("");
            System.out.println("===== Select Movie =====");
            System.out.print("Select a movie (0-" + (movies.size()-1) + "): ");

            if (scanner11.hasNextInt()) {
                selectedMovieIndex1 = scanner11.nextInt();

                if (selectedMovieIndex1 >= 0 && selectedMovieIndex1 < movies.size()) {
                    break; // ออกจากลูปเมื่อค่าถูกต้อง
                } else {
                    System.out.println("Invalid movie selection. Please try again.");
                }
            } else {
                scanner11.next(); // อ่านค่าสตริงที่ผู้ใช้ป้อนเพื่อล้างค่าในแถวคำสั่ง
                System.out.println("Invalid input. Please enter an integer value.");
            }
        } while (true);

        booking.selectMovie(selectedMovieIndex1);
        MovieTicketBooking ticketBooking = new MovieTicketBooking(movies, 5, 5);

        //        booking.displayMovies();
//        System.out.println("");
//        System.out.println("===== Select Movie ===== ");
//        System.out.print("Select a movie (0-" + (movies.size()-1) + "): ");
//        int selectedMovieIndex1 = scanner.nextInt();
//        booking.selectMovie(selectedMovieIndex1);
//        MovieTicketBooking ticketBooking = new MovieTicketBooking(movies, 5, 5);

       
        // Display available seats and prompt user to select a seat
        booking.displaySeats();
        System.out.println("===== Select Seat ===== ");
        System.out.print("Select a seat (row column): ");
         selectedSeatRow = scanner11.nextInt() - 1;
         selectedSeatColumn = scanner11.nextInt() - 1;
        booking.selectSeat(selectedSeatRow, selectedSeatColumn);

        // Prompt user to enter payment details

        System.out.println("");
        System.out.println("=====Payment=====");
        String creditCardNumber = null;
        ticketBooking.makeBooking();
        do {
            System.out.print("Enter credit card number (must be 16 digits): ");
            creditCardNumber = scanner11.next();
            if (creditCardNumber.length() != 16) {
                System.out.println("Invalid credit card number. Please enter a 16-digit credit card number.");
            }
        } while (creditCardNumber.length() != 16);

        // Continue with payment processing using the valid credit card number


        System.out.print("Enter expiry date (mm/yy): ");
        String expiryDate = scanner11.next();

        while (true) {
            try {
                String[] dateParts = expiryDate.split("/");
                String month = dateParts[0];
                String year = dateParts[1];

                // check if month is within range of 01-12
                if (!month.matches("^(0?[1-9]|1[0-2])$")) {
                    throw new Exception("Invalid month: " + month);
                }

                // check if year has no more than 2 digits
                if (!year.matches("^\\d{1,2}$")) {
                    throw new Exception("Invalid year: " + year);
                }

                // pad year with leading zeros if it has only one digit
                if (year.length() == 1) {
                    year = "0" + year;
                }

                // valid input, exit loop
                break;

            } catch (Exception e) {
                System.out.println(e.getMessage() + " Please enter a valid expiry date (mm/yy):");
                expiryDate = scanner11.next();
            }
        }
        System.out.print("Enter security code (3 digit): ");
        int securityCode = 0;
        while (true) {
            try {
                securityCode = scanner.nextInt();
                if (String.valueOf(securityCode).length() != 3) {
                    System.out.println("Invalid security code. Please enter a 3-digit security code.");
                    System.out.print("Enter security code (3 digit): ");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Number only. Please enter a valid 3-digit security code.");
                scanner.nextLine(); // clear input buffer
                System.out.print("Enter security code (3 digit): ");
            }
        }
       



        // Process payment and display ticket confirmation
        double ticketPrice = 10.0;
        System.out.println("Total cost: $" + String.format("%.2f", ticketPrice));
        System.out.println("Processing payment...");
        
        
        boolean paymentSuccessful = processPayment(creditCardNumber, expiryDate, securityCode, ticketPrice);
        if (paymentSuccessful) {
            System.out.println("");
            System.out.println("===== Slip =====");
            System.out.println("Payment successful!");
            System.out.println("Ticket confirmation:");
            System.out.println("Movie: " + movies.get(selectedMovieIndex1));
//            System.out.println("Seat: " + (selectedSeatRow+1) + "-" + (selectedSeatColumn+1));
//            
            System.out.println("Deduct " + String.format("%.2f", ticketPrice)+"$ from your cradit card amount.");
            System.out.println("================");

        } else {
            System.out.println("Payment failed. Please try again.");
        }
       
//        scanner.close();
    }
    private static void bookTicket() {
		// TODO Auto-generated method stub
		
	}

	private void makeBooking() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your surname: ");
        String surName = scanner.nextLine();
        
        // loop until the input contains no digits
        while (containsDigits(name) || containsDigits(surName)) {
            System.out.println("Please enter valid name and surname without numbers");
            System.out.print("Enter your name: ");
            name = scanner.nextLine();
            System.out.print("Enter your surname: ");
            surName = scanner.nextLine();
        }
        
        MyBooking booking = new MyBooking(name, surName);
        // booking.printDetails();
    }

    private boolean containsDigits(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean processPayment(String creditCardNumber, String expiryDate, int securityCode, double amount) {
        boolean paymentSuccessful = true;
        // Code to process payment goes here
        return paymentSuccessful;
    }
}
