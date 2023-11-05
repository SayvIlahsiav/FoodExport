/**
 * Write a description of FoodExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;

public class FoodExport 
{
    public void tester() 
    {       
        System.out.println("Program started");
        Scanner scn = new Scanner(System.in);
        FileResource fr = new FileResource("exportdata.csv");
        CSVParser parser;
        
        System.out.println("What do you want to do? Type 1, 2, 3 or 4: ");
        System.out.println("1 - Find Country Info");
        System.out.println("2 - Find Two Item Exporters");
        System.out.println("3 - Find No. Of Item Exporters");
        System.out.println("4 - Find Big Exporters");
        System.out.println("5 - Stop!");
        
        String choice = scn.nextLine();
        while (!choice.equals("5"))
        {
            if (choice.equals("1"))
            {
                System.out.println("COUNTRY INFO");
                System.out.print("Enter Country: ");
                String country = scn.nextLine();
                parser = fr.getCSVParser();
                System.out.println(countryInfo(parser, country));
                System.out.println();
            }
            if (choice.equals("2"))
            {
                System.out.println("TWO ITEM EXPORTERS");
                System.out.print("Enter Export Item 1: ");
                String item1 = scn.nextLine();
                System.out.print("Enter Export Item 2: ");
                String item2 = scn.nextLine();
                parser = fr.getCSVParser();
                listExportersTwoProducts(parser, item1, item2);
                System.out.println();
            }
            if (choice.equals("3"))
            {
                System.out.println("NO. OF ITEM EXPORTERS");
                System.out.print("Enter Export Item: ");
                String item = scn.nextLine();
                parser = fr.getCSVParser();
                numOfExporter(parser, item);
                System.out.println();
            }
            if (choice.equals("4"))
            {
                System.out.println("BIG EXPORTERS");
                System.out.print("Enter Value (with dollars and commas): ");
                String value = scn.nextLine();
                parser = fr.getCSVParser();
                bigExporters(parser, value);
                System.out.println();
            }
            System.out.println("What do you want to do? Type 1, 2, 3, 4 or 5: ");
            choice = scn.nextLine();
        }  
        scn.close();
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
    
    public static void main(String[] args) 
    {
        System.out.println("Main started");
        FoodExport fe = new FoodExport();
        fe.tester(); 
    }
}
