public class MultiDimensionalArray{
	public static void main(String[] args){
		int[][] numbers = {
			{17,34,43,52,12},
			{13,55,12,67,34},
			{97,52,23,10,30}
		};
		
		System.out.println("The elements of the array are:");
		for (int row = 0; row < 3; row++){
			for (int col = 0; col < 5; col++){
				System.out.print(numbers[row][col] + " ");
			}
			System.out.println();
		}
	}
}