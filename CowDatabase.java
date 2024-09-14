import java.io.*;
import java.util.*;

class CowDatabase {
    private Map<String, CowModel> cowDatabase;

    public CowDatabase() {
        this.cowDatabase = new HashMap<>();
        loadFromCSV();
    }

    public void loadFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("./cow_data.csv"))) {
            String line;
            // ข้ามแถวหัวข้อ
            br.readLine();
            while ((line = br.readLine()) != null) {
                CowModel cow = CowModel.fromCSV(line);
                cowDatabase.put(cow.getId(), cow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static CowModel fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        if (fields.length != 6) {
            throw new IllegalArgumentException("CSV line does not have the correct number of fields: " + csvLine);
        }
        CowModel cow = new CowModel(fields[0], fields[1], Integer.parseInt(fields[2]), Integer.parseInt(fields[3]));
        cow.milkProduced = Integer.parseInt(fields[4]);
        cow.isBSOD = Boolean.parseBoolean(fields[5]);
        return cow;
    }
    

    public void addCow(CowModel cow) {
        cowDatabase.put(cow.getId(), cow);
    }

    public CowModel findCowById(String id) {
        return cowDatabase.get(id);
    }

    public Collection<CowModel> getAllCows() {
        return cowDatabase.values();
    }

    public void saveToCSV() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveToCSV'");
    }
}
