package com.example.cyjdictionary.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDictionary is a Querydsl query type for Dictionary
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDictionary extends EntityPathBase<DictionaryPO> {

    private static final long serialVersionUID = -901011188L;

    public static final QDictionary dictionary = new QDictionary("dictionary");

    public final StringPath dictionaryName = createString("dictionaryName");

    public final StringPath dictionaryValue = createString("dictionaryValue");

    public final StringPath id = createString("id");

    public final StringPath pid = createString("pid");

    public final StringPath sortCode = createString("sortCode");

    public QDictionary(String variable) {
        super(DictionaryPO.class, forVariable(variable));
    }

    public QDictionary(Path<? extends DictionaryPO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDictionary(PathMetadata metadata) {
        super(DictionaryPO.class, metadata);
    }

}

