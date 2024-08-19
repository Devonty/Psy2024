package MyMath;

public class Comparator {
    public static final double TOLERANCE = 1E-4;

    public static boolean almostEqual(double a, double b, double eps) {
        // System.out.printf("a=%f, b=%f, esp=%f, delta=%f\n", a, b, eps, Math.abs(a - b));
        return Math.abs(a - b) < eps;
    }

    public static boolean almostEqual(double a, double b) {
        return almostEqual(a, b, TOLERANCE);
    }
}
