package de.bwv_ac.data;

import de.bwv_ac.util.Observer;
import de.bwv_ac.util.Subject;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Robin Görissen, Philipp Xhonneux
 */
public class Companies extends Subject implements DataCollection<Company> {

    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Company> companies;
    private String[] columns = new String[]{"ID", "Unternehmen", "Veranstaltung", "Max. Teilnehmer", "Max. Veranstaltung", "Frühster Zeitpunkt", "Teilnehmer", "Veranstaltungen",};

    /**
     * Create an observable object
     *
     * @param c A collection that you want like an ArrayList
     */
    public Companies(Collection<Observer> c) {
        super(c);
        companies = new ArrayList<>();
    }

    public void add(Company company){
        companies.add(company);
        notifyObservers();
    }

    public void add(Collection<Company> companies, String[] columns){
        for (Company company : companies){
            add(company);
        }
        this.columns = columns;
        notifyObservers();
    }

    public void remove(Company company){
        companies.remove(company);
        notifyObservers();
    }

    public Company get(int index){
        return companies.get(index);
    }

    public String[] getArray(int index){
        Company company = companies.get(index);
        return company.ToCSVString(";").split(";");
    }

    public String[][] getArrays(){
        String[][] companies = new String[this.companies.size()][];

        for (int i = 0; i< companies.length; i++){
            companies[i] = getArray(i);
        }
        return companies;
    }

    /**
     * Get columns aka. the first line of the file
     * @return the columns. Empty string size 4 if empty
     */
    public String[] getColumns(){
        if(columns == null)
            return new String[3];

        return columns;
    }


    public void change(int index, Company c) {
        companies.set(index, c);
        notifyObservers();
    }

    public Company getCompany(int index) {
        return companies.get(index);
    }

    public void removeCompany(Company c) {
        companies.remove(c);
    }
}
