package sky.pro.hw23;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Employee {
    public int employeeDepartmentId;
    public int employeeSalary;
    @JsonProperty("I")
    private String employeeName;
    @JsonProperty("F")
    private String employeeSurName;

    public Employee(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmployeeDepartmentId() {
        return employeeDepartmentId;
    }

    public Employee(String employeeName, String employeeSurName, int id, int employeeSalary) {
        this.employeeDepartmentId = employeeDepartmentId;
        this.employeeSalary = employeeSalary;
        this.employeeName = employeeName;
        this.employeeSurName = employeeSurName;
    }


    public void setEmployeeDepartment(int employeeDepartment) {
        this.employeeDepartmentId = employeeDepartmentId;
    }

    public void setEmployeeSurName(String employeeSurName) {
        this.employeeSurName = employeeSurName;
    }

    public String getEmployeeSurName() {
        return employeeSurName;
    }

    public int getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(int employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }


    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(employeeName, employee.employeeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeName);
    }

    @Override
    public String toString() {
        return "ФИО- " + employeeName;
    }
}