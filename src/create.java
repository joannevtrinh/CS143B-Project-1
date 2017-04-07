import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class create 
{
	ReadyList rList;
	private Scanner x;
	PrintWriter outputStream;
	String outputFileName = "/Volumes/cs143b/87597916.txt";
	
	public void print()
	{
		rList.printInfo();
	}
	
	public void openOutputFile()
	{
		try
		{
			outputStream = new PrintWriter(outputFileName);
			rList = new ReadyList(outputStream);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("OutputFile cannot be opened.");
		}

	}
	public void openFile()
	{
		try
		{
			x = new Scanner(new File("/Volumes/cs143b/input.txt"));
		}
		catch(Exception e)
		{
			System.out.println("Cannot find file");
		}
	}
	
	public void readFile()
	{
		while(x.hasNextLine())
		{
			String a = x.next();
			
			//System.out.println(a +" " + b +" ");
			
			if(a.equals("init"))
			{
				ReadyList newrList = new ReadyList(outputStream);
				rList = newrList;
			}
			//Create request
			if(a.equals("cr"))
			{				
				String b = x.next();
				String c = x.next();
				int cI = Integer.parseInt(c);
				
				boolean isDupe = false;
				boolean realP = false;
				
				for(int i = 0; i < rList.readyList2.size(); i++)
				{
					if(rList.readyList2.get(i).getName().equals(b))
					{
						isDupe = true;
					}
				}
				
				for(int i = 0; i < rList.readyList1.size(); i++)
				{
					if(rList.readyList1.get(i).getName().equals(b))
					{
						isDupe = true;
					}
				}
				
				if(cI == 0)
				{
					outputStream.print("Error");
					realP = true;
				}
				
				if(isDupe == false && realP == false)
				{
					rList.createP(b, cI, outputStream);
				}
				else
				{
					outputStream.print("Error");
				}
				
			}//End of Create request
			
			//Destroy request
			if(a.equals("de"))
			{
				String b = x.next();
				boolean found = false;
				//Will have an error if we cannot the process you want to destroy is there.
				for(int i = 0; i < rList.readyList2.size(); i++)
				{
					if(rList.readyList2.get(i).getName().equals(b))
					{
						found = true;
					}
				}
				for(int i = 0; i < rList.readyList1.size(); i++)
				{
					if(rList.readyList1.get(i).getName().equals(b))
					{
						found = true;
					}
				}
				for(int i = 0; i < rList.R1.WaitingList.size(); i++)
				{
					if(rList.R1.WaitingList.get(i).getName().equals(b))
					{
						found = true;
					}
				}
				for(int i = 0; i < rList.R2.WaitingList.size(); i++)
				{
					if(rList.R2.WaitingList.get(i).getName().equals(b))
					{
						found = true;
					}
				}
				for(int i = 0; i < rList.R3.WaitingList.size(); i++)
				{
					if(rList.R3.WaitingList.get(i).getName().equals(b))
					{
						found = true;
					}
				}
				for(int i = 0; i < rList.R4.WaitingList.size(); i++)
				{
					if(rList.R4.WaitingList.get(i).getName().equals(b))
					{
						found = true;
					}
				}
				
				if(b.equals("init"))
				{
					outputStream.print("Error");
					found = false;
				}
				
				if(found == false && !b.equals("init"))
				{
					outputStream.print("Error");
				}
				
				if(found == true)
				{
					rList.destroy(b, outputStream);
				}
				
			}//End of destroy request
			
			//Request resources command
			if(a.equals("req"))
			{
				String b = x.next();
				String c = x.next();
				int cI = Integer.parseInt(c);
				boolean isValidR = true;
				
				if(!b.equals("R1") && !b.equals("R2") && !b.equals("R3") && !b.equals("R4"))
				{
					isValidR = false;
					outputStream.print("Error");
				}
				
				if(b.equals("R1") && isValidR == true)
				{
					if(cI > 1)
					{
						outputStream.print("Error");
					}
					else
					{
						rList.request(b, cI, outputStream);
					}
				}
				
				if(b.equals("R2") && isValidR == true)
				{
					if(cI > 2)
					{
						outputStream.print("Error");
					}
					else
					{
						rList.request(b, cI, outputStream);
					}
				}
				
				if(b.equals("R3") && isValidR == true)
				{
					if(cI > 3)
					{
						outputStream.print("Error");
					}
					else
					{
						rList.request(b, cI, outputStream);
					}
				}
				if(b.equals("R4") && isValidR == true)
				{
					if(cI > 4)
					{
						outputStream.print("Error");
					}
					else
					{
						rList.request(b, cI, outputStream);
					}
				}
			}//End of request
	
			if(a.equals("rel"))
			{
				String b = x.next();
				String c = x.next();
				int cI = Integer.parseInt(c);
				boolean isValidR = true;
				boolean resourceE = false;
				int r = 0;
				Process running = new Process();
				
				if(!b.equals("R1") && !b.equals("R2") && !b.equals("R3") && !b.equals("R4"))
				{
					isValidR = false;
					outputStream.print("Error");
				}
				
				for(int i = 0; i < rList.readyList2.size(); i++)
				{
					if(rList.readyList2.get(i).getStatus().equals("running"))
					{
						running = rList.readyList2.get(i);
					}
				}
				for(int i = 0; i < rList.readyList1.size(); i++)
				{
					if(rList.readyList1.get(i).getStatus().equals("running"))
					{
						running = rList.readyList1.get(i);
					}
				}
				for(int i = 0; i < running.resourceList.size(); i++)
				{
					if(running.resourceList.get(i).getName().equals(b))
					{
						resourceE = true;
						r = running.resourceList.get(i).getUsedUnits();
					}
				}
				if(isValidR == true && resourceE == true && cI <= r)
				{
					rList.release(b, cI, outputStream);

				}
				else
				{
					outputStream.print("Error");
				}
			}//end of release
			
			if(a.equals("to"))
			{
				rList.timeout(outputStream);
			}
		}
	}
	
	public void closeFile()
	{
		x.close();
		outputStream.close();

	}
	
	
	public static void main (String [] args)
	{
		
		create obj = new create();
		obj.openOutputFile();
		obj.openFile();
		obj.readFile();
		obj.closeFile();
		
		//obj.print();
		//System.out.println(obj.rList.readyList2.get(0).resourceList.isEmpty());
		/* Test case one, passed.
		obj.rList.createP("x", 2);
		obj.rList.createP("y", 1);
		obj.rList.timeout();
		obj.rList.createP("z", 2);
		obj.rList.timeout();
		obj.rList.request("R1", 1);
		obj.rList.timeout();
		obj.rList.request("R1", 1);
		obj.rList.destroy("z"); //When we destroy z, we must find a way to check the waiting lists and release them manually.
		obj.rList.release("R1", 1);
		obj.rList.destroy("x");
	
		*/
		
		 //**CHECK BLOCKED STATEMENTS. ADDED RETURN***//
		
		//Test Case 2
		/*WORKS
		obj.rList.createP("x", 1);
		obj.rList.createP("p", 1);
		obj.rList.createP("q", 1);
		obj.rList.createP("r", 1);
		obj.rList.timeout();
		obj.rList.request("R2", 1);
		obj.rList.timeout();
		obj.rList.request("R3", 3);
		obj.rList.timeout();
		obj.rList.request("R4", 3);
		obj.rList.timeout();
		obj.rList.timeout();
		obj.rList.request("R3", 1);
		obj.rList.request("R4", 2);
		obj.rList.request("R2", 2);
		obj.rList.timeout();
		obj.rList.destroy("q");
		obj.rList.timeout(); //Brings out x, when it's suppose to be p.
		obj.rList.timeout();
		*/
		
		//Test Case 3
		
		/*
		 * cr x 2
			cr y 1
			to
			cr z 2
			to
			req R1 1
			to
			req R1 1
			de z
			rel R1 1
			de x
		 */
		
		/*
		
		
		obj.rList.createP("x", 2);
		obj.rList.createP("y", 1);
		obj.rList.timeout();
		obj.rList.createP("z", 2);
		obj.rList.timeout();
		obj.rList.request("R1",1);
		obj.rList.timeout();
		obj.rList.request("R1",1);
		obj.rList.destroy("z"); //Z's resources weren't properly released. Causing y to output in second to last statement.
		//System.out.println(obj.rList.R1.getFreeUnits());
		obj.rList.release("R1",1); //This test case might have wrong results because it is implied that when destroy happens
									// all resources are released and then rescheduled to the next waitinglist item.
								//In this case, X does get R1, and goes back on the ready List. However, if it were to request another R1,
								//It should be blocked.
		
		obj.print();
		obj.rList.destroy("x");


	
		
		/*
		obj.rList.createP("x", 1);
		obj.rList.createP("p", 1);
		obj.rList.createP("q", 1);
		obj.rList.createP("r", 1);
		obj.rList.timeout();
		obj.rList.request("R2", 2);
		obj.rList.timeout();
		obj.rList.request("R3", 2);
		obj.rList.timeout();
		obj.rList.request("R4", 1);
		obj.rList.timeout();
		obj.rList.timeout();
		obj.rList.request("R3", 2);
		obj.rList.request("R4", 4);
		obj.rList.request("R2", 1);
		obj.rList.timeout();
		obj.rList.destroy("q");
		obj.rList.timeout();
		obj.rList.timeout();
		//obj.rList.request("R1", 2);
		/*
		 * cr x 1
			cr p 1
			cr q 1
			cr r 1
			to
			req R2 2
			to
			req R3 2
			to
			req R4 1
			to
			to
			req R3 2
			req R4 4
			req R2 1
			to
			de q
			to
			to
			req R1 2

		 */
		
	


		



		
		
		
		

	}
}

