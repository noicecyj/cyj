package com.example.cyjpagemenu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDataFormItemPO is a Querydsl query type for DataFormItemPO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDataFormItemPO extends EntityPathBase<DataFormItemPO> {

    private static final long serialVersionUID = 460609934L;

    public static final QDataFormItemPO dataFormItemPO = new QDataFormItemPO("dataFormItemPO");

    public final StringPath id = createString("id");

    public final StringPath jsonData = createString("jsonData");

    public final StringPath pid = createString("pid");

    public final StringPath sortCode = createString("sortCode");

    public QDataFormItemPO(String variable) {
        super(DataFormItemPO.class, forVariable(variable));
    }

    public QDataFormItemPO(Path<? extends DataFormItemPO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDataFormItemPO(PathMetadata metadata) {
        super(DataFormItemPO.class, metadata);
    }

}

