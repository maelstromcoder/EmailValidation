// import java.util.Scanner;

// REMOVED USE OF ARRAYS
// ADDED USE OF SIGNED CHAR -128 to 127 TO BE COMPARED WITH CHARAT INT VALUE 

public class EmailValidation {

	// Input length to be used across methods
    static int length;
    // Prefix and domain declared
    static String prefix, domain;
    
    // Prefix first and last character
    static char firstCharacter, lastCharacter;
    // Domain portions declared
    static String firstPortion, secondPortion;
    static char firstCharDomain, lastCharDomain;
    
    // @ integer values for prefix and exactlyOneAt 
    static int mid, atCounter;
    
    // Boolean for methods
    static boolean isAlphanumeric, isValidPrefixChar, isValidDomainChar, exactlyOneAt;
    static boolean isValidPrefix, isValidDomain;

    // Booleans for Prefix
    static boolean prefixFirstChar, prefixLastChar, oneCharacter, onlyAlpha, followUpAlpha = true;
    static boolean noPrefix = false;

    // Booleans for Domain
    static boolean domainFirstPortion, domainLastPortion, domainFirstChar, domainLastChar, 
    	separatePoint, domainFirstOnlyAlpha, domainFollowUpAlpha, domainSecondOnlyAlpha, domainSecondOnlyNothing;
    static boolean noDomain = false;
    
    // static Scanner console = new Scanner(System.in);
	/* ************************************************************************************************************ */
    
    
	public static void main(String[] args) {
		
		// console input for tests
		// String input = console.nextLine();
		
		// args input for JVM compile
		String input = args[0];
		length = input.length();
		
		// Confirms if Email is valid
		System.out.println("isValidEmail (" + input + ") returns "+ isValidEmail(input) + ".");
		
		// Confirms if Prefix is valid and error handling if there are mistakes
		System.out.println("isValidPrefix (" + input + ") returns "+ isValidPrefix(input) + ".");
		PrefixErrorHandling();
		
		// Confirms if Domain is valid and error handling if there are mistakes
		System.out.println("isValidDomain (" + input + ") returns "+ isValidDomain(input) + ".");
		DomainErrorHandling();
		
		// Confirms if there is only 1 @
		System.out.println("exactlyOneAt (" + input + ") returns "+ exactlyOneAt(input) + ".");
		
		// Shows what the Prefix and Domain would be
		System.out.println("getPrefix (" + input + ") returns "+ getPrefix(input) + ".");
		System.out.println("getDomain (" + input + ") returns "+ getDomain(input) + ".");
	}
	/* ************************************************************************************************************ */
	
	
	/* Write the method isValidEmail() which takes as input a String and returns true if the string is a valid email
	address, false otherwise. To get full marks, your method must use all the methods you have written up to now
	(either directly or indirectly). */
	
	public static boolean isValidEmail(String input) {
				
		// Verifies if there is only 1 @ sign
		exactlyOneAt(input);
		
		// Verifies if prefix, domain and @ is correct
		if (isValidPrefix(input) == true && isValidDomain(input) == true && exactlyOneAt(input) == true) {
				return true;
			}
		return false;
	}
	/* ************************************************************************************************************ */
	
	
	/* Write a method exactlyOneAt() that takes as input a String representing a possible email address, and returns
	true if the string contains exactly one ‘@’, false otherwise. */
	
	public static boolean exactlyOneAt(String input) {
		
		// Reset Counter if method exactlyOneAt called again
		atCounter = 0;
		
		for (int i = 0; i < length; i++) {
			if (input.charAt(i) == '@') {
					atCounter++;
				}
		}
		
		if (atCounter == 1) {
			exactlyOneAt = true;
			return true; 
		}
		else { 
			return false;
		}
	}
	
	/* ************************************************************************************************************ */
	
	
	/* isValidPrefix() takes a String as input representing the prefix of a possible email address. The method
	returns true if the input adhere to all the constraints listed in the above paragraph titled “Acceptable
	prefix formats”, false otherwise. */
	
	public static boolean isValidPrefix(String input) {
		// Retrieves Prefix from input before @
		prefix = getPrefix(input);
		
			// If condition if Prefix is EMPTY
			if (prefix != "") {
			// Makes sure the retrieved Prefix contains at least one character
			int prefixLength = prefix.length();
			if (prefixLength >= 1) {
				oneCharacter = true;
			}
			
			int newLength = prefix.length() - 1;
			
			// If Prefix has something, make sure the first and last character is alphanumeric
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
		}
		if (prefixFirstChar == false && prefixLastChar == false && oneCharacter == false && onlyAlpha == false && followUpAlpha == false) {
				noPrefix = true;
			}
			
			
		return false;
	}
	/* ************************************************************************************************************ */
	
	
	/* Acceptable prefix formats. For a prefix to be acceptable it must adhere to the following constraints:
	• It contains at least one character.
	• It contains only alphanumeric characters, underscores (‘ ’), periods (‘.’), and dashes (‘-’).
	• An underscore, a period, or a dash must always be followed by one or more alphanumeric characters.
	• The first and last character must be alphanumeric. */
	
	public static void PrefixErrorHandling() {
		// Error handling telling us which error we have to correct
		if (noPrefix == false) { 
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
		else if (noPrefix == true) {
			System.out.println("You have not entered a proper prefix.");
		}
	}
	/* ************************************************************************************************************ */
	
	
	/* Write a method getPrefix() that takes as input a String representing a possible email address. The method
	returns a String containing the prefix of the possible email address. In this method, you can assume that the
	String received as input contains exactly one ‘@’. */
	
	public static String getPrefix(String input) {
		
		// If there is exactly one @, proceed
		if (exactlyOneAt = true) {
			for (int i = 0; i < length; i++) {
				isValidPrefixChar(input.charAt(i));
																
				if (input.charAt(i) == '@') {
					// Isolate Prefix in front of @
					String output = input.substring(0, input.lastIndexOf("@"));
					isValidPrefix = true;
					
					// If @ is first, meaning no prefix, return nothing for prefix
					if (input.charAt(0) == '@') {
						return "";
					}
					
					return output;
				} 
			} 
			// If there is no @, still print prefix as a single
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
	/* ************************************************************************************************************ */
		
	
	/* isValidDomain() takes a String as input representing the domain of a possible email address. The method
	returns true if the input adhere to all the constraints listed in the above paragraph titled “Acceptable
	domain formats”, false otherwise. */
	
	public static boolean isValidDomain(String input) {
		
		// Gets the domain after the @ 
		domain = getDomain(input);
		
		// If condition if Domain is EMPTY
		if (domain != "") {
			int domainLength = domain.length();
		
		
			// Creates for loop to verify each letter of domain if alphanumeric, dash or period
			for (int i = 0; i < domainLength; i++) {
				isValidDomainChar(domain.charAt(i));
				
				// Primary path if there is a period in the domain			
				if (domain.charAt(i) == '.') {
					if (isAlphanumeric(domain.charAt(i+1)) == true) {
						separatePoint = true;
					
						// Creates first portion of domain with last period and verifies first portion if it is at least 1 character
						firstPortion = domain.substring(0, domain.lastIndexOf("."));
						if (firstPortion.length() >= 1) {
							domainFirstPortion = true;
						}
						
						// Verifies if the domain before the period is only alphanumeric, dash or period
						for (int z = 0; z < firstPortion.length(); z++) {
							isValidDomainChar(firstPortion.charAt(z));
							if (isValidDomainChar(firstPortion.charAt(z)) == true) { 
								domainFirstOnlyAlpha = true;
							} 
							else {
								domainFirstOnlyAlpha = false;
							}
						}
						
						// Verifies first character of domain if alphanumeric
						firstCharDomain = domain.charAt(0);
						domainFirstChar = isAlphanumeric(firstCharDomain);
						
						// Verifies if first portion is at least 1 character and if it is an alphanumeric
						if (firstPortion.length() > 1) {
							lastCharDomain = domain.charAt(firstPortion.length()-1);
							domainLastChar = isAlphanumeric(lastCharDomain);
						}
						else {
							domainLastChar = false;
						}
															
						int newDomainLength = domainLength -1;
						
						// Verifies if the follow up of a dash or period is followed by an alphanumeric
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
				if (input.charAt(b) != '.') {
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
			    		domainFollowUpAlpha = true;
				   	}
			    	
			    	int IntsecondPortion = secondPortion.length() - 1;
			    	// Verifies if the follow up of a dash or period is followed by an alphanumeric
					for (int c = 0; c < IntsecondPortion; c++) {
						switch (secondPortion.charAt(c)) {
							case '-': 
							if (isAlphanumeric(domain.charAt(c+1)) == false) {
								c = IntsecondPortion;
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
								c = IntsecondPortion;
								break;
							}
							else if (isAlphanumeric(domain.charAt(c+1)) == true) {
								domainFollowUpAlpha = true;
								break;
							}
						}
					}    		
			    }
				else {
					domainLastPortion = false;
					domainSecondOnlyNothing = true;
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
		noDomain = true;
		return false;
	}
	/* ************************************************************************************************************ */
	
	
	/* Acceptable domain formats. For a domain to be acceptable it must adhere to the following constraints:
	• It is made up of two portions separated by a period.
	• The first portion contains at least one character.
	• The second portion contains at least two characters.
	• The first portion contains only alphanumeric characters, periods, and dashes. Moreover, a period or a
	dash must always be followed by one or more alphanumeric characters. Finally, the first and last
	character must be alphanumeric.
	• The second portion contains only letters of the alphabet. */
	
	public static void DomainErrorHandling() {
		// Error handling telling us which error we have to correct
		if (noDomain == false) {
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
		else if (noDomain == true) {
			System.out.println("You have not entered a domain.");
		}
	}
	/* ************************************************************************************************************ */
	
	
	/* Write a method getDomain() that takes as input a String representing a possible email address. The method
	returns a String containing the domain of the possible email address. In this method, you can assume that the
	String received as input contains exactly one ‘@’. */
	
	public static String getDomain(String input) {
		
		if (exactlyOneAt = true) {
			
			if (input != "") {
				for (int i = 0; i < length; i++) {
					isValidPrefixChar(input.charAt(i));
					if (input.charAt(i) == '@') {
						String output = input.substring(input.lastIndexOf("@") +1);
						isValidDomain = true;
						if (output == "") {
							return "there is nothing after the @";
						}
						return output;
					}
				} 
			}
		}
		return "";
	}
	/* ************************************************************************************************************ */
		
	
	/* Write a method isAlphanumeric() that takes as input a character. The method returns true if such character is
	a letter of the English alphabet (uppercase or lower case) or one of the arabic numerals. The method returns
	false otherwise. */
	
	public static boolean isAlphanumeric(char x) {
		// Compares signed char's int value to signed char value between a to z, A to Z and 1 to 9
		if ((x >= 'a' && x <= 'z') ||  (x >= 'A' && x <= 'Z') ||  (x >= 1 && x <= 9)) {
			return true;
		}
		else {
			return false;
		}
	}
	/* ************************************************************************************************************ */
	
	
	/* A method isValidPrefixChar() that takes as input a character and returns true if the character can be used
	in the prefix of a valid email address, false otherwise. Note that a valid prefix can contain only
	alphanumeric characters, dashes, periods, or underscores. For example, isValidPrefixChar(‘ ’) returns
	true, while isValidPrefixChar(‘&’) returns false. */
	
	public static boolean isValidPrefixChar(char x) {
		// Uses input x and calls method Alphanumeric for letters and adding underscore, dash and period in search
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
	/* ************************************************************************************************************ */
	
	
	/* A method isValidDomainChar() that takes as input a character and returns true if the character can be
	used in the domain (first portion) of a valid email address, false otherwise. Note that a valid first portion
	of a domain can contain only alphanumeric characters, dashes, or periods. For example,
	isValidDomainChar(‘-’) returns true, while isValidDomainChar(‘ ’) returns false. */
	
	public static boolean isValidDomainChar(char x) {
		// Uses input x and calls method Alphanumeric for letters and adding underscore, dash and period in search
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
}
