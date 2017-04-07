import java.util.LinkedList;


public class Resource 
{
	String name;
	int totalUnits;
	int usedUnits;
	int freeUnits;
	LinkedList<Process> WaitingList = new LinkedList<Process>();
	
	public Resource()
	{
		
	}
	
	public Resource(String name)
	{
		this.name = name;
	}
	
	public Resource(String name, int totalUnits)
	{
		this.name = name;
		this.totalUnits = totalUnits;
		this.freeUnits = totalUnits;
	}
	public int getFreeUnits()
	{
		return freeUnits;
	}
	public void setFreeUnits(int freeUnits)
	{
		this.freeUnits = freeUnits;
	}
	public String getName()
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getTotalUnits() 
	{
		return totalUnits;
	}

	public void setTotalUnits(int totalUnits)
	{
		this.totalUnits = totalUnits;
	}

	public int getUsedUnits() 
	{
		return usedUnits;
	}

	public void setUsedUnits(int usedUnits) 
	{
		this.usedUnits = usedUnits;
	}

	public LinkedList<Process> getWaitingList() 
	{
		return WaitingList;
	}

	public void setWaitingList(LinkedList<Process> waitingList)
	{
		WaitingList = waitingList;
	}
	

	
	
	
}
