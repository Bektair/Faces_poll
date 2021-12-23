package exercises;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import org.junit.jupiter.api.Test;

import exercises.CalculateIntrests.Period;

public class CalculateIntrestsTest {

	
	
	//Calculate simple intrests on initial loan, save this for later
	//Compound = Sum the initial loan and the calculated intrests so far
	//then calculate compuound
	
	
	@Test
	void calculate_simple_monthly_intrests() {
		//
		BigDecimal intrests = new BigDecimal("0.1");
		BigDecimal initialLoan = new BigDecimal("10000");
		BigDecimal months = new BigDecimal("2");
		
		BigDecimal expected  = initialLoan.multiply(intrests).multiply(months);
		BigDecimal actual = CalculateIntrests.simple_intrests
				(initialLoan,intrests,months, CalculateIntrests.Period.MONTHLY);
		assertEquals(expected, actual);
	}
	
	@Test
	void calculate_total_compound_loan() {
		long duration = 3;
		BigDecimal expected = BigDecimal.ZERO;
		
		BigDecimal intrests = new BigDecimal("0.05");
		BigDecimal intrestsSoFar = BigDecimal.ZERO;
		BigDecimal initialLoan = new BigDecimal("10000");
		BigDecimal loanSoFar = initialLoan;
	
		for(long i = 0; i < duration; i++) {
			intrestsSoFar=loanSoFar.multiply(intrests);
			loanSoFar = loanSoFar.add(intrestsSoFar);
		}
		
		expected = loanSoFar;
		
		//Value given with dates?
		BigDecimal actual = 
				CalculateIntrests.compound_intrests_total_loan(initialLoan,intrests,duration);
		assertEquals(expected, actual);
		
	}
	
	@Test
	void calculate_total_compound_intrests() {
		long duration = 3;
		BigDecimal intrests = new BigDecimal("0.05");
		BigDecimal initialLoan = new BigDecimal("10000");
		BigDecimal expected = new BigDecimal("1576.25");
		
		//Value given with dates?
		BigDecimal actual = 
				CalculateIntrests.compound_intrests_total(initialLoan,intrests,duration);
		assertEquals(expected, actual.stripTrailingZeros());
		
	}

	@Test
	void high_intrests() {
		BigDecimal maxIntrests = BigDecimal.ONE;
		Exception ex = assertThrows(Exception.class,()->{
		CalculateIntrests.compound_intrests_total_loan(
				new BigDecimal("1000"),maxIntrests,5);
		});
	}
	
	//Negative intrests exist
	@Test
	void negative_compound_intrests() {
		long duration = 3;
		BigDecimal intrests = new BigDecimal("-0.05");
		BigDecimal initialLoan = new BigDecimal("10000");
		BigDecimal expected = new BigDecimal("-1426.25");
		
		BigDecimal actual = 
				CalculateIntrests.compound_intrests_total(initialLoan,intrests,duration);
		assertEquals(expected, actual.stripTrailingZeros());
		
		
	}
	
	@Test
	void compare_reporting_periods() {
		//YOU CAN HAVE SEPERATE COMPIND AND INTEREST INTERVALS :O
		// https://www.thecalculatorsite.com/finance/calculators/compoundinterestcalculator.php
		
		BigDecimal expectedMonthly = new BigDecimal("32251.00");
		BigDecimal expectedSemiannually = new BigDecimal("10500.00");
		BigDecimal expectedYearly = new BigDecimal("11025.00");
		
		long months = 24;
		//Intrests represent both compound and regular intrests
		BigDecimal intrests = new BigDecimal("0.05");
		BigDecimal initialLoan = new BigDecimal("10000");
		Map<CalculateIntrests.Period, BigDecimal>all = CalculateIntrests
				.compare_intrest_periods(initialLoan,intrests,months);
		
		BigDecimal actualMonthly = all.get(CalculateIntrests.Period.MONTHLY);
		assertEquals(expectedMonthly, actualMonthly);
		BigDecimal actualYearly = all.get(CalculateIntrests.Period.YEARLY);
		assertEquals(expectedYearly, actualYearly);
		BigDecimal actualSemi_anually = all.get(CalculateIntrests.Period.SEMI_ANUALLY);
		assertEquals(expectedSemiannually, actualSemi_anually); 
		
	}
	
	@Test
	void seperateIntrest_yearly() {
		
		BigDecimal intrests = new BigDecimal("0.02");
		BigDecimal initialLoan = new BigDecimal("1000");
		
		BigDecimal expected = new BigDecimal("1020.18");
		BigDecimal actual = CalculateIntrests.seperateIntrestCalc
				(Period.MONTHLY, Period.YEARLY, intrests, initialLoan, 12);
		assertEquals(expected, actual);
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
