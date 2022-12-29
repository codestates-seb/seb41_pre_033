package server.tag.mapper;

import org.mapstruct.Mapper;
import server.tag.dto.TagDto;
import server.tag.entity.Tag;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    List<TagDto.Response> tagsToTagResponses(List<Tag> tags);
}
