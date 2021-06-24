package com.company;

public class Model {

    public int size;
    public int[][] flowMatrix;
    public int[][] distanceMatrix;


    public int getSize() {
        return  size;
    }

    public Model setSize(int size) {
        this.size = size;
        return this;
    }

    public int[][] getFlowMatrix(){
        return flowMatrix;
    }

    public Model setFlowMatrix(int[][] flowMatrix) {
        this.flowMatrix = flowMatrix;
        return this;
    }


    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }


    public Model setDistanceMatrix(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
        return this;
    }

}
