package exercises;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class CalculateIntrests {
	
	public enum Period{
		MONTHLY(1),
		QUARTERLY(4),
		YEARLY(12),
		SEMI_ANUALLY(24);
			
		private final int value;
		private Period(int value) {
			this.value=value;
		}

		public int getValue() {
			return value;
		}
	}
	
	
	public static BigDecimal simple_intrests(BigDecimal initialLoan, 
			BigDecimal intrests, BigDecimal duration, Period period) {
		return initialLoan.multiply(intrests).multiply(duration);
	}
	
	/**
	 * 
	 * @param initialLoan
	 * @param intrests
	 * @param duration
	 * @param period
	 * @return 
	 */
	public static BigDecimal compound_intrests_total_loan(BigDecimal initialLoan, 
		BigDecimal intrests, long duration) {
		
		//Less than 1 is good
		if(intrests.compareTo(BigDecimal.ONE)>=0) {
			throw new IllegalArgumentException("intrests");
		}
		
		BigDecimal intrestsSoFar = BigDecimal.ZERO;
		BigDecimal loanSoFar = initialLoan;
		
		for(long i = 0; i < duration; i++) {
			intrestsSoFar=loanSoFar.multiply(intrests);
			loanSoFar = loanSoFar.add(intrestsSoFar);
		}
		
		return loanSoFar;
	}

	//What is most important, 
	//interests paid after 3 years or the total loan after 3 years
	public static BigDecimal compound_intrests_total(BigDecimal initialLoan, 
			BigDecimal intrests, long duration) {
		return compound_intrests_total_loan(initialLoan, intrests, duration)
		.subtract(initialLoan);
	}
	
	public static Map<Period ,BigDecimal> compare_intrest_periods
		(BigDecimal initialLoan, BigDecimal intrests, long months) {
		
		HashMap<Period, BigDecimal> map = new HashMap<>();
		int precision = 2;
		
		BigDecimal totalMonthly = compound_intrests_total_loan
				(initialLoan, intrests, months).setScale(precision, RoundingMode.HALF_UP);
		map.put(Period.MONTHLY, totalMonthly);
		BigDecimal totalQuarterly = compound_intrests_total_loan
				(initialLoan, intrests, months/4).setScale(precision, RoundingMode.HALF_UP);;
		map.put(Period.QUARTERLY, totalQuarterly);
		BigDecimal totalYearly = compound_intrests_total_loan
				(initialLoan, intrests, months/12).setScale(precision, RoundingMode.HALF_UP);
		map.put(Period.YEARLY, totalYearly);
		BigDecimal totalSemi_Anually = compound_intrests_total_loan
				(initialLoan, intrests, months/24).setScale(precision, RoundingMode.HALF_UP);
		map.put(Period.SEMI_ANUALLY, totalSemi_Anually);
		
		return map;
	}
	
	/**
	 * 
	 * @param compPeriod
	 * @param intrestPeriod
	 * @param intrestRate
	 * @param initialLoan
	 * @param months
	 * @return
	 */
	public static BigDecimal seperateIntrestCalc(Period compPeriod, Period intrestPeriod,
			BigDecimal intrestRate, BigDecimal initialLoan, long months) {
		
		
		long compMonths = compPeriod.getValue();
		long intrestMonths = intrestPeriod.getValue();
		
		long intrestPeriods = months/intrestMonths;
		long compPeriods = months/compMonths; 
		
		BigDecimal intrestsSoFar = BigDecimal.ZERO;
		BigDecimal compoundSoFar = BigDecimal.ZERO;
		BigDecimal loanAccrued = initialLoan;
		BigDecimal totalIntrests = BigDecimal.ZERO;
		
		System.out.println("y:"+months+"|comp:"+compPeriods+"|int:"+intrestPeriods);
//		
//		//A = P(1+r/n)^(nt)
//		//Periods defined from intrest interval
		BigDecimal totalLoan = initialLoan.multiply(
				(BigDecimal.ONE.add(intrestRate.divide(
						BigDecimal.valueOf(compPeriods),50, RoundingMode.HALF_UP)))
				.pow((int)(intrestPeriods*compPeriods))
				);
		
		
		return totalLoan.setScale(2, RoundingMode.HALF_UP);
//		//totalIntrests = initialLoan.multiply(intrestRate);
//		BigDecimal balance = initialLoan;
//		
//		
//		for(long i = 1; i <= months; i++) {
//			if(i%intrestMonths==0 && i%compMonths==0) {
//				loanAccrued=loanAccrued.add(totalIntrests);
//				System.out.println("year" + loanAccrued);
//		  }
//			else if(i%compMonths==0) {
//				//every month
//				intrestsSoFar = initialLoan.multiply(intrestRate);
//				System.out.println(intrestsSoFar);
//
//				compoundSoFar=intrestsSoFar.multiply(intrestRate);
//				
//				totalIntrests = compoundSoFar.multiply(
//						balance.divide(BigDecimal.valueOf(compPeriods), 4, RoundingMode.HALF_UP));
//				System.out.println(compoundSoFar+  "total:" +totalIntrests);
//		  }
//			else if(i%intrestMonths==0) {
//		    intrestsSoFar = initialLoan.multiply(intrestRate);
//		    //Loan only increases yearly?
//		    
//		    loanAccrued = loanAccrued.add(intrestsSoFar.add(compoundSoFar));
//		    System.out.println("Year loanAccrued" + loanAccrued + "|comp"+compoundSoFar);
//		    
//		  }
//		  
//
//		}
//
//		return loanAccrued;
	}
	

	
	
	
}
