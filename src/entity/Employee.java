package entity;

public abstract class Employee {
    protected int id;
    protected String name;
    protected String surname;
    protected String position;

    public Employee(int id, String name, String surname, String position) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.position = position;
    }

    public int getId(){
        return id;
    }

    public String getFullName() {
        return name + " " + surname;
    }

    public String getPosition() {
        return position;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "ID: " + id +
                " | Full Name: " + getFullName() +
                " | Position: " + position +
                " | Salary: " + calculateSalary();
    }
}

