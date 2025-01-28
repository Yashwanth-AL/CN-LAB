package Lab2; //optional statement

import java.util.*;

class Main {
    static class Frame {
        String content;
        int fnum;

        Frame(int fnum, String content) {
            this.fnum = fnum;
            this.content = content;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of frames: ");
        int n = sc.nextInt();
        sc.nextLine(); 

        Frame[] F = new Frame[n];
        System.out.println("Enter the frame details:");
        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter frame number: ");
            int fnum = sc.nextInt();
            sc.nextLine(); 
            System.out.print("Enter frame content: ");
            String content = sc.nextLine();
            F[i] = new Frame(fnum, content);
        }

        Collections.shuffle(Arrays.asList(F));

        System.out.println("\nBefore Sorting (Shuffled frames):");
        for (Frame frame : F) {
            System.out.print(frame.content + " ");
        }

        Arrays.sort(F, (a, b) -> b.fnum - a.fnum);

        System.out.println("\n\nAfter Sorting the frames:");
        for (Frame frame : F) {
            System.out.print(frame.content + " ");
        }
        System.out.println();

        sc.close();
    }
}
