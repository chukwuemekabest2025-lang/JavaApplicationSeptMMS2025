public class Chapter3_39{
	public static void main(String[] args){
		double population = 8200000000.0;
		double rate = 0.0087;
		double increase;
		
		System.out.println("Year\tPopulation\tIncrease");
		System.out.println("");
		
		for (int year = 1; year <= 75; year++){
			increase = population * rate;
			population = population + increase;
			
			System.out.println(year + "\t" + (long) population + "\t" + (long) increase);
			
			if (population >= 16400000000.0){
				System.out.println("Population doubles in year " + year);
				break;
			}
		}
	}
}
