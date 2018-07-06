import java.io.FileNotFoundException;
//Li,Zhaomin 11671379
//Li, Kevin 58571313
public class Lab4
{
	
	public static void main(String[] args) throws FileNotFoundException{
		TrainSimulation trainSimulation= new TrainSimulation();
		try
		{
			trainSimulation.start();
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
