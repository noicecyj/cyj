package com.example.cyjlog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QServerPO is a Querydsl query type for ServerPO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QServerPO extends EntityPathBase<ServerPO> {

    private static final long serialVersionUID = 318378880L;

    public static final QServerPO serverPO = new QServerPO("serverPO");

    public final StringPath description = createString("description");

    public final StringPath id = createString("id");

    public final StringPath serverName = createString("serverName");

    public final StringPath serverPort = createString("serverPort");

    public final StringPath sortCode = createString("sortCode");

    public QServerPO(String variable) {
        super(ServerPO.class, forVariable(variable));
    }

    public QServerPO(Path<? extends ServerPO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QServerPO(PathMetadata metadata) {
        super(ServerPO.class, metadata);
    }

}

