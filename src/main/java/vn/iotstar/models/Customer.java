package vn.iotstar.models;

import java.io.Serializable;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Customer implements Serializable{
	private String id;
	private String name;
	private String phoneNumber;
	private String email;	
	
	
	
	
}
