package com.example.cyjentitycreater.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEntityNamePO is a Querydsl query type for EntityNamePO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEntityNamePO extends EntityPathBase<EntityNamePO> {

    private static final long serialVersionUID = -37774278L;

    public static final QEntityNamePO entityNamePO = new QEntityNamePO("entityNamePO");

    public final StringPath api = createString("api");

    public final StringPath appName = createString("appName");

    public final StringPath formModelCode = createString("formModelCode");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath path = createString("path");

    public final StringPath relEntity = createString("relEntity");

    public final StringPath sortCode = createString("sortCode");

    public final StringPath tableModelCode = createString("tableModelCode");

    public final StringPath type = createString("type");

    public QEntityNamePO(String variable) {
        super(EntityNamePO.class, forVariable(variable));
    }

    public QEntityNamePO(Path<? extends EntityNamePO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEntityNamePO(PathMetadata metadata) {
        super(EntityNamePO.class, metadata);
    }

}

