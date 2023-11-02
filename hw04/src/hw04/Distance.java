package hw04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Distance {
	public static double get(List<Double> p1, List<Double> p2) {
		return Distance.get(p1, p2);
	}
	
	public static double get(List<Double> p1, List<Double> p2, DistanceMetric metric) {
		return metric.distance(p1, p2);
	}
	
	public static List<List<Double>> matrix(List<List<Double>> points) {
			return Distance.matrix(points);
	}; 
	
	public static List<List<Double>> matrix(List<List<Double>> points, DistanceMetric metric) {
        int numOfPoints = points.size();

        List<List<Double>> distanceMatrix = IntStream.range(0, numOfPoints)
                .parallel() // Use parallel stream for improved performance
                .mapToObj(i -> IntStream.range(0, numOfPoints)
                        .mapToObj(j -> Distance.get(points.get(i), points.get(j), metric))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        return distanceMatrix;
    }

	public static void printDistanceMatrix(List<List<Double>> distanceMatrix) {
        for (List<Double> row : distanceMatrix) {
            for (Double distance : row) {
                System.out.print(distance + "\t");
            }
            System.out.println();
        }
    
	}
        public static void main(String[] args) {
            // Create instances of your distance calculation classes
            Euclidean euclideanCalculator = new Euclidean();
            Manhattan manhattanCalculator = new Manhattan();
    
            // Sample data
            List<Double> p1 = Arrays.asList(2.0, 3.0);
            List<Double> p2 = Arrays.asList(7.0, 5.0);
    
            // Calculate distances
            double euclideanDistance = euclideanCalculator.distance(p1, p2);
            double manhattanDistance = manhattanCalculator.distance(p1, p2);
    
            System.out.println("Euclidean Distance: " + euclideanDistance);
            System.out.println("Manhattan Distance: " + manhattanDistance);

			List<List<Double>> points = Arrays.asList(p1, p2);
            List<List<Double>> distanceMatrix = matrix(points, euclideanCalculator); 
            printDistanceMatrix(distanceMatrix);

            List<List<Double>> points1 = Arrays.asList(p1, p2);
            List<List<Double>> distanceMatrix1 = matrix(points1, manhattanCalculator); 
            System.out.println("Distance Matrix:");
            printDistanceMatrix(distanceMatrix1);
    }
    }

    


