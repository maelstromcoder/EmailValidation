// import java.util.Scanner;

public class EmailValidation {

	// Registry for alphanumeric
	final static String regex1 = "[a-z]";
    final static String regex2 = "[A-Z]";
    final static String regex3 = "[0-9]";
    final static String regex4 = "[!#$%&'()*+,/:;<>=?]";

    // Input length to be used across methods
    static int length;
    // Prefix and domain declared
    static String prefix, domain;
    
    // Prefix first and last character
    static char firstCharacter, lastCharacter;
    // Domain portions declared
    static String firstPortion, secondPortion;
    static char firstCharDomain, lastCharDomain;
    
    // Array declared and initialized with input length 
    static char[] array;
    // @ integer values for prefix and exactlyOneAt 
    static int mid, atCounter;
    
    // Boolean for methods
    static boolean isAlphanumeric, isValidPrefixChar, isValidDomainChar, exactlyOneAt;
    static boolean isValidPrefix, isValidDomain;

    // Booleans for Prefix
    static boolean prefixFirstChar, prefixLastChar, oneCharacter, onlyAlpha, followUpAlpha = true;

    // Booleans for Domain
    static boolean domainFirstPortion, domainLastPortion, domainFirstChar, domainLastChar, 
    	separatePoint, domainFirstOnlyAlpha, domainFollowUpAlpha, domainSecondOnlyAlpha, domainSecondOnlyNothing;
    
    // static Scanner console = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		// console input for tests
		// String input = console.nextLine();
		
		// args input for JVM compile
		String input = args[0];
		length = input.length();
		array = new char[length];
		
		System.out.println("isValidEmail (" + input + ") returns "+ isValidEmail(input) + ".");
		
		// Confirms if Prefix is valid and error handling if there are mistakes
		System.out.println("isValidPrefix (" + input + ") returns "+ isValidPrefix(input) + ".");
		PrefixErrorHandling();
		
		System.out.println("isValidDomain (" + input + ") returns "+ isValidDomain(input) + ".");
		DomainErrorHandling();
		
		// Confirms if there is only 1 @
		System.out.println("exactlyOneAt (" + input + ") returns "+ exactlyOneAt(input) + ".");
		
		// Shows what the Prefix and Domain would be
		System.out.println("getPrefix (" + input + ") returns "+ getPrefix(input) + ".");
		System.out.println("getDomain (" + input + ") returns "+ getDomain(input) + ".");
	}
	
	public static boolean isValidEmail(String input) {
				
		// Verifies if there is only 1 @ sign
		exactlyOneAt(input);
		
		// Verifies if prefix, domain and @ is correct
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
		
		if (prefix != "") {
			firstCharacter = prefix.charAt(0);
			lastCharacter = prefix.charAt(newLength);
		}
		// Boolean value for first character of Prefix to be alphanumeric
		prefixFirstChar = isAlphanumeric(firstCharacter);
		// Boolean value for last character of Prefix to be alphanumeric
		prefixLastChar = isAlphanumeric(lastCharacter);
		
		// Makes sure that Prefix only contains alphanumeric characters, underscores "_", periods ".", and dashes "-"		
		for (int i = 0; i < prefixLength; i++) {
			isValidPrefixChar(prefix.charAt(i));			
			if (isValidPrefixChar(prefix.charAt(i)) == false) { 
				if (prefix.charAt(i) == ' ') {
					onlyAlpha = true;
					break;
				}
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
					i = prefixLength;
					followUpAlpha = false;
					break;
				} 
				else if (isAlphanumeric(input.charAt(i+1)) == true) {
					followUpAlpha = true;
					break;
				}
				case '_': 
				if (isAlphanumeric(input.charAt(i+1)) == false) {
					i = prefixLength;
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
					i = prefixLength;
					break;
				}
				else if (isAlphanumeric(input.charAt(i+1)) == true) {
					followUpAlpha = true;
					break;
				}
			}
		}
		
		// If all booleans are true, we have a valid prefix
		if (prefixFirstChar == true && prefixLastChar == true && oneCharacter == true && onlyAlpha == true && followUpAlpha == true) {
			return true;
		}
		return false;
	}
	
	public static void PrefixErrorHandling() {
		// Error handling telling us which error we have to correct
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
					
					// Compensating for @ being the first character
					if (mid == 0) {
						mid = 1;
					}
					
					switch (input.charAt(mid-1)) {
					case '-': return "your dash must be followed by one or more alphanumeric characters"; 
					case '_': return "your underscore must be followed by one or more alphanumeric characters";	
					case '.': return "your period must be followed by one or more alphanumeric characters";
					}
					
					if (input.charAt(0) == '@') {
						return "";
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
		int domainLength = domain.length();
		
		for (int i = 0; i < domainLength; i++) {
			array[i] = domain.charAt(i);
			isValidDomainChar(array[i]);
			
			// Primary path if there is a period in the domain			
			if (domain.charAt(i) == '.') {
				if (isAlphanumeric(domain.charAt(i+1)) == true) {
					separatePoint = true;
				
					// Creates first portion of domain with last period and verifies first portion if it is at least 1 character
					firstPortion = domain.substring(0, domain.lastIndexOf("."));
					if (firstPortion.length() >= 1) {
						domainFirstPortion = true;
					}
					
					for (int z = 0; z < firstPortion.length(); z++) {
						isValidDomainChar(firstPortion.charAt(z));
						if (isValidDomainChar(firstPortion.charAt(z)) == true) { 
							domainFirstOnlyAlpha = true;
						} 
						else {
							domainFirstOnlyAlpha = false;
						}
					}
										
					firstCharDomain = domain.charAt(0);
					domainFirstChar = isAlphanumeric(firstCharDomain);
					
					if (firstPortion.length() > 1) {
						lastCharDomain = domain.charAt(firstPortion.length()-1);
						domainLastChar = isAlphanumeric(lastCharDomain);
					}
					else {
						domainLastChar = false;
					}
														
					int newDomainLength = domainLength -1;
					
					for (int c = 0; c < newDomainLength; c++) {
						switch (domain.charAt(c)) {
							case '-': 
							if (isAlphanumeric(domain.charAt(c+1)) == false) {
								c = newDomainLength;
								domainFollowUpAlpha = false;
								break;
							} 
							else if (isAlphanumeric(domain.charAt(c+1)) == true) {
								domainFollowUpAlpha = true;
								break;
							}
							case '.':	
							if (isAlphanumeric(domain.charAt(c+1)) == false) {
								domainFollowUpAlpha = false;
								c = newDomainLength;
								break;
							}
							else if (isAlphanumeric(domain.charAt(c+1)) == true) {
								domainFollowUpAlpha = true;
								break;
							}
						}
					}

					// Verifies the last portion of the domain, after the last period (.ca , .com. , .ru , etc)
					secondPortion = domain.substring(domain.lastIndexOf('.')+1);
			    	if (secondPortion.length() >= 2) {
			    		domainLastPortion = true;
			    		
			    		for (int x = 0; x < secondPortion.length(); x++) {
			    			if (isAlphanumeric(secondPortion.charAt(x)) == true) {
			    				domainSecondOnlyAlpha = true;
			    			}
			    			else {
			    				domainSecondOnlyAlpha = false;
			    			}
			    		}				    	
				   	} 
			    	else {
			    		domainLastPortion = false;
			    	} 	
				} 
				// Alternative path if period or dash is not followed by alphanumeric
				if (isAlphanumeric(domain.charAt(i+1)) == false) {
					domainSecondOnlyAlpha = false;
					// Breaks FOR loop to go to 
					i = domainLength;
				}
			}
		} 
		
		// Alternative path if there are no periods in the domain
		// Verifies first portion of the domain (what is in front of the period)
		for (int b = 0; b < domainLength; b++) {
			if (array[b] != '.') {
				
				// Verifies first portion if it is at least 1 character
				firstPortion = domain;
				if (firstPortion.length() >= 1) {
					domainFirstPortion = true;
				}
				
				firstCharDomain = domain.charAt(0);
				domainFirstChar = isAlphanumeric(firstCharDomain);
					
				if (firstPortion.length() > 1) {
					lastCharDomain = domain.charAt(firstPortion.length()-1);
					domainLastChar = isAlphanumeric(lastCharDomain);
				}
				else {
					domainLastChar = false;
				}
				
				for (int z = 0; z < firstPortion.length(); z++) {
					isValidDomainChar(firstPortion.charAt(z));
					if (isValidDomainChar(firstPortion.charAt(z)) == true) { 
						domainFirstOnlyAlpha = true;
					} 
					else {
						domainFirstOnlyAlpha = false;
					}
				}
				
				// Verifies the last portion of the domain, after the last period (.ca , .com. , .ru , etc)
				secondPortion = domain.substring(domain.lastIndexOf('.')+1);
		    	if (secondPortion.length() >= 2) {
		    		domainLastPortion = true;
		    		
		    		for (int x = 0; x < secondPortion.length(); x++) {
		    			if (isAlphanumeric(secondPortion.charAt(x)) == true) {
		    				domainSecondOnlyAlpha = true;
		    			}
		    			else {
		    				domainSecondOnlyAlpha = false;
		    			}
		    		}				    	
			   	} 
		    	else {
		    		domainLastPortion = false;
					domainFollowUpAlpha = true;
					domainSecondOnlyNothing = true;
		    	}
		    }
		}
		
		// If all booleans are true, we have a valid Domain
		if (domainFirstChar == true && domainLastChar == true && separatePoint == true && 
				domainFirstOnlyAlpha == true && domainFollowUpAlpha == true && domainSecondOnlyAlpha == true) {
			return true;
		} 
		else {
			return false;
		}
	}	
	
	
	public static void DomainErrorHandling() {
		// Error handling telling us which error we have to correct
		if (domainFirstPortion == false) {
			System.out.println("Your domain before the period does not contain at least one character.");
		}
		if (domainLastPortion == false) {
			System.out.println("Your domain after the period does not contain at least two characters.");
		}
		if (separatePoint == false) {
			System.out.println("Your domain is not made up of two portions separated by a period.");
		}
		if (domainFirstOnlyAlpha == false) {
			System.out.println("Your domain before the period does not only contain alphanumeric characters, periods, and dashes.");
		}
		if (domainFollowUpAlpha == false) {
			System.out.println("Your domain underscore, period or dash does not have one or more alphanumeric characters following it.");
		}
		if (domainSecondOnlyAlpha == false) {
			System.out.println("Your domain after the period does not contain only alphanumeric characters.");
		}
		// Else if condition added if Domain only has 1 part
		else if (domainSecondOnlyAlpha == false && domainSecondOnlyNothing == true) {
			System.out.println("Your domain after the period does not contain any alphanumeric characters.");
		}
		if (domainFirstChar == false) {
			System.out.println("Your domain first character is not an alphanumeric.");
		}
		if (domainLastChar == false) {
			System.out.println("Your domain last character is not an alphanumeric.");
		}
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
		
		// Reset Counter if method exactlyOneAt called again
		atCounter = 0;
		
		for (int i = 0; i < length; i++) {
			array[i] = input.charAt(i);
			if (input.charAt(i) == '@') {
				String test = "" + array[i];
				if (test.contains("@")) {
					atCounter++;
				}
			}
		} 
		if (atCounter == 1) {
			exactlyOneAt = true;
			return true; 
		}
		return false;
	}
}
