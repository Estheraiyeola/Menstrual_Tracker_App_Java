package controller;

import data.models.MenstrualCalculator;
import data.models.UserDetails;
import data.repository.MenstrualCalculatorRepository;
import data.repository.MenstrualCalculatorRepositoryImpl;
import data.repository.UserDetailsRepository;
import data.repository.UserDetailsRepositoryImpl;
import services.MenstrualCalculatorService;
import services.MenstrualCalculatorServiceImpl;
import services.UserDetailsService;
import services.UserDetailsServiceImpl;

import javax.swing.*;
import java.time.LocalDate;

import java.util.InputMismatchException;

public class MenstrualTrackerApp {
    UserDetailsService userDetailsService = new UserDetailsServiceImpl();
    UserDetailsRepository userDetailsRepository = new UserDetailsRepositoryImpl();
    MenstrualCalculatorService menstrualCalculatorService = new MenstrualCalculatorServiceImpl();
    MenstrualCalculatorRepository menstrualCalculatorRepository = new MenstrualCalculatorRepositoryImpl();
    MenstrualCalculator menstrualCalculator = new MenstrualCalculator();
    LocalDate localDate = LocalDate.now();



    private String firstname;
    private String lastname;

    public MenstrualTrackerApp() {
        this.firstname = "";
        this.lastname = "";
    }

    public void welcomePage(){
        display("""
                Hello Boss Lady,
                I guess you're getting confused constantly in calculating your safe periods and the whole menstrual cycle wahala.
                You're not the only one in this situation.
                
                Though, our menstrual cycle health is important to monitor, but we have more important things to focus on.
                That is why, we created this app to help you take care of that
                """);
        landingPage();
    }

    private void landingPage() {
        String userInput = input("""
                SELECT A NUMBER:
                1. Sign Up
                2. Log in
                3. Exit
                """);
        switch (userInput){
            case "1" -> signUpPage();
            case "2" -> logInPage();
            case "3" -> exit();
            default -> {
                display("Invalid Input");
                landingPage();
            }
        }

    }
    private void exit() {
        display("You are currently leaving this page");
        System.exit(0);
    }

    private void logInPage() {
        display("Logging In.........");
        try {
            String firstName = input("What is your first name: ");
            String lastName = input("What is your last name: ");
            String password = input("What is your password: ");
            boolean validate = userDetailsService.authentication(firstName, lastName, password);
            if (validate) {
                this.firstname = firstName;
                this.lastname = lastName;
                mainMenu();
            }
        }catch (InputMismatchException | NullPointerException e){
            display(e.getMessage());
            landingPage();
        }
    }


    private void mainMenu() {
        String userInput = input(String.format("""
                Welcome %s
                Select an Option:
                1. Update Name
                2. Check safe period
                3. Check fertile period
                4. Get Average Flow length
                5. Get Next Period Date
                6. Log out""", firstname + " " + lastname));
        switch (userInput){
            case "1" -> updateFirstName();
            case "2" -> checkSafePeriod();
            case "3" -> checkFertilePeriod();
            case "4" -> getAverageFlowLength();
            case "5" -> getNextPeriodDate();
            case "6" -> logOut();
            default -> {
                display("Invalid Input");
                mainMenu();
            }
        }
    }

    private void logOut() {
        display("Logging out");
        landingPage();
    }

    private void getNextPeriodDate() {
        try {
            averageCycleCalculator();
            int firstDayOfLastPeriod = Integer.parseInt(input("How many days ago did you start your last period? "));
            long result = menstrualCalculatorService.calculateNextPeriodDate(firstDayOfLastPeriod);
            display("Your next period date will be " + localDate.plusDays(result));
            mainMenu();
        }catch(InputMismatchException | NullPointerException e){
            display(e.getMessage());
            mainMenu();
        }
    }

    private void getAverageFlowLength() {
        try {
            averageCycleCalculator();
            mainMenu();
        }catch (InputMismatchException | NumberFormatException e){
            display(e.getMessage());
            mainMenu();
        }
    }

    private void checkFertilePeriod() {

        try {
            averageCycleCalculator();
            int periodStarted = Integer.parseInt(input("How many days ago did you see your period? "));
            menstrualCalculatorService.calculateOvulationPeriod();
            int beginning = menstrualCalculatorService.getFirstDayOf_OvulationPeriod();
            int end = menstrualCalculatorService.getLastDayOf_OvulationPeriod();
            display(localDate.minusDays(periodStarted).plusDays(beginning) + " - " + localDate.minusDays(periodStarted).plusDays(end));
            mainMenu();
        }catch (InputMismatchException | NullPointerException | NumberFormatException e){
            display(e.getMessage());
            mainMenu();
        }
    }

    private void checkSafePeriod() {
        try {
            averageCycleCalculator();
            int periodStarted = Integer.parseInt(input("How many days ago did you see your period? "));
            int periodLength = Integer.parseInt(input("How many days on average is your period? "));
            menstrualCalculatorService.calculateSafePeriods(periodLength);
            int first = menstrualCalculatorService.getFirstDayOf_FirstRangeOfPeriod();
            int second = menstrualCalculatorService.getLastDayOf_FirstRangeOfPeriod();
            int third = menstrualCalculatorService.getFirstDayOf_LastRangeOfPeriod();
            int fourth = menstrualCalculatorService.getLastDayOf_LastRangeOfPeriod();
            display("Your safe periods are between " +localDate.minusDays(periodStarted).plusDays(first) + " - " +
                    localDate.minusDays(periodStarted).plusDays(second) + " and " +
                    localDate.minusDays(periodStarted).plusDays(third) + " - " +
                    localDate.minusDays(periodStarted).plusDays(fourth) );
            mainMenu();
        }catch (NumberFormatException | InputMismatchException e){
            display(e.getMessage());
            mainMenu();
        }
    }

    private void updateFirstName() {
        try{
            UserDetails userDetails = userDetailsRepository.findByUsername(firstname + lastname);
            String updatedFirstName = input("Enter the new first name: ");
            String updatedLastName = input("Enter the new last name: ");
            userDetails.setFirstName(updatedFirstName);
            userDetails.setLastName(updatedLastName);
        }catch (InputMismatchException e){
            display(e.getMessage());
            mainMenu();
        }
    }

    private void signUpPage() {
        try {
            String firstName = input("What is your first name: ");
            String lastName = input("What is your last name: ");
            int age = Integer.parseInt(input("What is your age: "));
            String password = input("What is your password: ");
            userDetailsService.register(firstName, lastName, age, password);
            logInPage();
        }catch (InputMismatchException | NumberFormatException e){
            display(e.getMessage());
            landingPage();
        }

    }
    public void averageCycleCalculator(){
        if (menstrualCalculatorService.getAverageMenstrualCycle() == 0) {
            int firstMonth = Integer.parseInt(input("What was your cycle 3 months ago? "));
            int secondMonth = Integer.parseInt(input("What was your cycle 2 months ago? "));
            int thirdMonth = Integer.parseInt(input("What was your cycle 1 months ago? "));
            menstrualCalculatorService.calculateAverageMenstrualCycle(firstMonth, secondMonth, thirdMonth);
            menstrualCalculator.setCycleLength(menstrualCalculatorService.getAverageMenstrualCycle());
            display("Your Average Cycle Length " + menstrualCalculator.getCycleLength());
        }
    }

    private static String input(String message){
        return JOptionPane.showInputDialog(null, message);
    }
    private static void display(String message){
        JOptionPane.showMessageDialog(null, message);
    }
}
