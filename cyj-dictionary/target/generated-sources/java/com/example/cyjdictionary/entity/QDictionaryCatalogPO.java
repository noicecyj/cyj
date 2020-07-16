package com.example.cyjdictionary.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDictionaryCatalogPO is a Querydsl query type for DictionaryCatalogPO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDictionaryCatalogPO extends EntityPathBase<DictionaryCatalogPO> {

    private static final long serialVersionUID = 1466805292L;

    public static final QDictionaryCatalogPO dictionaryCatalogPO = new QDictionaryCatalogPO("dictionaryCatalogPO");

    public final StringPath catalogName = createString("catalogName");

    public final StringPath catalogValue = createString("catalogValue");

    public final StringPath description = createString("description");

    public final StringPath id = createString("id");

    public final StringPath sortCode = createString("sortCode");

    public QDictionaryCatalogPO(String variable) {
        super(DictionaryCatalogPO.class, forVariable(variable));
    }

    public QDictionaryCatalogPO(Path<? extends DictionaryCatalogPO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDictionaryCatalogPO(PathMetadata metadata) {
        super(DictionaryCatalogPO.class, metadata);
    }

}

