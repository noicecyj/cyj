package com.example.cyjpagemenu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.cyjpagemenu.entity.dto.DictionaryDTO;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDictionaryPO is a Querydsl query type for DictionaryPO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDictionaryPO extends EntityPathBase<DictionaryDTO> {

    private static final long serialVersionUID = 2009698467L;

    public static final QDictionaryPO dictionaryPO = new QDictionaryPO("dictionaryPO");

    public final StringPath dictionaryName = createString("dictionaryName");

    public final StringPath dictionaryValue = createString("dictionaryValue");

    public final StringPath id = createString("id");

    public final StringPath pid = createString("pid");

    public final StringPath sortCode = createString("sortCode");

    public QDictionaryPO(String variable) {
        super(DictionaryDTO.class, forVariable(variable));
    }

    public QDictionaryPO(Path<? extends DictionaryDTO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDictionaryPO(PathMetadata metadata) {
        super(DictionaryDTO.class, metadata);
    }

}

