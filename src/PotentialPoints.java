import java.util.Comparator;

public class PotentialPoints implements Comparator<PotentialPoints> {

    @Override
    public String toString() {
        return "PotentialPoints{" +
                "userId=" + userId +
                ", points=" + points +
                '}';
    }

    Integer userId;
    Double points;

    @Override
    public int compare(PotentialPoints o1, PotentialPoints o2) {
        return o1.points.compareTo(o2.points);
    }
}
