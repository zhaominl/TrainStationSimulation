
public class TrainStation
{
	private int[] totalDestinationRequests;
	private int[] arrivedPassengers;
	private int[] passengerRequests;
	int approachingtrain=-1;
	
	TrainStation(){
		totalDestinationRequests= new int[5];
		arrivedPassengers=new int[5];
		passengerRequests=new int[5];
		for (int i=0; i<5; ++i){
			totalDestinationRequests[i]=0;
			arrivedPassengers[i]=0;
			passengerRequests[i]=0;
		}
	}
	
	public int[] getTotalDestinationRequests()
	{
		return totalDestinationRequests;
	}
	public void setTotalDestinationRequests(int[] totalDestinationRequests)
	{
		this.totalDestinationRequests = totalDestinationRequests;
	}
	public int[] getArrivedPassengers()
	{
		return arrivedPassengers;
	}
	public void setArrivedPassengers(int[] arrivedPassengers)
	{
		this.arrivedPassengers = arrivedPassengers;
	}
	public int[] getPassengerRequests()
	{
		return passengerRequests;
	}
	public void setPassengerRequests(int[] passengerRequests)
	{
		this.passengerRequests = passengerRequests;
	}
	public int getApproachingtrain()
	{
		return approachingtrain;
	}
	public void setApproachingtrain(int approachingtrain)
	{
		this.approachingtrain = approachingtrain;
	}
	
}
