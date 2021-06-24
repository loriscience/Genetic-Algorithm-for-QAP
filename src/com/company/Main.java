package com.company;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {





        Reader reader = new Reader();
        List<Model> models ;
        models = reader.getModel();


        System.out.println("FLOW MATRIX");
        System.out.println("----------");
        for(int i = 0 ; i< models.get(0).getSize(); i++) {
            for(int j = 0 ; j< models.get(0).getSize() ; j++) {

                System.out.print(models.get(0).getFlowMatrix()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();


        System.out.println("DISTANCE MATRIX");
        System.out.println("----------");
        for(int i = 0 ; i< models.get(0).getSize(); i++) {
            for(int j = 0 ; j< models.get(0).getSize() ; j++) {
                System.out.print(models.get(0).getDistanceMatrix()[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("***********");




        long startTime = System.currentTimeMillis();

        SingleResult result = GreedyAlgorithm.run(models.get(0));
        ArrayList<Individual> supers = new ArrayList<>();



        for (int i = 0 ; i<1 ; i++) {
            Heuristic h = new Heuristic();
            h.Generic_Heuristic(result.getGenotype());
            supers.add(h.super_ind);
        }





        long endTime = System.currentTimeMillis();
        long TimeElapsed = endTime - startTime;
        System.out.println("Execution time in seconds " + TimeElapsed/1000);

        for (int i = 0 ; i<supers.size() ; i++) {
            System.out.println(supers.get(i).getGenotype() + " " + supers.get(i).fitness);
        }







//        System.out.println(models.get(0).getSize());
//        int sum = 0;
//        for (int i = 0; i < 12; i++) {
//            for (int j = 0; j < 12; j++) {
//                sum = sum + models.get(0).getFlowMatrix()[test_gen.get(i)][test_gen.get(j)] * models.get(0).getDistanceMatrix()[i][j];
//            }
//        }
//
//        System.out.println(sum / 2);

    }
}
