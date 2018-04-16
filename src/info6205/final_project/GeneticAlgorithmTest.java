package info6205.final_project;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;


public class GeneticAlgorithmTest {

    @Test
    public void testGenes() {
        final Solution solution = new Solution();
        HashSet<String> genes = new HashSet<>();
        for (String s : solution.genotype) {
            genes.add(s);
        }
        assertEquals(Configuration.NUMBER_OF_GENES, genes.size());
    }

    @Test
    public void testSexualReproduction() {
        final Solution parent1 = new Solution();
        final Solution parent2 = new Solution();
        final Solution child = new Solution(parent1, parent2);
        HashSet<String> genes = new HashSet<>();
        for (String i : child.genotype) {
            genes.add(i);
        }
        assertEquals(Configuration.NUMBER_OF_GENES, genes.size());
    }

    @Test
    public void testAsexualReproduction() {
        final Solution parent = new Solution();
        final Solution child = new Solution(parent);
        HashSet<String> genes = new HashSet<>();
        for (String i : child.genotype) {
            genes.add(i);
        }
        assertEquals(Configuration.NUMBER_OF_GENES, genes.size());
    }

    @Test
    public void testSort() {
        final GeneticAlgorithm GA = new GeneticAlgorithm();
        GA.sort();
        Solution solution1 = GA.solutions.get(0);
        GA.evolve();
        Solution solution2 = GA.solutions.get(0);
        assert (solution2.distance <= solution1.distance);
    }

    @Test
    public void testSelect() {
        final GeneticAlgorithm GA = new GeneticAlgorithm();
        GA.evolve();
        int originalSize = GA.solutions.size();
        GA.select();
        int newSize = GA.solutions.size();
        assert (originalSize > newSize);
        assert (GA.solutions.get(0).distance <= GA.solutions.get(GA.solutions.size() - 1).distance);
    }

    @Test
    public void testBestSolution() {
        final GeneticAlgorithm GA = new GeneticAlgorithm();
        GA.evolve();
        Solution solution = GA.bestSolution();
        for (Solution p : GA.solutions) {
            assert (p.distance >= solution.distance);
        }
    }

    @Test
    public void testMutate() {
        final Solution solution = new Solution();
        for (int j = 0; j < Configuration.MAX_MUTATION; j++) {
            solution.mutate();
            HashSet<Integer> set = new HashSet<>();
            for (String str : solution.genotype) {
                set.add(solution.expressSingleGene(str));
            }
            assertEquals(Configuration.NUMBER_OF_GENES, set.size());
        }
    }

    @Test
    public void testValidityOfSexualReproduction() {
        final Solution parent1 = new Solution();
        final Solution parent2 = new Solution();
        final Solution child = new Solution(parent1, parent2);
        assert (!child.genotype.toString().equals(parent1.genotype.toString()));
        assert (!child.genotype.toString().equals(parent2.genotype.toString()));
    }

    @Test
    public void testEvolve() {
        final GeneticAlgorithm GA = new GeneticAlgorithm();
        GA.evolve();
        assert (GA.solutions.get(0).distance <= GA.solutions.get(GA.solutions.size() - 1).distance);
    }

}