package exercises;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CalculateChange {
	
	
	private final static Logger LOGGER = 
			Logger.getLogger(CalculateChange.class.getName());
	private final static String NEGATIVE = "You input a negative number"; 
	private final static String WRONG = "You input less cash than cost, "
			+ " either cost or input cash is wrong";
	private final static String EMPTY = "Your input is empty";
	private final static String NOTNUMBER = "Your input isn't a number";
  
	//Supposed to have a bundle of exceptions that both test and class can reference
  //Like in a messages.properties file instead of this
	public static String getNOTNUMBER() {return NOTNUMBER;}
	
	public static String getEmpty() {return EMPTY;}

	public static String getNEGATIVE() {return NEGATIVE;}
	
	public static String getWRONG() {return WRONG;}
	
	public static Currency currency = Currency.getInstance("EUR"); 
	
	
	/**
	 * 
	 * @param input_cash
	 * @param cost
	 * @return change, -1 if error
	 */
	public static BigDecimal calculateChange(BigDecimal input_cash, BigDecimal cost) {
		BigDecimal change = BigDecimal.ZERO;
		if((input_cash.compareTo(BigDecimal.ZERO) < 0)||
				(cost.compareTo(BigDecimal.ZERO)< 0)) {
			throw new IllegalArgumentException(NEGATIVE);
		}
		else if(input_cash.compareTo(cost) < 0) {
	  	throw new IllegalArgumentException(WRONG);
	  }else {
	  	change = (input_cash.subtract(cost));
	  }

		return change;
	}
	
	public static boolean isNumeric(String str) {
		boolean numeric;
		if(str==null | str.isEmpty() | str.isBlank()) numeric=false;
		else {
			try {
				new BigDecimal(str); //If this doesnt throw exception then its a number
				numeric = true;
			}catch(NumberFormatException nfe) {
				//LOGGER.log(Level.INFO, "IsNumeric check failed");
				numeric = false;
			}
		}
		return numeric;
	}
	
	//If this is a bean the input will be of the type String
	public static String runTheChangeCalculator(String input_cash, String cost) {
		if(input_cash!=null && cost!=null) {
    	if(input_cash.length()>0 && cost.length()>0) {
    		if(isNumeric(input_cash) && isNumeric(cost)) {
    			return formatMoney(calculateChange(
    					new BigDecimal(input_cash), new BigDecimal(cost)));
    		}else throw new IllegalArgumentException(NOTNUMBER);
    	}else throw new IllegalArgumentException(EMPTY);
		}else throw new NullPointerException();
		
	}
	
	public static boolean isInteger(BigDecimal money) {
		return money.stripTrailingZeros().scale()<=0;
	}
	
	public static int decimalPlaces(BigDecimal money) {
		return money.stripTrailingZeros().scale();
	}
	
	public static String formatMoney(BigDecimal money) {
		System.out.println(money.stripTrailingZeros().scale());
		
		String moneyStr="";
		if(isInteger(money)) {moneyStr = money.setScale(0).toPlainString();
		}else {
			System.out.println(money);
			BigDecimal tri = money.setScale(3, RoundingMode.HALF_UP);
			System.out.println(tri.toString());
			
			moneyStr = tri
					.stripTrailingZeros().toPlainString();
		}
		
		

		
		String formatted = String.format("%2$s%1$s", currency.getSymbol(), moneyStr); //kinda like printf
		System.out.println(formatted);
		
		return formatted;
	}
	
	

  public static void main(String args[]) {
  	
  	if(args[0]!=null && args[1]!=null)
    	if(args[0].length()>0 && args[1].length()>0)
  	LOGGER.log(Level.INFO, "You get this much change: " + calculateChange(new BigDecimal(args[0]), new BigDecimal(args[1])).toString());
  	
  }
}
