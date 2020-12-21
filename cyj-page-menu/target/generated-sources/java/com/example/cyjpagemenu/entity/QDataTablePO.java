package com.example.cyjpagemenu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDataTablePO is a Querydsl query type for DataTablePO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDataTablePO extends EntityPathBase<DataTablePO> {

    private static final long serialVersionUID = 74945013L;

    public static final QDataTablePO dataTablePO = new QDataTablePO("dataTablePO");

    public final StringPath dataTableName = createString("dataTableName");

    public final StringPath description = createString("description");

    public final StringPath id = createString("id");

    public final StringPath sortCode = createString("sortCode");

    public QDataTablePO(String variable) {
        super(DataTablePO.class, forVariable(variable));
    }

    public QDataTablePO(Path<? extends DataTablePO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDataTablePO(PathMetadata metadata) {
        super(DataTablePO.class, metadata);
    }

}

