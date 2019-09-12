package com.milaev.medicine.service;

import com.milaev.medicine.dao.ArticlesDAO;
import com.milaev.medicine.dto.ArticleDTO;
import com.milaev.medicine.model.Article;
import com.milaev.medicine.utils.converters.ArticleConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService extends AbstractService  {

    private static Logger log = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    ArticlesDAO articlesDAO;

    @Transactional
    public List<ArticleDTO> getAll() {
        log.info("called AccountService.getAll");
        List<Article> list = articlesDAO.getAll();
        List<ArticleDTO> listDAO = new ArrayList<>();
        for (Article item : list) {
            listDAO.add(fillDTO(item));
        }
        return listDAO;
    }

    private ArticleDTO fillDTO(Article db) {
        log.info("called AccountService.fillDTO with db");
        if (db != null) {
            ArticleDTO dto = ArticleConverter.toDTO(db);
            return dto;
        }
        return null;
    }
}
