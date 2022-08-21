package sky.pro.hw23.employeeService;

import org.springframework.stereotype.Service;
import sky.pro.hw23.Employee;
import sky.pro.hw23.exeption.EmployeeAlreadyAddedExeption;
import sky.pro.hw23.exeption.EmployeeNotFoundExeption;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class EmployeeService {
    private static final int LIMIT = 10;
    private final Map<String, Employee> employees = new HashMap<>();


    public Employee addEmployee(String name, String surname, int id, int salary) {
        Employee employee = new Employee(name, surname, id, salary);
        if (employees.containsKey(name + surname)) {
            throw new EmployeeAlreadyAddedExeption();
        } else {
            employees.put(name + surname, employee);
            return employee;
        }
    }

        public Employee findEmployee (String name){
            Employee employee = new Employee(name);
            if (employees.containsKey(name)) {
                return employee;
            } else {
                throw new EmployeeNotFoundExeption();
            }

        }

        public Employee removeEmployee (String name){
            Employee employee = new Employee(name);
            if (!employees.containsKey(name)) {
                throw new EmployeeNotFoundExeption();
            }
            employees.remove(name);
            return employee;
        }

    }





