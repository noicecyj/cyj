package com.example.cyjdictionary.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDictionaryCatalog is a Querydsl query type for DictionaryCatalog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDictionaryCatalog extends EntityPathBase<DictionaryCatalogPO> {

    private static final long serialVersionUID = 618285421L;

    public static final QDictionaryCatalog dictionaryCatalog = new QDictionaryCatalog("dictionaryCatalog");

    public final StringPath catalogName = createString("catalogName");

    public final StringPath catalogValue = createString("catalogValue");

    public final StringPath description = createString("description");

    public final StringPath id = createString("id");

    public final StringPath sortCode = createString("sortCode");

    public QDictionaryCatalog(String variable) {
        super(DictionaryCatalogPO.class, forVariable(variable));
    }

    public QDictionaryCatalog(Path<? extends DictionaryCatalogPO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDictionaryCatalog(PathMetadata metadata) {
        super(DictionaryCatalogPO.class, metadata);
    }

}

