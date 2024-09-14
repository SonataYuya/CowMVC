import java.awt.event.*;

public class CowController {
    private CowView view;
    private CowDatabase cowDatabase;

    public CowController(CowView view, CowDatabase cowDatabase) {
        this.view = view;
        this.cowDatabase = cowDatabase;

        // Load cow data from CSV file at the start
        cowDatabase.loadFromCSV();

        this.view.addSubmitListener(new SubmitListener());
        this.view.addMilkListener(new MilkListener(false));
        this.view.addLemonMilkListener(new MilkListener(true));
    }

    public void saveData() {
        cowDatabase.saveToCSV();
    }

    class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cowId = view.getCowId();
            if (isValidId(cowId)) {
                CowModel cow = cowDatabase.findCowById(cowId);
                if (cow != null) {
                    view.setResultArea("Cow found: " + cow.getColor() + " Age: " + cow.getAgeYears() + " years, " + cow.getAgeMonths() + " months.");
                    view.showMilkingOptions(cow.getColor().equals("White"));
                } else {
                    view.setResultArea("Cow ID not found.");
                }
            } else {
                view.setResultArea("Invalid ID format. Must be 8 digits and not start with 0.");
            }
        }

        private boolean isValidId(String cowId) {
            return cowId.matches("[1-9][0-9]{7}");
        }
    }

    class MilkListener implements ActionListener {
        private boolean addLemon;

        public MilkListener(boolean addLemon) {
            this.addLemon = addLemon;
        }

        public void actionPerformed(ActionEvent e) {
            String cowId = view.getCowId();
            CowModel cow = cowDatabase.findCowById(cowId);
            if (cow != null) {
                if (cow.isBSOD()) {
                    view.setResultArea("Cow is in BSOD state and cannot be milked.");
                    return;
                }
                cow.incrementMilkProduced();
                String milkType = "Plain milk";
                if (addLemon && cow.getColor().equals("White")) {
                    milkType = "Sour milk with lemon";
                } else if (cow.getColor().equals("Brown")) {
                    milkType = "Chocolate milk";
                }
                view.setResultArea("Cow milked: " + milkType);
                saveData();
            } else {
                view.setResultArea("Cow not found.");
            }
        }
    }
}
