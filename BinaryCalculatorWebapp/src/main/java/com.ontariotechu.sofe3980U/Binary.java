package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	 * A constructor that generates a binary object.
	 *
	 * @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	 */
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}

		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}

		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}

		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);

		// uncomment the following code

		if (this.number.isEmpty()) { // replace empty strings with a single zero
			this.number = "0";
		}

	}
	/**
	 * Return the binary value of the variable
	 *
	 * @return the binary value in a string format.
	 */
	public String getValue()
	{
		return this.number;
	}
	/**
	 * Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	 *
	 * @param num1 The first addend object
	 * @param num2 The second addend object
	 * @return A binary variable with a value of <i>num1+num2</i>.
	 */
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;

	}
	// Bitwise OR operation
	public static Binary or(Binary num1, Binary num2) {
		int maxLength = Math.max(num1.number.length(), num2.number.length());
		StringBuilder result = new StringBuilder();

		// Pad the shorter binary string with leading zeros
		String num1Padded = String.format("%" + maxLength + "s", num1.number).replace(' ', '0');
		String num2Padded = String.format("%" + maxLength + "s", num2.number).replace(' ', '0');

		// Perform bitwise OR
		for (int i = 0; i < maxLength; i++) {
			if (num1Padded.charAt(i) == '1' || num2Padded.charAt(i) == '1') {
				result.append("1");
			} else {
				result.append("0");
			}
		}

		return new Binary(result.toString());
	}

	// Bitwise AND operation
	public static Binary and(Binary num1, Binary num2) {
		int maxLength = Math.max(num1.number.length(), num2.number.length());
		StringBuilder result = new StringBuilder();

		// Pad the shorter binary string with leading zeros
		String num1Padded = String.format("%" + maxLength + "s", num1.number).replace(' ', '0');
		String num2Padded = String.format("%" + maxLength + "s", num2.number).replace(' ', '0');

		// Perform bitwise AND
		for (int i = 0; i < maxLength; i++) {
			if (num1Padded.charAt(i) == '1' && num2Padded.charAt(i) == '1') {
				result.append("1");
			} else {
				result.append("0");
			}
		}

		return new Binary(result.toString());
	}


	// Multiply two binary numbers using the add function
	public static Binary multiply(Binary num1, Binary num2) {
		Binary result = new Binary("0");
		String num2Value = num2.getValue();

		// Loop through each bit in num2 from right to left
		for (int i = num2Value.length() - 1; i >= 0; i--) {
			if (num2Value.charAt(i) == '1') {
				// Shift num1 by the correct number of places (i from right to left)
				Binary shiftedNum1 = shiftLeft(num1, num2Value.length() - 1 - i);
				System.out.println("Shifted num1: " + shiftedNum1.getValue());

				// Add shifted num1 to the result
				result = Binary.add(result, shiftedNum1);
			}
		}

		System.out.println("Final result: " + result.getValue());
		return result;
	}




	// Helper function to shift a binary number to the left by n positions
	public static Binary shiftLeft(Binary binary, int positions) {
		String shiftedValue = binary.getValue() + "0".repeat(positions); // Shift by appending zeros
		return new Binary(shiftedValue);

	}

	// NOT operation-> inverts every bit
	public static Binary not(Binary num) {
		StringBuilder result = new StringBuilder();

		// Flip each bit, (0 becomes 1, 1 becomes 0)
		for (char bit : num.getValue().toCharArray()) {
			result.append(bit == '0' ? '1' : '0');
		}

		return new Binary(result.toString());
	}




}