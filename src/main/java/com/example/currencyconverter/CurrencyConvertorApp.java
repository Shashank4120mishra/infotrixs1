package com.example.currencyconverter;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConvertorApp {

    private static Map<String, Double> favoriteCurrencies;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        favoriteCurrencies = new HashMap<>();

        while (true) {
            System.out.println("Currency Converter Menu:");
            System.out.println("1. Add Favorite Currency");
            System.out.println("2. View Favorite Currencies");
            System.out.println("3. Update Favorite Currency Rate");
            System.out.println("4. Show Exchange Rates");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addFavoriteCurrency(scanner);
                    break;
                case 2:
                    viewFavoriteCurrencies();
                    break;
                case 3:
                    updateFavoriteCurrencyRate(scanner);
                    break;
                case 4:
                    showExchangeRates();
                    break;
                case 5:
                    System.out.println("Exiting. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addFavoriteCurrency(Scanner scanner) {
        System.out.print("USD ");
        String currencyCode = scanner.nextLine().toUpperCase();

        if (isValidCurrencyCode(currencyCode)) {
            favoriteCurrencies.put(currencyCode, 0.0);
            System.out.println(currencyCode + " has been added to your favorite currencies.");
        } else {
            System.out.println("Invalid currency code. Please enter a valid currency code.");
        }
    }

    // Helper method to check if a currency code is valid
    private static boolean isValidCurrencyCode(String currencyCode) {
        String[] validCurrencyCodes = {"USD", "EUR", "GBP", "JPY", "AUD", "CAD", "CHF", "CNY"};


        for (String validCode : validCurrencyCodes) {
            if (validCode.equals(currencyCode)) {
                return true;
            }
        }


        return false;
        // return currencyCode.matches("^[A-Z]{3}$");
    }

    private static void viewFavoriteCurrencies() {
        if (favoriteCurrencies.isEmpty()) {
            System.out.println("You haven't added any favorite currencies yet.");
        } else {
            System.out.println("Your Favorite Currencies:");
            for (Map.Entry<String, Double> entry : favoriteCurrencies.entrySet()) {
                String currencyCode = entry.getKey();
                double exchangeRate = entry.getValue();
                System.out.println(currencyCode + ": " + exchangeRate);
            }
        }
    }

    private static void updateFavoriteCurrencyRate(Scanner scanner) {
        if (favoriteCurrencies.isEmpty()) {
            System.out.println("You haven't added any favorite currencies yet.");
            return;
        }


        System.out.println("Select a currency to update:");
        int index = 1;
        Map<Integer, String> indexToCurrency = new HashMap<>();
        for (String currencyCode : favoriteCurrencies.keySet()) {
            System.out.println(index + ". " + currencyCode);
            indexToCurrency.put(index, currencyCode);
            index++;
        }

        System.out.print("Enter the number of the currency to update: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (indexToCurrency.containsKey(choice)) {
            String selectedCurrency = indexToCurrency.get(choice);
            System.out.print("Enter the new exchange rate for " + selectedCurrency + ": ");
            double newRate = scanner.nextDouble();
            scanner.nextLine();

            favoriteCurrencies.put(selectedCurrency, newRate);
            System.out.println(selectedCurrency + " exchange rate has been updated to " + newRate);
        } else {
            System.out.println("Invalid selection. Please enter a valid number.");
        }
    }


    private static void showExchangeRates() {
        try {
            Map<String, Double> exchangeRates = CurrencyConvertor.getExchangeRates();

            System.out.println("Exchange Rates:");
            for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
                String currencyCode = entry.getKey();
                double rate = entry.getValue();
                System.out.println(currencyCode + ": " + rate);
            }
        } catch (Exception e) {
            System.out.println("Failed to fetch exchange rates: " + e.getMessage());
        }
    }
}










