package scratch;

import java.io.*;

public class FastaParser
{
	public static void main(String[] args) throws Exception
	{	
		BufferedReader reader = new BufferedReader (new FileReader (new File("/Users/laurenbrazell/Documents/eclipse_workspace/Lab_3/anole.fa")));
		BufferedWriter writer = new BufferedWriter (new FileWriter (new File("/Users/laurenbrazell/Documents/eclipse_workspace/Lab_3/parsed_anole.txt")));
		String seqID = "";
		String seqDets = "";
		
		writer.write("sequenceID\tnumA\tnumC\tnumG\tnumT\tsequence");
		
		for(String nextLine = reader.readLine(); nextLine != null; nextLine = reader.readLine())
		{
			if (nextLine.startsWith(">"))
			//Checks first condition, the nextLine begins with ">", prompts writing of seqID and removes the ">" for readability in the file
			{
				
				if (seqID.length() > 0) 
				//If there is a seqID (i.e., the length is greater than 0), count the # of characters in the string	
				{
					int numA = 0;
					int numC = 0;
					int numG = 0;
					int numT = 0;	
					for (int c = 0; c < seqDets.length(); c++)
						{
							if (seqDets.charAt(c) == 'A')
							{
								numA++;
							}
							else if (seqDets.charAt(c) == 'C')
							{
								numC++;
							}
							else if (seqDets.charAt(c) == 'G')
							{
								numG++;
							}
							else if (seqDets.charAt(c) == 'T')
							{
								numT++;
							}
						}	
				writer.write("\n" + seqID);
					
				writer.write("\t" + numA);
				writer.write("\t"+ numC);
				writer.write("\t"+ numG);
				writer.write("\t" + numT);
				writer.write("\t" + seqDets);	
				
				//Write everything out
				
				seqID = "";	
				seqDets = "";
				//Clear out your variables
				}
				

				seqID = nextLine.replace(">", "");
				
			} 
			else if (nextLine.equals("")) 
			//Checks second condition, the nextLine is blank
			{
				int numA = 0;
				int numC = 0;
				int numG = 0;
				int numT = 0;	
				for (int c = 0; c < seqDets.length(); c++)
					{
						if (seqDets.charAt(c) == 'A')
						{
							numA++;
						}
						else if (seqDets.charAt(c) == 'C')
						{
							numC++;
						}
						else if (seqDets.charAt(c) == 'G')
						{
							numG++;
						}
						else if (seqDets.charAt(c) == 'T')
						{
							numT++;
						}
					}	
			writer.write("\n" + seqID);	
			writer.write("\t" + numA);
			writer.write("\t"+ numC);
			writer.write("\t"+ numG);
			writer.write("\t" + numT);
			writer.write("\t" + seqDets);	
			
			seqID = "";
			seqDets = "";
			}
			
			else
			//If we have seqDets, add this string and the nextLine to concatenate multiple lines together
			{
				seqDets = seqDets + nextLine;
			}
		}
		
		if (seqDets.length() > 0) 
		//Checks third condition, if we have a seqID but we've reached the end of the file without writing seqDets, go ahead and write it
		{
			int numA = 0;
			int numC = 0;
			int numG = 0;
			int numT = 0;	
			for (int c = 0; c < seqDets.length(); c++)
				{
					if (seqDets.charAt(c) == 'A')
					{
						numA++;
					}
					else if (seqDets.charAt(c) == 'C')
					{
						numC++;
					}
					else if (seqDets.charAt(c) == 'G')
					{
						numG++;
					}
					else if (seqDets.charAt(c) == 'T')
					{
						numT++;
					}
				}	
			writer.write("\n" + seqID);	
			writer.write("\t" + numA);
			writer.write("\t"+ numC);
			writer.write("\t"+ numG);
			writer.write("\t" + numT);
			writer.write("\t" + seqDets);	
			
			seqID = "";
			seqDets = "";
		}
		writer.flush(); 
		writer.close();
		reader.close();
	}
}