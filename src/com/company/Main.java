package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);


        System.out.println("Good day and welcome to the EncryptOrDecodeInator, where we serve all your encoding OR decoding needs!");
        System.out.println("Here at CWC (Christians Wacky CreationsTM), we pride ourselves in the amount of choices available to our customers.");
        System.out.println("In this program we have three available ciphers to offer to you: ");
        System.out.println("--------------------");
        System.out.println("Number cipher");
        System.out.println("--------------------");
        System.out.println("Caesar cipher");
        System.out.println("--------------------");
        System.out.println("Vigenère cipher");
        System.out.println("--------------------");

        System.out.println("Please choose the cipher you wish to use: (N/C/V)");
        String whichCipherSmall = in.nextLine();
        String whichCipherBig = whichCipherSmall.toUpperCase();


        if(whichCipherBig.equals("N")){
         System.out.println("Do you wish to encrypt, or decode? (E/D)");
         String encryptOrDecryptNumberCipher = in.nextLine();
         if(encryptOrDecryptNumberCipher.equals("E")){
             System.out.println("Input the message you wish to encode: ");
             String numberMessageToBeEncoded = in.nextLine();
             System.out.print("Your encoded word is: ");
          System.out.print(Arrays.toString(getNumberCipherEncoded(numberMessageToBeEncoded)));

         }else{
             int[] messageToDecode = getNumberArrayFromUser();
             System.out.print("Your decoded word is: ");
             System.out.print(getNumberCipherDecoded(messageToDecode));

         }
        }
        if(whichCipherBig.equals("C")){
            System.out.println("Do you wish to encrypt, or decode? (E/D)");
         String encryptOrDecryptCaesarCipher = in.nextLine();
         if(encryptOrDecryptCaesarCipher.equals("E")){
             System.out.println("Please input the text you wish to encode: ");
             String caesarMessageToBeEncoded = in.nextLine();
             String caesarMessageToBeEncodedBig = caesarMessageToBeEncoded.toUpperCase();
             System.out.println("Further more you need to input the shift you wish to use: ");
             int caesarShiftUsedForEncoding = in.nextInt();
             System.out.println(getCaesarCipherEncoded(caesarMessageToBeEncodedBig, caesarShiftUsedForEncoding));

         }else{
             System.out.println("Please input the cipher you wish to decode: ");
             String caesarMessageToBeDecoded = in.nextLine();
             String caesarMessageToBeDecodedBig = caesarMessageToBeDecoded.toUpperCase();
             System.out.println("Further more you need to input the shift used in the cipher: ");
             int caesarShiftUsedForDecoding = in.nextInt();
          System.out.println(getCaesarCipherDecoded(caesarMessageToBeDecodedBig, caesarShiftUsedForDecoding));


         }

        }//TODO Tilføj Vigenère cipheren til programmet
        /*if(whichCipherBig.equals("V")){
         String encryptOrDecryptVigenèreCipher = in.nextLine();
         if(encryptOrDecryptVigenèreCipher.equals("E")){
          System.out.println(getVigenèreCipherEncoded);

         }else{
          System.out.println(getVigenèreCipherDecoded);

         }

        }*/




    }
    public static int[] getNumberCipherEncoded(String numberMessageToBeEncoded){
        int[] ASCIIValueOfString = stringToASCIIValue(numberMessageToBeEncoded);

        int[] sortedListOfNumbers = ASCIIValuesToSortedList(ASCIIValueOfString);

        return sortedListOfNumbers;
    }
    public static char[] getNumberCipherDecoded(int[] encryptedNumberCipher){
        int[] numberASCIIValueOfList = sortedListToASCIIValues(encryptedNumberCipher);

        char[] decodedNumberMessage = sortedListToLetterArray(numberASCIIValueOfList);

        return decodedNumberMessage;
    }
    public static char[] getCaesarCipherEncoded(String caesarMessageToBeEncoded, int caesarCipherShiftValue ){
        int[] caesarMessageToASCII = stringToASCIIValue(caesarMessageToBeEncoded);

        int[] sortedListOfNumbers = ASCIIValuesToSortedList(caesarMessageToASCII);

        int[] shiftedCaesarList = addShiftToSortedList(sortedListOfNumbers, caesarCipherShiftValue);

        int[] shiftedCaesarASCII = sortedListToASCIIValues(shiftedCaesarList);

        char[] encodedCaesarString = sortedListToLetterArray(shiftedCaesarASCII);

        return encodedCaesarString;
    }
    public static char[] getCaesarCipherDecoded(String caesarMessageToBeDecoded, int caesarCipherShiftValue ){
        int[] encodedCaesarCipherInASCII = stringToASCIIValue(caesarMessageToBeDecoded);

        int[] shiftedCaesarList = ASCIIValuesToSortedList(encodedCaesarCipherInASCII);

        int[] unShiftedCaesarList = unShiftedSortedList(shiftedCaesarList, caesarCipherShiftValue);

        int[] unShiftedCaesarASCII = sortedListToASCIIValues(unShiftedCaesarList);

        char[] decodedCaesarString = sortedListToLetterArray(unShiftedCaesarASCII);

        return decodedCaesarString;
    }


    public static int[] stringToASCIIValue(String textToBeConvertedToValueOf) {
     int messageLength = textToBeConvertedToValueOf.length();
     String bigtextToBeConvertedToValueOf = textToBeConvertedToValueOf.toUpperCase();

     //  Konverter string til char array
     char[] messageToChar = bigtextToBeConvertedToValueOf.toCharArray();

     // Skab et int array med længden af koden der skal krypteres
     int[] numbers = new int[messageLength];

     // Få værdien af de enkelte tal i hele stringen
     for (int i = 0; i < messageToChar.length; i++) {
      numbers[i] = bigtextToBeConvertedToValueOf.codePointAt(i);
     }
     return numbers;
    }
    public static int[] ASCIIValuesToSortedList(int[] ASCIIValues){
        // Tjek de forskellige værdier, hvis bogstavet er indenfor de første 26,
        // skal der trækkes 64 fra, for at få værdierne til at passe med 1-26
        // ÆØÅ og mellemrum har andre værdier som skal tages hånd om
        for (int i = 0; i < ASCIIValues.length ; i++) {
            if(ASCIIValues[i] == 32){
                ASCIIValues[i] -= 32;
            }if(ASCIIValues[i] == 198){
                ASCIIValues[i] -= 107;
            }if(ASCIIValues[i] == 216){
                ASCIIValues[i] -= 124;
            }if(ASCIIValues[i] == 197){
                ASCIIValues[i] -= 104;
            }if(ASCIIValues[i] == 0){
                ASCIIValues[i] = 0;
            }else{
                ASCIIValues[i] -= 64;
            }
        }

        return ASCIIValues;
    }
    public static int[] sortedListToASCIIValues(int[] sortedListOfNumbers) {

        for (int i = 0; i < sortedListOfNumbers.length; i++) {

            if (sortedListOfNumbers[i] == 27) {
                sortedListOfNumbers[i] += 107;
            }
            if (sortedListOfNumbers[i] == 28) {
                sortedListOfNumbers[i] += 124;
            }
            if (sortedListOfNumbers[i] == 29) {
                sortedListOfNumbers[i] += 104;
            }
            if (sortedListOfNumbers[i] == 0) {
                sortedListOfNumbers[i] += 32;
            } else {
                sortedListOfNumbers[i] += 64;
            }
        }
        return sortedListOfNumbers;
    }
    public static char[] sortedListToLetterArray(int[] sortedListToBeConverted){
        char[] decryptedMessage = new char[sortedListToBeConverted.length];
        for (int j = 0; j < sortedListToBeConverted.length; j++) {
            decryptedMessage[j] = (char) sortedListToBeConverted[j];

        }
        return decryptedMessage;
    }
    public static int[] getNumberArrayFromUser(){
        Scanner in = new Scanner(System.in);
        System.out.println("How many numbers are in your codeword?");
        int sizeOfCodeWord = in.nextInt();
        int[] messageInNumberArray = new int[sizeOfCodeWord];
        for (int i = 0; i < sizeOfCodeWord; i++) {
            System.out.print("Indtast tal: ");
            messageInNumberArray[i] = in.nextInt();
        }
        return(messageInNumberArray);


    }
    public static int[] addShiftToSortedList(int[] sortedList, int shiftValue){
        // Ændrer sortedList til shiftedList bare får klargørelse af hvad der bliver returneret
        int[] shiftedList = sortedList;
        for (int i = 0; i < shiftedList.length; i++) {
            if(shiftedList[i] != 0) {
                shiftedList[i] += shiftValue;
            }
            if(shiftedList[i] > 29){
                shiftedList[i] -= 29;
            }

        }
        return shiftedList;
    }
    public static int[] unShiftedSortedList(int[] shiftedList, int shiftValue){
        // Ændrer shiftedList til unShiftedList bare får klargørelse af hvad der bliver returneret
        int[] unShiftedList = shiftedList;
        for (int i = 0; i < unShiftedList.length; i++) {
            if(unShiftedList[i] != 0){
                unShiftedList[i] -= shiftValue;
            }
            if(unShiftedList[i] <= 0 ){
                unShiftedList[i] += 29;
            }
        }
        return unShiftedList;
    }
}
