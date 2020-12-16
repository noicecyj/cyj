package com.example.cyjpagemenu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDataTableItemPO is a Querydsl query type for DataTableItemPO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDataTableItemPO extends EntityPathBase<DataTableItemPO> {

    private static final long serialVersionUID = -267741848L;

    public static final QDataTableItemPO dataTableItemPO = new QDataTableItemPO("dataTableItemPO");

    public final StringPath dataIndex = createString("dataIndex");

    public final StringPath id = createString("id");

    public final StringPath pid = createString("pid");

    public final StringPath sortCode = createString("sortCode");

    public final StringPath title = createString("title");

    public final StringPath width = createString("width");

    public QDataTableItemPO(String variable) {
        super(DataTableItemPO.class, forVariable(variable));
    }

    public QDataTableItemPO(Path<? extends DataTableItemPO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDataTableItemPO(PathMetadata metadata) {
        super(DataTableItemPO.class, metadata);
    }

}

