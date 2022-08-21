package sky.pro.hw23;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sky.pro.hw23.employeeService.DepartmentService;
import sky.pro.hw23.employeeService.EmployeeService;
import sky.pro.hw23.exeption.EmployeeAlreadyAddedExeption;
import sky.pro.hw23.exeption.EmployeeNotFoundExeption;
import sky.pro.hw23.exeption.EmployeeStorageFullExeption;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeService();

    @ParameterizedTest
    @MethodSource("params")
    public void addNegativeTest1 (String name, String surname, int id, int salary) {
        Employee expected = new Employee(name, surname, id, salary);
        assertThat(employeeService.addEmployee(name, surname, id, salary)).isEqualTo(expected);

        assertThatExceptionOfType(EmployeeAlreadyAddedExeption.class)
                .isThrownBy(() -> employeeService.addEmployee(name, surname, id, salary));
    }
    @ParameterizedTest
    @MethodSource("params")
    public void addNegativeTest2 (String name, String surname, int id, int salary) {
        List<Employee> employees = generateEmployees(10);
        employees.forEach(employee ->
                assertThat(employeeService.addEmployee(employee.getEmployeeName(), employee.getEmployeeSurName(), employee.getEmployeeDepartmentId(), employee.getEmployeeSalary()))
                        .isEqualTo(employee));

        assertThatExceptionOfType(EmployeeStorageFullExeption.class)
                .isThrownBy(() -> employeeService.addEmployee(name, surname, id, salary));
    }
    @ParameterizedTest
    @MethodSource("params")
    public void RemoveNegativeTest(String name, String surname, int id, int salary) {
        assertThatExceptionOfType(EmployeeNotFoundExeption.class)
                .isThrownBy(() -> employeeService.removeEmployee("test"));

        Employee expected = new Employee(name, surname, id, salary);

        assertThatExceptionOfType(EmployeeNotFoundExeption.class)
                .isThrownBy(() -> employeeService.removeEmployee("test"));

    }
    @ParameterizedTest
    @MethodSource("params")
    public void RemovePositiveTest(String name, String surname, int id, int salary) {
        Employee expected = new Employee(name, surname, id, salary);
        assertThat(employeeService.addEmployee(name, surname, id, salary)).isEqualTo(expected);
        assertThat(employeeService.removeEmployee(name)).isEqualTo(expected);
        assertThat(employeeService.getAll()).isEmpty();
    }
    @ParameterizedTest
    @MethodSource("params")
    public void FindNegativeTest(String name, String surname, int id, int salary) {
        assertThatExceptionOfType(EmployeeNotFoundExeption.class)
                .isThrownBy(() -> employeeService.findEmployee("test"));

        Employee expected = new Employee(name, surname, id, salary);
        assertThat(employeeService.addEmployee(name, surname, id, salary)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundExeption.class)
                .isThrownBy(() -> employeeService.findEmployee("test"));

    }
    @ParameterizedTest
    @MethodSource("params")
    public void FindPositiveTest(String name, String surname, int id, int salary) {
        Employee expected = new Employee(name, surname, id, salary);
        assertThat(employeeService.addEmployee(name, surname, id, salary)).isEqualTo(expected);

        assertThat(employeeService.findEmployee(name)).isEqualTo(expected);
        assertThat(employeeService.getAll()).hasSize(1);
    }

    private List<Employee> generateEmployees(int size) {
        return Stream.iterate(1, i -> i + 1)
                .limit(size)
                .map(i -> new Employee("Name" + (char) ((int) 'a' + i), "Surname" + (char) ((int) 'a' + i), i, 10_000 + i))
                .collect(Collectors.toList());
    }
    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("Ivan", "Ivanov", 1, 50_000),
                Arguments.of("Vasya", "Petrov", 1, 66_000),
                Arguments.of("Veronika", "Karter", 1, 69_000));
    }
}
