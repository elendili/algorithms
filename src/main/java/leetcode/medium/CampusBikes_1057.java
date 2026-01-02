package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;

import static helpers.TestHelper.extract1dArrayFromBracketedString;
import static helpers.TestHelper.extract2dArrayFromBracketedString;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/campus-bikes/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class CampusBikes_1057 {
    // Class to store (worker, bike, distance)
    class WorkerBikePair {
        int workerIndex;
        int bikeIndex;
        int distance;

        // Constructor to initialize the member variables
        WorkerBikePair(int workerIndex, int bikeIndex, int distance) {
            this.workerIndex = workerIndex;
            this.bikeIndex = bikeIndex;
            this.distance = distance;
        }
    }

    // Custom comparator for sorting
    Comparator<WorkerBikePair> WorkerBikePairComparator
            = new Comparator<WorkerBikePair>() {
        @Override
        public int compare(WorkerBikePair a, WorkerBikePair b) {
            if (a.distance != b.distance) {
                // Prioritize the one having smaller distance
                return a.distance - b.distance;
            } else if (a.workerIndex != b.workerIndex) {
                // Prioritize according to the worker index
                return a.workerIndex - b.workerIndex;
            } else {
                // Prioritize according to the bike index
                return a.bikeIndex - b.bikeIndex;
            }
        }
    };

    // Function to return the Manhattan distance
    int findDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        // List of WorkerBikePair's to store all the possible pairs
        List<WorkerBikePair> allTriplets = new ArrayList<>();

        // Generate all the possible pairs
        for (int worker = 0; worker < workers.length; worker++) {
            for (int bike = 0; bike < bikes.length; bike++) {
                int distance = findDistance(workers[worker], bikes[bike]);
                WorkerBikePair workerBikePair
                        = new WorkerBikePair(worker, bike, distance);
                allTriplets.add(workerBikePair);
            }
        }

        // Sort the triplets as per the custom comparator 'WorkerBikePairComparator'
        Collections.sort(allTriplets, WorkerBikePairComparator);

        // Initialize all values to false, to signify no bikes have been taken
        boolean bikeStatus[] = new boolean[bikes.length];
        // Initialize all index to -1, to mark all the workers available
        int workerStatus[] = new int[workers.length];
        Arrays.fill(workerStatus, -1);
        // Keep track of how many worker-bike pairs have been made
        int pairCount = 0;

        for (WorkerBikePair triplet : allTriplets) {
            int worker = triplet.workerIndex;
            int bike = triplet.bikeIndex;

            // If both worker and bike are free, assign them to each other
            if (workerStatus[worker] == -1 && !bikeStatus[bike]) {
                bikeStatus[bike] = true;
                workerStatus[worker] = bike;
                pairCount++;

                // If all the workers have the bike assigned, we can stop
                if (pairCount == workers.length) {
                    return workerStatus;
                }
            }
        }

        return workerStatus;
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    expected | workers             | bikes
                    [1,0]    | [[0,0],[2,1]]       | [[1,2],[3,3]]
                    [0,2,1]  | [[0,0],[1,1],[2,0]] | [[1,0],[2,2],[2,1]]
                    """
    )
    public void test(String expected, String workers, String bikes) {
        assertEquals(
                Arrays.toString(extract1dArrayFromBracketedString(expected)),
                Arrays.toString(assignBikes(
                        extract2dArrayFromBracketedString(workers),
                        extract2dArrayFromBracketedString(bikes)
                )));
    }

}
