package server.tag.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import server.tag.entity.Tag;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {
    public static Page<Tag> findTags(int i, int size) {
        return null;
    }

    public static List<Tag> findTags(){
        //Todo 해당 부분 수정 필요
        List<Tag> tags = new ArrayList<>();
        return tags;
    }
}
