package com.company;

import com.sun.source.doctree.UnknownBlockTagTree;
import jdk.swing.interop.SwingInterOpUtils;
import javax.management.ObjectName;
import java.io.IOException;
import java.io.PipedInputStream;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Heuristic {

    public static int size_model;
    public Population superiors_of_populations;
    public Individual super_ind;




    public Heuristic(){

    }

    public void Generic_Heuristic(ArrayList<Integer> greedy_temp) throws IOException {


        superiors_of_populations = new Population();
        // This list gives the best of the generations


        // PARAMETERS

        int Population_Size = 5000;
        int Maximum_Generation = 2000;
        double CrossOverRate = 0.50;
        double mutation_rate = 0.75;
        int generation_Count = 0;

        Model model_1;

        Reader r = new Reader();
        List<Model> models ;
        models = r.getModel();
        model_1 = models.get(0);
        size_model = model_1.getSize();


        System.out.println();
        Population pop = new Population();
        pop.randomPopulate(model_1  , Population_Size);

        while (generation_Count < Maximum_Generation) {


            System.out.println("New Generation");
            System.out.println("------------------------");
            pop.show_individuals();
            System.out.println( "AVERAGE FITNESS CALCULATED : " + pop.calculate_average_fitness());

            Individual bestindividual = pop.find_superior_genetic();
            superiors_of_populations.getPopulation().add(bestindividual);

            if (Math.random() < 0.05) {
                for (int i = 0 ; i< Population_Size/3 ; i++){
                    int rnd = ThreadLocalRandom.current().nextInt(0, pop.tell_size_population());
                    pop.getPopulation().remove(rnd);
                }
            }

            pop.make_selection(pop);

            Individual greedy_ind = new Individual(model_1);
            greedy_ind.setGenotype(greedy_temp);
            pop.getPopulation().add(greedy_ind);



            if (Math.random() < 0.70) {
                int best_soln = pop.find_superior_genetic().fitness;
                ArrayList<Integer> test_genotype;
                test_genotype = pop.find_superior_genetic().getGenotype();
                Individual test = new Individual(model_1);
                for (int i = 0 ; i<size_model -1 ; i++ ) {
                    for (int j = 0 ; j<size_model ; j++) {
                        Collections.swap(test_genotype, i , j);
                        test.setGenotype(test_genotype);
                        if (test.fitness < best_soln) {
                            pop.getPopulation().add(test);
                        }
                        else {
                            Collections.swap(test_genotype,i,j);
                        }
                    }
                }
            }


            if (Math.random() < 0.70) {
                int best_soln = pop.find_superior_genetic().fitness;
                ArrayList<Integer> test_genotype;
                test_genotype = pop.GetRandom_individual(pop.tell_size_population()).getGenotype();
                Individual test = new Individual(model_1);
                for (int i = 0 ; i<size_model -1 ; i++ ) {
                    for (int j = 0 ; j<size_model ; j++) {
                        Collections.swap(test_genotype, i , j);
                        test.setGenotype(test_genotype);
                        if (test.fitness < best_soln) {
                            pop.getPopulation().add(test);
                        }
                        else {
                            Collections.swap(test_genotype,i,j);
                        }
                    }
                }
            }



            while(pop.tell_size_population() < Population_Size) {
                Individual individual1 = pop.GetRandom_individual(pop.tell_size_population());
                Individual individual2 = pop.GetRandom_individual(pop.tell_size_population());
                Individual offspring_ind = new Individual(model_1);

                ArrayList<Integer> offspring = new ArrayList<>();



                double pivott = CrossOverRate * size_model;
                int pivot = (int)pivott;

                if (Math.random() < 0.8) {
                    for(int i = 0 ; i<size_model ; i++ ) {
                        offspring.add(-1);
                    }
                    for (int i = 0 ; i<pivot ; i++) {
                        offspring.set(i, individual1.getGenotype().get(i));
                    }
                    for(int j = pivot ; j<size_model; j++) {
                        offspring.set(j, individual2.getGenotype().get(j));
                    }
                    fix_array(offspring);
                    offspring_ind.setGenotype(offspring);
                }

                else {
                    for(int i = 0 ; i<size_model ; i++ ) {
                        offspring.add(-1);
                    }
                    for (int i = 0 ; i<pivot ; i++) {
                        offspring.set(i, individual2.getGenotype().get(i));
                    }
                    for(int j = pivot ; j<size_model; j++) {
                        offspring.set(j, individual1.getGenotype().get(j));
                    }

                    fix_array(offspring);
                    offspring_ind.setGenotype(offspring);
                }

                if (Math.random()<mutation_rate) {

                    if (Math.random() < 0.5 ) {
                        mutation_1(offspring_ind);
                        offspring_ind.setGenotype(offspring_ind.genotype);

                    }
                    else {
                        mutation_2(offspring_ind);
                        offspring_ind.setGenotype(offspring_ind.genotype);
                    }
                }
                pop.getPopulation().add(offspring_ind);
            }

            generation_Count++;
        }


        System.out.println("SUPERIOR INDIVIDUAL : ");
        superiors_of_populations.clean_identicals(superiors_of_populations);
        super_ind = superiors_of_populations.find_superior_genetic();

        System.out.println(super_ind.genotype + "  " + super_ind.fitness);


//        superiors_of_populations.show_individuals();
    }



    public void fix_array(ArrayList<Integer> offspring) {
        int length = size_model;
        List<Integer> never_used = new ArrayList<>();
        List<Integer> bad_index = new ArrayList<>();

        for (int i = 0 ; i< length ; i++) {
            never_used.add(i);
        }


        for (int i = 0 ; i<length ; i++) {
            if (never_used.contains(offspring.get(i))){
                int a= never_used.indexOf(offspring.get(i));
                never_used.remove(a);
            }
            else {
                bad_index.add(i);
            }
        }

        for (int j = 0 ; j<bad_index.size() ; j++) {
            int b = bad_index.get(j);
            offspring.set(b,never_used.get(j));
        }
    }


    public void mutation_1(Individual individual) {
        List<Integer> not_mutated_list = individual.getGenotype();
        Collections.shuffle(not_mutated_list);
        individual.swapSolutionValues(not_mutated_list.get(0),not_mutated_list.get(1));
    }

    public void mutation_2(Individual individual) {
        List<Integer> mutated_list = individual.getGenotype();
        double x = Math.random()*10;
        int xy = (int)x;

        if (xy>=0 && 3>=xy) {
            individual.swapSolutionValues(0,size_model-1);
        }
        else  if(xy>6) {
            individual.swapSolutionValues(size_model/2-1,size_model/2+1);
        }
        else {
            individual.swapSolutionValues(size_model/3, 2*size_model/3);
        }
    }

}
