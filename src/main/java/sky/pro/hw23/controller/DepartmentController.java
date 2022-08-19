package sky.pro.hw23.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.hw23.Employee;
import sky.pro.hw23.employeeService.DepartmentService;
import sky.pro.hw23.exeption.EmployeeAlreadyAddedExeption;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    public final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/add")
    public Employee addEmployee(@RequestParam("name") String name,@RequestParam("surname") String surname,
                                @RequestParam("id") int id,@RequestParam("salary") int salary) {
        if ((StringUtils.containsAny(name, '1','2', '3', '4', '5', '6', '7', '8', '9', '0'))||
                ((StringUtils.containsAny(surname, '1','2', '3', '4', '5', '6', '7', '8', '9', '0')))) {
            throw new EmployeeAlreadyAddedExeption();
        }
        return departmentService.addEmployee(StringUtils.capitalize(name),StringUtils.capitalize(surname), id,salary);
    }

    @GetMapping("/all-by-dep")
    public List<Employee> getAllByDep(@RequestParam("id") int id) {
        return departmentService.getAllByDep(id);
    }

    @GetMapping("/all")
    public Map<Integer,List<Employee>> getAll() {
        return departmentService.getAll();
    }

    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam("id") int id ) {
        return departmentService.minSalary(id);
    }

    @GetMapping("/max-salary")
    public Employee maxSalary(@RequestParam("id") int id ) {
        return departmentService.maxSalary(id);

    }
}
