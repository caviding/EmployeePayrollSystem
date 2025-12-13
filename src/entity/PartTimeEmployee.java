package entity;

public class PartTimeEmployee extends Employee {

    private double hourlyRate;
    private int hoursWorked;

    public PartTimeEmployee(int id, String name, String surname, String position,
                            double hourlyRate, int hoursWorked) {
        super(id, name, surname, position);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Type: Part-Time" +
                " | Hourly: " + hourlyRate +
                " | Hours: " + hoursWorked;
    }
}
