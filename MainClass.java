import java.util.ArrayList;
import java.util.Collections;

interface Payable {
    double getPaymentAmount();
}

class Person implements Payable, Comparable<Person> {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String surname;

    public Person() {
        this.id = idCounter++;
    }

    public Person(String name, String surname) {
        this();
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String toString() {
        return String.format("%s: %d. %s %s", getPosition(), getId(), getName(), getSurname());
    }

    public String getPosition() {
        return "Person";
    }

    public double getPaymentAmount() {
        return 0.0;
    }

    public int compareTo(Person other) {
        return Double.compare(this.getPaymentAmount(), other.getPaymentAmount());
    }
}

class Employee extends Person {
    private String position;
    private double salary;

    public Employee() {
        super();
    }

    public Employee(String name, String surname, String position, double salary) {
        super(name, surname);
        this.position = position;
        this.salary = salary;
    }

    public String toString() {
        return String.format("Employee: %s %s", super.toString(), getPosition());
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getPaymentAmount() {
        return getSalary();
    }
}

class Student extends Person {
    private double gpa;
    private static final double STIPEND_AMOUNT = 36660.00;

    public Student() {
        super();
    }

    public Student(String name, String surname, double gpa) {
        super(name, surname);
        this.gpa = gpa;
    }

    public String toString() {
        return String.format("Student: %s %s", super.toString(), getPosition());
    }

    public String getPosition() {
        return "Student";
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getPaymentAmount() {
        return (gpa > 2.67) ? STIPEND_AMOUNT : 0.0;
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Employee("John", "Lennon", "Manager", 27045.78));
        people.add(new Employee("George", "Harrison", "Developer", 50000.00));
        people.add(new Student("Ringo", "Starr", 2.5));
        people.add(new Student("Paul", "McCartney", 3.0));

        Collections.sort(people);

        printData(people);
    }

    public static void printData(Iterable<Person> people) {
        for (Person person : people) {
            System.out.printf("%s earns %.2f tenge\n", person.toString(), person.getPaymentAmount());
        }
    }
}
