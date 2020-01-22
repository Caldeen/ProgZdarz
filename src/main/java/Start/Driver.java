package Start;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Driver")
public class Driver implements Serializable  {
    public Driver(int id, String firstName, String lastName, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.hours_worked=0;
    }
    public Driver(){
    }
    @Id
    @Column(name ="driverID",unique = true,nullable = false)
    private int id;

    @Column(name ="firstName",nullable = false)
    private String firstName;
    @Column(name ="lastName",nullable = false)
    private String lastName;
    @Column(name = "salary",nullable = false)
    private double salary;

    @Column(name = "hours_worked",nullable = true)
    private Integer hours_worked;

    public Integer getHours_worked() {
        return hours_worked;
    }

    public void setHours_worked(Integer hours_worked) {
        this.hours_worked = hours_worked;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return firstName+" "+lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
