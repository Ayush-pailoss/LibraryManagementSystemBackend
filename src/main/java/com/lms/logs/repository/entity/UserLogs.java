package com.lms.logs.repository.entity;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Document(collection = "usersActivity")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogs {

	private String  level;
	private String message ;
	private String dateTime;
}
