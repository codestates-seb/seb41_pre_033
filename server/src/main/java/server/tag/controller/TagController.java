package server.tag.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.dto.MultiResponseDto;
import server.tag.entity.Tag;
import server.tag.mapper.TagMapper;
import server.tag.service.TagService;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/tags")
@Validated
public class TagController {
    private static final int SIZE = 36;
    private final TagService tagService;
    private final TagMapper tagMapper;

    public TagController(TagService tagService, TagMapper tagMapper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
    }

    @GetMapping
    public ResponseEntity getTags(@Positive @RequestParam int page,
                                  @RequestParam(required = false, defaultValue = "name")String tab){
        Page<Tag> pageTags = tagService.findTags(page-1, SIZE, tab);
        List<Tag> tags = pageTags.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(tagMapper.tagsToTagResponses(tags),pageTags),
                HttpStatus.OK);
    }

//    //Todo: search 기능 추가, 정렬
//    @GetMapping("/search")
//    public ResponseEntity getSearchTags(@Positive @RequestParam int page,
//                                        @RequestParam String search){
//        List<Tag> tags = tagService.findTags();
//
//        return new ResponseEntity<>(
//                new MultiResponseDto<>(tagMapper.tagsToTagResponseDtos(tags)),
//                HttpStatus.OK);
//    }
}
