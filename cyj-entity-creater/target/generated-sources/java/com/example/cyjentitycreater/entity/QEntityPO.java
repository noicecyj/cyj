package com.example.cyjentitycreater.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEntityPO is a Querydsl query type for EntityPO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEntityPO extends EntityPathBase<EntityPO> {

    private static final long serialVersionUID = -1201056689L;

    public static final QEntityPO entityPO = new QEntityPO("entityPO");

    public final StringPath description = createString("description");

    public final StringPath entityName = createString("entityName");

    public final StringPath entityProperty = createString("entityProperty");

    public final StringPath id = createString("id");

    public final StringPath pid = createString("pid");

    public final StringPath sortCode = createString("sortCode");

    public QEntityPO(String variable) {
        super(EntityPO.class, forVariable(variable));
    }

    public QEntityPO(Path<? extends EntityPO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEntityPO(PathMetadata metadata) {
        super(EntityPO.class, metadata);
    }

}

