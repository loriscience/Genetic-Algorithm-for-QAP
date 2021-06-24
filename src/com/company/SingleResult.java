package com.company;


import java.util.ArrayList;

public class SingleResult {
    private int generation;
    private int bestFitness;
    private double averageFitness;
    private int worstFitness;
    private ArrayList<Integer> genotype;

    public SingleResult(int generation, int bestFitness, double averageFitness, int worstFitness,ArrayList<Integer> genotype) {
        this.generation = generation;
        this.bestFitness = bestFitness;
        this.averageFitness = averageFitness;
        this.worstFitness = worstFitness;
        this.genotype = genotype;
    }

    public SingleResult(Population population, int generation) {
        this.generation = generation;
        this.bestFitness = population.getFittest().getFitness();
        this.averageFitness = population.getAverageFitness();
        this.worstFitness = population.getWorst().getFitness();
    }

    public int getGeneration() {
        return generation;
    }

    public int getBestFitness() {
        return bestFitness;
    }

    public double getAverageFitness() {
        return averageFitness;
    }

    public int getWorstFitness() {
        return worstFitness;
    }

    public ArrayList<Integer> getGenotype() {return genotype; }
}
