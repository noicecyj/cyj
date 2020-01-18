package com.example.cyjdictionary.serviceImpl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class baseService {

    @Autowired
    @PersistenceContext
    protected EntityManager entityManager;

    protected JPAQueryFactory queryFactory;

    @PostConstruct
    public void init(){
        queryFactory = new JPAQueryFactory(entityManager);
    }
}
