package projekt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída představující databázi pojištěných osob
 */
public class Database {
    /**
     * Seznam osob uložených v databázi
     */
    private List<InsuredPerson> listOfInsuredPersons;

    /**
     * Konstruktor databáze - inicializuje seznam
     */
    public Database() {
        this.listOfInsuredPersons = new ArrayList<>();
    }

    /**
     * Metoda pro přidání osoby do seznamu
     *
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param dateOfBirth
     */
    public boolean addRecord(String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth) {
        return listOfInsuredPersons.add(new InsuredPerson(firstName, lastName, phoneNumber, dateOfBirth));
    }

    /**
     * Metoda k vyhledání záznamu v databázi dle jména
     *
     * @param firstName Křestní jméno
     * @param lastName  Příjmení
     * @return Vrací seznam nalezených záznamů
     */
    public List<InsuredPerson> searchByName(String firstName, String lastName) {
        List<InsuredPerson> searchedRecords = listOfInsuredPersons.stream()
                .filter(insuredPerson -> insuredPerson.getLastName().equals(lastName))
                .filter(insuredPerson -> insuredPerson.getFirstName().equals(firstName))
                .toList();
        return searchedRecords;
    }

    /**
     * Metoda pro zobrazení seznamu všech pojištěných osob
     *
     * @return Vrátí seznam všech pojištěných osob
     */
    public List<InsuredPerson> getListOfPersons() {
        return listOfInsuredPersons;
    }
}
