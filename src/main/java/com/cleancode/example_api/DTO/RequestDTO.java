package com.cleancode.example_api.DTO;

import com.cleancode.example_api.CustomUtility.CustomAnnotation.SearchProjectId;
import lombok.Data;

import java.util.Map;

@Data
public class RequestDTO {
    private String provider;
    @SearchProjectId
    private String[] searchList;
    private Map<String,?> requestMatchParameter;
    private Map<String,?> requestGroupByParameter;
    private Map<String,?> requestSortParameter;

	//시간정보 설정: localDateTime
	private LocalDateTime today_time;

//계정생성: read권한+test db권한
db.createUser({
	user: "readAccountExample",
	pwd: "1234"
	roles: [{
		role: "read", db:"test"
	}]
})



}
