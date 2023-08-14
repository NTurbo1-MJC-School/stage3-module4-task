package com.mjc.school.controller.impl;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.interfaces.AuthorControllerInterface;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.interfaces.AuthorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/authors", consumes = {"application/JSON"}, produces = {"application/JSON", "application/XML"})
public class AuthorController implements AuthorControllerInterface {

  private final AuthorServiceInterface authorService;

  @Autowired
  public AuthorController(@Qualifier("authorService") AuthorServiceInterface authorService) {
    this.authorService = authorService;
  }

  @CommandHandler("2")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<AuthorDtoResponse> readAll(@RequestParam String sort,
                                         @RequestParam int page) {
    List<AuthorDtoResponse> authors = authorService.readAll();
    if (authors.isEmpty()) {
      System.out.println("There is no author data in the database yet.");
    }
    return authors;
  }

  @CommandHandler("5")
  @GetMapping("/{id:\\d+}")
  @ResponseStatus(HttpStatus.OK)
  public AuthorDtoResponse readById(@PathVariable Long id) {
      return authorService.readById(id);
  }

  @CommandHandler("8")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AuthorDtoResponse create(@RequestBody AuthorDtoRequest dtoRequest) {
    return authorService.create(dtoRequest);
  }

  @CommandHandler("11")
  @PutMapping("/{id:\\d+}")
  @ResponseStatus(HttpStatus.OK)
  public AuthorDtoResponse update(@RequestBody AuthorDtoRequest dtoRequest) {
    return authorService.update(dtoRequest);
  }

  @CommandHandler("14")
  @DeleteMapping("/{id:\\d+}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable Long id) {
      authorService.deleteById(id);
  }

  @CommandHandler("16")
  @GetMapping("/{newsId:\\d+}")
  @ResponseStatus(HttpStatus.OK)
  public AuthorDtoResponse readByNewsId(@PathVariable Long newsId) {
    return authorService.readByNewsId(newsId);
  }
}
