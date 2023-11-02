package hw04;

import java.util.List;
import java.util.stream.IntStream;

public class Euclidean implements DistanceMetric {
    public double distance(List<Double> p1, List<Double> p2) {
        double sumOfSquared = IntStream.range(0, p1.size())
                .mapToDouble(i -> Math.pow(p1.get(i) - p2.get(i), 2))
                .sum();
        return Math.sqrt(sumOfSquared);
    }
}
