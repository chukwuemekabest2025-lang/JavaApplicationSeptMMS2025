/*Error: While is capitalized. In a do...while statement, the loop must execute while the counter is less than or equal to 100, otherwise it misses 100.

Correction:

Java*/
int counter = 2; 
do { 
    System.out.println(counter);
    counter += 2; 
} while (counter <= 100);