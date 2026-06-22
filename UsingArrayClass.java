import java.util.Arrays;

public class UsingArrayClass{
	public static void main(String[] args){
		int[] numbers = {5,8,2,3,9,4,1,6,7,10};
		int[] a = {8,9,6};
		int[] b = {8,9,6};
		
		int[] c = {8,6,7};
		int[] d = {4,3,6};
		
		int[] e = {4,3,6};
		int[] f = {8,6,7};
		
		int[] g = {9,4,5};
		int[] h = {5,7,3};
		
		
		int[] myFillArr = new int[10];
		Arrays.fill(myFillArr,5);
		
		Arrays.sort(numbers);
		System.out.println("The element of the array are");
		
		for(int number : numbers){
			System.out.printf("%d%n",number);
		}
		System.out.println("Binary search");
		int index = Arrays.binarySearch(numbers,9);
		System.out.printf("The index number of the element 9 is %d%n",index);
		
		boolean isEqual = Arrays.equals(a,b);
		System.out.printf("The result is %b%n", isEqual);
		
		int isProper = Arrays.compare(c,d);
		System.out.printf("The result is %d%n", isProper);
		
		int isImproper = Arrays.compare(e,f);
		System.out.printf("The result is %d%n", isImproper);
		
		int isproEqual = Arrays.compare(g,h);
		System.out.printf("The result is %d%n", isproEqual);
		
		System.out.println("My fill arrays are");
		for(int number : myFillArr){
			System.out.printf("%d%n",number);
		}
		
		int[] evenNumbers = {2,4,6,8,10,12,14,16,18,20};
		int[] copyArr = Arrays.copyOf(evenNumbers,10);
		System.out.println("The elements of the array are");
		for(int arr : copyArr){
			System.out.printf("%d%n",arr);
		}
		
		System.out.println(Arrays.toString(evenNumbers));
		
		int[][] multi = {
			{9,8,6},
			{6,7,4},
			{9,3,2},
		};
		System.out.println(Arrays.deepToString(multi));
	}
}