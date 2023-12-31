package com.cleancode.example_api.CustomUtility.CustomAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SearchProjectId {
    String[] value() default {"project1", "project2", "project3"};

    //Todo [js] 부서정보가 있을 경우 조회하여 검색해야 할 프로젝트 리스트를 뽑아오기
}
