/*Error: The loop is trying to count down from 19 to 1, but the expression uses i += 2, which increments i infinitely upward.

Correction: Change += to -=.

Java*/
for (int i = 19; i >= 1; i -= 2) 
    System.out.println(i);