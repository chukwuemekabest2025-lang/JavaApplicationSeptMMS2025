Missing opening brace and incorrect semicolon
Error: There is a semicolon right after the while condition, which creates an infinite loop that does nothing. Also, there is a closing brace } without an opening brace {.

Correction: Remove the semicolon and add the opening brace.

Java
i = 1;
while (i <= 10) { 
    ++i; 
}