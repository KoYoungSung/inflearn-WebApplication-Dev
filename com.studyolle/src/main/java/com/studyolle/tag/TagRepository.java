package com.studyolle.tag;

import com.studyolle.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByTitle(String title);
}
