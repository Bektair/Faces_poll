package examples.dynamic;

public class stringUtils {

	public static boolean isPalindrome(String test) {
		
		
		for(int i = 0; i<test.length()/2; i++) {
			System.out.println(test.charAt(i) +":compare:"+test.charAt(test.length()-1-i));
			if(test.charAt(i)==test.charAt(test.length()-1-i)) {
				
			}else {
				return false;
			}
			
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		System.out.println(isPalindrome("anna"));
		System.out.println(isPalindrome("madam"));
		System.out.println(isPalindrome("funnnnuf"));
		
		
		
	}
	
	
}
