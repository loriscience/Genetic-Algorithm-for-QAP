package com.company;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

public class Individual {

    public ArrayList<Integer> genotype;
    public Model model;
    public int fitness;
    public int score;


    public Individual() {

    }

    public Individual(Model model) {
        this.model= model;
        fitness = -1;
    }
    public Individual(Individual individual) {
        this.model= individual.model;
        this.genotype = individual.genotype;
    }



    public int calculate_cost(int firstFactory, int secondFactory, int firstLocation, int secondLocation) {
        return (model.getFlowMatrix()[firstFactory][secondFactory] * model.getDistanceMatrix()[firstLocation][secondLocation]) ;
    }


    public void swapSolutionValues(int i , int j) {
        int firstLocation = genotype.get(j);
        int secondLocation = genotype.get(i);
        genotype.set(i,firstLocation);
        genotype.set(j,secondLocation);
    }

    public ArrayList<Integer> getGenotype() {
        return genotype;
    }


    public void setGenotype(ArrayList<Integer> genotype) {
        this.genotype = genotype;
        setFitness();

    }

    public void setFitness() {
        if (genotype.size() != model.getSize()) {
            System.out.println("ERROR  ; Check your instances please");
        }
        int sum = 0;
        for (int i = 0; i < model.getSize(); i++) {
            for (int j = 0; j < model.getSize(); j++) {
                sum += calculate_cost(i, j, genotype.get(i), genotype.get(j));
            }
        }
        fitness = sum;
    }

    public Individual fix_offspring(ArrayList<Integer> child) {
        Individual individual = new Individual();
        ArrayList<Integer> used_integers = new ArrayList<>();
        ArrayList<Integer> never_used = new ArrayList<>();
        ArrayList<Integer> bad_index = new ArrayList<>();


        for (int i = 0 ; i< model.getSize();i++) {
            never_used.add(i);
        }

        for (int i = 0 ; i< model.getSize() ; i++) {
            if (used_integers.contains(child.get(i))) {
                bad_index.add(i);
                continue;
            }
            used_integers.add(child.get(i));
            never_used.remove(i);
        }
        for (int i = 0 ; i<bad_index.size() ; i++) {
            child.set(bad_index.get(i), never_used.get(i));
        }


        individual.setGenotype(child);
        return individual;
    }


    public int getFitness() {
        if (fitness < 0) {
            setFitness();
        }
        return fitness;
    }

    public Model getModel() {
        return model;
    }

}



