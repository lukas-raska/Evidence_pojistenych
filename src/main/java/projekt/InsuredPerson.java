package projekt;

import java.time.LocalDate;

/**
 * Třída představující pojištěnou osobu
 */
public class InsuredPerson {

    /**
     * Křestní jméno osoby
     */
    private String firstName;

    /**
     * Příjmení osoby
     */
    private String lastName;

    /**
     * Telefonní číslo
     */
    private String phoneNumber;

    /**
     * Datum narození
     */
    private LocalDate dateOfBirth;

    /**
     * Věk osoby
     */
    private int age;

    /**
     * Evidenční číslo (ID)
     */
    private int id;

    /**
     * Statická proměnná sloužící k aktualizaci 'id'. Zvýší se při každé vytvořené instanci (viz konstruktor)
     */
    private static int nextId = 1;

    /**
     * Konstruktor instance  osoby
     *
     * @param firstName   Křestní jméno
     * @param lastName    Příjmení
     * @param phoneNumber Telefonní číslo
     * @param dateOfBirth Datum narození
     */
    public InsuredPerson(String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.setAge();
        this.id = nextId;
        nextId++;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

        public int getAge() {
        return age;
    }

    public void setAge() {
        this.age = this.dateOfBirth.until(LocalDate.now()).getYears();
    }


    /**
     * Metoda pro výpis instance osoby v požadovaném formátu
     * @return Vrací informace o pojištěné osobě v požadovaném formátu
     */
    @Override
    public String toString() {
        String formatID = String.format("%05d", this.id);
        return String.format("%-15s %-15s %-15s %-15d %-15s%n", formatID, this.firstName, this.lastName, this.age, this.phoneNumber);
    }
}
