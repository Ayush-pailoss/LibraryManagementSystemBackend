package com.lms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LibraryManagementSystemApplicationTests {
Calc calc = new Calc();
	
@Test
	void sumTest() {
	
	float expectedOp= 5;
	calc.add(3,2);
	assertEquals(expectedOp, 6);
	}

@Test
void muliTest() {
	 float expOp=10;
	 calc.multi(5, 5);
	 assertEquals(expOp, 25);
}
}
