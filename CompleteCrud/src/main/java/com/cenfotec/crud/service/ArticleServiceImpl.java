package com.cenfotec.crud.service;

import com.cenfotec.crud.domain.Article;
import com.cenfotec.crud.repo.ArticleRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    ArticleRepositoty repo;

    @Override
    public void save(Article article) {
        repo.save(article);
    }

    @Override
    public Optional<Article> get(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Article> find(String name) {
        return repo.findByNameContaining(name);
    }

    @Override
    public List<Article> getAll() {
        return repo.findAll();
    }

    @Override
    public void deleteArticle(Long id){ repo.deleteById(id); }
}
