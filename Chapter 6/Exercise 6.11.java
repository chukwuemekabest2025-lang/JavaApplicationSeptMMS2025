a) Set the 10 elements of integer array counts to zero.
for (int i = 0; i < counts.length; i++) {
    counts[i] = 0;
}
// Alternatively: java.util.Arrays.fill(counts, 0);

b) Add one to each of the 15 elements of integer array bonus.
for (int i = 0; i < bonus.length; i++) {
    bonus[i]++;
}

c) Display the five values of integer array bestScores in column format.
for (int i = 0; i < bestScores.length; i++) {
    System.out.println(bestScores[i]);
}
