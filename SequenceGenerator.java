package scratch;
import java.util.Random;

public class SequenceGenerator 
{
	public static void main(String[] args)
	{
	String triplet = "aaa";
	String alphabet = "actg";
	int counter = 0;
	Random random = new Random();
	int n = alphabet.length();
		
	for(int start = 0; start < 1001; start++)
	{
		String codon = alphabet.charAt(random.nextInt(n)) + "" + alphabet.charAt(random.nextInt(n)) + "" + alphabet.charAt(random.nextInt(alphabet.length()));
		System.out.println(codon);
		
		if(codon.equals(triplet))
		{
		++counter;
		}
	}

		System.out.println("There are " + counter + " triplets reading 'aaa'.");
		
	}

}