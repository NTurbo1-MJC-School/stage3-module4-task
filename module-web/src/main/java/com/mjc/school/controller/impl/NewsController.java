package com.mjc.school.controller.impl;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.interfaces.NewsControllerInterface;
import com.mjc.school.service.interfaces.NewsServiceInterface;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/news", consumes = {"application/JSON"}, produces = {"application/JSON", "application/XML"})
public class NewsController implements NewsControllerInterface {

  private final NewsServiceInterface newsService;

  @Autowired
  public NewsController(@Qualifier("newsService") NewsServiceInterface newsService) {
    this.newsService = newsService;
  }

  @CommandHandler("1")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<NewsDtoResponse> readAll(@RequestParam String sort,
                                       @RequestParam int page) {
    List<NewsDtoResponse> news = newsService.readAll();
    if (news.isEmpty()) {
      System.out.println("There is no news data in the database yet.");
    }
    return news;
  }

  @CommandHandler("4")
  @GetMapping("/{id:\\d+}")
  @ResponseStatus(HttpStatus.OK)
  public NewsDtoResponse readById(@PathVariable Long id) {
    return newsService.readById(id);
  }

  @CommandHandler("7")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public NewsDtoResponse create(@RequestBody NewsDtoRequest dtoRequest) {
    return newsService.create(dtoRequest);
  }

  @CommandHandler("10")
  @PutMapping("/{id:\\d+}")
  @ResponseStatus(HttpStatus.OK)
  public NewsDtoResponse update(@PathVariable Long id,
                                @RequestBody NewsDtoRequest dtoRequest) {
    return newsService.update(dtoRequest);
  }

  @CommandHandler("13")
  @DeleteMapping("/{id:\\d+}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable Long id) {
    newsService.deleteById(id);
  }
}
