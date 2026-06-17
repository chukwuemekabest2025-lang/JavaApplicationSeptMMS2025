public class MultiDimensionalArray{
	public static void main(String[] args){
		int[][] numbers = {
			{7,8,9,6,4},
			{12,40,50,2,1},
			{25, 63, 223, 22,34}
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