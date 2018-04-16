package info6205.final_project;

import org.apache.log4j.Logger;

import java.util.ArrayList;

public class Configuration {
    // configurationfor GeneticAlgorithm

    public static final int INITIAL_SOLUTIONS = 200;
    public static final double MAX_GENERATION = 2000;
    public static final double CULLING_PERCENTAGE = 0.6;
    public static final double CULLING_THRESHOLD = 4;

    // coordinates of the 48 cities

    // in the order that commonly used for the continental capitals of U.S. in TSP
    public static final double[] Y = {
            1453, 10, 1424, 841, 1644, 4458, 3716, 1268, 1885, 2049, 2606, 2873, 2674, 2035, 2683, 669,
            5184, 3590, 4723, 3561, 3369, 1110, 2182, 2809, 2322, 1006, 4819, 3981, 756, 4506, 2801, 3305,
            3173, 1198, 2216, 3779, 4595, 2244, 2829, 2135, 140, 1569, 4899, 3239, 2676, 2993, 3258, 1942,
    };
    // coordinates for X axis

    // coordinates for Y axis
    public static final double[] X = {
            6734, 2233, 5530, 401, 3082, 7608, 7573, 7265, 6898, 1112, 5468, 5989, 4706, 4612, 6347, 6107,
            7611, 7462, 7732, 5900, 4483, 6101, 5199, 1633, 4307, 675, 7555, 7541, 3177, 7352, 7545, 3245,
            6426, 4608, 23, 7248, 7762, 7392, 3484, 6271, 4985, 1916, 7280, 7509, 10, 6807, 5185, 3023,
    };


    // configuration for solution

    public static final int NUMBER_OF_GENES = 48;
    public static final double RATE_TO_MUTATE = 0.6;
    public static final int MAX_MUTATION = 14;
    public static final String[] GENES;


    // configuration for logging

    public final static Logger logger = Logger.getLogger(GeneticAlgorithm.class);

    // set all genes
    static {
        GENES = new String[NUMBER_OF_GENES];
        for (int i = 0; i < NUMBER_OF_GENES; i++) {
            GENES[i] = Integer.toString(i, 2);
        }
    }

    public static void logging(int generation, GeneticAlgorithm GA) {
        StringBuffer path = new StringBuffer();

        if (Configuration.logger.isInfoEnabled()) {
            // indicate each new generation
            Configuration.logger.info("----------------");
            Configuration.logger.info("Generation: " + generation);
            Configuration.logger.info("Candidate solution: " + GA.solutions.size());

            // get the order to visit the cities
            ArrayList<Integer> cities = GA.bestSolution().express();
            for (Integer i : cities) {
                path.append(i + " -> ");
            }
            Configuration.logger.info("Shortest distance: " + GA.solutions.get(0).distance);
            Configuration.logger.info("Path: " + path + "\n");
        }
    }
}
