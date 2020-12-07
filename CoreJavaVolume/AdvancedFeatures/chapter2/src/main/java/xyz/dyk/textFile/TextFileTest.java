package xyz.dyk.textFile;

import xyz.dyk.employee.Employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Scanner;

public class TextFileTest {
    public static void main(String[] args) throws IOException {
        String property = System.getProperty("line.separator");
        System.out.println("property = " + property);
        System.out.println("File.separator = " + File.separator);
        System.out.println("Charset.defaultCharset() = " + Charset.defaultCharset());
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        //  save all employee records to the file employee.dat
        try (PrintWriter out = new PrintWriter("employee.dat", "UTF-8")) {
            writeData(staff, out);
        }
        try (Scanner in = new Scanner(new FileInputStream("employee.dat"), "UTF-8")) {
            Employee[] employees = readData(in);
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    private static void writeData(Employee[] employees, PrintWriter out) throws IOException {
        //  write number for employees
        out.println(employees.length);
        for (Employee employee : employees) {
            writeEmployee(out, employee);
        }
    }

    public static void writeEmployee(PrintWriter out, Employee employee) {
        out.println(employee.getName() + "|" + employee.getSalary() + "|" + employee.getHireDay());
    }

    private static Employee[] readData(Scanner in) {
        //  retrieve the array size
        int n = in.nextInt();
        in.nextLine();  //  consume newline

        Employee[] employees = new Employee[n];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = readEmployee(in);
        }
        return employees;
    }

    public static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        LocalDate hireDate = LocalDate.parse(tokens[2]);
        int year = hireDate.getYear();
        int month = hireDate.getMonthValue();
        int day = hireDate.getDayOfMonth();
        return new Employee(name, salary, year, month, day);
    }
}
