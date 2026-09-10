import java.util.Scanner;

/**
 * Case Study 1: Bus Ticket Booking System
 * Accepts passenger's age and number of seats.
 * Handles:
 *  1. Invalid age (age must be greater than 0)
 *  2. Invalid number of seats (seats must be greater than 0)
 *  3. Insufficient seats (requested seats exceed available seats)
 *  4. Invalid input (user enters text instead of number)
 */
public class BusTicketBookingSystem {

    // Custom Exception Classes
    public static class InvalidAgeException extends Exception {
        public InvalidAgeException(String message) {
            super(message);
        }
    }

    public static class InvalidSeatsException extends Exception {
        public InvalidSeatsException(String message) {
            super(message);
        }
    }

    public static class InsufficientSeatsException extends Exception {
        public InsufficientSeatsException(String message) {
            super(message);
        }
    }

    public static class InvalidInputException extends Exception {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    public static void bookTicket() throws InvalidAgeException, InvalidSeatsException, InsufficientSeatsException, InvalidInputException {
        Scanner sc = new Scanner(System.in);
        int availableSeats = 50; // Total available seats in bus

        System.out.print("Enter passenger's age: ");
        int age;
        try {
            age = Integer.parseInt(sc.next());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid input: Age must be a valid numeric integer.");
        }

        if (age <= 0) {
            throw new InvalidAgeException("Invalid age: Age must be greater than 0 (entered: " + age + ").");
        }

        System.out.print("Enter number of seats to book: ");
        int seats;
        try {
            seats = Integer.parseInt(sc.next());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid input: Number of seats must be a valid numeric integer.");
        }

        if (seats <= 0) {
            throw new InvalidSeatsException("Invalid number of seats: Number of seats must be greater than 0 (entered: " + seats + ").");
        }

        if (seats > availableSeats) {
            throw new InsufficientSeatsException("Insufficient seats: Requested " + seats + " seats, but only " + availableSeats + " seats are available.");
        }

        // Booking successful
        System.out.println("\n========== BOOKING CONFIRMATION ==========");
        System.out.println("Booking Status  : Confirmed");
        System.out.println("Passenger Age   : " + age);
        System.out.println("Seats Booked    : " + seats);
        System.out.println("Remaining Seats : " + (availableSeats - seats));
        System.out.println("==========================================");
    }

    public static void main(String[] args) {
        try {
            bookTicket();
        } catch (InvalidAgeException | InvalidSeatsException | InsufficientSeatsException | InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}