package server.tag.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = { "Tag Controller" })
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

    @ApiOperation(value = "전체 태그 조회", notes = "page 번호와 정렬 기준을 tab으로 받아서 기본은 이름순, 다른 옵션으로는 used를 기준으로 정렬하여 페이지네이션을 응답으로 반환한다.")
    @GetMapping
    public ResponseEntity getTags(@ApiParam(value = "page 번호 입력") @Positive @RequestParam int page,
                                  @ApiParam(value = "name(default), used 중 하나를 선택해서 입력") @RequestParam(required = false, defaultValue = "name")String tab){
        Page<Tag> pageTags = tagService.findTags(page-1, SIZE, tab);
        List<Tag> tags = pageTags.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(tagMapper.tagsToTagResponses(tags),pageTags),
                HttpStatus.OK);
    }

//    //Todo: search 기능 추가, 정렬
    @ApiOperation(value = "태그 검색", notes = "page 번호와 검색어를 입력받아서 전체 목록을 응답으로 반환")
    @GetMapping("/search")
    public ResponseEntity searchTags(@ApiParam(value = "검색어 입력")@RequestParam String q){
        List<Tag> tags = tagService.searchTags(q);

        return new ResponseEntity<>(
                new MultiResponseDto<>(tagMapper.tagsToTagResponses(tags)),
                HttpStatus.OK);
}
}
