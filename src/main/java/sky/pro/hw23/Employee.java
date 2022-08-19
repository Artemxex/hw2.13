package sky.pro.hw23;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Employee {
    public int employeeDepartment;
    public int employeeSalary;
    @JsonProperty("I")
    private String employeeName;
    @JsonProperty("F")
    private String employeeSurName;

    public Employee(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmployeeDepartment() {
        return employeeDepartment;
    }

    public Employee(String employeeName, String employeeSurName, int employeeDepartment, int employeeSalary) {
        this.employeeDepartment = employeeDepartment;
        this.employeeSalary = employeeSalary;
        this.employeeName = employeeName;
        this.employeeSurName = employeeSurName;
    }


    public void setEmployeeDepartment(int employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
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