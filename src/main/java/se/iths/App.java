package se.iths;

import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static User user;
    public static void main(String[] args) {
        createUser();
//        System.out.println("""
//                Meny:\s
//                1. Skapa löprunda\s
//                2. Lista alla löprundor\s
//                3. Visa detaljer för en löprunda\s
//                4. Ta bort en löprunda\s
//                """
//        );
//        String input = scanner.nextLine();
//        switch (input) {
//            case "1":
        
              
//            case "2":
//                user.printActivities();
//                break;
//            case "3":
//                System.out.println("Ange ett id för en löprunda");
//                String id = scanner.nextLine();
//                user.printActivityById(id);
//                break;
//            case "4":
//                while(true) {
//                    System.out.println("Ange ett id för en löprunda");
//                    String id2 = scanner.nextLine();
//                    try {
//                        System.out.println("Vill du ta bort följande löprunda? (ja/nej)");
//                        user.printActivityById(id2);
//                        if(scanner.nextLine().equals("ja")) {
//                            user.deleteActivityById(id2);
//                            System.out.println("Löprunda med id " + id2 + " har tagits bort");
//                            break;
//                        }
//                    } catch (NullPointerException e) {
//                        System.out.println(e);
//                    }
//                }
        //}
    }
    public static void createUser() {
        System.out.println("Ange ditt namn:");
        String name = scanner.nextLine();
        System.out.println("Ange din längd i centimeter");
        int height = Integer.parseInt(scanner.nextLine());
        System.out.println("Ange din vikt i kilogram");
        int weight = Integer.parseInt(scanner.nextLine());
        System.out.println("Ange din ålder i år");
        int age = Integer.parseInt(scanner.nextLine());
        user = new User(name);
        user.setHeight(height);
        user.setWeight(weight);
        user.setAge(age);
        System.out.println(user);
    }

    public static void createActivity() {
        System.out.println("Ange datum i formatet YYYY-MM-DD, lämna tomt för dagens datum:");
        String date = scanner.nextLine();
        System.out.println("Ange distans i kilometer:");
        int distance = Integer.parseInt(scanner.nextLine());
        System.out.println("Ange tid i formatet HH:MM:SS");
        String duration = scanner.nextLine();
        Activity activity = new Activity(distance, duration, date);
        user.addActivity(activity);
    }
}
