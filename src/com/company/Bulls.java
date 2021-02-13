package com.company;

import java.util.Scanner;

public class Bulls {
    public static void main(String[] args) {
        printWelcomeMessage();
        playTheGame();

    }

    public static String firstPlayerNumber = "";
    public static String secondPlayerNumber = "";
    public static String randomNumberStr = "";

    public static void printWelcomeMessage() {
        System.out.println("Welcome to the game of BULLS and COWS.");
        System.out.println("The objective in this game is for you to guess a 4-digit number.");
        System.out.println("The computer responds with how close your guess is to the target.");
        System.out.println("BULLS = # common digits with exact matches.");
        System.out.println("COWS = # common digits in wrong position.");
        System.out.println("Enter number of players (1 or 2)");
        System.out.println();
    }

    public static String createRandomNumber() {
        int randomNumber = 1000 + ((int) (Math.random() * 10000) % 9000);
        randomNumberStr = Integer.toString(randomNumber);
        while (hasRepeatingDigits(randomNumberStr)) {
            createRandomNumber();
        }
        return randomNumberStr;
    }

    public static boolean hasRepeatingDigits(String numberComputer) {
        for (int i = 0; i < numberComputer.length() - 1; i++) {
            for (int j = i + 1; j < numberComputer.length(); j++) {
                if (numberComputer.charAt(i) == numberComputer.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsNonDigits(String num) {
        if (!num.matches("^[0-9]+$")) {
            return true;
        }
        return false;
    }

    public static int computeBulls(String computeNumber, String playersNumber) {
        int bullCounter = 0;

        for (int i = 0; i < computeNumber.length(); i++) {
            if (computeNumber.charAt(i) == playersNumber.charAt(i)) {
                bullCounter++;
            }
        }
        return bullCounter;
    }

    public static int computeCows(String computeNumber, String playersNumber) {
        int cowsCounter = 0;
        for (int i = 0; i < computeNumber.length(); i++) {
            for (int j = 0; j < playersNumber.length(); j++) {
                if (i != j) {
                    if (computeNumber.charAt(i) == playersNumber.charAt(j)) {
                        cowsCounter++;
                    }
                }
            }
        }
        return cowsCounter;
    }

    public static void checkPlayersNumber(String enteredNumber, int bulls, int cows) {
        if (hasRepeatingDigits(enteredNumber)) {
            System.out.println("Your guess should not contain repeating digits.");
        } else if (enteredNumber.length() != 4) {
            System.out.println("Your guess should contain 4 symbols (Digits)");
        } else if (containsNonDigits(enteredNumber)) {
            System.out.println("Your guess should not contain non-digits.");
        } else if (!hasRepeatingDigits(enteredNumber) && !containsNonDigits(enteredNumber))
            System.out.println("Bulls = " + bulls + "\tCows = " + cows);
    }

    public static Boolean checkForWin(int bulls) {
        if (bulls == 4) {
            System.out.println("Congratulations! You won!");
            return true;
        }
        return false;
    }

    public static void playOnePlayer() {
        Scanner sc = new Scanner(System.in);
        int bulls = 0;
        int cows = 0;
        boolean notFound = false;
        firstPlayerNumber = createRandomNumber();
        while (!notFound) {
            System.out.print("Enter your number: ");
            String guessedNumber = sc.nextLine();

            bulls = computeBulls(guessedNumber, firstPlayerNumber);
            cows = computeCows(guessedNumber, firstPlayerNumber);

            checkPlayersNumber(guessedNumber, bulls, cows);
            notFound = checkForWin(bulls);
        }
    }

    public static void playTwoPlayer() {
        Scanner sc = new Scanner(System.in);
        int playerTurn = 0;
        int bulls = 0;
        int cows = 0;
        boolean notFound = false;
        int numberOfPlayers = sc.nextInt();

        firstPlayerNumber = createRandomNumber();

        if (numberOfPlayers == 2) {
            secondPlayerNumber = createRandomNumber();

            while (!notFound) {

                if (playerTurn % 2 == 0) {
                    System.out.println("Enter your number first player: ");
                    String guessedNumber = sc.nextLine();

                    bulls = computeBulls(guessedNumber, firstPlayerNumber);
                    cows = computeCows(guessedNumber, firstPlayerNumber);

                    checkPlayersNumber(guessedNumber, bulls, cows);
                    notFound = checkForWin(bulls);
                } else {
                    System.out.print("Enter your number second player: ");
                    String guessedNumber = sc.nextLine();

                    bulls = computeBulls(guessedNumber, secondPlayerNumber);
                    cows = computeCows(guessedNumber, secondPlayerNumber);

                    checkPlayersNumber(guessedNumber, bulls, cows);
                    notFound = checkForWin(bulls);
                }
                playerTurn++;

            }
        }
    }

    public static void playTheGame() {
        playTwoPlayer();
        playOnePlayer();
    }

}


