public class ListParser
{

	public class FastaSequence 
	{
		String seqID;
		String seqDets;
		
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
			float gcRatio = 0;
			
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
				gcRatio = ((numG+numC)/seqDets.length());	
				}
			return gcRatio;
		}
	}
	
	public static List <FastaSequence> readFastaFile(String file) throws Exception
	{
		
		
		BufferedReader reader = new BufferedReader (new FileReader (new File("/Users/laurenbrazell/Documents/eclipse_workspace/Lab5/anole.fa")));
		
		List<FastaSequence> fastaList = null;
		
		FastaSequence fs = new ListParser.FastaSequence();
		
		for(String nextLine = reader.readLine(); nextLine != null; nextLine = reader.readLine())
		{
			if (nextLine.startsWith(">"))
			//If the nextLine begins with ">", prompts writing of seqID and removes the ">" for readability in the file.
			{
				if (fs.seqID.length() > 0) 
				//If there is a sequence ID (i.e., the length is greater than 0), this counts the # of characters in the string.	
				{
					fastaList.add(fs);
					fs = null;
				//Clear out your variables
				}
				fs.seqID = nextLine.replace(">", "");
			} 
			
			else if (nextLine.equals("")) 
			// If next line does not have a value, that means we are not in a sequence. Therefore we need to write out
			// the data that we have.
			{
				fastaList.add(fs);
				fs = null;
			}
			
			else
			//If we are not on a sequence ID or the end of a sequence, this adds the string and the nextLine
			//to concatenate multiple lines together.
			{
				fs.seqDets = fs.seqDets + nextLine;
			}
		}
		
		if (fs.seqDets.length() > 0) 
		//If we have a sequence ID but we've reached the end of the file without writing sequence details, 
		//this writes it.
		{
			fastaList.add(fs);
			fs = null;
		} 
		
		
		
		return fastaList;
		
	}	
	

	public static void writeUnique(String inputFile, String outputFile) throws Exception
	
	{
		BufferedWriter writer = new BufferedWriter (new FileWriter (new File(outputFile)));
		
		List<FastaSequence>fastaList = ListParser.readFastaFile(inputFile);
		HashMap<String, Integer> countMap = new HashMap<String, Integer>();
		
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
		
			
			//Sort values here from smallest count to largest before writing
			countMap.forEach((key,value) -> 
			{
				try
				{
					writer.write(key +"\n" + value +"\n");
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
		{
			
		}
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
