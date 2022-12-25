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
import server.tag.dto.TagDto;
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
    private TagService tagService;
    private TagMapper tagMapper;

    public TagController(TagService tagService, TagMapper tagMapper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
    }

    @GetMapping("/tags")
    public ResponseEntity getTags(@Positive @RequestParam int page){
        Page<Tag> pageTags = tagService.findTags(page-1, SIZE);
        List<Tag> tags = pageTags.getContent();

        //Todo 정렬 기능 추가
//
//        return new ResponseEntity<>(
//                new MultiResponseDto<>(tagMapper.tagsToTagResponseDtos(tags),pageTags),
//                HttpStatus.OK);
        return null;
    }

    //Todo: search 기능 추가, 정렬
    @GetMapping("/tags/search")
    public ResponseEntity getSearchTags(@Positive @RequestParam int page,
                                        @RequestParam String search){
        List<Tag> tags = tagService.findTags();

//        return new ResponseEntity<>(
//                new MultiResponseDto<>(tagMapper.tagsToTagResponseDtos(tags)),
//                HttpStatus.OK);
        return null;
    }
}
