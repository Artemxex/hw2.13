package sky.pro.hw23.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.hw23.Employee;
import sky.pro.hw23.employeeService.EmployeeService;
import sky.pro.hw23.exeption.EmployeeAlreadyAddedExeption;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("name") String name) {
        return employeeService.findEmployee(name);

    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam("name") String name) {
        return employeeService.removeEmployee(name);

    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam("name") String name,@RequestParam("surname") String surname,
                                @RequestParam("id") int id,@RequestParam("salary") int salary) {
        if ((StringUtils.containsAny(name, '1','2', '3', '4', '5', '6', '7', '8', '9', '0'))||
                ((StringUtils.containsAny(surname, '1','2', '3', '4', '5', '6', '7', '8', '9', '0')))) {
            throw new EmployeeAlreadyAddedExeption();
        }
        return employeeService.addEmployee(StringUtils.capitalize(name),StringUtils.capitalize(surname), id,salary);
    }


}
