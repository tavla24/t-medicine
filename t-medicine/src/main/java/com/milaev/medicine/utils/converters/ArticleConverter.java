package com.milaev.medicine.utils.converters;

import com.milaev.medicine.dto.ArticleDTO;
import com.milaev.medicine.model.Article;
import com.milaev.medicine.utils.MapperUtil;

public class ArticleConverter {
    public static ArticleDTO toDTO(Article db){
        ArticleDTO dto = new ArticleDTO();
        MapperUtil.toDTOArticle().accept(db, dto);
        return dto;
    }

    public static Article toEntity(ArticleDTO dto, Article db){
        MapperUtil.toEntityArticle().accept(dto, db);
        return db;
    }
}
