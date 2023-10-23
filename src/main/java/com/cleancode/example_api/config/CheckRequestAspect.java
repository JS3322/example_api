package com.cleancode.example_api.config;

import com.cleancode.example_api.DTO.RequestDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckRequestAspect {

//    @Before("@annotation(CheckRequestParameters)")
//    public void checkParameters(JoinPoint joinPoint) throws Exception {
//        for (Object arg : joinPoint.getArgs()) {
//            if (arg instanceof RequestDTO) {
//                RequestDTO dto = (RequestDTO) arg;
//
//                if (dto.getRequestMatchParameter() != null && !dto.getRequestMatchParameter().isEmpty()) {
//                    System.out.println("100");
//                }
//
//                if (dto.getRequestGroupByParameter() != null && !dto.getRequestGroupByParameter().isEmpty()) {
//                    System.out.println("200");
//                }
//
//                if (dto.getRequestSortParameter() != null && !dto.getRequestSortParameter().isEmpty()) {
//                    System.out.println("300");
//                }
//            }
//        }
//    }
}
