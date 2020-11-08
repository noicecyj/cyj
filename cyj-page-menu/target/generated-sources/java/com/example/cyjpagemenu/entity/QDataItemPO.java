package com.example.cyjpagemenu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDataItemPO is a Querydsl query type for DataItemPO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDataItemPO extends EntityPathBase<DataItemPO> {

    private static final long serialVersionUID = -849055830L;

    public static final QDataItemPO dataItemPO = new QDataItemPO("dataItemPO");

    public final StringPath id = createString("id");

    public final StringPath label = createString("label");

    public final StringPath name = createString("name");

    public final StringPath pid = createString("pid");

    public final StringPath required = createString("required");

    public final StringPath sortCode = createString("sortCode");

    public final StringPath type = createString("type");

    public QDataItemPO(String variable) {
        super(DataItemPO.class, forVariable(variable));
    }

    public QDataItemPO(Path<? extends DataItemPO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDataItemPO(PathMetadata metadata) {
        super(DataItemPO.class, metadata);
    }

}

