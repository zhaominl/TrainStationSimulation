import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
//Li,Zhaomin 11671379
//Li, Kevin 58571313
public class TrainSimulation
{
	private ArrayList<ArrayList<PassengerArrival>> paList=new ArrayList<ArrayList<PassengerArrival>>();
	private int totalSimulationTime=0;
	private int secondRate=0;
	private TrainSystemManager trainSystemManager=new TrainSystemManager();
	private Train[] trains= new Train[5];
	public static boolean simulationEnd=false;
	
	public TrainSimulation(){
		for (int i =0; i<5; ++i){
			trains[i]=new Train(i,trainSystemManager);
		}
	}
	
	public void getFile() throws FileNotFoundException{
		try{
			int line = 0;
			PassengerArrival eachPA;
			File file= new File("src/TrainConfig.txt");
			Scanner s = new Scanner(file);
			//s.useDelimiter("\n");
			
			totalSimulationTime = Integer.valueOf(s.nextLine());
			secondRate = Integer.parseInt(s.nextLine());
			System.out.println("Total simulation time is: " +Integer.toString(totalSimulationTime)+
				"\nSecond Rate is: "+secondRate+"\n");
			
			String[] temp;
			//s.useDelimiter(";");
			while(s.hasNext()){
				
				
				temp = s.nextLine().split(";");
			
				for (String eachArrival : temp){
					String[] arrivalInfo = eachArrival.split(" ");
					eachPA=new PassengerArrival(Integer.parseInt(arrivalInfo[0]),Integer.parseInt(arrivalInfo[1]),Integer.parseInt(arrivalInfo[2]));
					//System.out.println(arrivalInfo.length);
//					eachPA.setNumPassengers(Integer.parseInt(arrivalInfo[0]));
//					eachPA.setDestinationTrainStation(Integer.parseInt(arrivalInfo[1]));
//					eachPA.setTimePeriod(Integer.parseInt(arrivalInfo[2]));
//					eachPA.setExpectedTimeOfArrival(eachPA.getTimePeriod());
					if (paList.size()<line+1)paList.add(new ArrayList<PassengerArrival>());
					paList.get(line).add(eachPA);//may have issue
				}
				line++;
			}
//			for (int stationNum=0; stationNum<5; ++stationNum){
//				for (int indexPA=0; indexPA < paList.get(stationNum).size() ; ++indexPA){
//					
//						System.out.println("Train station "+stationNum+" has "+paList.get(stationNum).get(indexPA).getNumPassengers()
//						+" person(s) requesting to go to the station"+paList.get(stationNum).get(indexPA).getDestinationTrainStation()
//						+"in "+paList.get(stationNum).get(indexPA).getTimePeriod()+"\n");
//						
//					
//				}
//			}
		}catch(FileNotFoundException e){
			System.out.println("File not Found!!!");
		}
	}
	public void printTrainState(){
		TrainStation stations[] = trainSystemManager.getStation();
		int index=0;
		for (TrainStation eachStation : stations){
			System.out.println("Station "+Integer.toString(index)+":\n");
			int totalRequest=0,currentRequest=0,totalExit=0;
			String headingTrain = "No";
			for (int i : eachStation.getTotalDestinationRequests()){
				totalRequest+=i;
			}
			for (int i : eachStation.getArrivedPassengers()){
				totalExit+=i;
			}
			for (int i : eachStation.getPassengerRequests()){
				currentRequest+=i;
			}
			if (eachStation.getApproachingtrain()!=-1)headingTrain=Integer.toString(eachStation.getApproachingtrain());
			System.out.println("  Total number of passenger requesting: "+Integer.toString(totalRequest)+"\n"+
					"  Total number of passenger exiting: "+Integer.toString(totalExit)+"\n"+
					"  Current number of passenger requesting: "+Integer.toString(currentRequest)+"\n"+
					"  Current heading train: "+headingTrain+"\n");
			index++;
		}
		
		for (Train eachTrain:trains){
			System.out.println("Train "+Integer.toString(eachTrain.getTrainID())+":\n");
			System.out.println("  Total number of passenger entered: "+Integer.toString(eachTrain.getTotalLoadedPassengers())+"\n"+
					"  Total number of passenger exited: "+Integer.toString(eachTrain.getTotalUnloadedPassengers())+"\n"+
					"  Current number of passenger: "+Integer.toString(eachTrain.getNumPassengers())+"\n");
		}
		
	}
	
	public void start() throws FileNotFoundException, InterruptedException{
		this.getFile();
		
//		ArrayList<ArrayList<Integer>> numOfFormerUpdate= new ArrayList<ArrayList<Integer>>();
//		for (int stationNum=0; stationNum<5; ++stationNum){
//			ArrayList<Integer> intAL=new ArrayList<Integer>();
//			numOfFormerUpdate.add(intAL);
//			for (PassengerArrival eachPAUpdate: paList.get(stationNum)){
//				numOfFormerUpdate.get(stationNum).add(0);
//			}
//		}//recording the former times of passenger arrival update for one station with one destination
		
		SimClock simclock= new SimClock();
		int startTime= simclock.getSimTime();
		//five thread run
		//keeping updating the information in systemManager 
		//while clock<=
		
		Thread thread0 = new Thread(trains[0]);
		Thread thread1 = new Thread(trains[1]);
		Thread thread2 = new Thread(trains[2]);
		Thread thread3 = new Thread(trains[3]);
		Thread thread4 = new Thread(trains[4]);
		thread0.start();
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		//new Thread(trains[1]).start();
		//new Thread(trains[2]).start();
		//new Thread(trains[3]).start();
		//new Thread(trains[4]).start();
				
		while(simclock.getSimTime()<totalSimulationTime){
			//updating the tsm
			int time = simclock.getSimTime();
			for (int stationNum=0; stationNum<5; ++stationNum){
				for (int indexPA=0; indexPA < paList.get(stationNum).size() ; ++indexPA){
					if (time >= paList.get(stationNum).get(indexPA).getExpectedTimeOfArrival() ){
						System.out.println("Simulated Clock: "+Integer.toString(paList.get(stationNum).get(indexPA).getExpectedTimeOfArrival())+
						"\n"+"Train station "+stationNum+" has "+paList.get(stationNum).get(indexPA).getNumPassengers()
						+" person(s) requesting to go to the station "+paList.get(stationNum).get(indexPA).getDestinationTrainStation()
						+"\n");
						paList.get(stationNum).get(indexPA).setExpectedTimeOfArrival(
							paList.get(stationNum).get(indexPA).getExpectedTimeOfArrival()+paList.get(stationNum).get(indexPA).getTimePeriod());
						trainSystemManager.departGuest(paList.get(stationNum).get(indexPA).getNumPassengers(), 
							paList.get(stationNum).get(indexPA).getDestinationTrainStation(), 
							stationNum);
					}
				}
			}
			Thread.sleep(secondRate);
			simclock.tick();
//			
//			int realTimeToSimTime= simclock.getTime() / secondRate;
//			
//			simclock.increaseTime(realTimeToSimTime);
		}
		//simulationEnd=true;
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		simulationEnd=true;
		System.out.println("\nEnd of Simulation\nSimulation Clock: "+Integer.toString(simclock.getSimTime()));
		this.printTrainState();
		thread0.interrupt();
		thread1.interrupt();
		thread2.interrupt();
		thread3.interrupt();
		thread4.interrupt();
		
	}
	
}
