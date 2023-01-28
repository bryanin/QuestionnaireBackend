package ru.bryanin.dev.questionnairebackend.office.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.bryanin.dev.questionnairebackend.office.dto.user.EmployeeDTO;
import ru.bryanin.dev.questionnairebackend.office.entity.user.Employee;
import ru.bryanin.dev.questionnairebackend.office.entity.user.EmployeePosition;
import ru.bryanin.dev.questionnairebackend.office.service.EmployeeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:read', " +
            "'head_of_promotion_department_assistant:read'," +
            "'head_of_design_department:read'," +
            "'senior_designer:read'," +
            "'middle_designer:read'," +
            "'junior_designer:read'," +
            "'head_of_engineer_promotion_department:read'," +
            "'senior_engineer:read'," +
            "'middle_engineer:read'," +
            "'junior_engineer:read'," +
            "'head_of_sales:read'," +
            "'senior_sales_manager:read'," +
            "'middle_sales_manager:read'," +
            "'junior_sales_manager:read')")
    public List<EmployeeDTO> getAllEmployeesDTO() {
        return employeeService.getAllEmployeesDTO();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:read', " +
            "'head_of_promotion_department_assistant:read'," +
            "'head_of_design_department:read'," +
            "'senior_designer:read'," +
            "'middle_designer:read'," +
            "'junior_designer:read'," +
            "'head_of_engineer_promotion_department:read'," +
            "'senior_engineer:read'," +
            "'middle_engineer:read'," +
            "'junior_engineer:read'," +
            "'head_of_sales:read'," +
            "'senior_sales_manager:read'," +
            "'middle_sales_manager:read'," +
            "'junior_sales_manager:read')")
    public ResponseEntity<EmployeeDTO> getEmployeeDTO(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeDTO(id));
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:read', " +
            "'head_of_promotion_department_assistant:read'," +
            "'head_of_design_department:read'," +
            "'senior_designer:read'," +
            "'middle_designer:read'," +
            "'junior_designer:read'," +
            "'head_of_engineer_promotion_department:read'," +
            "'senior_engineer:read'," +
            "'middle_engineer:read'," +
            "'junior_engineer:read'," +
            "'head_of_sales:read'," +
            "'senior_sales_manager:read'," +
            "'middle_sales_manager:read'," +
            "'junior_sales_manager:read')")
    public ResponseEntity<Employee> getUserByEmail(@RequestParam(required = false) String email) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeByEmail(email));
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:write', " +
            "'head_of_promotion_department_assistant:write'," +
            "'head_of_design_department:write'," +
            "'head_of_engineer_promotion_department:write'," +
            "'head_of_sales:write')")
    public ResponseEntity<Employee> addNewEmployeeDTO(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addNewEmployeeDTO(employeeDTO));
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:write', " +
            "'head_of_promotion_department_assistant:write'," +
            "'head_of_design_department:write'," +
            "'head_of_engineer_promotion_department:write'," +
            "'head_of_sales:write')")
    public ResponseEntity<EmployeeDTO> deleteEmployeeDTO(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeDTO(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:write', " +
            "'head_of_promotion_department_assistant:write'," +
            "'head_of_design_department:write'," +
            "'head_of_engineer_promotion_department:write'," +
            "'head_of_sales:write')")
    public ResponseEntity<EmployeeDTO> updateEmployeeDTO(@PathVariable Long id,
                                               @RequestBody(required = false) EmployeeDTO employeeDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployeeDTO(id, employeeDTO));
    }

    @GetMapping("/position")
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:read', " +
            "'head_of_promotion_department_assistant:read'," +
            "'head_of_design_department:read'," +
            "'senior_designer:read'," +
            "'middle_designer:read'," +
            "'junior_designer:read'," +
            "'head_of_engineer_promotion_department:read'," +
            "'senior_engineer:read'," +
            "'middle_engineer:read'," +
            "'junior_engineer:read'," +
            "'head_of_sales:read'," +
            "'senior_sales_manager:read'," +
            "'middle_sales_manager:read'," +
            "'junior_sales_manager:read')")
    public Map<String, String> getAllEmployeesPositions() {
        return employeeService.getAllEmployeesPositions();
    }
}
