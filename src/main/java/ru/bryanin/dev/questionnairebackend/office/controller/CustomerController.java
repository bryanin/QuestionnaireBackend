package ru.bryanin.dev.questionnairebackend.office.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.bryanin.dev.questionnairebackend.office.entity.user.Customer;
import ru.bryanin.dev.questionnairebackend.office.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
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
    @CrossOrigin(origins = "http://localhost:5000")
    public List<Customer> getAllUsers() {
        return customerService.getAllUsers();
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
    public ResponseEntity<Customer> getUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getUser(id));
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:write', " +
            "'head_of_promotion_department_assistant:write'," +
            "'head_of_design_department:write'," +
            "'head_of_engineer_promotion_department:write'," +
            "'head_of_sales:write')")
    public ResponseEntity<Customer> addNewUser(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addNewUser(customer));
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:write', " +
            "'head_of_promotion_department_assistant:write'," +
            "'head_of_design_department:write'," +
            "'head_of_engineer_promotion_department:write'," +
            "'head_of_sales:write')")
    public ResponseEntity<Customer> deleteUser(@PathVariable("id") Long id) {
        customerService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:write', " +
            "'head_of_promotion_department_assistant:write'," +
            "'head_of_design_department:write'," +
            "'head_of_engineer_promotion_department:write'," +
            "'head_of_sales:write')")
    public ResponseEntity<Customer> updateUser(@PathVariable Long id,
                                               @RequestBody(required = false) Customer customer) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateUser(id, customer));
    }
}
