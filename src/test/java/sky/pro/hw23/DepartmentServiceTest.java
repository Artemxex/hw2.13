package sky.pro.hw23;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.hw23.employeeService.DepartmentService;
import sky.pro.hw23.employeeService.EmployeeService;
import sky.pro.hw23.exeption.EmployeeAlreadyAddedExeption;
import sky.pro.hw23.exeption.EmployeeNotFoundExeption;
import sky.pro.hw23.exeption.EmployeeStorageFullExeption;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeservice;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void beforeEach() {
        List<Employee> employees = List.of(
                new Employee("Иван", "Иванов", 1, 55_000),
                new Employee("Артём", "Васильев", 1, 51_000),
                new Employee("Вася", "Пупкин", 2, 69_000),
                new Employee("Женя", "Игнатьева", 2, 42_000),
                new Employee("Ибрагим", "Баранов", 3, 59_000)
                );
        when(employeeservice.getAll()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalaryParams")
    public void employeeWithMaxSalaryPositiveTest(int id, Employee expected) {
        assertThat(departmentService.maxSalary(id).isEqualTo(expected));
    }

    @Test
    public void employeeWithMaxSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundExeption.class)
                .isThrownBy(() ->departmentService.maxSalary(id));
    }

    @ParameterizedTest
    @MethodSource("employeeWithMinSalaryParams")
    public void employeeWithMinSalaryPositiveTest(int id, Employee expected) {
        assertThat(departmentService.minSalary(id).isEqualTo(expected));
    }

    @Test
    public void employeeWithMinSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundExeption.class)
                .isThrownBy(() ->departmentService.minSalary(id));
    }

    @ParameterizedTest
    @MethodSource("employeeFromDepartmentParams")
    public void employeeFromDepartmentPositiveTest(int id, List<Employee> expected) {
        assertThat(departmentService.getAllByDep(id)).containsExactlyElementsOf(expected);
    }

         public static Stream<Arguments> employeeWithMaxSalaryParams() {
        return Stream.of(
                Arguments.of(1, new Employee("Иван", "Иванов", 1, 55_000)),
                Arguments.of(1, new Employee("Вася", "Васин", 2, 59_000))
        );
         }
    public static Stream<Arguments> employeeWithMinSalaryParams() {
        return Stream.of(
                Arguments.of(1, new Employee("Марат", "Иванов", 1, 55_000)),
                Arguments.of(1, new Employee("Макар", "Васин", 2, 59_000))
        );
    }
    public static Stream<Arguments> employeeFromDepartmentParams() {
        return Stream.of(
                Arguments.of(1, List.of(new Employee("Женя", "Иванов", 1, 55_000), new Employee("Зина", "Васина", 2, 59_000))),
                Arguments.of(2, List.of(new Employee("Ваня", "Пупкин", 1, 55_000), new Employee("Зинаида", "Васиновна", 2, 59_000))),
                Arguments.of(3, Collections.emptyList())
        );
    }


}

