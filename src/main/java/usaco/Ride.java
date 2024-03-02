package usaco;/*
ID: elendil2
LANG: JAVA
TASK: ride
*/

import java.io.*;

// https://train.usaco.org/usacoprob2?a=UcYDRz1p0P0&S=ride
public class Ride {
    static boolean decideToGo(String s1, String s2) {
        return getStringProductModulo47(s1) == getStringProductModulo47(s2);
    }

    static int getStringProductModulo47(String s) {
        int out = 1;
        for (char c : s.toCharArray()) {
            out *= (c - 'A' + 1);
        }
        return out % 47;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader f = new BufferedReader(new FileReader("ride.in"));
             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));) {
            boolean b = decideToGo(f.readLine(), f.readLine());
            // Get line, break into tokens
            out.println(b ? "GO" : "STAY");
        }
    }
}

