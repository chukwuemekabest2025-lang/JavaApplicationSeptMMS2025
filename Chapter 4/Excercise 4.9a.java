Capitalization and syntax inside for header
Error: For is capitalized (Java is case-sensitive). Also, commas are used instead of semicolons to separate the clauses.

Correction:

Java
for (int i = 100; i >= 1; i--) 
    System.out.println(i); 
(Note: I also changed i++ to i-- because counting down from 100 to 1 with an increment would cause an infinite loop).