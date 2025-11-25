/**
 * File: MapParser.java
 * Description: Responsible for reading the input map text file and converting it into internal data structures.
 * Author: Atefeh Akhlaqi
 * Student ID: 110374792
 * Email ID: akhay006
 * AI Tool Used:
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapParser {

    public static Graph parseFile(String filename) {
        Graph graph = new Graph();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean parsingEdges = false;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                if (line.equals("--")) {
                    parsingEdges = true;
                    continue;
                }

                if (!parsingEdges) {

                    String[] parts = line.split(":");
                    int id = Integer.parseInt(parts[0].trim());
                    int points = Integer.parseInt(parts[1].trim());
                    graph.addNode(id, points);
                } else {

                    String[] side = line.split("/");
                    String[] nodeParts = side[0].split(",");

                    int from = Integer.parseInt(nodeParts[0].trim());
                    int to = Integer.parseInt(nodeParts[1].trim());
                    int time = Integer.parseInt(side[1].trim());

                    graph.addEdge(from, to, time);
                    graph.addEdge(to, from, time);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file " + filename + ": " + e.getMessage());
        }

        return graph;
    }
}
