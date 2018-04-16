package info6205.final_project;

import java.util.ArrayList;
import java.util.Random;

// implement the Comparable interface for comparisons
public class Solution implements Comparable {

    // instance variables

    // genotype for a specific solution
    public ArrayList<String> genotype;

    // the total distance of a specific solution
    public double distance = 0;

    // generate a set of random solutions(visiting order)
    public Solution() {
        Random r = new Random();
        int k = 0;
        genotype = new ArrayList<>(Configuration.NUMBER_OF_GENES);
        for (int i = 0; i < Configuration.NUMBER_OF_GENES; i++) {
            do {
                k = r.nextInt(Configuration.NUMBER_OF_GENES);
            } while (genotype.contains(Configuration.GENES[k]));
            genotype.add(Configuration.GENES[k]);
        }
    }

    // asexual reproduction
    public Solution(Solution origin) {
        this.genotype = new ArrayList<>(origin.genotype);
        mutate();
    }

    // sexual reproduction
    public Solution(Solution parent1, Solution parent2) {
        crossover(parent1, parent2, this);
        mutate();
    }

    // mimics the crossover observed in the natural world
    public void crossover(Solution parent1, Solution parent2, Solution child) {
        child.genotype = new ArrayList<>();
        for (int i = 0; i < Configuration.NUMBER_OF_GENES; i++) {
            // for the even indecies, use cities from parent1
            if (i % 2 == 0) {
                child.genotype.add(parent1.genotype.get(i));
            }
            // for the odd indecies, take them from parent2
            else {
                child.genotype.add(" ");
            }
        }
        // index in parent2
        int i = 0;
        // index in parent1 for the non-assigned genes
        int j = 1;
        while (child.genotype.contains(" ")) {
            if (child.genotype.contains(parent2.genotype.get(i)) == false) {
                child.genotype.set(j, parent2.genotype.get(i));
                j += 2;
                i++;
            }
            else {
                i++;
            }
            // circle i within parent2
            i %= Configuration.NUMBER_OF_GENES;
        }

        mutate();
    }

    // mimics the mutate observed in the natural world
    public void mutate() {
        Random r = new Random();
        int maxMutation = r.nextInt(Configuration.MAX_MUTATION);
        for (int t = 0; t < maxMutation; t++) {
            double odds = Math.random();
            if (odds < Configuration.RATE_TO_MUTATE) {
                int i, j;
                // make sure to swap different cities
                do {
                    i = r.nextInt(Configuration.NUMBER_OF_GENES);
                    j = r.nextInt(Configuration.NUMBER_OF_GENES);
                } while (i == j);
                String tempCity = this.genotype.get(j);
                this.genotype.set(j, this.genotype.get(i));
                this.genotype.set(i, tempCity);
            }
        }
    }

    // expressing genotype to phenotype
    public ArrayList<Integer> express() {
        ArrayList<Integer> phenotype = new ArrayList<>();
        for (String s : genotype) {
            phenotype.add(Integer.parseInt(s, 2));
        }
        return phenotype;
    }

    // translate a single gene to an index of a city
    public int expressSingleGene(String gene) {
        return Integer.parseInt(gene, 2);
    }

    // compareing solutions based on the distance
    public int compareTo(Object o) {
        if (this.distance > ((Solution) o).distance) {
            return 1;
        } else if (this.distance < ((Solution) o).distance) {
            return -1;
        }
        return 0;
    }
}
