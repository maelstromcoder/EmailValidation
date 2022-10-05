import java.util.Scanner;

public class EmailValidation {

	final static String regex1 = "[a-z]";
    final static String regex2 = "[A-Z]";
    final static String regex3 = "[0-9]";
    final static String regex4 = "[!#$%&'()*+,/:;<>=?]";
    static int length;
    static String firstPortion, secondPortion;
    static String prefix, domain;
    static char[] array, portionOne, portionTwo;
    static boolean[][] verifyOne, verifyTwo;
    static boolean[] badMemory;
    static int mid;
    static int badCounter = 0;
    static int AtCounter = 0;
    static char firstCharacter, lastCharacter;
    
    static boolean isAlphanumeric, isValidPrefixChar, isValidDomainChar, exactlyOneAt;
    static boolean isValidPrefix, isValidDomain;
    static boolean oneCharacter, onlyAlpha, followUpAlpha;
    static boolean prefixFirstChar, prefixLastChar;
    static boolean prefixObtained;
    
    
    static Scanner console = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		// To change to args[0]
		String input = console.next();
		length = input.length();
		array = new char[length];
		
		System.out.println("isValidEmail (" + input + ") returns "+ isValidEmail(input) + ".");
	
		System.out.println("isValidPrefix (" + input + ") returns "+ isValidPrefix(input) + ".");
		
		if (prefixFirstChar == false) {
			System.out.println("Your prefix first character is not an alphanumeric.");
		}
		if (prefixLastChar == false) {
			System.out.println("Your prefix last character is not an alphanumeric.");
		}
		if (oneCharacter == false) {
			System.out.println("Your prefix does not contain at least one character.");
		}
		if (onlyAlpha == false) {
			System.out.println("Your prefix does not contain only alphanumeric characters or underscores, periods, and dashes.");
		}
		if (followUpAlpha == false) {
			System.out.println("Your prefix underscore, period or dash does not have one or more alphanumeric characters following it.");
		}
		
		System.out.println("isValidDomain (" + input + ") returns "+ isValidDomain(input) + ".");
		
		System.out.println("exactlyOneAt (" + input + ") returns "+ exactlyOneAt(input) + ".");
		
		System.out.println("getPrefix (" + input + ") returns "+ getPrefix(input) + ".");
		System.out.println("getDomain (" + input + ") returns "+ getDomain(input) + ".");
						
				
	}
	
	public static boolean isValidEmail(String input) {
				
		// Verifies if there is only 1 @ sign
		exactlyOneAt(input);
		
		if (isValidPrefix(input) == true && isValidDomain(input) == true && exactlyOneAt(input) == true) {
				return true;
			}
		return false;
	}
	
	public static boolean isValidPrefix(String input) {
		// Retrieves Prefix from input String
		prefix = getPrefix(input);
		
		// Makes sure the retrieved Prefix contains at least one character
		int prefixLength = prefix.length();
		if (prefixLength >= 1) {
			oneCharacter = true;
		}
		
		// Makes sure the first and last character is alphanumeric
		int newLength = prefix.length() - 1;
		firstCharacter = prefix.charAt(0);
		lastCharacter = prefix.charAt(newLength);
		// Boolean value for first character of Prefix to be alphanumeric
		prefixFirstChar = isAlphanumeric(firstCharacter);
		// Boolean value for last character of Prefix to be alphanumeric
		prefixLastChar = isAlphanumeric(lastCharacter);
		
		// Makes sure that Prefix only contains alphanumeric characters, underscores "_", periods ".", and dashes "-"		
		for (int i = 0; i < prefixLength; i++) {
			isValidPrefixChar(prefix.charAt(i));
			if (isValidPrefixChar(prefix.charAt(i)) == false) { 
				onlyAlpha = false;
				break;
			}
			onlyAlpha = true;
			}

		// Makes sure that Prefix underscore, period or dash is always followed by one or more alphanumeric characters
		for (int i = 0; i < prefixLength; i++) {
			switch (input.charAt(i)) {
				case '-': 
				if (isAlphanumeric(input.charAt(i+1)) == false) {
					followUpAlpha = false;
					break;
				} 
				else if (isAlphanumeric(input.charAt(i+1)) == true) {
					followUpAlpha = true;
					break;
				}
				case '_': 
				if (isAlphanumeric(input.charAt(i+1)) == false) {
					followUpAlpha = false;
					break;
				} 
				else if (isAlphanumeric(input.charAt(i+1)) == true) {
					followUpAlpha = true;
					break;
				}
				case '.':	
				if (isAlphanumeric(input.charAt(i+1)) == false) {
					followUpAlpha = false;
					break;
				}
				else if (isAlphanumeric(input.charAt(i+1)) == false) {
					followUpAlpha = true;
					break;
				}
			}
		}
		
		System.out.println(followUpAlpha);
		
		if (prefixFirstChar == true && prefixLastChar == true && oneCharacter == true && onlyAlpha == true && followUpAlpha == true) {
			return true;
		}
		return false;
	}
	
	public static String getPrefix(String input) {
		
		if (exactlyOneAt = true) {
			for (int i = 0; i < length; i++) {
				array[i] = input.charAt(i);
				isValidPrefixChar(array[i]);
																
				if (input.charAt(i) == '@') {
					String output = input.substring(0, input.lastIndexOf("@"));
					isValidPrefix = true;
					mid = i;
										
					switch (input.charAt(mid-1)) {
					case '-': return "your dash must be followed by one or more alphanumeric characters"; 
					case '_': return "your underscore must be followed by one or more alphanumeric characters";	
					case '.': return "your period must be followed by one or more alphanumeric characters";
					}
					return output;
				} 
			} 
			if (input.charAt(length-1) != '@') 
			{
				String output = input.substring(0, length);
				return output;
			}
			return "the prefix " + input + " is correct but has no @";
		}
		else {
			return "no handle exception, please check what you entered";
		}
	}
	
	public static boolean isValidDomain(String input) {
		domain = getDomain(input);
		return true;
	}
	
	public static String getDomain(String input) {
		
		if (exactlyOneAt = true) {
			for (int i = 0; i < length; i++) {
				isValidPrefixChar(array[i]);
				if (input.charAt(i) == '@') {
					String output = input.substring(input.lastIndexOf("@") +1);
					isValidDomain = true;
					if (output == "") {
						return "there is nothing after the @";
					}
					return output;
				}
			} return "domain inaccessible";
		}
		else {
			return "no handle exception, please check what you entered";
		}
		
	}
		
	
	/* Write a method isAlphanumeric() that takes as input a character. The method returns true if such character is
	a letter of the English alphabet (uppercase or lower case) or one of the arabic numerals. The method returns
	false otherwise. */
	
	public static boolean isAlphanumeric(char x) {
		
		String z = "" + x; 
		
		if (z.matches(regex1)) {
			return true;
		}
		else if (z.matches(regex2)) {
			return true;
		}
		else if (z.matches(regex3)) {
			return true;
		}
		else if (z.matches(regex4)) {
			return false;
		}
		else {
			return false;
		}
	}
	
	/* A method isValidPrefixChar() that takes as input a character and returns true if the character can be used
	in the prefix of a valid email address, false otherwise. Note that a valid prefix can contain only
	alphanumeric characters, dashes, periods, or underscores. For example, isValidPrefixChar(‘ ’) returns
	true, while isValidPrefixChar(‘&’) returns false. */
	
	public static boolean isValidPrefixChar(char x) {
		
		if (isAlphanumeric(x)) {
			return true;
		}
		else if (x == '_') {
			return true;
		}
		else if (x == '-') {
			return true;
		}
		else if (x == '.') {
			return true;
		}
		else {
			return false;
		}
	}
	
	/* A method isValidDomainChar() that takes as input a character and returns true if the character can be
	used in the domain (first portion) of a valid email address, false otherwise. Note that a valid first portion
	of a domain can contain only alphanumeric characters, dashes, or periods. For example,
	isValidDomainChar(‘-’) returns true, while isValidDomainChar(‘ ’) returns false. */
	
	public static boolean isValidDomainChar(char x) {

		if (isAlphanumeric(x)) {
			return true;
		}
		else if (x == '-') {
			return true;
		}
		else if (x == '.') {
			return true;
		}
		else {
			return false;
		}
	}
	
	/* Write a method exactlyOneAt() that takes as input a String representing a possible email address, and returns
	true if the string contains exactly one ‘@’, false otherwise. */
	
	public static boolean exactlyOneAt(String input) {
		
		for (int i = 0; i < length; i++) {
			array[i] = input.charAt(i);
			if (input.charAt(i) == '@') {
				exactlyOneAt = true;
				return true;
			}
		}
		return false;
	}
		
	
	
	
	

	
	
}
