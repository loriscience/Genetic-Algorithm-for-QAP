package com.company;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Population {
    public ArrayList<Individual> population;


    public Population(){
        population = new ArrayList<>();
    }


    public void randomPopulate(Model model, int popSize) {
        for (int i = 0; i < popSize; i++) {
            Individual individual = new Individual(model);
            individual.setGenotype(getRandomsoln(model));
            population.add(individual);

        }
    }


    public ArrayList<Integer> getRandomsoln(Model model) {
        int n = model.getSize();
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0 ; i< model.size ; i++) {
            result.add(0);
        }
        List<Integer> factories = new ArrayList<>();
        for(int i = 0 ; i<model.size ; i++) {
            factories.add(i);
        }
        Collections.shuffle(factories);
        for(int i = 0 ; i<n ; i++) {
            result.set(i, factories.get(i));
        }
        return result;
    }


    public void show_individuals() {
        for(int i = 0 ; i< population.size() ; i++) {
            System.out.println(population.get(i).getGenotype() + " =  " + population.get(i).fitness);
        }
    }


    public double calculate_average_fitness(){
        double result = 0;

        for(int i = 0 ; i<population.size(); i++){
            result = result + population.get(i).fitness;
        }

        return result / population.size();
    }

    public Individual GetRandom_individual(int max) {
        int min = 0 ;


        int random_num = ThreadLocalRandom.current().nextInt(min, max);
        return population.get(random_num);

    }

    public ArrayList<Individual> make_selection(Population pop1) {

        double average = pop1.calculate_average_fitness();

        for (int i = 0 ; i < population.size() ; i++) {

            if (population.get(i).fitness > average) {
                population.remove(i);
            }
        }
        return population;
    }



    public Individual find_superior_genetic() {
        int max = Integer.MAX_VALUE ;
        Individual superior = null;


        for (int i = 0 ; i<population.size() ; i++) {
            if (population.get(i).fitness < max) {
                max = population.get(i).fitness;
                superior = population.get(i);
            }
        }
        return superior;
    }


    public Individual get_individual(int i) {
        return population.get(i);
    }

    public int tell_size_population() {
        return population.size();
    }


    public ArrayList<Individual> getPopulation(){
        return population;
    }

    public ArrayList<Individual> find_all_Superior_genetics() {
        ArrayList<Individual> arr = new ArrayList<>();
        int max = Integer.MAX_VALUE ;


        for (int i = 0 ; i<population.size() ; i++) {
            if (population.get(i).fitness < max) {
                max = population.get(i).fitness;
            }
        }

        for (int i = 0 ; i<population.size(); i++) {
            if (population.get(i).fitness == max) {
                arr.add(population.get(i));
            }
        }
        return arr;
    }


    public Population  clean_identicals(Population pop2) {


//        for (int i = 0 ; i< population.size() -1  ; i++) {
//            for (int j = i+1 ; j< population.size(); j++) {
//                    if (population.get(i).genotype == population.get(j).genotype) {
//                        population.remove(j);
//                        if (i!=0)
//                            i--;
//                    }
//            }
//        }
//
//        for (int i = 0 ; i< population.size();i++) {
//            if (!pop2.getPopulation().contains(population.get(i).getGenotype())){
//                pop2.getPopulation().add(population.get(i));
//            }
//        }

        for (int i = 0 ; i<pop2.getPopulation().size()-1 ; i++){
            if (pop2.getPopulation().get(i).genotype == (pop2.getPopulation().get(i+1).genotype)) {
                pop2.getPopulation().remove(i + 1);
                i--;
            }
        }

        return pop2;
    }

    public double getAverageFitness() {
        double fitness = 0;
        for (Individual individual : population) {
            fitness += individual.getFitness();
        }
        fitness = fitness / population.size();
        return fitness;
    }

    public Individual getWorst() {
        Individual worst = population.get(0);
        for (Individual individual : population) {
            if (worst.getFitness() < individual.getFitness()) {
                worst = individual;
            }
        }
        return worst;
    }

    public Individual getFittest() {
        Individual fittest = population.get(0);
        for (Individual individual : population) {
            if (fittest.getFitness() > individual.getFitness()) {
                fittest = individual;
            }
        }
        return fittest;
    }


}


