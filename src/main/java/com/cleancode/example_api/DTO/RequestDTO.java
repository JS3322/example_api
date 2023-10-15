package com.cleancode.example_api.DTO;

import com.cleancode.example_api.CustomUtility.CustomAnnotation.SearchProjectId;
import lombok.Data;
import lombok.Getter;

import java.util.Map;

@Data
@Getter
public class RequestDTO {
    private String provider;
    @SearchProjectId
    private String[] searchList;
    private Map<String,?> requestMatchParameter;
    private Map<String,?> requestGroupByParameter;
    private Map<String,?> requestSortParameter;
}
