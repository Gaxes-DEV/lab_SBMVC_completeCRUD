package com.cenfotec.crud.service;

import com.cenfotec.crud.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    public void save(Article article);
    public Optional<Article> get(Long id);
    public List<Article> getAll();
    public List<Article> find(String name);
    public void deleteArticle(Long id);
}
