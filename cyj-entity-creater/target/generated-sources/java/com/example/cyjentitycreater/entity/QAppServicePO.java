package com.example.cyjentitycreater.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAppServicePO is a Querydsl query type for AppServicePO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAppServicePO extends EntityPathBase<AppServicePO> {

    private static final long serialVersionUID = 904652544L;

    public static final QAppServicePO appServicePO = new QAppServicePO("appServicePO");

    public final StringPath appApi = createString("appApi");

    public final StringPath appPort = createString("appPort");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath sortCode = createString("sortCode");

    public QAppServicePO(String variable) {
        super(AppServicePO.class, forVariable(variable));
    }

    public QAppServicePO(Path<? extends AppServicePO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAppServicePO(PathMetadata metadata) {
        super(AppServicePO.class, metadata);
    }

}

