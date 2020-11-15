package com.cenfotec.crud.repo;

import com.cenfotec.crud.domain.Article;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepositoty extends JpaRepository<Article, Long> {
    public List<Article> findByNameContaining(String word);

}
