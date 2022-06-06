import dsa.Dijkstra;
import dsa.EdgeWeightedDiGraph;
import dsa.SeparateChainingHashST;
import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

import java.util.NoSuchElementException;

public class MBTA_Guide {
    public static void main(String[] args) {
        String filename1 = args[0]; // MBTA_stations.txt (CSV): containing all MBTA stations' name.
        // Read all lines from filename1.
        In in1 = new In(filename1);
        String[] lines = in1.readAllLines();
        in1.close();

        // st1: a symbol table that maps a station ID to the corresponding station.
        SeparateChainingHashST<Integer, String> st1 = new SeparateChainingHashST<>();
        // st2: a symbol table that maps a station name to the corresponding ID.
        SeparateChainingHashST<String, Integer> st2 = new SeparateChainingHashST<>();

        for (String line : lines) {
            String[] a = line.split(",");
            int stationID = Integer.parseInt(a[0]);
            String stationName = a[1];
            st1.put(stationID, stationName);
            st2.put(stationName, stationID);
        }

        // Get departure station and destination station from Standard Input.
        StdOut.println("Departure:");
        String departure = StdIn.readLine();
        StdOut.println("Destination:");
        String destination = StdIn.readLine();
        // If a given name does not exist, return a NoSuchElementException.
        if (!st2.contains(departure)) {
            throw new NoSuchElementException("No such stations exists");
        }
        if (!st2.contains(destination)) {
            throw new NoSuchElementException("No such station exists");
        }

        String filename2 = args[1]; // MBTA_maps: containing all relationships between stations.
        In in2 = new In(filename2);
        int s = st2.get(departure);
        int t = st2.get(destination);
        EdgeWeightedDiGraph EWD = new EdgeWeightedDiGraph(in2); // Convert the entire route to a directed graph.
        Dijkstra dijkstra = new Dijkstra(EWD, s); // Create a dijkstra object.
        in2.close();

        if (dijkstra.hasPathTo(t)) {
            double time = dijkstra.distTo(t); // approximate time to get the destination.
            StdOut.print("The shortest path from " + departure + " to " + destination);
            StdOut.printf(" (About %.1f min):\n", time);
            int count = 0;
            for (int ID : dijkstra.pathTo(t)) {
                String station_name = st1.get(ID); // Convert a stationID to the corresponding station name.
                count++;
                if (count == 1) {
                    StdOut.print("(" + station_name + ")");
                } else {
                    StdOut.print(" -> (" + station_name + ")");
                }
            }
            StdOut.println();
        } else {
            // the case where the route does not exist.
            StdOut.println("No path from " + departure + " to " + destination);
        }
    }
}

