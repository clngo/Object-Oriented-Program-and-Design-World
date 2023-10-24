import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AStarPathingStrategy implements PathingStrategy{

    public List<Point> computePath(
            Point start,
            Point end,
            Predicate<Point> canPassThrough,
            BiPredicate<Point, Point> withinReach,
            Function<Point, Stream<Point>> potentialNeighbors
    ) {
        List<Point> openset = new ArrayList<>();
        List<Point> closedset = new ArrayList<>();
        HashMap<Point, Integer> gscores = new HashMap<>();
        HashMap<Point, Integer> fscores = new HashMap<>();
        HashMap<Point, Point> previousmap = new HashMap<>();

        int hscore = Math.abs(end.x - start.x) + Math.abs(end.y - start.y);
        gscores.put(start, 0);
        fscores.put(start, hscore);
        openset.add(0, start);
        while (openset.isEmpty() == false) {

            //Select the node from the Open Set with the best F-Value as the Current node
            int bestf = fscores.get(openset.get(0)); // best = lowest fvalue
            Point current = openset.get(0);
            for (int i = 1; i < openset.size(); i++) {
                //get f value from openset
                int f = fscores.get(openset.get(i));
                if (f < bestf) {
                    bestf = f;
                    current = openset.get(i);
                }
            }
            //Move Current from the Open Set to the Closed Set
            closedset.add(0, current);
            openset.remove(current);

            //If Current is within reach of the End node, go to step 5
            if (withinReach.test(current, end)) {
                List<Point> path = new ArrayList<>();
                while (!current.equals(start)) {
                    path.add(0, current);
                    current = previousmap.get(current);
                }
                return path;
            }

            //get each traversal neighbor of current from potential neighbors
            Stream<Point> neighborStream = potentialNeighbors.apply(current);

            // 1. Filter out impassable neighbors
            // TODO: Obtain all neighbors!
            List<Point> neighborList = neighborStream
                    .filter(canPassThrough) //traversable
                    .filter(p -> !(closedset.contains(p))) //not in the Closed Set
                    .toList();


            //For each traversable Neighbor of Current that is not in the Closed Set:
            for (Point neighbor : neighborList) {

                //if the Neighbor is not already in the Open Set, add it to the Open Set.
                if (!(openset.contains(neighbor))) {
                    openset.add(0, neighbor);
                }

                //Calculate the G- and F-Value of the neighbor (E.g., g(Neighbor) = g(Current) + 1)
                int tempgscore = gscores.get(current) + 1;
                hscore = Math.abs(end.x - neighbor.x) + Math.abs(end.y - neighbor.y);
                int tempfscore = hscore + tempgscore;


                /* If this G-Value is better than a previously calculated one for this Neighbor (or this
                is the first time it's being calculated), record the Neighbor's G-Value, F-Value, and
                update its Previous Mapping (i.e., Neighbor (key)/Current (Value))
                */
                if (!gscores.containsKey(neighbor)|| tempgscore < gscores.get(neighbor)) {
                    gscores.put(neighbor, tempgscore);
                    fscores.put(neighbor, tempfscore);
                    previousmap.put(neighbor, current);
                }
            }
        }
        //No path was found: return an empty list, `Null`, the Start node, etc.
        return null;
    }


}