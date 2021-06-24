package com.company;

import java.io.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

    public String Path_4__size = "/home/lori/IdeaProjects/IE414M/test-data.txt";
    public String Path_12__size = "/home/lori/IdeaProjects/IE414M/chr12a.dat.txt";  // OPTIMAL 9552
    public String Path_18_size = "/home/lori/IdeaProjects/IE414M/chr18a.dat.txt";   // OPTIMAL 11098
    public String Path_20_size = "/home/lori/IdeaProjects/IE414M/lipa20a.dat.txt";  // OPTIMAL 3683
    public String Path_25_size = "/home/lori/IdeaProjects/IE414M/chr25a.dat.txt";  // OPTIMAL 3796
    public String Path_30_size = "/home/lori/IdeaProjects/IE414M/lipa30b.dat.txt";  // 151426
    public String Path_40_size = "/home/lori/IdeaProjects/IE414M/lipa40a.dat.txt";  //31538
    public String Path_50_size = "/home/lori/IdeaProjects/IE414M/lipa50a.dat.txt";   // 62093
    public String Path_60_size = "/home/lori/IdeaProjects/IE414M/lipa60a.dat.txt";   //  107218
    public String Path_70_size = "/home/lori/IdeaProjects/IE414M/lipa70a.dat.txt";   //  169755
    public String Path_90_size = "/home/lori/IdeaProjects/IE414M/lipa90a.dat.txt";    //  360630
    public String Path_100_size = "/home/lori/IdeaProjects/IE414M/sko100a.dat.txt";   //  152002 NOT OPT 6.14% GAP

    public Reader(){

    }


    public List<Model> getModel() throws IOException {
        List<Model> model_1 = new ArrayList<>();
        int selected_path = 5;
        int number_locations;
        String path = null;

        if(selected_path == 1) {
            path = Path_4__size;
        }
        else if (selected_path == 2) {
            path = Path_12__size;
        }
        else if (selected_path ==3) {
            path = Path_18_size;
        }
        else if (selected_path == 4){
            path = Path_20_size;
        }
        else if (selected_path == 5){
            path = Path_25_size;
        }
        else if (selected_path == 6){
            path = Path_30_size;
        }
        else if (selected_path == 7){
            path = Path_40_size;
        }
        else if (selected_path == 8){
            path = Path_50_size;
        }
        else if (selected_path == 9){
            path = Path_60_size;
        }
        else if (selected_path == 10){
            path = Path_70_size;
        }
        else if (selected_path == 11){
            path = Path_90_size;
        }
        else if (selected_path == 12){
            path = Path_100_size;
        }






        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Scanner scanner = new Scanner(bufferedReader);

        while (scanner.hasNextInt()) {
            number_locations = scanner.nextInt();
            Model model = new Model();
            model.setSize(number_locations);

            int[][] flowMatrix = new int[number_locations][number_locations];
            int[][] distanceMatrix = new int[number_locations][number_locations];

            for (int i = 0; i < number_locations; i++) {
                for (int j = 0; j < number_locations; j++) {
                    int n = scanner.nextInt();
                    flowMatrix[i][j] = n;
                }
            }


            for (int i = 0; i < number_locations; i++) {
                for (int j = 0; j < number_locations; j++) {
                    distanceMatrix[i][j] = scanner.nextInt();
                }
            }

            model.setFlowMatrix(flowMatrix).setDistanceMatrix(distanceMatrix);
            model_1.add(model);
        }
        scanner.close();
        bufferedReader.close();

        return model_1;
        }


    }

