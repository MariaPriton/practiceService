package com.example.practiceservice1.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DroolsLoanDetailsPayload {

	Integer amount;
	Integer tenure;
	Integer pf;
	Integer gst;
	Integer insurance;
	Double total_fee;
	int installments;
	String type;
}
