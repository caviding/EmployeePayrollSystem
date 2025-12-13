package entity;

public class FullTimeEmployee extends Employee {
    private double monthlySalary;
    private double bonus;

    public FullTimeEmployee(int id, String name, String surname, String position,
                            double monthlySalary, double bonus) {
        super(id, name, surname, position);
        this.monthlySalary = monthlySalary;
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary + bonus;
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Type: Full-Time" +
                " | Monthly: " + monthlySalary +
                " | Bonus: " + bonus;
    }
}
