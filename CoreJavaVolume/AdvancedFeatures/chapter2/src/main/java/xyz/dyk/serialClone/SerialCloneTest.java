package xyz.dyk.serialClone;

import java.io.*;
import java.time.LocalDate;

public class SerialCloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employee harry = new Employee("Harry Hacker", 35000, 1989, 10, 1);
        //  clone harry
        Employee clone = (Employee) harry.clone();

        harry.raiseSalary(10);

        //  now harry and the clone are different
        System.out.println(harry);
        System.out.println(clone);
    }
}

/**
 * A class whose clone method uses serialization.
 */
class SerialCloneable implements Cloneable, Serializable {
    public Object clone() throws CloneNotSupportedException {
        try {
            //  save the object to a byte array
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try (ObjectOutputStream outputStream = new ObjectOutputStream(out)) {
                outputStream.writeObject(this);
            }

            //  read a clone of the object from the byte array
            try (InputStream bin = new ByteArrayInputStream(out.toByteArray())) {
                ObjectInputStream input = new ObjectInputStream(bin);
                return input.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            CloneNotSupportedException e2 = new CloneNotSupportedException();
            e2.initCause(e);
            throw e2;
        }
    }
}

class Employee extends SerialCloneable {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}