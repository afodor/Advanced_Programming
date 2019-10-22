package scratch;
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;
import java.io.*;
import java.util.*;

public class ListParser
{

	public static class FastaSequence 
	{
		 String seqID;
		 String seqDets;
		
		FastaSequence(String seqID, String seqDets)
		{
			this.seqID = seqID;
			this.seqDets = seqDets;
		}
		
		public String getHeader()
		{
		return seqID;
		}

		public String getSequence()
		{
		return seqDets;
		}

		public float getGCRatio()
		{
			int numC = 0;
			int numG = 0;	
			
			for (int c = 0; c < seqDets.length(); c++)
				{
				
					if (seqDets.charAt(c) == 'G')
					{
						
						numG++;
					}
					else if (seqDets.charAt(c) == 'C')
					{
						numC++;
					}	
				}

			int GCSum = numC + numG;
			
			return ((float)GCSum / seqDets.length());
			
		}
	
	}
	
	public static List <FastaSequence> readFastaFile(String file) throws Exception
	{
		
		
		BufferedReader reader = new BufferedReader (new FileReader (new File(file)));
		
		List<FastaSequence> fastaList = new ArrayList<FastaSequence>();
	
		FastaSequence fsTemp = new FastaSequence(null, "");

		//need to assign value to seqID within the fs instance of the FastaSequence class
		
		for(String nextLine = reader.readLine(); nextLine != null; nextLine = reader.readLine())
		{
			
			if (nextLine.startsWith(">"))
			//If the nextLine begins with ">", prompts writing of seqID and removes the ">" for readability in the file.
			{
				if (fsTemp.seqID == null) 
				//If there is a sequence ID, this adds it to seqID.	
				{
					
					fsTemp.seqID = nextLine.replace(">", "");
					
				}
				else
				{
					fastaList.add(fsTemp);
					fsTemp = new FastaSequence(null, "");
				}
			} 
			
			else if (nextLine.equals("")) 
			{
				if (fsTemp.seqID != null)
			// If next line does not have a value, that means we are not in a sequence. Therefore we need to store
			// the data that we have.
				{
					fastaList.add(fsTemp);
					fsTemp = new FastaSequence(null, "");
				}
			}
			
			else
			//If we are not on a sequence ID or the end of a sequence, this adds the string and the nextLine
			//to concatenate multiple lines together.
			{
			
				fsTemp.seqDets = fsTemp.seqDets + nextLine;
			
			}
		}//closes out the for loop
		
		if (fsTemp.seqDets.length() > 0) 
		//If we have a sequence ID but we've reached the end of the file without storing sequence details, 
		//this stores it.
		{
			fastaList.add(fsTemp);
		} 
		
		reader.close();
		
		return fastaList;
	}	
	

	public static void writeUnique(String inputFile, String outputFile) throws Exception
	{
		BufferedWriter writer = new BufferedWriter (new FileWriter (new File(outputFile)));
		
		List<FastaSequence>fastaList = ListParser.readFastaFile(inputFile);
		HashMap<String, Integer> countMap = new LinkedHashMap<String, Integer>();
		
		for (FastaSequence fs : fastaList)
		{
			if (countMap.containsKey(fs.seqDets)) 
			{
				countMap.put(fs.seqDets, countMap.get(fs.seqDets) + 1);
			}
			else
			{
				countMap.put(fs.seqDets, 1);
			}
		}
		
			HashMap <String, Integer> sortedCounts = countMap
			.entrySet()
			.stream()
			.sorted(comparingByValue())
			.collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
			
			sortedCounts.forEach((key,value) -> 
			{
				try
				{
					writer.write(key +"\t" + value +"\n");
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			});
			writer.close();
	}

	public static void main(String[] args) throws Exception
	{

		List<FastaSequence>fastaList = ListParser.readFastaFile("/Users/laurenbrazell/Documents/eclipse_workspace/Lab5/anole.fa");
		
		for (FastaSequence fs : fastaList)
		{
	
			System.out.println(fs.getHeader());
			System.out.println(fs.getSequence());
			System.out.println(fs.getGCRatio());
			
			writeUnique("/Users/laurenbrazell/Documents/eclipse_workspace/Lab5/anole.fa", "/Users/laurenbrazell/Documents/eclipse_workspace/Lab5/parsed_anole.txt");
		}
		
	}
}
