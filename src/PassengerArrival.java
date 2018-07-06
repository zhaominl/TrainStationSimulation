//Li,Zhaomin 11671379
//Li, Kevin 58571313
public class PassengerArrival
{
	private int numPassengers;
	private int destinationTrainStation;
	private int timePeriod;
	private int expectedTimeOfArrival;
	public PassengerArrival(int np, int dts, int tp){
		numPassengers=np;
		destinationTrainStation=dts;
		timePeriod=tp;
		expectedTimeOfArrival=timePeriod;
	}
	public int getNumPassengers()
	{
		return numPassengers;
	}
	public void setNumPassengers(int numPassengers)
	{
		this.numPassengers = numPassengers;
	}
	public int getDestinationTrainStation()
	{
		return destinationTrainStation;
	}
	public void setDestinationTrainStation(int destinationTrainStation)
	{
		this.destinationTrainStation = destinationTrainStation;
	}
	public int getTimePeriod()
	{
		return timePeriod;
	}
	public void setTimePeriod(int timePeriod)
	{
		this.timePeriod = timePeriod;
	}
	public int getExpectedTimeOfArrival()
	{
		return expectedTimeOfArrival;
	}
	public void setExpectedTimeOfArrival(int expectedTimeOfArrival)
	{
		this.expectedTimeOfArrival = expectedTimeOfArrival;
	}
	
}
