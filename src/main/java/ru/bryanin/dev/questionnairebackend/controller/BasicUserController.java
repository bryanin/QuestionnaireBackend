package ru.bryanin.dev.questionnairebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.bryanin.dev.questionnairebackend.model.user.BasicUser;
import ru.bryanin.dev.questionnairebackend.service.BasicUserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class BasicUserController {

    @Autowired
    private final BasicUserService basicUserService;

    public BasicUserController(BasicUserService basicUserService) {
        this.basicUserService = basicUserService;
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
    public List<BasicUser> getAllUsers() {
        return basicUserService.getAllUsers();
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
    public ResponseEntity<BasicUser> getUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(basicUserService.getUser(id));
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:write', " +
            "'head_of_promotion_department_assistant:write'," +
            "'head_of_design_department:write'," +
            "'head_of_engineer_promotion_department:write'," +
            "'head_of_sales:write')")
    public ResponseEntity<BasicUser> addNewUser(@RequestBody BasicUser basicUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(basicUserService.addNewUser(basicUser));
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:write', " +
            "'head_of_promotion_department_assistant:write'," +
            "'head_of_design_department:write'," +
            "'head_of_engineer_promotion_department:write'," +
            "'head_of_sales:write')")
    public ResponseEntity<BasicUser> deleteUser(@PathVariable("id") Long id) {
        basicUserService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:write', " +
            "'head_of_promotion_department_assistant:write'," +
            "'head_of_design_department:write'," +
            "'head_of_engineer_promotion_department:write'," +
            "'head_of_sales:write')")
    public ResponseEntity<BasicUser> updateUser(@PathVariable Long id,
                                                @RequestBody(required = false) BasicUser basicUser) {
        return ResponseEntity.status(HttpStatus.OK).body(basicUserService.updateUser(id, basicUser));
    }
}
