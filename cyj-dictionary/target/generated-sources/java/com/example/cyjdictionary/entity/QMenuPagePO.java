package com.example.cyjdictionary.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenuPagePO is a Querydsl query type for MenuPagePO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMenuPagePO extends EntityPathBase<MenuPagePO> {

    private static final long serialVersionUID = -1880065149L;

    public static final QMenuPagePO menuPagePO = new QMenuPagePO("menuPagePO");

    public final StringPath icon = createString("icon");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath path = createString("path");

    public final StringPath pid = createString("pid");

    public final StringPath sortCode = createString("sortCode");

    public QMenuPagePO(String variable) {
        super(MenuPagePO.class, forVariable(variable));
    }

    public QMenuPagePO(Path<? extends MenuPagePO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenuPagePO(PathMetadata metadata) {
        super(MenuPagePO.class, metadata);
    }

}

