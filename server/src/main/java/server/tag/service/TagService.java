package server.tag.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import server.exception.BusinessLogicException;
import server.exception.ExceptionCode;
import server.tag.entity.Tag;
import server.tag.repository.TagRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Page<Tag> findTags(int i, int size) {
        return null;
    }

    public List<Tag> findTags(){
        //Todo 해당 부분 수정 필요
        List<Tag> tags = new ArrayList<>();
        return tags;
    }

    public Tag findVerifiedTag(String name) {
        Optional<Tag> optionalTag = tagRepository.findByName(name);
        Tag findTag =
                optionalTag.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND));
        return findTag;
    }
}
