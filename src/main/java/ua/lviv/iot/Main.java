package ua.lviv.iot;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer foodPackages;
        Integer hamstersNumber;
        ArrayList<Hamster> hamsters;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("src/main/resources/input.txt"));
            foodPackages = Integer.parseInt(reader.readLine());
            hamstersNumber = Integer.parseInt(reader.readLine());
            hamsters = new ArrayList<Hamster>();
            String line = reader.readLine();
            int i = 0;
            while (line != null) {
                String[] dailyAndGreed = line.split(" ");
                hamsters.add(new Hamster(Integer.parseInt(dailyAndGreed[0]),
                        Integer.parseInt(dailyAndGreed[1])));
                line = reader.readLine();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/output.txt"));
            writer.write("You can buy " + buyHamsters(hamsters, foodPackages) + " hamsters");

            writer.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static int buyHamsters(ArrayList<Hamster> hamsters, Integer foodPackages) {
        if (hamsters.isEmpty()) {
            return 0;
        }
        Integer totalConsumption;
        Integer begin = 0;
        Integer current = 0;
        Integer end = hamsters.size();
        Integer canBuy = 0;

        while (begin <= end) {
            current = (int)Math.ceil((begin + end) / 2.0);
            for (Hamster i : hamsters) {
                i.countConsumption(current);
            }
            QuickSelect.kthSmallest(hamsters, current);

            totalConsumption = 0;
            for (int i = 0; i < current; i++) {
                totalConsumption += hamsters.get(i).consumption;
            }

            if(totalConsumption < foodPackages) {
                canBuy = current;
                begin = current+1;
            } else if(totalConsumption > foodPackages){
                end = current-1;
            } else {
                canBuy = current;
                return canBuy; }
        }
        return canBuy;
    }
}