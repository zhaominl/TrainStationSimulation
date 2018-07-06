//Li,Zhaomin 11671379
//Li, Kevin 58571313
public class TrainSystemManager
{
	private TrainStation[] station = new TrainStation[5];
	public TrainSystemManager(){
		for (int i=0;i<5;++i){
			station[i]=new TrainStation();
		}
	}
	
	synchronized void departGuest(int numOfGuest, int destination, int stationNum){
		int[]update = station[stationNum].getPassengerRequests();
		update[destination]+=numOfGuest;
		station[stationNum].setPassengerRequests(update);
		
		update = station[stationNum].getTotalDestinationRequests();
		update[destination]+=numOfGuest;
		station[stationNum].setTotalDestinationRequests(update);
		
	}
	
	public TrainStation[] getStation()
	{
		return station;
	}

	public void setStation(TrainStation[] station)
	{
		this.station = station;
	}

	synchronized void currentDepartingGuest(int destination , int stationNum){
		station[stationNum].setApproachingtrain(-1);
		
		int[] nonArray=new int[5];
		for (int i=0; i<5; ++i){
			if (i != destination){
				nonArray[i]=station[stationNum].getPassengerRequests()[i];
			}
			else{
				nonArray[i]=0;
			}
		}
		station[stationNum].setPassengerRequests(nonArray);
	}
	
	synchronized void arrivedGuest(int numOfGuest, int stationNum){
		
		int[] update = station[stationNum].getArrivedPassengers();
		update[stationNum]+=numOfGuest;
		station[stationNum].setArrivedPassengers(update);
	}
	
	synchronized void updateApproachingTrain(int trainID,int stationNum){
		station[stationNum].setApproachingtrain(trainID);
	}
	
	synchronized int checkUpdateApproachingTrain(boolean trainForward,int currentTrainStation,int trainID){
		int indexOfStation=0;
		int destinationStation = -1;
		for(TrainStation eachStation : station){
			int total=0;
			for(int each : eachStation.getPassengerRequests()){
				total+=each;
			}
			//manager.getStations()[indexOfStation];
			if (total!=0 && eachStation.getApproachingtrain() == -1){
				if(destinationStation!=-1&&!trainForward && indexOfStation>= currentTrainStation)break;
				destinationStation=indexOfStation;
				if(trainForward && destinationStation >= currentTrainStation)break;
				//else if(!trainForward && destinationStation <= currentTrainStation)break;
			}
			indexOfStation++;
		}
		if (destinationStation!=-1){
		station[destinationStation].setApproachingtrain(trainID);
		}
		
		return destinationStation;
	}
}
