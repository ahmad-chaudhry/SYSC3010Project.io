package com.example.jaska.buspassengertraffic;

public class Hero {
    private String capacityzone1;
    private String capacityzone2;
    private String capacityzone3;
    private String capacityzone4;
    private String capacityzone5;

    public Hero(String capacityzone1, String capacityzone2, String capacityzone3, String capacityzone4, String capacityzone5) {
        this.capacityzone1 = capacityzone1;
        this.capacityzone2 = capacityzone2;
        this.capacityzone3 = capacityzone3;
        this.capacityzone4 = capacityzone4;
        this.capacityzone5 = capacityzone5;
    }

    public String getCapacityzone1() {
        return capacityzone1;
    }

    public String getCapacityzone2() {
        return capacityzone2;
    }

    public String getCapacityzone3() {
        return capacityzone3;
    }

    public String getCapacityzone4() {
        return capacityzone4;
    }

    public String getCapacityzone5() {
        return capacityzone5;
    }
}
