import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;

public class ReadyList 
{
	LinkedList<Process> readyList2 = new LinkedList<Process>();
	LinkedList<Process> readyList1 = new LinkedList<Process>();
	LinkedList<Process> readyList0 = new LinkedList<Process>();
	
	Resource R1 = new Resource("R1", 1);
	Resource R2 = new Resource("R2", 2);
	Resource R3 = new Resource("R3", 3);
	Resource R4 = new Resource("R4", 4);
		
	public ReadyList(PrintWriter outputStream)
	{
		Process init = new Process("init", 0);
		init.setStatus("running");
		readyList0.add(init);
		
		outputStream.println("\n");
		outputStream.print((init.getName()) + " ");
	}
	
	public void release(String reName, int units, PrintWriter outputStream)
	{
		//Find running process
		Resource temp = new Resource(reName);
		Process running = new Process();
		
		for(int i = 0; i < readyList2.size(); i++)
		{
			if(readyList2.get(i).getStatus().compareTo("running") == 0)
			{
				running = readyList2.get(i);
			}
		}
		
		for(int i = 0; i < readyList1.size(); i++)
		{
			if(readyList1.get(i).getStatus().compareTo("running") == 0)
			{
				running = readyList1.get(i);
			}
		}
		
		//find the resource inside this you want to delete.
		for(int i = 0; i < running.resourceList.size(); i++)
		{
			if(running.resourceList.get(i).getName().compareTo(reName) == 0)
			{
				temp = running.resourceList.get(i);
				
				if(reName.equals("R1"))
				{
					int RU = R1.getUsedUnits();
					R1.setUsedUnits(RU - units);
					int f = R1.getFreeUnits();
					R1.setFreeUnits(f + units);
					
				}
				if(reName.equals("R2"))
				{
					int RU = R2.getUsedUnits();
					R2.setUsedUnits(RU - units);
					int f = R2.getFreeUnits();
					R2.setFreeUnits(f + units);
					
				}
				if(reName.equals("R3"))
				{
					int RU = R3.getUsedUnits();
					R3.setUsedUnits(RU - units);
					int f = R3.getFreeUnits();
					R3.setFreeUnits(f + units);
					
				}
				if(reName.equals("R4"))
				{
					int RU = R4.getUsedUnits();
					R4.setUsedUnits(RU - units);
					int f = R4.getFreeUnits();
					R4.setFreeUnits(f + units);
					
				}
				
				for(int a = 0; a < readyList2.size(); a++)
				{
					if(readyList2.get(a).getStatus().compareTo("running") == 0)
					{
						for(int b = 0; b < readyList2.get(a).resourceList.size(); b++)
						{
							if(readyList2.get(a).resourceList.get(b).getName().compareTo(reName) == 0)
							{
								int u = readyList2.get(a).resourceList.get(b).getUsedUnits();
								int newU = u - units;
								if(newU <= 0)
								{
									readyList2.get(a).resourceList.remove(temp);
								}
								else
								{
									readyList2.get(a).resourceList.get(b).setUsedUnits(newU);
								}
							}
						}
					}
				}
				
				for(int a = 0; a < readyList1.size(); a++)
				{
					if(readyList1.get(a).getStatus().compareTo("running") == 0)
					{
						for(int b = 0; b < readyList1.get(a).resourceList.size(); b++)
						{
							if(readyList1.get(a).resourceList.get(b).getName().compareTo(reName) == 0)
							{
								int u = readyList1.get(a).resourceList.get(b).getUsedUnits();
								int newU = u - units;
								if(newU <= 0)
								{
									readyList1.get(a).resourceList.remove(temp);
								}
								else
								{
									readyList1.get(a).resourceList.get(b).setUsedUnits(newU);
								}
							}
						}
					}
				}
				
			}
		}
		
		int b = 0;
		
		if(reName.equals("R1"))
		{
			while(!R1.WaitingList.isEmpty() && R1.getFreeUnits() >= R1.WaitingList.get(b).reqUnits)
			{
				Resource tempR = new Resource(reName);
				tempR.setUsedUnits(R1.WaitingList.get(b).reqUnits);
				
				int f = R1.getFreeUnits();
				R1.setFreeUnits(f - R1.WaitingList.get(b).reqUnits);
				
				
				Process tempP = R1.WaitingList.get(b);
				R1.WaitingList.remove(b);
				tempP.setStatus("ready");
				tempP.setReqUnits(0);
				tempP.resourceList.add(tempR);
				int p = tempP.getPriority();
				
				if(p == 2)
				{
					readyList2.add(tempP);
				}
				if(p == 1)
				{
					readyList1.add(tempP);
				}
				
				Scheduler(outputStream);
				
			}
		}
		
		if(reName.equals("R2"))
		{
			while(!R2.WaitingList.isEmpty() && R2.getFreeUnits() >= R2.WaitingList.get(b).reqUnits)
			{
				Resource tempR = new Resource(reName);
				tempR.setUsedUnits(R2.WaitingList.get(b).reqUnits);
				
				int f = R2.getFreeUnits();
				R2.setFreeUnits(f - R2.WaitingList.get(b).reqUnits);
				
				
				Process tempP = R2.WaitingList.get(b);
				R2.WaitingList.remove(b);
				tempP.setStatus("ready");
				tempP.setReqUnits(0);
				tempP.resourceList.add(tempR);
				int p = tempP.getPriority();
				
				if(p == 2)
				{
					readyList2.add(tempP);
				}
				if(p == 1)
				{
					readyList1.add(tempP);
				}
				
				Scheduler(outputStream);
				
			}
		}
		
		if(reName.equals("R3"))
		{
			while(!R3.WaitingList.isEmpty() && R3.getFreeUnits() >= R3.WaitingList.get(b).reqUnits)
			{
				Resource tempR = new Resource(reName);
				tempR.setUsedUnits(R3.WaitingList.get(b).reqUnits);
				
				int f = R3.getFreeUnits();
				R3.setFreeUnits(f - R3.WaitingList.get(b).reqUnits);
				
				
				Process tempP = R3.WaitingList.get(b);
				R3.WaitingList.remove(b);
				tempP.setStatus("ready");
				tempP.setReqUnits(0);
				tempP.resourceList.add(tempR);
				int p = tempP.getPriority();
				
				if(p == 2)
				{
					readyList2.add(tempP);
				}
				if(p == 1)
				{
					readyList1.add(tempP);
				}
				
				Scheduler(outputStream);
				
			}
		}
		
		if(reName.equals("R4"))
		{
			while(!R4.WaitingList.isEmpty() && R4.getFreeUnits() >= R4.WaitingList.get(b).reqUnits)
			{
				Resource tempR = new Resource(reName);
				tempR.setUsedUnits(R4.WaitingList.get(b).reqUnits);
				
				int f = R4.getFreeUnits();
				R4.setFreeUnits(f - R4.WaitingList.get(b).reqUnits);
				
				
				Process tempP = R4.WaitingList.get(b);
				R4.WaitingList.remove(b);
				tempP.setStatus("ready");
				tempP.setReqUnits(0);
				tempP.resourceList.add(tempR);
				int p = tempP.getPriority();
				
				if(p == 2)
				{
					readyList2.add(tempP);
				}
				if(p == 1)
				{
					readyList1.add(tempP);
				}
				
				Scheduler(outputStream);
				
			}
		}
		
		whoseRunning(outputStream);
	}
	public void request(String name, int units, PrintWriter outputStream)
	{
		Resource tempR = new Resource(name);
		
		if(name.equals("R1"))
		{
			if(units <= R1.getFreeUnits())
			{
				tempR.setUsedUnits(units);
				int usedR = R1.getUsedUnits();
				R1.setUsedUnits(usedR + units);
				int freeU = R1.getFreeUnits();
				R1.setFreeUnits(freeU - units);
			
				for(int i = 0; i < readyList2.size(); i++)
				{
					if(readyList2.get(i).getStatus().compareTo("running") == 0)
					{
						readyList2.get(i).resourceList.add(tempR);
						whoseRunning(outputStream);
					}
				}
				for(int i = 0; i < readyList1.size(); i++)
				{
					if(readyList1.get(i).getStatus().compareTo("running") == 0)
					{
						readyList1.get(i).resourceList.add(tempR);
						whoseRunning(outputStream);
					}
				}
			}
			else
			{
				for(int i = 0; i < readyList2.size(); i++)
				{
					if(readyList2.get(i).getStatus().compareTo("running") == 0)
					{
						readyList2.get(i).setStatus("blocked");
						readyList2.get(i).setReqUnits(units);
						R1.WaitingList.add(readyList2.get(i));
						readyList2.remove(i);
						Scheduler(outputStream);
						return;
					}
				}
				for(int i = 0; i < readyList1.size(); i++)
				{
					if(readyList1.get(i).getStatus().compareTo("running") == 0)
					{
						readyList1.get(i).setStatus("blocked");
						readyList1.get(i).setReqUnits(units);
						R1.WaitingList.add(readyList1.get(i));
						readyList1.remove(i);
						Scheduler(outputStream);
						return;
						
					}
				}
			}
		}
		if(name.equals("R2"))
		{
			if(units <= R2.getFreeUnits())
			{
				tempR.setUsedUnits(units);
				int usedR = R2.getUsedUnits();
				R2.setUsedUnits(usedR + units);
				int freeU = R2.getFreeUnits();
				R2.setFreeUnits(freeU - units);
			
				for(int i = 0; i < readyList2.size(); i++)
				{
					if(readyList2.get(i).getStatus().compareTo("running") == 0)
					{
						readyList2.get(i).resourceList.add(tempR);
						whoseRunning(outputStream);

					}
				}
				for(int i = 0; i < readyList1.size(); i++)
				{
					if(readyList1.get(i).getStatus().compareTo("running") == 0)
					{
						readyList1.get(i).resourceList.add(tempR);
						whoseRunning(outputStream);

					}
				}
			}
			else
			{
				for(int i = 0; i < readyList2.size(); i++)
				{
					if(readyList2.get(i).getStatus().compareTo("running") == 0)
					{
						readyList2.get(i).setStatus("blocked");
						readyList2.get(i).setReqUnits(units);
						R2.WaitingList.add(readyList2.get(i));
						readyList2.remove(i);
						Scheduler(outputStream);
						return;
						
					}
				}
				for(int i = 0; i < readyList1.size(); i++)
				{
					if(readyList1.get(i).getStatus().compareTo("running") == 0)
					{
						readyList1.get(i).setStatus("blocked");
						readyList1.get(i).setReqUnits(units);
						R2.WaitingList.add(readyList1.get(i));
						readyList1.remove(i);
						Scheduler(outputStream);
						return;
						
					}
				}
			}
		}
		if(name.equals("R3"))
		{
			if(units <= R3.getFreeUnits())
			{
				tempR.setUsedUnits(units);
				int usedR = R3.getUsedUnits();
				R3.setUsedUnits(usedR + units);
				int freeU = R3.getFreeUnits();
				R3.setFreeUnits(freeU - units);
			
				for(int i = 0; i < readyList2.size(); i++)
				{
					if(readyList2.get(i).getStatus().compareTo("running") == 0)
					{
						readyList2.get(i).resourceList.add(tempR);
						whoseRunning(outputStream);

					}
				}
				for(int i = 0; i < readyList1.size(); i++)
				{
					if(readyList1.get(i).getStatus().compareTo("running") == 0)
					{
						readyList1.get(i).resourceList.add(tempR);
						whoseRunning(outputStream);

					}
				}
			}
			else
			{
				for(int i = 0; i < readyList2.size(); i++)
				{
					if(readyList2.get(i).getStatus().compareTo("running") == 0)
					{
						readyList2.get(i).setStatus("blocked");
						readyList2.get(i).setReqUnits(units);

						R3.WaitingList.add(readyList2.get(i));
						readyList2.remove(i);
						Scheduler(outputStream);
						return;
						
					}
				}
				for(int i = 0; i < readyList1.size(); i++)
				{
					if(readyList1.get(i).getStatus().compareTo("running") == 0)
					{
						readyList1.get(i).setStatus("blocked");
						readyList1.get(i).setReqUnits(units);
						R3.WaitingList.add(readyList1.get(i));
						readyList1.remove(i);
						Scheduler(outputStream);
						return;
						
					}
				}
			}
			
		}
		
		if(name.equals("R4"))
		{
			if(units <= R4.getFreeUnits())
			{
				tempR.setUsedUnits(units);
				int usedR = R4.getUsedUnits();
				R4.setUsedUnits(usedR + units);
				int freeU = R4.getFreeUnits();
				R4.setFreeUnits(freeU - units);
			
				for(int i = 0; i < readyList2.size(); i++)
				{
					if(readyList2.get(i).getStatus().compareTo("running") == 0)
					{
						readyList2.get(i).resourceList.add(tempR);
						whoseRunning(outputStream);

					}
				}
				for(int i = 0; i < readyList1.size(); i++)
				{
					if(readyList1.get(i).getStatus().compareTo("running") == 0)
					{
						readyList1.get(i).resourceList.add(tempR);
						whoseRunning(outputStream);

						
					}
				}
			}
			else
			{
				for(int i = 0; i < readyList2.size(); i++)
				{
					if(readyList2.get(i).getStatus().compareTo("running") == 0)
					{
						readyList2.get(i).setStatus("blocked");
						readyList2.get(i).setReqUnits(units);
						R4.WaitingList.add(readyList2.get(i));
						readyList2.remove(i);
						Scheduler(outputStream);
						return;
						
					}
				}
				for(int i = 0; i < readyList1.size(); i++)
				{
					if(readyList1.get(i).getStatus().compareTo("running") == 0)
					{
						readyList1.get(i).setStatus("blocked");
						readyList1.get(i).setReqUnits(units);
						R4.WaitingList.add(readyList1.get(i));
						readyList1.remove(i);
						Scheduler(outputStream);
						return;
						
					}
				}
			}
		}
	}
	
	public void Scheduler(PrintWriter outputStream)
	{
		//Find the process that's running
		Process current = new Process();
		
		for(int i = 0; i < readyList2.size(); i++)
		{
			if(readyList2.get(i).status.compareTo("running") == 0)
			{
				current = readyList2.get(i);
			}
		}
		
		for(int i = 0; i < readyList1.size(); i++)
		{
			if(readyList1.get(i).status.compareTo("running") == 0)
			{
				current = readyList1.get(i);
			}
		}
		for(int i = 0; i < readyList0.size(); i++)
		{
			if(readyList0.get(i).status.compareTo("running") == 0)
			{
				current = readyList0.get(i);
			}
		}
		
		// Find the highest priority now.
		Process highestP = new Process();
		int hPriority = 0;
		
		for(int i = 0; i < readyList2.size(); i++)
		{
			if(readyList2.get(i).priority > hPriority)
			{
				highestP = readyList2.get(i);
				hPriority = highestP.getPriority();

			}
		}
		
		for(int i = 0; i < readyList1.size(); i++)
		{
			if(readyList1.get(i).priority > hPriority)
			{
				highestP = readyList1.get(i);
				hPriority = highestP.getPriority();
			}
		}
		
		for(int i = 0; i < readyList0.size(); i++)
		{
			if(readyList0.get(i).priority >= hPriority)
			{
				highestP = readyList0.get(i);
				hPriority = highestP.getPriority();
			}
		}
		
		if(highestP.getPriority() == 0 && current.getPriority() == 0)
		{
			readyList0.getFirst().setStatus("running");
		}
		
		if(current.getStatus() == null)
		{
			current = highestP;
		}
		
		//current.getStatus() != "running"
		if(current.getPriority() < highestP.getPriority() || current == null
				|| !current.getStatus().equals("running"))
		{
			current.setStatus("ready");
			highestP.setStatus("running");
						
				for(int i = 0; i < readyList2.size(); i++)
				{
					if(readyList2.get(i).getName() == current.getName())
					{
						readyList2.get(i).setStatus("ready");
					}
				}
				
				for(int i = 0; i < readyList1.size(); i++)
				{
					if(readyList1.get(i).getName() == current.getName())
					{
						readyList1.get(i).setStatus("ready");
					}
				}
				
				for(int i = 0; i < readyList0.size(); i++)
				{
					if(readyList0.get(i).getName() == current.getName())
					{
						readyList0.get(i).setStatus("ready");
					}
				}
			}
			
			for(int i = 0; i < readyList2.size(); i++)
			{
				if(readyList2.get(i).getName() == highestP.getName())
				{
					readyList2.get(i).setStatus("running");
				}
			}
			
			for(int i = 0; i < readyList1.size(); i++)
			{
				if(readyList1.get(i).getName() == highestP.getName())
				{
					readyList1.get(i).setStatus("running");
				}
			}
			
			for(int i = 0; i < readyList0.size(); i++)
			{
				if(readyList0.get(i).getName() == highestP.getName())
				{
					readyList0.get(i).setStatus("running");
				}
			}
		
		
		whoseRunning(outputStream);
	}
	
	public void SchedulerD(PrintWriter outputStream)
	{
		//Find the process that's running
		Process current = new Process();
		
		for(int i = 0; i < readyList2.size(); i++)
		{
			if(readyList2.get(i).status.compareTo("running") == 0)
			{
				current = readyList2.get(i);
			}
		}
		
		for(int i = 0; i < readyList1.size(); i++)
		{
			if(readyList1.get(i).status.compareTo("running") == 0)
			{
				current = readyList1.get(i);
			}
		}
		for(int i = 0; i < readyList0.size(); i++)
		{
			if(readyList0.get(i).status.compareTo("running") == 0)
			{
				current = readyList0.get(i);
			}
		}
		
		// Find the highest priority now.
		Process highestP = new Process();
		int hPriority = 0;
		
		for(int i = 0; i < readyList2.size(); i++)
		{
			if(readyList2.get(i).priority > hPriority)
			{
				highestP = readyList2.get(i);
				hPriority = highestP.getPriority();

			}
		}
		
		for(int i = 0; i < readyList1.size(); i++)
		{
			if(readyList1.get(i).priority > hPriority)
			{
				highestP = readyList1.get(i);
				hPriority = highestP.getPriority();
			}
		}
		
		for(int i = 0; i < readyList0.size(); i++)
		{
			if(readyList0.get(i).priority > hPriority)
			{
				highestP = readyList0.get(i);
				hPriority = highestP.getPriority();
			}
		}
		
		if(highestP.getPriority() == 0 && current.getPriority() == 0)
		{
			readyList0.getFirst().setStatus("running");
		}
		
		if(current == null)
		{
			current = highestP;
		}
		
		if(current.getPriority() < highestP.getPriority() 
				|| !current.getStatus().equals("running")
				|| current == null)
		{
			current.setStatus("ready");
			highestP.setStatus("running");
						
			for(int i = 0; i < readyList2.size(); i++)
			{
				if(readyList2.get(i).getName() == current.getName())
				{
					readyList2.get(i).setStatus("ready");
				}
			}
			
			for(int i = 0; i < readyList1.size(); i++)
			{
				if(readyList1.get(i).getName() == current.getName())
				{
					readyList1.get(i).setStatus("ready");
				}
			}
			
			for(int i = 0; i < readyList0.size(); i++)
			{
				if(readyList0.get(i).getName() == current.getName())
				{
					readyList0.get(i).setStatus("ready");
				}
			}
			
			for(int i = 0; i < readyList2.size(); i++)
			{
				if(readyList2.get(i).getName() == highestP.getName())
				{
					readyList2.get(i).setStatus("running");
				}
			}
			
			for(int i = 0; i < readyList1.size(); i++)
			{
				if(readyList1.get(i).getName() == highestP.getName())
				{
					readyList1.get(i).setStatus("running");
				}
			}
			
			for(int i = 0; i < readyList0.size(); i++)
			{
				if(readyList0.get(i).getName() == highestP.getName())
				{
					readyList0.get(i).setStatus("running");
				}
			}
		}
		
	}
	
	public void timeout(PrintWriter outputStream)
	{
		//Find the running process P
		int size2 = readyList2.size();
		
		//Find the running process within RL2
		for(int i = 0; i < size2; i++)
		{
			if(readyList2.get(i).status.compareTo("running") == 0)
			{
				Process temp = readyList2.get(i);
				readyList2.remove(i);
				temp.setStatus("ready");
				readyList2.add(temp);
				Scheduler(outputStream);
			}
		}
		
		int size1 = readyList1.size();
		
		//Find running process with RL1
		for(int i = 0; i < size1; i++)
		{
			if(readyList1.get(i).status.compareTo("running") == 0)
			{
				Process temp = readyList1.get(i);
				readyList1.remove(i);
				temp.setStatus("ready");
				readyList1.add(temp);
				Scheduler(outputStream);
			}
		}
	}
	
	public void whoseRunning(PrintWriter outputStream)
	{
		for(int i = 0; i < readyList2.size(); i++)
		{
			if(readyList2.get(i).status.compareTo("running") == 0)
			{
				outputStream.print((readyList2.get(i).getName()) + " ");
			}
		}
		
		for(int i = 0; i < readyList1.size(); i++)
		{
			if(readyList1.get(i).status.compareTo("running") == 0)
			{
				outputStream.print((readyList1.get(i).getName()) + " ");

			}
		}
		
		for(int i = 0; i < readyList0.size(); i++)
		{
			if(readyList0.get(i).status.compareTo("running") == 0)
			{
				outputStream.print((readyList0.get(i).getName()) + " ");

			}
		}
	}
	
	public void printInfo()
	{
		for(int i = 0; i < readyList2.size(); i++)
		{
			System.out.println(readyList2.get(i).getName());
			System.out.println(readyList2.get(i).getPriority());

		}
		for(int i = 0; i < readyList1.size(); i++)
		{
			System.out.println(readyList1.get(i).getName());
			System.out.println(readyList1.get(i).getPriority());

		}
		for(int i = 0; i < readyList0.size(); i++)
		{
			System.out.println(readyList0.get(i).getName());
			System.out.println(readyList0.get(i).getPriority());

		}

	}
	
	public void createP(String name, int priority, PrintWriter outputStream)
	{
		Process temp = new Process(name, priority);
		int p = temp.getPriority();
		
		for(int i = 0; i < readyList2.size(); i++)
		{
			if(readyList2.get(i).status.compareTo("running") == 0)
			{
				readyList2.get(i).childrenList.add(temp);
			}
		}
		
		for(int i = 0; i < readyList1.size(); i++)
		{
			if(readyList1.get(i).status.compareTo("running") == 0)
			{
				readyList1.get(i).childrenList.add(temp);
			}
		}
		
		for(int i = 0; i < readyList0.size(); i++)
		{
			if(readyList0.get(i).status.compareTo("running") == 0)
			{
				readyList0.get(i).childrenList.add(temp);
			}
		}
		
		if(p == 2)
		{
			readyList2.add(temp);
		}
		else if(p == 1)
		{
			readyList1.add(temp);
		}
		else if(p == 0)
		{
			readyList0.add(temp);
		}
		
		Scheduler(outputStream);
	}
	
	public void releaseD(String reName, int reqUnits, PrintWriter outputStream)
	{
		int b = 0;
	
		if(reName.equals("R1"))
		{
			while(!R1.WaitingList.isEmpty() && R1.getFreeUnits() >= R1.WaitingList.get(b).reqUnits)
			{
				Resource tempR = new Resource(reName);
				tempR.setUsedUnits(R1.WaitingList.get(b).reqUnits);
				
				int f = R1.getFreeUnits();
				R1.setFreeUnits(f - R1.WaitingList.get(b).reqUnits);
				
				Process tempP = R1.WaitingList.get(b);
				R1.WaitingList.remove(b);
				tempP.setStatus("ready");
				tempP.setReqUnits(0);
				tempP.resourceList.add(tempR);
				int p = tempP.getPriority();
				
				if(p == 2)
				{
					readyList2.add(tempP);
				}
				if(p == 1)
				{
					readyList1.add(tempP);
				}
				
				SchedulerD(outputStream);
				
			}
		}
		
		if(reName.equals("R2"))
		{
			while(!R2.WaitingList.isEmpty() && R2.getFreeUnits() >= R2.WaitingList.get(b).reqUnits)
			{
				Resource tempR = new Resource(reName);
				tempR.setUsedUnits(R2.WaitingList.get(b).reqUnits);
				
				int f = R2.getFreeUnits();
				R2.setFreeUnits(f - R2.WaitingList.get(b).reqUnits);
				
				
				Process tempP = R2.WaitingList.get(b);
				R2.WaitingList.remove(b);
				tempP.setStatus("ready");
				tempP.setReqUnits(0);
				tempP.resourceList.add(tempR);
				int p = tempP.getPriority();
				
				if(p == 2)
				{
					readyList2.add(tempP);
				}
				if(p == 1)
				{
					readyList1.add(tempP);
				}
				
				SchedulerD(outputStream);
				
			}
		}
		if(reName.equals("R3"))
		{
			while(!R3.WaitingList.isEmpty() && R3.getFreeUnits() >= R3.WaitingList.get(b).reqUnits)
			{
				Resource tempR = new Resource(reName);
				tempR.setUsedUnits(R3.WaitingList.get(b).reqUnits);
				
				int f = R3.getFreeUnits();
				R3.setFreeUnits(f - R3.WaitingList.get(b).reqUnits);
				
				
				Process tempP = R3.WaitingList.get(b);
				R3.WaitingList.remove(b);
				tempP.setStatus("ready");
				tempP.setReqUnits(0);
				tempP.resourceList.add(tempR);
				int p = tempP.getPriority();
				
				if(p == 2)
				{
					readyList2.add(tempP);
				}
				if(p == 1)
				{
					readyList1.add(tempP);
				}
				
				SchedulerD(outputStream);
				
			}
		}
		if(reName.equals("R4"))
		{
			while(!R4.WaitingList.isEmpty() && R4.getFreeUnits() >= R4.WaitingList.get(b).reqUnits)
			{
				Resource tempR = new Resource(reName);
				tempR.setUsedUnits(R4.WaitingList.get(b).reqUnits);
				
				int f = R4.getFreeUnits();
				R4.setFreeUnits(f - R4.WaitingList.get(b).reqUnits);
				
				
				Process tempP = R4.WaitingList.get(b);
				R4.WaitingList.remove(b);
				tempP.setStatus("ready");
				tempP.setReqUnits(0);
				tempP.resourceList.add(tempR);
				int p = tempP.getPriority();
				
				if(p == 2)
				{
					readyList2.add(tempP);
				}
				if(p == 1)
				{
					readyList1.add(tempP);
				}
				
				SchedulerD(outputStream);
				
			}
		}
	}
	
	public void destroy(String name, PrintWriter outputStream)
	{
		destroyR(name, outputStream);
		Scheduler(outputStream);
	}
	
	public void destroyR(String name, PrintWriter outputStream)
	{
		Process destroy = new Process();
		
		for(int i = 0; i < readyList2.size(); i++)
		{
			if(readyList2.get(i).getName().compareTo(name) == 0)
			{
				destroy = readyList2.get(i);
			}
		}
		for(int i = 0; i < readyList1.size(); i++)
		{
			if(readyList1.get(i).getName().compareTo(name) == 0)
			{
				destroy = readyList1.get(i);
			}
		}
		
		for(int i = 0; i < R1.WaitingList.size(); i++)
		{
			if(R1.WaitingList.get(i).getName().compareTo(name) == 0)
			{
				destroy = R1.WaitingList.get(i);
			}
		}
		for(int i = 0; i < R2.WaitingList.size(); i++)
		{
			if(R2.WaitingList.get(i).getName().compareTo(name) == 0)
			{
				destroy = R2.WaitingList.get(i);
			}
		}
		for(int i = 0; i < R3.WaitingList.size(); i++)
		{
			if(R3.WaitingList.get(i).getName().compareTo(name) == 0)
			{
				destroy = R3.WaitingList.get(i);
			}
		}
		for(int i = 0; i < R4.WaitingList.size(); i++)
		{
			if(R4.WaitingList.get(i).getName().compareTo(name) == 0)
			{
				destroy = R4.WaitingList.get(i);
			}
		}
		
		while(!destroy.childrenList.isEmpty())
		{
			destroyR(destroy.childrenList.getFirst().getName(), outputStream);
			destroy.childrenList.removeFirst();
		}
		
		if(destroy.childrenList.isEmpty())
		{
			if(readyList1.contains(destroy) || R1.WaitingList.contains(destroy) || R2.WaitingList.contains(destroy)
					|| R3.WaitingList.contains(destroy) || R4.WaitingList.contains(destroy))
			{
				while(!destroy.resourceList.isEmpty())
				{
					int usedUnits = destroy.resourceList.get(0).getUsedUnits();
					String nameR = destroy.resourceList.get(0).getName();
					
					if(nameR.equals("R1"))
					{
						int u = R1.getUsedUnits();
						R1.setUsedUnits(u - usedUnits);
						int f = R1.getFreeUnits();
						R1.setFreeUnits(f + usedUnits);
						R1.WaitingList.remove(destroy);
						releaseD("R1", usedUnits, outputStream);
						
					}
					
					if(nameR.equals("R2"))
					{
						int u = R2.getUsedUnits();
						R2.setUsedUnits(u - usedUnits);
						int f = R2.getFreeUnits();
						R2.setFreeUnits(f + usedUnits);
						R2.WaitingList.remove(destroy);
						releaseD("R2", usedUnits, outputStream);


					}
					
					if(nameR.equals("R3"))
					{
						int u = R3.getUsedUnits();
						R3.setUsedUnits(u - usedUnits);
						int f = R3.getFreeUnits();
						R3.setFreeUnits(f + usedUnits);
						R3.WaitingList.remove(destroy);
						releaseD("R3", usedUnits, outputStream);


					}
					
					if(nameR.equals("R4"))
					{
						int u = R4.getUsedUnits();
						R4.setUsedUnits(u - usedUnits);
						int f = R4.getFreeUnits();
						R4.setFreeUnits(f + usedUnits);
						R4.WaitingList.remove(destroy);
						releaseD("R4", usedUnits, outputStream);


					}
					
					destroy.resourceList.remove(0);

				}
				
				if(destroy.getStatus().compareTo("blocked") == 0)
				{
					for(int d = 0; d < R1.WaitingList.size(); d++)
					{
						if(R1.WaitingList.contains(destroy))
						{
							R1.WaitingList.remove(destroy);
						}
					}
					
					for(int d = 0; d < R2.WaitingList.size(); d++)
					{
						if(R2.WaitingList.contains(destroy))
						{
							R2.WaitingList.remove(destroy);
						}
					}
					
					for(int d = 0; d < R3.WaitingList.size(); d++)
					{
						if(R3.WaitingList.contains(destroy))
						{
							R3.WaitingList.remove(destroy);
						}
					}
					for(int d = 0; d < R4.WaitingList.size(); d++)
					{
						if(R4.WaitingList.contains(destroy))
						{
							R4.WaitingList.remove(destroy);
						}
					}
					
				}
				
				for(int a = 0; a < readyList1.size(); a++)
				{
					if(readyList1.get(a).getName() == destroy.getName())
					{
						readyList1.remove(a);
					}
				}
				
			}
		}
		
		if(readyList2.contains(destroy) || R1.WaitingList.contains(destroy) || R2.WaitingList.contains(destroy)
				|| R3.WaitingList.contains(destroy) || R4.WaitingList.contains(destroy))
		{
			while(!destroy.resourceList.isEmpty())
			{
				int usedUnits = destroy.resourceList.get(0).getUsedUnits();
				String nameR = destroy.resourceList.get(0).getName();
				
				if(nameR.equals("R1"))
				{
					int u = R1.getUsedUnits();
					R1.setUsedUnits(u - usedUnits);
					int f = R1.getFreeUnits();
					R1.setFreeUnits(f + usedUnits);
					R1.WaitingList.remove(destroy);
					releaseD("R1", usedUnits, outputStream);

				}
				
				if(nameR.equals("R2"))
				{
					int u = R2.getUsedUnits();
					R2.setUsedUnits(u - usedUnits);
					int f = R2.getFreeUnits();
					R2.setFreeUnits(f + usedUnits);
					R2.WaitingList.remove(destroy);
					releaseD("R2", usedUnits, outputStream);


				}
				
				if(nameR.equals("R3"))
				{
					int u = R3.getUsedUnits();
					R3.setUsedUnits(u - usedUnits);
					int f = R3.getFreeUnits();
					R3.setFreeUnits(f + usedUnits);
					R3.WaitingList.remove(destroy);
					releaseD("R3", usedUnits, outputStream);


				}
				
				if(nameR.equals("R4"))
				{
					int u = R4.getUsedUnits();
					R4.setUsedUnits(u - usedUnits);
					int f = R4.getFreeUnits();
					R4.setFreeUnits(f + usedUnits);
					R4.WaitingList.remove(destroy);
					releaseD("R4", usedUnits, outputStream);
				}
				
				destroy.resourceList.remove(0);
				
			}
			if(destroy.getStatus().compareTo("blocked") == 0)
				{
					for(int d = 0; d < R1.WaitingList.size(); d++)
					{
						if(R1.WaitingList.contains(destroy))
						{
							R1.WaitingList.remove(destroy);
						}
					}
					
					for(int d = 0; d < R2.WaitingList.size(); d++)
					{
						if(R2.WaitingList.contains(destroy))
						{
							R2.WaitingList.remove(destroy);
						}
					}
					
					for(int d = 0; d < R3.WaitingList.size(); d++)
					{
						if(R3.WaitingList.contains(destroy))
						{
							R3.WaitingList.remove(destroy);
						}
					}
					for(int d = 0; d < R4.WaitingList.size(); d++)
					{
						if(R4.WaitingList.contains(destroy))
						{
							R4.WaitingList.remove(destroy);
						}
					}
				}
			}		
			
			for(int a = 0; a < readyList2.size(); a++)
			{
				if(readyList2.get(a).getName() == destroy.getName())
				{
					readyList2.remove(a);
				}
			}
				
		}

	
	public Process getProcess()
	{
		Process temp = readyList2.get(1);
		return temp;
	}
	
}

