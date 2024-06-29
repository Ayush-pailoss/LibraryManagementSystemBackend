package com.lms.users.controller.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BorrowBookRequest {
	
   private String email;
   private List<String> borrowBooks ;
      
}
