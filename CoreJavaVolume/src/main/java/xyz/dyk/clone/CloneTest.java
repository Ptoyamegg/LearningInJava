package xyz.dyk.clone;

public class CloneTest {
    public static void main(String[] args) {
        try {
            Employee employee = new Employee("John Q. Public", 50000);
            employee.setHireDay(2000, 1, 1);
            Employee copy = employee.clone();
            copy.raiseSalary(10);
            copy.setHireDay(2002, 12, 31);
            System.out.println("original = " + employee);
            System.out.println("copy =" + copy);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
