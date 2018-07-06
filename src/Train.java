import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Li,Zhaomin 11671379
//Li, Kevin 58571313

public class Train implements Runnable
{
	private int trainID;
	private int currentTrainStation;
	private int numPassengers=0;
	private boolean trainForward = true;
	private int totalLoadedPassengers=0;

	private int totalUnloadedPassengers=0; //total number of passengers this train has unloaded
	private ArrayList<TrainEvent> moveQueue;
	private TrainEvent primaryEvent =null;
	private int primaryDestination;
	private int[] passengerDestinations;
	private TrainSystemManager manager;
	
	//This one is new
	private ArrayList<PassengerArrival> arrivalList;
	
	public Train(int newTrainID, TrainSystemManager tsm){
		trainID = newTrainID;
		manager = tsm;
		//makeNewMoveQueue();
		
	}
	


	
	//Train will pick up pasengers that will want to get dropped off at the closest station
	//No requirements for which train will get it
	//Don't use sleep
	//Moving for pick up and drop off
	//
	
	//Static variable inside TrainSim
	//When while loop in simulation ends, we set the variable to True
	//While(SimulationEnd == False):
	//keep running the loop until you set simulationEnd == True
	//While loop will end when simulation ends
	
	public void run()
	{
		// TODO Auto-generated method stub
		//When the train
		//Method calls need to be synchronized for tsm
		//Use synchronized methods
//		for (TrainEvent event : moveQueue){
//			if even
		
		
		
		while(!TrainSimulation.simulationEnd){
			
			if(numPassengers == 0){
				if (primaryEvent==null){
					//get new passengers
					//Creating an event
					
					
					
//					int indexOfStation=0;
//					int destinationStation = -1;
//					for(TrainStation station : manager.getStation()){
//						int total=0;
//						for(int each : station.getPassengerRequests()){
//							total+=each;
//						}
//						//manager.getStations()[indexOfStation];
//						if (total!=0 && station.getApproachingtrain() == -1){
//							if(destinationStation!=-1&&!trainForward && indexOfStation>= currentTrainStation)break;
//							destinationStation=indexOfStation;
//							if(trainForward && destinationStation >= currentTrainStation)break;
//							//else if(!trainForward && destinationStation <= currentTrainStation)break;
//						}
//						indexOfStation++;
//					}
					int destinationStation=manager.checkUpdateApproachingTrain(trainForward, currentTrainStation, trainID);
					//look at the destination and say if we change the direction
					boolean defaultDirection = trainForward;//!!!!
					if((destinationStation - currentTrainStation)<0&&destinationStation != -1) trainForward = false;
					if((destinationStation - currentTrainStation)>=0&&destinationStation != -1) trainForward = true;
					if(destinationStation != -1){
						primaryEvent=new TrainEvent();
						int duration = -1;
						if (destinationStation == currentTrainStation){duration=10;}
						else{
						if(trainForward) duration = 5*(destinationStation - currentTrainStation -1) + 10;
						else duration = 5*(currentTrainStation-destinationStation-1)+10;
						}
						primaryEvent.setExpectedArrival(SimClock.getSimTime() + duration);
						primaryEvent.setDestination(destinationStation);
						
//						if (manager.getStation()[destinationStation].getApproachingtrain()==-1){
//							for (int j=0;j<trainID;j++){try
//							{
//								Thread.sleep(2);
//							}
//							catch (InterruptedException e)
//							{
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}}
						//manager.updateApproachingTrain(trainID,primaryEvent.getDestination());
						//System.out.println("Getting New Passengers");
						System.out.println("Simulated Clock: "+SimClock.getSimTime()+
							"\nTrain "+trainID+" heads to station "+primaryEvent.getDestination()+" for pick-up\n");
						}
						else{
							primaryEvent=null;trainForward=defaultDirection;
						}
						
					//}
					//else primaryEvent=null;
				}//if no event , find the new destination to pick up
				
				else{
					if(SimClock.getSimTime()>= primaryEvent.getExpectedArrival()){
						
						currentTrainStation = primaryEvent.getDestination();
						//manager.updateApproachingTrain(trainID,primaryEvent.getDestination());
						
						//Drop off passengers
						//manager.arrivedGuest(numPassengers,currentTrainStation);
						//There are methods in TrainStation
						
						//pick up passengers
						//manager.
						//Also methods in TS<
						primaryEvent = new TrainEvent();
						
					
						
						//HERE
						//I need to update event for dropoff
						//primaryEvent.setDestination(manager.getStations()[currentTrainStation].getPassengerRequests()[trainID]);
						//HERE
							
						//int	index=0
							int destination=-1;
							int[]currentPassengerInStation = manager.getStation()[currentTrainStation].getPassengerRequests();
							for(int d=0;d<4;++d){
								if (currentPassengerInStation[d]>0){
									if(destination!=-1&&!trainForward && d>= currentTrainStation)break;
									destination=d;
									if(trainForward && destination >= currentTrainStation)break;
									//break;
								}

								//station needs to come from tsm
								//
								//if(destinationStation!=-1&&!trainForward && indexOfStation>= currentTrainStation)break;
								//if (station.getPassengerRequest[i]!=0)destinationStation=i;
								//if(trainForward && destinationStation >= currentTrainStation)break;
								//After you find the station don't forget to change the forward and backward
							}
							if (destination ==-1)destination =4;
							numPassengers=currentPassengerInStation[destination];
							totalLoadedPassengers+=numPassengers;
							currentPassengerInStation[destination]=0;
							manager.getStation()[currentTrainStation].setPassengerRequests(currentPassengerInStation);
							//look at the destination and say if we change the direction
							if((destination - currentTrainStation)<0) trainForward = false;
							if((destination - currentTrainStation)>0) trainForward = true;
							if(destination!=-1){
								primaryEvent=new TrainEvent();
								int duration = -1;
								if (destination == currentTrainStation){duration=10;}
								else{
								if(trainForward) duration = 5*(destination - currentTrainStation -1) + 10;
								else duration = 5*(currentTrainStation-destination-1)+10;
								}
								primaryEvent.setExpectedArrival(SimClock.getSimTime() + duration);
								primaryEvent.setDestination(destination);
								System.out.println("Simulated Clock: "+SimClock.getSimTime()+
									"\nTrain "+trainID+" is at station "+currentTrainStation+
									" to pick up "+numPassengers+" who are(is) heading to station "+
										primaryEvent.getDestination()+"\n");
								System.out.println("Simulated Clock: "+SimClock.getSimTime()+
									"\nTrain "+trainID+" heads to station "+primaryEvent.getDestination()+" for drop-off\n");
							}
						
						manager.updateApproachingTrain(-1,currentTrainStation);
					}
					
					//else System.out.println("Train"+Integer.toString(trainID)+" is still on the way to pick-up");
				}//if has event, it is now on the way or picking up guest,update the event
				
			}
			//
			else if(numPassengers != 0){
				//Go drop off passengers or still on the way
				if(SimClock.getSimTime()>= primaryEvent.getExpectedArrival()){
					currentTrainStation = primaryEvent.getDestination();
					System.out.println("Simulated Clock: "+SimClock.getSimTime()+
						"\nTrain "+trainID+" is at station "+currentTrainStation+
						" to drop off "+numPassengers+" passenger\n");
					totalUnloadedPassengers += numPassengers;
					
					manager.arrivedGuest(numPassengers, primaryEvent.getDestination());
					numPassengers = 0;
					//manager.updateApproachingTrain(-1,primaryEvent.getDestination());

					primaryEvent = null;

					
				}
				
				//else System.out.println("Train"+Integer.toString(trainID)+" is still on the way to drop-off");
				
		}//if has passenger, then it must be drop-off
				
//				if(primaryEvent.getDestination() != currentTrainStation){
//					if(trainForward){
//						//tick
//						
//						duration = 5*(destinationStation - currentTrainStation -1) + 10;
//					}
//					else duration = 5*(currentTrainStation-destinationStation-1)+10;
//				}
//				else{
//					//
//				}
			}
}
			//
			
	
    public int getTrainID()
	{
		return trainID;
	}


	public void setTrainID(int trainID)
	{
		this.trainID = trainID;
	}


	public int getCurrentTrainStation()
	{
		return currentTrainStation;
	}


	public void setCurrentTrainStation(int currentTrainStation)
	{
		this.currentTrainStation = currentTrainStation;
	}


	public int getNumPassengers()
	{
		return numPassengers;
	}


	public void setNumPassengers(int numPassengers)
	{
		this.numPassengers = numPassengers;
	}


	public int getTotalLoadedPassengers()
	{
		return totalLoadedPassengers;
	}


	public void setTotalLoadedPassengers(int totalLoadedPassengers)
	{
		this.totalLoadedPassengers = totalLoadedPassengers;
	}


	public int getTotalUnloadedPassengers()
	{
		return totalUnloadedPassengers;
	}


	public void setTotalUnloadedPassengers(int totalUnloadedPassengers)
	{
		this.totalUnloadedPassengers = totalUnloadedPassengers;
	}


	public ArrayList<TrainEvent> getMoveQueue()
	{
		return moveQueue;
	}


	public void setMoveQueue(ArrayList<TrainEvent> moveQueue)
	{
		this.moveQueue = moveQueue;
	}

	public int[] getPassengerDestinations()
	{
		return passengerDestinations;
	}
	public void setPassengerDestinations(int[] passengerDestinations)
	{
		this.passengerDestinations = passengerDestinations;
	}


	public TrainSystemManager getManager()
	{
		return manager;
	}


	public void setManager(TrainSystemManager manager)
	{
		this.manager = manager;
	}


}