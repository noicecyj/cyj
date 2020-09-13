package com.example.cyjdictionary.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCatalogPO is a Querydsl query type for CatalogPO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCatalogPO extends EntityPathBase<CatalogPO> {

    private static final long serialVersionUID = 165521570L;

    public static final QCatalogPO catalogPO = new QCatalogPO("catalogPO");

    public final StringPath catalogName = createString("catalogName");

    public final StringPath catalogValue = createString("catalogValue");

    public final StringPath description = createString("description");

    public final StringPath id = createString("id");

    public final StringPath sortCode = createString("sortCode");

    public QCatalogPO(String variable) {
        super(CatalogPO.class, forVariable(variable));
    }

    public QCatalogPO(Path<? extends CatalogPO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCatalogPO(PathMetadata metadata) {
        super(CatalogPO.class, metadata);
    }

}

