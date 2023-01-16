package ru.bryanin.dev.questionnairebackend.office.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.bryanin.dev.questionnairebackend.office.dto.task.CommentDTO;
import ru.bryanin.dev.questionnairebackend.office.entity.task.Comment;
import ru.bryanin.dev.questionnairebackend.office.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}/comment")
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
    public List<CommentDTO> getAllCommentsByTaskIdDTO(@PathVariable Long id) {
        return commentService.getAllCommentsByTaskIdDTO(id);
    }

    @PostMapping("/{id}/comment")
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
    public List<CommentDTO> addNewCommentDTO(@RequestBody CommentDTO commentDTO) {
        return commentService.addNewCommentAndGetAllCommentsByTaskIdDTO(commentDTO);
    }
}
