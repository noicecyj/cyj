package com.example.cyjapi.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRolePO is a Querydsl query type for RolePO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRolePO extends EntityPathBase<RolePO> {

    private static final long serialVersionUID = 2115089353L;

    public static final QRolePO rolePO = new QRolePO("rolePO");

    public final StringPath description = createString("description");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath sortCode = createString("sortCode");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QRolePO(String variable) {
        super(RolePO.class, forVariable(variable));
    }

    public QRolePO(Path<? extends RolePO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRolePO(PathMetadata metadata) {
        super(RolePO.class, metadata);
    }

}

