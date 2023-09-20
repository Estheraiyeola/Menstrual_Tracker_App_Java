package controller;

import data.models.UserDetails;
import data.repository.UserDetailsRepository;
import data.repository.UserDetailsRepositoryImpl;
import services.UserDetailsService;

import javax.swing.*;
import java.util.InputMismatchException;

public class MenstrualTrackerApp {
    UserDetailsService userDetailsService = new UserDetailsService();
    UserDetailsRepository userDetailsRepository = new UserDetailsRepositoryImpl();
    private String firstname;
    private String lastname;

    public MenstrualTrackerApp() {
        this.firstname = "";
        this.lastname = "";
    }

    public void welcomePage(){
        display(String.format("""
                Hello Boss Lady,
                I guess you're getting confused constantly in calculating your safe periods and the whole menstrual cycle wahala.
                You're not the only one in this situation.
                
                Though, our menstrual cycle health is important to monitor, but we have more important things to focus on.
                That is why, we created this app to help you take care of that
                """));
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
        try {
            String firstName = input("What is your first name: ");
            String lastName = input("What is your last name: ");
            String password = input("What is your password: ");
            userDetailsService.authentication(firstName, lastName, password);
            this.firstname = firstName;
            this.lastname = lastName;
            mainMenu();
        }catch (InputMismatchException e){
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
            UserDetailsService userDetailsService = new UserDetailsService();
            userDetailsService.register(firstName, lastName, age, password);
            logInPage();
        }catch (InputMismatchException e){
            display(e.getMessage());
            landingPage();
        }

    }


    private static String input(String message){
        return JOptionPane.showInputDialog(null, message);
    }
    private static void display(String message){
        JOptionPane.showMessageDialog(null, message);
    }
}