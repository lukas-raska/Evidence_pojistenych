package projekt;


import java.util.Scanner;

public class ApplicationMain {
    public static void main(String[] args) {

        //inicializace databáze a uživatelského rozhraní
        Scanner scanner = new Scanner(System.in, "UTF-8");
        Database databaseOfInsureds = new Database();
        UserInteractionManager manager = new UserInteractionManager(databaseOfInsureds, scanner);

        boolean programContinues = true;
        //hlavní smyčka programu
        while (programContinues) {

            //intro
            manager.printTextWithLines("EVIDENCE POJIŠTĚNÝCH OSOB", "=");

            //výpis možností
            String text =
                    "[1 - Přidání nového záznamu] " +
                    "[2 - Vyhledání záznamu dle jména] " +
                    "[3 - Výpis všech záznamů] " +
                    "[4 - Konec]";
            manager.printTextWithLines(text, "=");

            //zadání a ověření volby
            boolean correctChoice = false;
            String choosedOption = new String();
            while (!correctChoice) {
                System.out.print("Výběr: ");
                choosedOption = scanner.nextLine().trim();
                switch (choosedOption) {
                    case "1" -> {
                        manager.createAnInsuredPerson();
                        correctChoice = true;
                    }
                    case "2" -> {
                        manager.printTheSearchedPersons();
                        correctChoice = true;
                    }
                    case "3" -> {
                        manager.printAllRecords();
                        correctChoice = true;
                    }
                    case "4" -> {
                        manager.printTextWithLines("Děkuji za použití programu", "=");
                        correctChoice=true;
                        programContinues = false;
                    }
                    default -> System.out.println("Neplatné zadání - nutno zadat volbu dle zobrazené nabídky!");
                }
            }
        }
    }
}

