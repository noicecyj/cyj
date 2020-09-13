package com.example.cyjquery.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSqlPO is a Querydsl query type for SqlPO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSqlPO extends EntityPathBase<SqlPO> {

    private static final long serialVersionUID = -1269900725L;

    public static final QSqlPO sqlPO = new QSqlPO("sqlPO");

    public final StringPath id = createString("id");

    public final StringPath sortCode = createString("sortCode");

    public final StringPath sqlDescription = createString("sqlDescription");

    public final StringPath sqlStr = createString("sqlStr");

    public final StringPath sqlType = createString("sqlType");

    public QSqlPO(String variable) {
        super(SqlPO.class, forVariable(variable));
    }

    public QSqlPO(Path<? extends SqlPO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSqlPO(PathMetadata metadata) {
        super(SqlPO.class, metadata);
    }

}

