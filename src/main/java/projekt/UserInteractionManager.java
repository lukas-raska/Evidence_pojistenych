package projekt;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Třída sloužící pro interakci s uživatelem
 */
public class UserInteractionManager {
    /**
     * Databáze pojištěných osob
     */
    private Database databaseOfInsureds;

    /**
     * Scanner pro vstup z klávesnice
     */
    private Scanner scanner;

    /**
     * Konstruktor instance uživatelského rozhraní
     *
     * @param databaseOfInsureds Databáze pojištěných osob
     * @param scanner            Scanner pro zadání vstupních dat
     */
    public UserInteractionManager(Database databaseOfInsureds, Scanner scanner) {
        this.databaseOfInsureds = databaseOfInsureds;
        this.scanner = scanner;
    }

    /**
     * Metoda pro načtení křestního od uživatele
     *
     * @return Po validaci vstupu vrací křestní jméno
     */
    private String readFirstNameFromUser() {
        String input = new String();
        while (input.isBlank()) {
            System.out.print("Zadej křestní jméno: ");
            input = scanner.nextLine().trim();
        }
        return input;
    }

    /**
     * Metoda pro načtení příjmení od uživatele
     *
     * @return Po validaci vstupu vrací příjmení
     */
    private String readLastNameFromUser() {
        String input = new String();
        while (input.isBlank()) {
            System.out.print("Zadej příjmení: ");
            input = scanner.nextLine().trim();
        }
        return input;
    }

    /**
     * Metoda pro načtení telefonního čísla od uživatele
     *
     * @return Vrací telefonní číslo
     */
    private String readPhoneNumberFromUser() {
        String input = new String();
        while (input.isBlank()) {
            System.out.print("Zadej telefonní číslo: ");
            input = scanner.nextLine().trim();
        }
        return input;
    }

    /**
     * Metoda pro načtení a parsování data narození od uživatele
     *
     * @return Vrací datum narození
     */
    private LocalDate readDateOfBirthFromUser() {
        boolean correctInput = false;
        LocalDate dateOfBirth = null;
        while (!correctInput) {
            try {
                System.out.print("Zadej datum narození ve formátu [dd.MM.yyyy]: ");
                dateOfBirth = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                correctInput = true;
                if (dateOfBirth.isAfter(LocalDate.now())) {
                    correctInput = false;
                    System.out.println(" Neplatné zadání. Datum narození nelze zadat vyšší, než je aktuální datum!");
                }
            } catch (DateTimeException exception) {
                System.out.println(" Neplatné zadání. Nutno zadat platné datum v požadovaném formátu!");
            }
        }
        return dateOfBirth;
    }


    /**
     * Metoda k vytvoření pojištěné osoby uživatelem
     */
    public void createAnInsuredPerson() {
        //nadpis
        printTextWithLines("PŘIDÁNÍ POJIŠTĚNÉ OSOBY:", "=");
        //přidání záznamu do databáze
        boolean succesfullyAdded = databaseOfInsureds.addRecord(
                readFirstNameFromUser(),
                readLastNameFromUser(),
                readPhoneNumberFromUser(),
                readDateOfBirthFromUser()
        );
        //výpis
        if (succesfullyAdded) {
            printTextWithLines(" Záznam úspěšně přidán do databáze.", "-");
        } else {
            printTextWithLines(" Nepodařilo se přidat záznam.", "-");
        }
    }

    /**
     * Metoda k vyhledání pojištěné osoby uživatelem (dle jména a příjmení)
     */
    public void printTheSearchedPersons() {
        //nadpis
        printTextWithLines("VYHLEDÁNÍ POJIŠTĚNÉ OSOBY", "=");
        //hledání v databázi
        List<InsuredPerson> foundedInsuredPeople = databaseOfInsureds.searchByName(readFirstNameFromUser(), readLastNameFromUser());
        //výpis
        if (foundedInsuredPeople.isEmpty()) {
            printTextWithLines("Záznam nenalezen.", "-");
        } else {
            //hlavička tabulky
            String header = String.format("%-15s %-15s %-15s %-15s %-15s", "ID", "Jméno", "Příjmení", "Věk", "Telefon");
            printTextWithLines(header, "-");
            //výpis nalezených dat
            foundedInsuredPeople.stream().forEach(System.out::print);
            //výpis počtu záznamů
            String text = "Počet nalezených záznamů: " + foundedInsuredPeople.size();
            printTextWithLines(text, "-");
        }
    }

    /**
     * Metoda k vypsání všech pojištěných osob
     */
    public void printAllRecords() {
        //nadpis
        printTextWithLines("VÝPIS VŠECH POJIŠTĚNÝCH OSOB", "=");
        //hlavička tabulky
        String header = String.format("%-15s %-15s %-15s %-15s %-15s", "ID", "Jméno", "Příjmení", "Věk", "Telefon");
        printTextWithLines(header, "-");
        //výpis nalezených záznamů
        databaseOfInsureds.getListOfPersons().stream()
                .forEach(System.out::print);
        //výpis počtu záznamů
        String numberOfRecords = String.format("Celkem záznamů:  %d", databaseOfInsureds.getListOfPersons().size());
        printTextWithLines(numberOfRecords, "-");
        System.out.println();
    }

    /**
     * Metoda k formátovanému výstupu textu. Obalí text z horní i spodní strany linkou a udělá mi to hezký :-)
     *
     * @param titleText  Vypisovaný text
     * @param charInLine Znak použitý v lince
     */
    public void printTextWithLines(String titleText, String charInLine) {
        String line = charInLine.repeat(titleText.length());
        System.out.println(line);
        System.out.println(titleText);
        System.out.println(line);
    }
}






