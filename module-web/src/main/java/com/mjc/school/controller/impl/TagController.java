package com.mjc.school.controller.impl;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.interfaces.TagControllerInterface;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.interfaces.TagServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tags", consumes = {"application/JSON"}, produces = {"application/JSON", "application/XML"})
public class TagController implements TagControllerInterface {

    private final TagServiceInterface tagService;

    @Autowired
    public TagController(@Qualifier("tagService") TagServiceInterface tagService) {
        this.tagService = tagService;
    }

    @CommandHandler("3")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TagDtoResponse> readAll(@RequestParam String sort,
                                        @RequestParam int page) {
        List<TagDtoResponse> tags = tagService.readAll();
        if (tags.isEmpty()) {
            System.out.println("There is no tag data in the database yet.");
        }
        return tags;
    }

    @CommandHandler("6")
    @GetMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public TagDtoResponse readById(@PathVariable Long id) {
        return tagService.readById(id);
    }

    @CommandHandler("9")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagDtoResponse create(@RequestBody TagDtoRequest dtoRequest) {
        return tagService.create(dtoRequest);
    }

    @CommandHandler("12")
    @PutMapping("/{id}:\\d+")
    @ResponseStatus(HttpStatus.OK)
    public TagDtoResponse update(@RequestBody TagDtoRequest dtoRequest) {
        return tagService.update(dtoRequest);
    }

    @CommandHandler("15")
    @DeleteMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        tagService.deleteById(id);
    }

    @CommandHandler("17")
    @GetMapping("/{newsId:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public List<TagDtoResponse> readByNewsId(@PathVariable Long newsId) {
        return tagService.readByNewsId(newsId);
    }
}
