Error: The condition n < 10 means the loop stops when n hits 10. It will only print values from 1 to 9.

Correction: Change the condition to n <= 10.

Java
n = 1;
while (n <= 10) 
    System.out.println(n++);