package server.tag.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.exception.BusinessLogicException;
import server.exception.ExceptionCode;
import server.tag.entity.Tag;
import server.tag.repository.TagRepository;

import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Page<Tag> findTags(int page, int size, String tab){
        Page<Tag> result;
        if ("popular".equals(tab)) {
            result = tagRepository.findAll(PageRequest.of(page, size,
                    Sort.by("used").descending()));
        } else {
            result = tagRepository.findAll(PageRequest.of(page, size,
                    Sort.by("name").ascending()));
        }
        return result;
    }

    public void findVerifiedTag(String name) {
        Optional<Tag> optionalTag = tagRepository.findByName(name);
                optionalTag.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND));
    }
}
