//Li,Zhaomin 11671379
//Li, Kevin 58571313
public class SimClock
{


	private static long currentTime = 0;
	private static long startTime;
	private static int simTime = 0;
	
	public SimClock(){
		startTime = System.currentTimeMillis();
		//startTime = 0;
		
	}
	
	public static void tick(){
		//Every 100 ms tick, called and increase simulated clock value by one
		simTime += 1;
	}
	
	public static void increaseTime(int n){
		simTime += n;
	}
	
	public static int getTime(){
		currentTime = System.currentTimeMillis();
		return (int) (currentTime - startTime);
		//return 0;
	}
	
	public static long getCurrentTime()
	{
		return currentTime;
	}

	public static void setCurrentTime(long currentTime)
	{
		SimClock.currentTime = currentTime;
	}

	public static long getStartTime()
	{
		return startTime;
	}

	public static void setStartTime(long startTime)
	{
		SimClock.startTime = startTime;
	}

	public static int getSimTime()
	{
		return simTime;
	}

	public static void setSimTime(int simTime)
	{
		SimClock.simTime = simTime;
	}
	
	
}
