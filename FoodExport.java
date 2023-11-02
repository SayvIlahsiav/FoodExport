/**
 * Write a description of FoodExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class FoodExport 
{
    public void tester() 
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        String cInfo = countryInfo(parser, "Germany");
        System.out.println(cInfo);
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");
        
        parser = fr.getCSVParser();
        numOfExporter(parser, "gold");
        
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999");
    }
    
    // returns a string of information about the country
    public String countryInfo(CSVParser parser, String country) 
    {
        for (CSVRecord r : parser)
        {
            String c = r.get("Country");
            if (c.contains(country)) 
                return country 
                + ": " 
                + r.get("Exports") 
                + ": " 
                + r.get("Value (dollars)");
        }
        return "NOT FOUND";
    }
    
    // prints the names of all the countries that have both exportItem1 and exportItem2 as export items
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) 
    {
        for (CSVRecord r : parser)
        {
            String e = r.get("Exports");
            if (e.contains(exportItem1) && e.contains(exportItem2)) 
                System.out.println(r.get("Country"));
        }
    }
    
    // returns the number of countries that export exportItem
    public void numOfExporter(CSVParser parser, String exportItem) 
    {
        int count = 0;
        for (CSVRecord r : parser)
        {
            String e = r.get("Exports");
            if (e.contains(exportItem)) 
                count++; 
        }
        System.out.println(count);
    }
    
    // prints the names of countries and their Value amount for all countries whose Value (dollars) string is longer than the amount string
    public void bigExporters(CSVParser parser, String amount) 
    {
        for (CSVRecord r : parser)
        {
             String a = r.get("Value (dollars)");
             if (a.length() > amount.length())
                 System.out.println(r.get("Country") + " " + a);
        }
    }
    
    public static void main(String args[]) 
    {
        FoodExport fe = new FoodExport();
        fe.tester();
    }
}
