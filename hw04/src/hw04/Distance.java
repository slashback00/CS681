package hw04;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Distance {

    public static double get(List<Double> p1, List<Double> p2, DistanceMetric metric) {
        return metric.distance(p1, p2);
    }

    public static double[][] matrix(List<List<Double>> points, DistanceMetric metric) {
        int numOfPoints = points.size();
        double[][] distanceMatrix = new double[numOfPoints][numOfPoints];

        for (int i = 0; i < numOfPoints; i++) {
            for (int j = 0; j < i; j++) { 
                double distance = get(points.get(i), points.get(j), metric);
                distanceMatrix[i][j] = distance;
                distanceMatrix[j][i] = distance; 
            }
            distanceMatrix[i][i] = 0.0; 
        }

        return distanceMatrix;
    }

    public static void printDistanceMatrix(double[][] distanceMatrix) {
        for (double[] row : distanceMatrix) {
            for (double distance : row) {
                System.out.printf("%.3f\t", distance);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Euclidean euclideanCalculator = new Euclidean();
        Manhattan manhattanCalculator = new Manhattan();
    
        //  generate a list of 1,000 points, each with 100 dimensions
        List<List<Double>> largePoints = IntStream.range(0, 1000)
                .mapToObj(i -> IntStream.range(0, 100)
                        .mapToDouble(j -> Math.random())
                        .boxed()
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    
     
        
        double[][] largeDistanceMatrixEuclidean = matrix(largePoints, euclideanCalculator);
       
        System.out.printf("Distance between first and second point (Euclidean): %.3f%n", largeDistanceMatrixEuclidean[0][1]);
    
              
        double[][] largeDistanceMatrixManhattan = matrix(largePoints, manhattanCalculator);
       
        System.out.printf("Distance between first and second point (Manhattan): %.3f%n", largeDistanceMatrixManhattan[0][1]);
      
    }
}
