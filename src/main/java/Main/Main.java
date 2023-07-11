package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the IP Address Validator!");

        Set<String> blacklistedIPs = readBlacklistFromFile("E:\\MateAcademy\\test-task\\jjd1\\blacklist.txt\\");

        String ipAddress = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!ipAddress.equalsIgnoreCase("quit")) {
            System.out.print("Enter IP address or 'quit' to exit: ");
            try {
                ipAddress = reader.readLine().trim();

                if (!ipAddress.equalsIgnoreCase("quit")) {
                    if (!isValidIPAddress(ipAddress)) {
                        System.out.println("Invalid IP address");
                    } else if (blacklistedIPs.contains(ipAddress)) {
                        System.out.println("Access disallowed");
                    } else {
                        System.out.println("Access allowed");
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading user input: " + e.getMessage());
            }

            // Load the updated blacklist from the file
            blacklistedIPs = readBlacklistFromFile("E:\\MateAcademy\\test-task\\jjd1\\blacklist.txt\\");
        }
    }

    private static Set<String> readBlacklistFromFile(String filename) {
        Set<String> blacklistedIPs = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                blacklistedIPs.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading the blacklist file: " + e.getMessage());
        }

        return blacklistedIPs;
    }

    private static boolean isValidIPAddress(String ipAddress) {
        String ipAddressRegex = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        return ipAddress.matches(ipAddressRegex);
    }
}
