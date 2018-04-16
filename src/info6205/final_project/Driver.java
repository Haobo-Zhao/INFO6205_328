package info6205.final_project;

public class Driver {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        GeneticAlgorithm GA = new GeneticAlgorithm();
        runGA(GA);
        System.out.println("Finish running the program using " + (System.currentTimeMillis() - start) + " ms.");
        Configuration.logger.info("\nProgram finished.");
    }

    public static void runGA(GeneticAlgorithm GA) {
        for (int i = 0; i < Configuration.MAX_GENERATION; i++) {
            Configuration.logging(i + 1, GA);
            GA.evolve();
        }
    }
}
