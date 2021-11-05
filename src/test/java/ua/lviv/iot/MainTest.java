package ua.lviv.iot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class MainTest {
    public ArrayList<Hamster> hamsters;

    @Before
    public void fillArrays(){
        hamsters = new ArrayList<Hamster>();
        for (int i = 1; i < 5; i++){
            hamsters.add(new Hamster(i, i));
        }
    }

    @Test
    public void testBuyHamstersOneElemArray(){
        ArrayList<Hamster> hamster = new ArrayList<Hamster>();
        hamster.add(new Hamster(1,2));
        Assert.assertEquals(1,Main.buyHamsters(hamster, 50));
        Assert.assertEquals(0, Main.buyHamsters(hamster, 0));
    }

    @Test
    public void testBuyHamsters(){
        Integer actualResult = Main.buyHamsters(this.hamsters, 40);
        Integer expectedResult = 4;
        Assert.assertEquals(expectedResult, actualResult);
    }

}
