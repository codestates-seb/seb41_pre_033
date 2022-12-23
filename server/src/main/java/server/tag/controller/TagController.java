package server.tag.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.tag.mapper.TagMapper;
import server.tag.service.TagService;

@RestController
@RequestMapping("/tags")
@Validated
public class TagController {
    private TagService tagService;
    private TagMapper tagMapper;

    public TagController(TagService tagService, TagMapper tagMapper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
    }
}
