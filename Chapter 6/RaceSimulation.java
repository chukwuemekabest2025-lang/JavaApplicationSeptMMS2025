import java.util.Random;

public class RaceSimulation {
    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random();
        int tortoise = 1;
        int hare = 1;

        System.out.println("BANG !!!!!\nAND THEY'RE OFF !!!!!");

        while (tortoise < 70 && hare < 70) {
            int i = rand.nextInt(10) + 1; 

            if (i <= 5) tortoise += 3;       
            else if (i <= 7) tortoise -= 6;  
            else tortoise += 1;              
            if (tortoise < 1) tortoise = 1;

            
            if (i <= 2) {}                   
            else if (i <= 4) hare += 9;      
            else if (i == 5) hare -= 12;     
            else if (i <= 8) hare += 1;      
            else hare -= 2;                  
            if (hare < 1) hare = 1;

            for (int pos = 1; pos <= 70; pos++) {
                if (pos == tortoise && pos == hare) {
                    System.out.print("OUCH!!!");
                    pos += 6; 
                } else if (pos == tortoise) {
                    System.out.print("T");
                } else if (pos == hare) {
                    System.out.print("H");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
            Thread.sleep(100); 
        }

        if (tortoise >= 70 && hare >= 70) System.out.println("It's a tie.");
        else if (tortoise >= 70) System.out.println("TORTOISE WINS!!! YAY!!!");
        else System.out.println("Hare wins. Yuch.");
    }
}