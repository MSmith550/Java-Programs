package checkinglogs;

import java.util.*;

/**
 *
 * @author Matt
 */
public class Tester {

    public static void main(String[] args) {
        //testLogEntry();
        testLogAnalyzer();
    }
    
    public static void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public static void testLogAnalyzer() {
        // complete method
        LogAnalyzer analyzer = new LogAnalyzer();
        //String fileName = "weblog-short_log.txt";
        //String fileName = "short-test_log.txt";
        String fileName = "Data\\UniqueIPData\\weblog2_log.txt";
        //String fileName = "Data\\CountingVisitsData\\weblog3-short_log.txt";
        analyzer.readFile(fileName);
        //analyzer.printAll();
        System.out.println("Unique Addresses: " + analyzer.countUniqueAddresses());
        //analyzer.printAllHigherThanNum(400);
        //analyzer.uniqueIPVisitsOnDay("Sep 24");
        //analyzer.countUniqueIPsInRange(200, 299);
        
        HashMap<String, Integer> counts = analyzer.countsVisitPerIP();
        System.out.println(counts);
    }
    
    public void testUniqueIPOnDay(){
        LogAnalyzer analyzer = new LogAnalyzer();
        String fileName = "Data\\CountingVisitsData\\weblog3-short_log.txt";
        analyzer.readFile(fileName);
        analyzer.uniqueIPVisitsOnDay2("Sep 30");
    }
    
    public void testDaysWithMostIPVisits(){
        LogAnalyzer analyzer = new LogAnalyzer();
        //String fileName = "Data\\CountingVisitsData\\weblog3-short_log.txt";
        String fileName = "Data\\UniqueIPData\\weblog2_log.txt";
        analyzer.readFile(fileName);
        analyzer.dayWithMostIPVisits();
    }
    
    public void testiPsWithMostVisitsOnDay(){
        LogAnalyzer analyzer = new LogAnalyzer();
        //String fileName = "Data\\CountingVisitsData\\weblog3-short_log.txt";
        String fileName = "Data\\UniqueIPData\\weblog2_log.txt";
        analyzer.readFile(fileName);
        analyzer.iPsWithMostVisitsOnDay("Sep 29");
    }
}
