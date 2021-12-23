package exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Contigu {

	//Calculate the largest sum of contigous sequences in the input 
	//Think as much in a line as possible
	// ADJACENT if we take them as a whole
	// -5, -4, 0, 2, 3
	public static void main(String[] args) throws IOException {
	  InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    String line;
    while ((line = in.readLine()) != null) {
    	int sum = 0;
    	String[] sequence =  line.split(",");
    	int[] largestSum = new int[sequence.length];
    	int largestSumOfAll = 0;
    	
    	for(int i = 0; i < sequence.length; i++) {
    		//System.out.println("I: -----" + i);
    		sum=0;
    		for(int j = i; j < sequence.length; j++) {
    			sum+=Integer.parseInt(sequence[j].trim());
      		//System.out.println("Sum: ++++" + sum);

    			if(sum > largestSum[i]) { 
    				largestSum[i] = sum;
    			}
    		}
        //System.out.println(largestSum[i]);

    		if(largestSum[i]>largestSumOfAll) {
    			largestSumOfAll = largestSum[i];
    		}
    	}

      System.out.println(largestSumOfAll);
    }
	}

}
