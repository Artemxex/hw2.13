package sky.pro.hw23.employeeService;

import org.springframework.stereotype.Service;
import sky.pro.hw23.Employee;
import sky.pro.hw23.exeption.EmployeeNotFoundExeption;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    private static final int LIMIT = 10;
    private final Map<String, Employee> employees = new HashMap<>();

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getAllByDep(int id) {
        return employees.values().stream().filter(e -> e.getEmployeeDepartmentId() == id).collect(Collectors.toList());
    }
    public Map<Integer, List<Employee>> getAll() {
        return employees.values().stream()
                .collect(Collectors.groupingBy(Employee::getEmployeeDepartmentId));
    }
    public Employee minSalary(int i) {
        return employees.values().stream().filter(e -> e.getEmployeeDepartmentId() == i).min((e1, e2) -> {
            return e1.getEmployeeSalary() - e2.getEmployeeSalary();
        }).orElseThrow(() ->  new EmployeeNotFoundExeption());

    }

    public Employee maxSalary(int i) {
        return employees.values().stream().filter(e -> e.getEmployeeDepartmentId() == i).max((e1, e2) -> {
            return e1.getEmployeeSalary() - e2.getEmployeeSalary();
        }).orElseThrow(() -> new EmployeeNotFoundExeption());

    }




}
