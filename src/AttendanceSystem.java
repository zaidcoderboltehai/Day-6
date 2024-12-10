class Employee {
    String name;
    String attendanceStatus; // "present" or "absent"

    public Employee(String name) {
        this.name = name;
        this.attendanceStatus = "absent"; // Default attendance is absent
    }

    // Method to display employee information
    public void displayEmployeeInfo() {
        System.out.println("Employee Name: " + name + ", Attendance: " + attendanceStatus);
    }

    // Method to set attendance
    public void markAttendance(String status) {
        this.attendanceStatus = status;
    }
}

public class AttendanceSystem {

    // Array to store Employee objects
    static Employee[] employees = new Employee[6];

    // Function to mark attendance by HR
    public static void markAttendanceByHR(int index) {
        if (index >= 0 && index < employees.length) {
            employees[index].markAttendance("present");
            System.out.println("HR marked " + employees[index].name + " as present.");
        } else {
            System.out.println("Invalid employee index.");
        }
    }

    // Function to mark attendance by Manager (Manager can only mark if HR has marked as present)
    public static void markAttendanceByManager(int index) {
        if (index >= 0 && index < employees.length) {
            if (employees[index].attendanceStatus.equals("present")) {
                System.out.println("Manager marked " + employees[index].name + " as present.");
            } else {
                System.out.println("Manager cannot mark attendance. HR has not marked " + employees[index].name + " as present.");
            }
        } else {
            System.out.println("Invalid employee index.");
        }
    }

    public static void main(String[] args) {
        // Creating 6 employee objects and adding them to the employees array
        employees[0] = new Employee("John");
        employees[1] = new Employee("Alice");
        employees[2] = new Employee("Bob");
        employees[3] = new Employee("Eve");
        employees[4] = new Employee("Charlie");
        employees[5] = new Employee("David");

        // Display initial attendance (all are absent)
        System.out.println("Initial Attendance Status:");
        for (Employee employee : employees) {
            employee.displayEmployeeInfo();
        }

        // Mark attendance for HR (HR marks John, Alice, and Bob as present)
        markAttendanceByHR(0);
        markAttendanceByHR(1);
        markAttendanceByHR(2);

        // HR Attendance marked
        System.out.println("\nAfter HR has marked attendance:");
        for (Employee employee : employees) {
            employee.displayEmployeeInfo();
        }

        // Manager tries to mark attendance
        System.out.println("\nManager tries to mark attendance:");
        markAttendanceByManager(0); // Manager can mark as present because HR has done so
        markAttendanceByManager(3); // Manager cannot mark Eve as present because HR has not marked
        markAttendanceByManager(1); // Manager can mark Alice as present because HR has done so

        // Final Attendance Status
        System.out.println("\nFinal Attendance Status:");
        for (Employee employee : employees) {
            employee.displayEmployeeInfo();
        }
    }
}
