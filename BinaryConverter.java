/*
 By: Sophy Hok
 Date: 03/09/2016
 
 This is 16 bits binary conveter.
 This program will accept user input and convert to binary, decimal, 
 hexadecimal, and 2's complement binary.
 */
package binaryconverter;
import java.util.*;

public class BinaryConverter {
static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        // first run menu()
        // call getChoice() to get user menu choice
        // call GetInput() to take input 
        // then verify the max digit or value 
        // then call convert() to do conversion
        // AgainOrNot() to repeat the program or quit
        while(menu()){
            char choice = GetChoice();
            String userInput = GetInput(choice);
            int digit = userInput.length();
            if (!VerifyMax(userInput,digit,choice)){
                System.out.println("\nYou have input incorrect value/digit "
                        + "number.\n" + "Please try again.\n");
                continue;
            }  
        userInput = Convert(userInput,choice);
        System.out.println ("--*-- RESULT = " +userInput + " --*--");
        AgainOrNot();
        }
    }
    //to show menu option
    public static boolean menu() {
        System.out.println("***This is a 16 bits Binary Converter***");
        System.out.println("....................................");
        System.out.println("Please choose one option (use small letter only):");
        System.out.println("a. Binary to Decinal");
        System.out.println("b. Decimal to Binary");
        System.out.println("c. Hexadecimal to Binary");
        System.out.println("d. Binary to Hexadecimal"); 
        System.out.println("e. Decimal to Binary (2's complement)");
        System.out.println("f. Binary to Decimal (2's complement)");
        System.out.println("x. Exit");
        System.out.println("....................................");
        System.out.print("Type here: ");
        return true;
    }
    //to get menu choice input
    public static char GetChoice(){
        char choice;
        choice = console.next().charAt(0);
        switch (choice){
            case 'a': break;
            case 'b': break;
            case 'c': break;
            case 'd': break;
            case 'e': break;
            case 'f': break;
            case 'x': 
            case 'X': System.exit(0); break;
            default: System.out.println("You did not choose the "
                    + "available option.");
        }
        return choice;
    }
    //to get user input for conversion
    public static String GetInput(char choice){
        String input="";
        switch(choice){
            case 'a':
            case 'd':
            case 'f':
                System.out.print("Only maximum of 16 bits are allowed."
                        + "Please input number here: ");
                input = console.next();
                break;
            case 'b': 
                System.out.print("Value from 0 to 65535 are allowed. "
                        + "Please input number here: ");
                input = console.next();
                break;
            case 'c': 
                System.out.print("Only maximum of 4 hex digits are allowed. "
                          + "Please input number here: ");
                input = console.next();
                break;
            case 'e':
                System.out.print("Value from -32768 to 32767 are allowed. "
                        + "Please input number here: ");
                input = console.next();
                break;
        }
        return input;
    }
    //to verify max number of digits from user input for each conversion
    public static boolean VerifyMax(String userInput, int digit, char choice){
        boolean verify = true; 
        boolean verifyDec = true;
        if (choice == 'a' || choice == 'd' || choice == 'f'){
            if (digit <= 16) verify = true;
            else verify = false;
        }
        else if (choice == 'c'){
            if (digit <= 4) verify = true;
            else verify = false;
        }
        else if (choice == 'b'){
            if (VerifyUnsignedInt(userInput)){
                 if (Integer.parseInt(userInput) <= 65535) verify = true;
                 else verify = false;
            }else{
                verify = false;
            }
        }
        else if (choice == 'e'){
            if (VerifySignedInt(userInput)){
                Long inputValue = Long.parseLong(userInput);
                if (inputValue >= -32768 && inputValue <= 32767) verify = true;
                else verify = false;
            }else{
                verify = false;
            }
        }
        return verify;
    }
    //to let user make another conversion or exit
    public static void AgainOrNot(){
        char choice;
        System.out.print("\nDo you want another conversion?\nType y/n: ");
        choice = console.next().charAt(0);
        if ((choice == 'n') || (choice == 'N')) 
             System.exit(0);
        System.out.println();
      }    
    // to convert all options by calling each function
    public static String Convert(String userInput, char choice){
        String result="";
        switch(choice){    
            case 'a': result = ConvertBinToDec(userInput); break;
            case 'b': result = ConvertDecToBin(userInput); break;
            case 'c' : result = ConvertHexToBin(userInput); break;
            case 'd' : result = ConvertBinToHex(userInput); break;
            case 'e': result = ConvertDecToBinTwoCom(userInput); break; 
            case 'f': result = ConvertBinToDecTwoCom(userInput);
        }
        return result;
      }
    //a. convert binary to dec
    public static String ConvertBinToDec(String userInput){
        long temp = 0;
        int result = 0;
        int i = 0;
        //verify zero or One binary digit
        boolean verify = VerifyZeroOne(userInput); 
        while (!verify)
        {
            System.out.print("You have not entered correct binary digit. Please "
                    + "reenter: ");
            userInput = console.next();
            verify = VerifyZeroOne(userInput);
        }
        Long input = Long.parseUnsignedLong(userInput);
        //convert to decimal then return
        while(input != 0){
            temp = input % 10;
            input = input/10;
            result += temp * Math.pow(2.0, i);
            i++;
        }
        userInput = Integer.toString(result);
        return userInput;
    }
    //b. to convert decimal to binary
    public static String ConvertDecToBin(String userInput){
        int input = Integer.parseInt(userInput);
        int mod = 0;
        int length = 0;
        String result="";
        String temp="";
        if (input == 1) result ="1";
        else if (input == 0) result="0";
        while (input > 1){
            mod = input%2;
            input = input/2;
            temp = Integer.toString(mod);
            result = result + temp;
        }
        if (input ==1)
            result = result+"1";
        length = result.length();
        switch (length){
            case 1:
            case 5: 
            case 9:
            case 13: result = result + "000"; break;
            case 2:            
            case 6: 
            case 10:
            case 14: result = result + "00"; break;
            case 3:
            case 7: 
            case 11: 
            case 15: result = result + "0"; break;
        }
        String reverse = new StringBuffer(result).reverse().toString();
        return reverse;
    }
    //c. convert hex to binary
    public static String ConvertHexToBin(String userInput) {
        boolean verify = VerifyHexDigit(userInput);
        String result = "";
        while(!verify){
            System.out.print("Please enter correct hex digits: ");
            userInput = console.next();
            verify = VerifyHexDigit(userInput);
        }
        char bit[]=userInput.toCharArray();
        int length = bit.length;
        for (int i=0; i<length;i++){
            switch(bit[i]){
                case '0': userInput = "0000"; break;
                case '1': userInput = "0001"; break;
                case '2': userInput = "0010"; break;
                case '3': userInput = "0011"; break;
                case '4': userInput = "0100"; break;
                case '5': userInput = "0101"; break;
                case '6': userInput = "0110"; break;
                case '7': userInput = "0111"; break;
                case '8': userInput = "1000"; break;
                case '9': userInput = "1001"; break;
                case 'A': userInput = "1010"; break;
                case 'B': userInput = "1011"; break;
                case 'C': userInput = "1100"; break;
                case 'D': userInput = "1101"; break;                
                case 'E': userInput = "1110"; break;
                case 'F': userInput = "1111"; break;
            }
            result = result + userInput;
        }
        if (length == 3)
            result = "0000" + result;
        else if (length == 2)
            result = "00000000" + result;
        else if (length == 1)
            result = "000000000000" + result;
        return result;
    }
    //d. to convert binary to hexadicimal
    public static String ConvertBinToHex(String userInput) {
        String input = "";
        int j = 0;
        int i = 0;
        boolean verify = VerifyZeroOne(userInput); 
        while (!verify)
        {
            System.out.print("You have not entered correct binary digit. Please "
                    + "reenter: ");
            userInput = console.next();
            verify = VerifyZeroOne(userInput);
        }
        //add extra bits to make it 16 bits
        int length =userInput.length();
        if (length == 1) userInput = "000000000000000" + userInput;
        else if (length == 2) userInput = "00000000000000" + userInput;
        else if (length == 3) userInput = "0000000000000" + userInput;
        else if (length == 4) userInput = "000000000000" + userInput;
        else if (length == 5) userInput = "00000000000" + userInput;
        else if (length == 6) userInput = "0000000000" + userInput;
        else if (length == 7) userInput = "000000000" + userInput;
        else if (length == 8) userInput = "00000000" + userInput;
        else if (length == 9) userInput = "0000000" + userInput;
        else if (length == 10) userInput = "000000" + userInput;
        else if (length == 11) userInput = "00000" + userInput;
        else if (length == 12) userInput = "0000" + userInput;
        else if (length == 13) userInput = "000" + userInput;
        else if (length == 14) userInput = "00" + userInput;
        else if (length == 15) userInput = "0" + userInput;
        // to test adding bits. 
        //System.out.println("userInput before split: " + userInput);
        //split string into 4 sub strings 
        length = userInput.length();
        String sub[] = new String [length/4];
        for (i=0;i<4;i++){
            sub[i]=userInput.substring((i*4),(i+1)*4);
        }
        for(j=0;j<4;j++){
            switch (sub[j]){
                case "0000": userInput = "0"; break;
                case "0001": userInput = "1"; break;
                case "0010": userInput = "2"; break;
                case "0011": userInput = "3"; break;
                case "0100": userInput = "4"; break;
                case "0101": userInput = "5"; break;
                case "0110": userInput = "6"; break;
                case "0111": userInput = "7"; break;
                case "1000": userInput = "8"; break;
                case "1001": userInput = "9"; break;
                case "1010": userInput = "A"; break;
                case "1011": userInput = "B"; break;
                case "1100": userInput = "C"; break;
                case "1101": userInput = "D"; break;
                case "1110": userInput = "E"; break;
                case "1111": userInput = "F"; break;
            }
            input = input + userInput;   
        }
        //print out value after 0
        char hex[] = input.toCharArray();
        for (i=0;i<input.length();i++){
            if (hex[i] != '0'){
                userInput = input.substring(i);
                break;
            } 
        }
        return userInput;
    }
    //e. to convert decimal to binary using 2's complement
    public static String ConvertDecToBinTwoCom(String userInput){
        //String addOne = "1";
        String result = "";
        int length = 0;
        int i = 0;
        //int j = 0;
        int inputInt=Integer.parseInt(userInput);
        //System.out.println("convert to int " + inputInt);
        if (inputInt >= 0){
            result = ConvertDecToBin(userInput);
        }
        else {
            //make it positive
            inputInt = inputInt * (-1);
            result = Integer.toString(inputInt);
            //System.out.println("positive decimal " + result);
            //convert to binary
            result = ConvertDecToBin(result);
            //System.out.println("negative convert to positive: "+ result);
            //add bits
            length = result.length();
            if (length == 1) result = "000000000000000" + result;
            else if (length == 2) result = "00000000000000" + result;
            else if (length == 3) result = "0000000000000" + result;
            else if (length == 4) result = "000000000000" + result;
            else if (length == 5) result = "00000000000" + result;
            else if (length == 6) result = "0000000000" + result;
            else if (length == 7) result = "000000000" + result;
            else if (length == 8) result = "00000000" + result;
            else if (length == 9) result = "0000000" + result;
            else if (length == 10) result = "000000" + result;
            else if (length == 11) result = "00000" + result;
            else if (length == 12) result = "0000" + result;
            else if (length == 13) result = "000" + result;
            else if (length == 14) result = "00" + result;
            else if (length == 15) result = "0" + result;
            //inverse every bit
            char inverse[] = result.toCharArray();
            for (i = 0; i<inverse.length;i++){
                if (inverse[i] == '0')
                    inverse[i] = '1';
                else if (inverse[i] == '1')
                    inverse[i] = '0';
            }
            result = new String(inverse);
            //System.out.println("after inverse " + result);
            //convert to decimal to add one
            result = ConvertBinToDec(result);
            inputInt = Integer.parseInt(result);
            //System.out.println("convert to decimal before add one "+ inputInt);
            inputInt = inputInt + 1;
            // System.out.println("inputInt + 1 = " + inputInt);
            //convert back to binary
            result = Integer.toString(inputInt);
            result = ConvertDecToBin(result);
        }
        return result;
    }
    //function to convert binary to decimal using 2's complement 
    public static String ConvertBinToDecTwoCom(String userInput) {
        String result = "";
        int inputInt = 0;
        int checkSign = 0;
        int i = 0;
        char arrayInput[] = userInput.toCharArray();
        //System.out.println("arrayInput first element " + arrayInput[0]);
        //check positive
        if (arrayInput[0] == '0'){
            checkSign = 0;
            //System.out.println("checkSign positive " + checkSign );
        }
        //check negative
        else if (arrayInput[0] == '1'){
            checkSign = 1; 
            //System.out.println("checkSign negative " + checkSign );
        }
        //if positive call ConvertBinToDec(
        if (checkSign == 0)
           result = ConvertBinToDec(userInput);
        //if negative add bits and negate then call ConvertBinToDec()
        else { 
            for (i = 0; i<arrayInput.length;i++){
                if (arrayInput[i] == '0')
                    arrayInput[i] = '1';
                else if (arrayInput[i] == '1')
                    arrayInput[i] = '0';
            }
            result = new String(arrayInput);
            //System.out.print("After inverse " + result);
            // after negate add one
            result = ConvertBinToDec(result);
            inputInt = Integer.parseInt(result) + 1;
            result = Integer.toString(inputInt);
        }
        if (checkSign != 0)
            result = "-"+result;
        return result;
    }
    //function to verify binary digits from user input
    public static boolean VerifyZeroOne(String userInput){
        boolean verify = true;
        char zeroOrOne[] = userInput.toCharArray();
        for (int i = 0; i< zeroOrOne.length;i++)
        {
            if (zeroOrOne[i] == '0')
                verify = true;
            else if (zeroOrOne[i ] == '1') 
                verify = true;
            else {
                verify = false;
                break;
            }
        }
        return verify;
    }
    //function to verify correct hex digits from user input
    public static boolean VerifyHexDigit(String userInput) {
        boolean verify = true;
        char hex[] = userInput.toCharArray();
        for (int i = 0; i<hex.length; i++){
            switch (hex[i]){
                case '1': verify = true; break;
                case '2': verify = true; break;
                case '3': verify = true; break;
                case '4': verify = true; break;
                case '5': verify = true; break;
                case '6': verify = true; break;
                case '7': verify = true; break;
                case '8': verify = true; break;
                case '9': verify = true; break;
                case 'A': verify = true; break;
                case 'B': verify = true; break;
                case 'C': verify = true; break;
                case 'D': verify = true; break;
                case 'E': verify = true; break;
                case 'F': verify = true; break;
                case '0': verify = true; break;
                default: verify = false;
            }
            if (!verify) break;
        }
        return verify;
        }
    private static boolean VerifyUnsignedInt(String userInput){
        boolean verify = true;
        char digit[] = userInput.toCharArray();
        for (int i = 0; i<digit.length;i++){
            switch (digit[i]){
                case '0': verify = true; break;
                case '1': verify = true; break;
                case '2': verify = true; break;
                case '3': verify = true; break;
                case '4': verify = true; break;
                case '5': verify = true; break;
                case '6': verify = true; break;
                case '7': verify = true; break;
                case '8': verify = true; break;
                case '9': verify = true; break;
                default : verify = false;
            }
            if (!verify) break;
        }
        return verify;        
    }
    private static boolean VerifySignedInt(String userInput){
        boolean verify = true;
        char digit[] = userInput.toCharArray();
        for (int i = 0; i<digit.length;i++){
            switch (digit[i]){
                case '-': verify = true;
                    if (i>0) verify = false; 
                    break;
                case '0': verify = true; break;
                case '1': verify = true; break;
                case '2': verify = true; break;
                case '3': verify = true; break;
                case '4': verify = true; break;
                case '5': verify = true; break;
                case '6': verify = true; break;
                case '7': verify = true; break;
                case '8': verify = true; break;
                case '9': verify = true; break;
                default : verify = false;
            }
            if (!verify) break;
        }
        return verify;        
    }
}
