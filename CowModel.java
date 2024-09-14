import java.io.*;

public class CowModel {
    private String id;
    private String color;
    private int ageYears;
    private int ageMonths;
    int milkProduced;
    boolean isBSOD;

    public CowModel(String id, String color, int ageYears, int ageMonths) {
        this.id = id;
        this.color = color;
        this.ageYears = ageYears;
        this.ageMonths = ageMonths;
        this.milkProduced = 0;
        this.isBSOD = false;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public int getAgeYears() {
        return ageYears;
    }

    public int getAgeMonths() {
        return ageMonths;
    }

    public int getMilkProduced() {
        return milkProduced;
    }

    public boolean isBSOD() {
        return isBSOD;
    }

    public void resetBSOD() {
        this.isBSOD = false;
    }

    public void incrementMilkProduced() {
        this.milkProduced++;
    }

    public void setBSOD(boolean isBSOD) {
        this.isBSOD = isBSOD;
    }

    public String toCSV() {
        return String.format("%s,%s,%d,%d,%d,%b", id, color, ageYears, ageMonths, milkProduced, isBSOD);
    }

    public static CowModel fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        CowModel cow = new CowModel(fields[0], fields[1], Integer.parseInt(fields[2]), Integer.parseInt(fields[3]));
        cow.milkProduced = Integer.parseInt(fields[4]);
        cow.isBSOD = Boolean.parseBoolean(fields[5]);
        return cow;
    }
}
