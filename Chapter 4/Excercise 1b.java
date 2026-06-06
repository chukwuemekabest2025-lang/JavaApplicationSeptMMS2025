Error: Using k != 1.0 as a termination condition with floating-point numbers (double or float) is dangerous. Because of binary floating-point precision inaccuracies, k will likely never exactly equal 1.0, causing an infinite loop.

Correction: Use a relational inequality condition like <.

Java
for (double k = 0.1; k < 1.0; k += 0.1) 
    System.out.println(k);