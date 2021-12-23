package exercises;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalculateChangeTest {
	private final static Logger LOGGER = 
			Logger.getLogger(CalculateChangeTest.class.getName());
	
	@Test
	void is_numeric_excludes_word_strings() {
		boolean actual = CalculateChange.isNumeric("fun");
		assertFalse(actual);	
	}
	
	@Test
	void is_numeric_includes_decimal_string() {
		boolean actual = CalculateChange.isNumeric("3.3");
		assertTrue(actual);
	}
	
	@Test
	void change_whole_number_test() {
		BigDecimal expected = new BigDecimal(100);
		BigDecimal actual = CalculateChange.calculateChange(
				new BigDecimal(400), new BigDecimal(300));
		assertEquals(expected, actual);
	}
	
	//Includes the message check, since the negative input check might trigger this
	@Test
	void input_cash_less_than_cost() {
	  Exception exception = assertThrows(Exception.class, () ->{
	  	CalculateChange.calculateChange(new BigDecimal(30), new BigDecimal(40));
	  }); 

	  LOGGER.log(Level.INFO, exception.getMessage());
	}
	
	//input_cash_less_than_cost can also trigger IllegalArgument, so gotta check message also
	@Test
	void negative_input_cash_or_cost() {
		Exception ex = assertThrows(Exception.class, () ->{
			CalculateChange.calculateChange(new BigDecimal(-10), new BigDecimal(10));
		});	 
		LOGGER.log(Level.INFO, ex.getMessage());
		
		Exception exCost = assertThrows(Exception.class, () ->{
			CalculateChange.calculateChange(new BigDecimal(10), new BigDecimal(-10));
		});	
		LOGGER.log(Level.INFO, exCost.getMessage());

	}
	
  
	@Test
	void change_decimal_number_test() {
		BigDecimal expected = new BigDecimal(14.02);
		BigDecimal actual = CalculateChange.calculateChange(
				new BigDecimal(22.22), new BigDecimal(8.2));
		assertEquals(expected, actual);

	}
	
	@Test
	void run_change_calculator_empty_or_null_string() {
		Exception nullCostEx = assertThrows(Exception.class, () ->{
			CalculateChange.runTheChangeCalculator("40", null);
		});	
		LOGGER.log(Level.INFO, nullCostEx.getMessage());
		Exception nullCashEx = assertThrows(Exception.class, () ->{
			CalculateChange.runTheChangeCalculator(null, "40");
		});	
		LOGGER.log(Level.INFO, nullCashEx.getMessage());
		Exception emptyCashEx = assertThrows(Exception.class, () ->{
			CalculateChange.runTheChangeCalculator("", "40");
		});
		LOGGER.log(Level.INFO, emptyCashEx.getMessage());
		Exception emptyCostEx = assertThrows(Exception.class, () ->{
			CalculateChange.runTheChangeCalculator("40", "");
		});
		LOGGER.log(Level.INFO, emptyCostEx.getMessage());
	}
	
	@Test
	void format_whole_number() {
		
		String test = CalculateChange.formatMoney(new BigDecimal(3));
		
		assertTrue(test.contains("3"));
		assertTrue(test.contains("€"));
	}
	
	@Test
	void format_decimal_number() {
		
		String oneDecimal = CalculateChange.formatMoney(new BigDecimal("3.5"));
		assertTrue(oneDecimal.contains("3.5"));
		assertTrue(oneDecimal.contains("€"));
		String threeDecimal = CalculateChange.formatMoney(new BigDecimal("3.555"));
		assertTrue(threeDecimal.contains("3.555"));
		String fourDecimal = CalculateChange.formatMoney(new BigDecimal("3.5555"));
		assertTrue(fourDecimal.contains("3.556"));
	}

	@Test
	void run_calculator_non_numeric() {
		Exception CashNumberEx = assertThrows(Exception.class, ()->{
			CalculateChange.runTheChangeCalculator("ape", "30");
		});
		LOGGER.log(Level.INFO, CashNumberEx.getMessage());
		Exception CostNumberEx = assertThrows(Exception.class, ()->{
			CalculateChange.runTheChangeCalculator("30", "ape");
		});
		LOGGER.log(Level.INFO, CostNumberEx.getMessage());
	}
}
