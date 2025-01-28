package Lab4;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of vertices: ");
        int n = sc.nextInt();

        System.out.println("Enter the weight matrix of the graph :");
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter the source vertex: ");
        int source = sc.nextInt();
        bellmanFord(n, graph, source); 

        sc.close();
    }

    public static void bellmanFord(int n, int graph[][], int source) {
        int[] dist = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE); 
        dist[source] = 0; 

        // Step 2: Relax edges repeatedly
        for (int i = 0; i < n - 1; i++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                            && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v]; // Relax the edge
                    }
                }
            }
        }

        // Step 3: Check for negative weight cycles
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    System.out.println("Negative weight cycle detected.");
                    return;
                }
            }
        }

        printSolution(dist);
    }

    public static void printSolution(int[] dist) {
        System.out.println("\nVertex \t Distance from Source");
        for (int i = 0; i < dist.length; i++) {
            System.out.println((i) + " \t\t " + (dist[i] == Integer.MAX_VALUE ? "Infinity" : dist[i]));
        }
    }
}

