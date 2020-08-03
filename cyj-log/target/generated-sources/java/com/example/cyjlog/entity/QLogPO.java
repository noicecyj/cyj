package com.example.cyjlog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLogPO is a Querydsl query type for LogPO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLogPO extends EntityPathBase<LogPO> {

    private static final long serialVersionUID = -2031755771L;

    public static final QLogPO logPO = new QLogPO("logPO");

    public final StringPath appPort = createString("appPort");

    public final StringPath className = createString("className");

    public final StringPath classpath = createString("classpath");

    public final StringPath createDate = createString("createDate");

    public final StringPath id = createString("id");

    public final StringPath method = createString("method");

    public final StringPath msg = createString("msg");

    public final StringPath msgLevel = createString("msgLevel");

    public final StringPath threadName = createString("threadName");

    public QLogPO(String variable) {
        super(LogPO.class, forVariable(variable));
    }

    public QLogPO(Path<? extends LogPO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLogPO(PathMetadata metadata) {
        super(LogPO.class, metadata);
    }

}

