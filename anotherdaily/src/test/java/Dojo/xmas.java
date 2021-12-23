package Dojo;

import java.util.HashMap;
import java.util.Stack;
import java.util.TreeMap;

public class xmas {
    /*
On the first day of Christmas
My true love gave to me:
A partridge in a pear tree.

On the second day of Christmas
My true love gave to me:
Two turtle doves and
A partridge in a pear tree.

On the third day of Christmas
My true love gave to me:
Three french hens
Two turtle doves and
A partridge in a pear tree.

On the forth day of Christmas
My true love gave to me:
Four calling birds
Three french hens
Two turtle doves and
A partridge in a pear tree.

On the fifth day of Christmas
My true love gave to me:
Five golden rings
Four calling birds
Three french hens
Two turtle doves and
A partridge in a pear tree.

On the sixth day of Christmas,
My true love gave to me:
Six geese a-laying
Five golden rings
Four calling birds
Three french hens
Two turtle doves and
A partridge in a pear tree.

On the seventh day of Christmas,
My true love gave to me:
Seven swans a-swimming
Six geese a-laying
Five golden rings
Four calling birds
Three french hens
Two turtle doves and
A partridge in a pear tree.

On the eight day of Christmas,
My true love gave to me:
Eight maids a-milking
Seven swans a-swimming
Six geese a-laying
Five golden rings
Four calling birds
Three french hens
Two turtle doves and
A partridge in a pear tree.

On the ninth day of Christmas,
My true love gave to me:
Nine ladies dancing
Eight maids a-milking
Seven swans a-swimming
Six geese a-laying
Five golden rings
Four calling birds
Three french hens
Two turtle doves and
A partridge in a pear tree.

On the tenth day of Christmas,
My true love gave to me:
Ten lords a-leaping
Nine ladies dancing
Eight maids a-milking
Seven swans a-swimming
Six geese a-laying
Five golden rings
Four calling birds
Three french hens
Two turtle doves and
A partridge in a pear tree.

On the eleventh day of Christmas,
My true love gave to me:
Eleven pipers piping
Ten lords a-leaping
Nine ladies dancing
Eight maids a-milking
Seven swans a-swimming
Six geese a-laying
Five golden rings
Four calling birds
Three french hens
Two turtle doves and
A partridge in a pear tree.

On the Twelfth day of Christmas,
My true love gave to me:
Twelve drummers drumming
Eleven pipers piping
Ten lords a-leaping
Nine ladies dancing
Eight maids a-milking
Seven swans a-swimming
Six geese a-laying
Five golden rings
Four calling birds
Three french hens
Two turtle doves
And a partridge in a pear tree.*/
   
    
    public static String answer() {  	
    	TreeMap<String, String> tree = new TreeMap<>();
    	tree.put("A.First", "A partridge in a pear tree.");
    	tree.put("B.Secound", "Two turtle doves and");
    	tree.put("C.Third", "Three french hens");
    	tree.put("D.Fourth", "Four calling birds");
    	tree.put("E.Fifth", "Five golden rings");
    	tree.put("F.Sixth", "Six geese a-laying");
    	tree.put("G.Seventh", "Seven swans a-swimming");
    	tree.put("H.Eigth", "Eight maids a-milking");
    	tree.put("I.Nineth", "Nine ladies dancing");
    	tree.put("J.Tenth", "Ten lords a-leaping");
    	tree.put("K.Elleventh", "Eleven pipers piping");
    	tree.put("L.Twelth", "Twelve drummers drumming");
      String str = "";
      for(String day : tree.keySet()) {
        String line = "On the " + day.substring(2) + " day of Christmas\nMy true love gave to me:";
        System.out.println(line);
        //String current = "";
        Stack<String> stack = new Stack<>();
        for(String current : tree.keySet()) {
        	stack.add(tree.get(current));
        	if(current==day) break;
        }
        while(!stack.isEmpty()) {
        	System.out.println(stack.pop());
        }
        System.out.println();
      }
      return str;
  }
    
    public static void main(String[] args) {
    	answer();
    }
    
}
