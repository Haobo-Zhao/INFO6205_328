package info6205.final_project;

import java.util.*;

public class GeneticAlgorithm {


    // calculate the distance between two cities
    public static double distanceBetweenTwoCities(List<Double> city1, List<Double> city2) {
        return Math.sqrt((Math.pow(city1.get(0) - city2.get(0), 2) + Math.pow(city1.get(1) - city2.get(1), 2)) / 10);
    }

    public List<Solution> solutions;
    public ArrayList<ArrayList<Double>> cities;

    // create the 'seeds', a set of random candidates, for a run of the genetic algorithm
    public GeneticAlgorithm() {
        // initialise locations for all cities
        cities = new ArrayList<>();
        for (int i = 0; i < Configuration.NUMBER_OF_GENES; i++) {
            ArrayList<Double> city = new ArrayList<>();
            city.add(Configuration.X[i]);
            city.add(Configuration.Y[i]);
            cities.add(city);
        }
        // 'seeds' solutions, all randomly generated
        solutions = new ArrayList<>();
        for (int i = 0; i < Configuration.INITIAL_SOLUTIONS; i++) {
            solutions.add(new Solution());
        }
    }

    // fitness method, the smaller the value is, the better the solution evaluated is
    public double fitness(Solution solution) {
        // calculate the unknown distance
        if (solution.distance == 0) {
            double distance = 0;
            int cur = solution.expressSingleGene(solution.genotype.get(0));
            int pre = 0;
            // calculate the total distance of current solution
            for (int i = 1; i < Configuration.NUMBER_OF_GENES; i++) {
                pre = cur;
                cur = solution.expressSingleGene(solution.genotype.get(i));
                distance += Math.rint(distanceBetweenTwoCities(cities.get(cur), cities.get(pre)));
            }
            solution.distance = distance;
        }
        return solution.distance;
    }

    // put the solutions in order, solutions with shorter distance, which means better fitness value, coming before ones with longer distance
    public void sort() {
        for (int i = 0; i < solutions.size(); i++) {
            // calculate each distance of solutions for the following comparison and store in the solution
            fitness(solutions.get(i));
        }
        Collections.sort(solutions);
    }

    // cull the population
    public void select() {
        sort();
        int endPoint = (int) (solutions.size() * Configuration.CULLING_PERCENTAGE);
        if (endPoint > Configuration.CULLING_THRESHOLD * Configuration.INITIAL_SOLUTIONS)
            endPoint /= 2;
        //culling the population
        solutions = solutions.subList(0, endPoint);
    }

    // generate new solutions and cull the population
    public void evolve() {
        Random rand = new Random();
        int size = solutions.size();
        for (int i = 0; i < size; i++) {
            int j = rand.nextInt(size);
            solutions.add(new Solution(solutions.get(i), solutions.get(j)));
        }
        sort();
        select();
    }

    // get the best solution, which has the shortest path currently found
    public Solution bestSolution() {
        return solutions.get(0);
    }

    // get the best solution, which has the longest path currently found
    public Solution worstSolution() {
        return solutions.get(solutions.size() - 1);
    }

}
