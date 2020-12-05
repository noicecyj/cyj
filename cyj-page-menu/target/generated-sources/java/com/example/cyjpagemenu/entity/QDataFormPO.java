package com.example.cyjpagemenu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDataFormPO is a Querydsl query type for DataFormPO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDataFormPO extends EntityPathBase<DataFormPO> {

    private static final long serialVersionUID = -939173605L;

    public static final QDataFormPO dataFormPO = new QDataFormPO("dataFormPO");

    public final StringPath dataFormName = createString("dataFormName");

    public final StringPath dataFormType = createString("dataFormType");

    public final StringPath description = createString("description");

    public final StringPath id = createString("id");

    public final StringPath sortCode = createString("sortCode");

    public QDataFormPO(String variable) {
        super(DataFormPO.class, forVariable(variable));
    }

    public QDataFormPO(Path<? extends DataFormPO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDataFormPO(PathMetadata metadata) {
        super(DataFormPO.class, metadata);
    }

}

