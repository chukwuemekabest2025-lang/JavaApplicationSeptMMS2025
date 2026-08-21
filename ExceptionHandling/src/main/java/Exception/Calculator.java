
package Exception;


public class Calculator {
    public static void main(String [] args){
        int num1 = 10;
        int num2 = 0;
        
        int result = num1 /num2;
        
        System.out.printf("The result is %d%n",result);
        
        if(num1 > 5){
            System.out.println("num 1 is greater than 5");
        }
        else{
            System.out.println("num 1 is not greater than 5");
        }
    }
    
}
