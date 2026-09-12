import java.util.Stack;

/**
 * Case Study 11: Car Parking System using Stack
 * 
 * A parking system can accommodate 5 cars.
 * Cars enter the parking area one by one and are stored in a Stack.
 * 
 * The system supports:
 * 1. Add cars if space is available (max capacity: 5).
 * 2. Reject a car if parking is full.
 * 3. Remove the last parked car when it leaves (LIFO order).
 * 4. Display the remaining cars.
 * 5. Calculate parking charges:
 *    - First 2 hours        -> Rs. 50
 *    - Each additional hour -> Rs. 20
 */
public class CaseStudy11 {

    public static final int MAX_CAPACITY = 5;

    static class Car {
        private String licensePlate;
        private String carModel;
        private int hoursParked;

        public Car(String licensePlate, String carModel, int hoursParked) {
            this.licensePlate = licensePlate;
            this.carModel = carModel;
            this.hoursParked = hoursParked;
        }

        public String getLicensePlate() {
            return licensePlate;
        }

        public String getCarModel() {
            return carModel;
        }

        public int getHoursParked() {
            return hoursParked;
        }

        @Override
        public String toString() {
            return licensePlate + " (" + carModel + ", Parked: " + hoursParked + " hrs)";
        }
    }

    private Stack<Car> parkingStack;

    public CaseStudy11() {
        parkingStack = new Stack<>();
    }

    // 1 & 2. Add car if space available, otherwise reject
    public boolean parkCar(Car car) {
        System.out.println("\n[Action] Attempting to park car: " + car.getLicensePlate() + " (" + car.getCarModel() + ")");
        if (parkingStack.size() < MAX_CAPACITY) {
            parkingStack.push(car);
            System.out.println("Status: PARKED successfully.");
            System.out.println("Available slots: " + (MAX_CAPACITY - parkingStack.size()) + " / " + MAX_CAPACITY);
            return true;
        } else {
            System.out.println("Status: REJECTED! Parking lot is FULL (Max capacity: " + MAX_CAPACITY + ").");
            return false;
        }
    }

    // 5. Calculate parking charges
    public static double calculateCharges(int hours) {
        if (hours <= 0) return 0.0;
        if (hours <= 2) {
            return 50.0;
        } else {
            return 50.0 + (hours - 2) * 20.0;
        }
    }

    // 3. Remove last parked car (LIFO) and calculate charges
    public Car removeLastCar() {
        System.out.println("\n[Action] Removing last parked car (Exit)...");
        if (parkingStack.isEmpty()) {
            System.out.println("Parking lot is already empty. No cars to remove.");
            return null;
        }
        Car departingCar = parkingStack.pop();
        double charges = calculateCharges(departingCar.getHoursParked());

        System.out.println("Departing Car  : " + departingCar.getLicensePlate() + " [" + departingCar.getCarModel() + "]");
        System.out.println("Hours Parked   : " + departingCar.getHoursParked() + " hour(s)");
        System.out.printf("Parking Fee    : Rs. %.2f (First 2 hrs: Rs. 50, Extra hrs @ Rs. 20/hr)\n", charges);
        System.out.println("Available slots: " + (MAX_CAPACITY - parkingStack.size()) + " / " + MAX_CAPACITY);
        return departingCar;
    }

    // 4. Display remaining cars
    public void displayRemainingCars() {
        System.out.println("\n--- Currently Parked Cars (Top of Stack = Last In) ---");
        if (parkingStack.isEmpty()) {
            System.out.println("No cars currently in parking lot.");
            return;
        }
        for (int i = parkingStack.size() - 1; i >= 0; i--) {
            int positionFromExit = parkingStack.size() - i;
            System.out.println("  Position " + positionFromExit + " (Slot " + (i + 1) + "): " + parkingStack.get(i));
        }
        System.out.println("Total cars currently parked: " + parkingStack.size());
    }

    public static void main(String[] args) {
        CaseStudy11 parkingLot = new CaseStudy11();

        System.out.println("===== CASE STUDY 11: CAR PARKING MANAGEMENT (STACK) =====");
        System.out.println("Capacity: " + MAX_CAPACITY + " cars");
        System.out.println("Tariff: First 2 hours = Rs. 50 | Additional hours = Rs. 20/hr");

        // Step 1: Fill the parking lot up to capacity (5 cars)
        parkingLot.parkCar(new Car("DL-01-AB-1234", "Honda City", 2));
        parkingLot.parkCar(new Car("UP-14-CD-5678", "Hyundai Creta", 4));
        parkingLot.parkCar(new Car("HR-26-EF-9012", "Maruti Swift", 1));
        parkingLot.parkCar(new Car("MH-02-GH-3456", "Kia Seltos", 5));
        parkingLot.parkCar(new Car("KA-03-IJ-7890", "Tata Nexon", 3));

        // Step 2: Attempt to park 6th car (Should be rejected)
        parkingLot.parkCar(new Car("DL-04-KL-1122", "Toyota Fortuner", 2));

        // Step 4: Display remaining cars
        parkingLot.displayRemainingCars();

        // Step 3 & 5: Remove cars (LIFO) and calculate parking charges
        parkingLot.removeLastCar(); // Removes KA-03-IJ-7890 (3 hrs -> 50 + 20 = 70)
        parkingLot.removeLastCar(); // Removes MH-02-GH-3456 (5 hrs -> 50 + 3*20 = 110)

        // Step 4: Display remaining cars after departures
        parkingLot.displayRemainingCars();

        // Park another car now that space has cleared
        parkingLot.parkCar(new Car("UK-07-MN-3344", "Mahindra XUV700", 2));

        // Final view of cars
        parkingLot.displayRemainingCars();
    }
}
