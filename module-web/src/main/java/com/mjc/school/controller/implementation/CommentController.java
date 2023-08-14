package com.mjc.school.controller.implementation;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.interfaces.CommentControllerInterface;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.interfaces.CommentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/comments", consumes = {"application/JSON"}, produces = {"application/JSON", "application/XML"})
public class CommentController implements CommentControllerInterface {

    private final CommentServiceInterface commentService;

    @Autowired
    public CommentController(@Qualifier("commentService") CommentServiceInterface commentService) {
        this.commentService = commentService;
    }
    @Override
    @CommandHandler("18")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDtoResponse> readAll() {
        List<CommentDtoResponse> comments = commentService.readAll();
        if (comments.isEmpty()) {
            System.out.println("There is no comment data in the database yet.");
        }
        return comments;
    }

    @Override
    @CommandHandler("19")
    @GetMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDtoResponse readById(@PathVariable Long id) {
        return commentService.readById(id);
    }

    @Override
    @CommandHandler("20")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDtoResponse create(CommentDtoRequest createRequest) {
        return commentService.create(createRequest);
    }

    @Override
    @CommandHandler("21")
    @PutMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDtoResponse update(CommentDtoRequest updateRequest) {
        return commentService.update(updateRequest);
    }

    @Override
    @CommandHandler("22")
    @DeleteMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteById(@PathVariable Long id) {
        return commentService.deleteById(id);
    }

    @Override
    @GetMapping("/{newsId:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDtoResponse> readByNewsId(@PathVariable Long newsId) {
        return null;
    }
}
