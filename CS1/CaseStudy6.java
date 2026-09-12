/**
 * Case Study 6: Hospital Patient Management
 * Patient with ID, Name, Age, Temperature.
 * Determine if patient has fever (temperature > 100.4°F).
 */
public class CaseStudy6 {

    static class Patient {
        private int patientId;
        private String name;
        private int age;
        private double temperature;

        public Patient(int patientId, String name, int age, double temperature) {
            this.patientId = patientId;
            this.name = name;
            this.age = age;
            this.temperature = temperature;
        }

        public String checkFever() {
            return (temperature > 100.4) ? "Fever" : "Normal";
        }

        public void displayPatient() {
            System.out.println("Patient ID   : " + patientId);
            System.out.println("Name         : " + name);
            System.out.println("Age          : " + age);
            System.out.println("Temperature  : " + temperature + "°F");
            System.out.println("Status       : " + checkFever());
        }
    }

    public static void main(String[] args) {
        Patient p1 = new Patient(1, "Ravi Teja", 30, 98.6);
        Patient p2 = new Patient(2, "Sita Devi", 45, 102.3);
        Patient p3 = new Patient(3, "Krishna Rao", 12, 100.4);
        Patient p4 = new Patient(4, "Ananya Singh", 28, 101.0);

        System.out.println("===== PATIENT STATUS =====");
        p1.displayPatient();
        System.out.println();
        p2.displayPatient();
        System.out.println();
        p3.displayPatient();
        System.out.println();
        p4.displayPatient();
    }
}
