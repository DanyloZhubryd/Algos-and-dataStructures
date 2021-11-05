package ua.lviv.iot;

public class Hamster {
    public Integer daily;
    public Integer greed;
    public Integer consumption;

    public Hamster(Integer daily, Integer greed) {
        resetHamster(daily, greed, 0);
    }

    public Hamster(Hamster hamster){
        copyHamster(hamster);
    }

    public void copyHamster(Hamster hamster){
        resetHamster(hamster.daily, hamster.greed, hamster.consumption);
    }

    private void resetHamster(Integer daily, Integer greed, Integer consumption){
        this.daily = daily;
        this.greed = greed;
        this.consumption = consumption;
    }

    public Integer countConsumption(int n) {
        this.consumption = this.daily + (n-1) * this.greed;
        return this.consumption;
    }
}
