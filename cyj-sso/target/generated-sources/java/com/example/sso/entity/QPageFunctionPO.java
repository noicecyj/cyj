package com.example.sso.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPageFunctionPO is a Querydsl query type for PageFunctionPO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPageFunctionPO extends EntityPathBase<PageFunctionPO> {

    private static final long serialVersionUID = -455184895L;

    public static final QPageFunctionPO pageFunctionPO = new QPageFunctionPO("pageFunctionPO");

    public final StringPath id = createString("id");

    public QPageFunctionPO(String variable) {
        super(PageFunctionPO.class, forVariable(variable));
    }

    public QPageFunctionPO(Path<? extends PageFunctionPO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPageFunctionPO(PathMetadata metadata) {
        super(PageFunctionPO.class, metadata);
    }

}

