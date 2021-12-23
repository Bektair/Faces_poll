package exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

//Calculate the change received from the cash register in the given types
//Hundred, fifty, twenty, ten, five, two, one, half_dollar, quarter, dime, nickel, penny
public class Main {

	
	
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    String line;
    while ((line = in.readLine()) != null) {
      String[]input = line.split(";");
      
      //PurchasePrice
      BigDecimal PP = new BigDecimal(input[0]);
  		BigDecimal CH = new BigDecimal(input[1]);
  		
  		if(PP.compareTo(CH)>0) {
  			System.out.println("ERROR");
  		}else if(PP.compareTo(CH)==0) {
  			System.out.println("ZERO");
  		}else {
  			BigDecimal payoutLeft = CH.subtract(PP);
  			String payoutStr = "";
  			for(cashTypes type : cashTypes.values()) {
  				while (payoutLeft.compareTo(type.getValue()) >= 0) {
  					payoutLeft=payoutLeft.subtract(type.getValue());
  					payoutStr += type.name()+",";
  				}
  			}
  			System.out.println(payoutStr.substring(0, payoutStr.length()-1));
  		}
    }
	}

}
