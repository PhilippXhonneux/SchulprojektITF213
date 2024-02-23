package de.bwv_ac.data;

import de.bwv_ac.util.Observer;
import de.bwv_ac.util.Subject;

import java.util.ArrayList;
import java.util.Collection;

public class Companies extends Subject {

    private ArrayList<Company> companies;
    private String[] columns = new String[]{"ID", "Unternehmen", "Veranstaltung", "Max. Teilnehmer", "Max. Veranstaltung", "Fühster Zeitpunkt", "Teilnehmer", "Veranstaltungen",};

    /**
     * Create an observable object
     *
     * @param c A collection that you want like an ArrayList
     */
    public Companies(Collection<Observer> c) {
        super(c);
        companies = new ArrayList<>();
    }

    public void addCompany(Company company){
        companies.add(company);
        notifyObservers();
    }

    public void addCompanies(ArrayList<Company> companies, String[] columns){
        for (Company company : companies){
            addCompany(company);
        }
        this.columns = columns;
        notifyObservers();
    }

    public void removeCompany(Company company){
        companies.remove(company);
        notifyObservers();
    }

    public Company getCompany(int index){
        return companies.get(index);
    }

    public String[] getCompanyArray(int index){
        Company company = companies.get(index);
        return company.ToCSVString(";").split(";");
    }

    public String[][] getCompaniesArray(){
        String[][] companies = new String[this.companies.size()][];

        for (int i = 0; i< companies.length; i++){
            companies[i] = getCompanyArray(i);
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


    public void changeCompany(int index, Company c) {
        companies.set(index, c);
        System.out.println(index);
        notifyObservers();
    }
}
