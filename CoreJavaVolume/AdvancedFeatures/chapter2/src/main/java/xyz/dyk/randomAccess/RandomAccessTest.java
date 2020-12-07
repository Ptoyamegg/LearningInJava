package xyz.dyk.randomAccess;

import java.io.*;
import java.time.LocalDate;

public class RandomAccessTest {
    public static void main(String[] args) throws IOException {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("employee.dat"))) {
            //  save all employee records to the file employee.dat
            for (Employee employee : staff) {
                writeDate(out, employee);
            }
        }
        try (RandomAccessFile in = new RandomAccessFile("employee.dat", "r")) {
            //  retrieve all records into a new array

            //  compute the array size
            int n = (int) in.length() / Employee.RECORD_SIZE;
            Employee[] newStaff = new Employee[n];

            //  read employee in reverse order
            for (int i = n-1; i >= 0; i--) {
                newStaff[i] = new Employee();
                in.seek(i * Employee.RECORD_SIZE);
                newStaff[i] = readData(in);
            }
            //  print the newly read employee records
            for (Employee employee : newStaff) {
                System.out.println(employee);
            }
        }
    }

    public static void writeDate(DataOutput out, Employee e) throws IOException {
        DataIO.writeFixedString(e.getName(), Employee.NAME_SIZE, out);
        out.writeDouble(e.getSalary());
        LocalDate hireDay = e.getHireDay();
        out.writeInt(hireDay.getYear());
        out.writeInt(hireDay.getMonthValue());
        out.writeInt(hireDay.getDayOfMonth());
    }

    public static Employee readData(DataInput in) throws IOException {
        String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
        double salary = in.readDouble();
        int year = in.readInt();
        int month = in.readInt();
        int day = in.readInt();
        return new Employee(name, salary, year, month, day);
    }
}
