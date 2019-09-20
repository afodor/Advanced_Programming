package scratch;
import java.util.Random;
import java.util.Scanner;

public class AminoAcids
{
	public static String[] SHORT_NAMES =

		{"A","R", "N", "D", "C", "Q", "E",

		"G", "H", "I", "L", "K", "M", "F",

		"P", "S", "T", "W", "Y", "V"};

	public static String[] FULL_NAMES =

		{"alanine","arginine", "asparagine",

		"aspartic acid", "cysteine",

		"glutamine", "glutamic acid",

		"glycine" ,"histidine","isoleucine",

		"leucine", "lysine", "methionine",

		"phenylalanine", "proline",

		"serine","threonine","tryptophan",
		
		"tyrosine", "valine"};

	public static void main(String[] args)
	{
		boolean x = true;
		AminoAcids quizAttempt = new AminoAcids();
		//New instance of the class AminoAcids
		long startTime = System.currentTimeMillis();
		//Gets current time in milliseconds
		double elapsedTime = 0;
		//Defines elapsed time in milliseconds
		Random random = new Random();
		int correctAnswers = 0;		
		while (x == true && elapsedTime < 5000)
			{
			//Add 1 second to current time as long as x = true
			int randomNumber = random.nextInt(FULL_NAMES.length);
			System.out.println(FULL_NAMES[randomNumber]);
			//Call random instance of class by generating random number to correspond
			String guess = System.console().readLine().toUpperCase();
			//Prompt user to enter letter, make input case insensitive
			//Start series of if statements to check
				if(guess.equals(SHORT_NAMES[randomNumber]))
					{
					System.out.println("Great job.");
					++correctAnswers;
					//Add 1 to correct answers
					}
				else
					{
					System.out.println("Sorry, the quiz has ended.");
					break;
					}
				elapsedTime = System.currentTimeMillis() - startTime;
				//System.out.println(elapsedTime);
			}
		System.out.println("You scored a " + correctAnswers + " out of 20.");
	}
}	
//Quiz is 30 seconds long
//Ends after 30 seconds, or when an incorrect acid is input
//Program displays full name of amino acid and asks user to type in one character code
//Quiz is not case sensitive
//Total score is number of correct answers.
