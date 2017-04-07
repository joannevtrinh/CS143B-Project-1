import java.util.LinkedList;

public class Process
{
	String name; //PID
	int priority; //In this case, can only be 2, 1, 0. 2 being highest.
	String status; // Running or blocked. 
					//If running, it should have a backpointer that points to the Ready List
					//If blocked, '															'
					// Purpose of having a backpointer is to be able to find it on the B or R lists. 
					//But probably could be done without having a backpointer
	
	//***CREATION TREE***
	// Needs to have a backpointer if it has a parent
	// If the process has created children, it needs to keep track of them.
	//**Basically, we must track what children it's created, and if it's created from some sort of parent.**
	LinkedList<Process> childrenList = new LinkedList<Process>();
	LinkedList<Resource> resourceList = new LinkedList<Resource>(); //Linked List or some data structure that holds 
												//the resources this process has acquired
	int reqUnits;

	public int getReqUnits() 
	{
		return reqUnits;
	}

	public void setReqUnits(int reqUnits)
	{
		this.reqUnits = reqUnits;
	}

	public Process()
	{
		
	}
	
	public Process(String name, int priority)
	{
		this.name = name;
		this.priority = priority;
		this.status = "ready";
	}
	
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getPriority() 
	{
		return priority;
	}

	public void setPriority(int priority)
	{
		this.priority = priority;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
	
	
}
