package exercises;

import java.math.BigDecimal;

public enum cashTypes {
	ONE_HUNDRED(new BigDecimal("100")),
	FIFTY(new BigDecimal("50")),
	TWENTY(new BigDecimal("20")),
	TEN(new BigDecimal("10")),
	FIVE(new BigDecimal("5")),
	TWO(new BigDecimal("2")),
	ONE(new BigDecimal("1")),
	HALF_DOLLAR(new BigDecimal("0.5")),
	QUARTER(new BigDecimal("0.25")),
	DIME(new BigDecimal("0.10")),
	NICKEL(new BigDecimal("0.05")),
	PENNY(new BigDecimal("0.01"));

		
	private final BigDecimal value;
	private cashTypes(BigDecimal value) {
		this.value=value;
	}

	public BigDecimal getValue() {
		return value;
	}

}
