package exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class stacky {
  private Node first;
  //private int n; only if you need size
  
  private class Node {
    Integer value;
    Node next;
  }
  
  
  public boolean isEmpty(){
    if(first==null) return true;
    else return false;
  }
  
  public void push(Integer i){
    Node newNode = new Node();
    newNode.next = first;
    newNode.value = i;
    first = newNode;
  }
  
  public Integer pop(){
    if(!isEmpty()){
    	Integer temp = first.value;
      first = first.next;
      return temp;
    }else{
      throw new IllegalArgumentException("You tried to pop an empty stack");
    }
  }
  
  //Implement a stack, make it reverse the input and print out every other number 
	public static void main(String[] args) throws IOException {
	  InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    String line;
    while ((line = in.readLine()) != null) {
      //System.out.println(line);
      String[] inputs = line.trim().split(" ");
      System.out.println(inputs.length);
      stacky stack = new stacky();
      int count = 0;
      for(int i = 0; i<inputs.length; i++) {
      	if(!inputs[i].isEmpty()) {
      		count++;
      		if((count)%2==0) {
      		stack.push(Integer.valueOf(inputs[i]));
      		}
      	}
      }
      String result = "";
      while(!stack.isEmpty()) {
      	result += stack.pop()+(" ");
      }
      if(!result.isEmpty()) {
      	System.out.println(result.substring(0, result.length()));
      }else {
      	System.out.println("No input gotten");
      }
    }
	
	}
}
