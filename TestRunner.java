
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.runner.*;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import tests.M2PublicTests;



public class TestRunner {


    public static void main(String[] args) {
        var runner = new JUnitCore();
        var lisener = new ExecutionLisener();
        runner.addListener(lisener);
        System.out.println("Input the number of trials for the tests:");
        var sc = new Scanner(System.in);
        var trials = sc.nextInt();
        for (int i = 0; i < trials; i++)
            runner.run(M2PublicTests.class);
        System.out.print("\033[H\033[2J");
        System.out.println("Total number of failures after summing all failures from all tests trials: ");
        System.out.println(lisener.numberOfFaluires);
        System.out.println(lisener.names);
        sc.close();
    }



    public static class ExecutionLisener extends RunListener {
        public int numberOfFaluires = 0;
        public ArrayList<String> names = new ArrayList<String>();
        @Override
        public void testFailure(Failure failure) throws Exception {
            names.add( failure.getTestHeader() + ":  "+ failure.getMessage());

            numberOfFaluires++;
        }
    }
}
