package checkinglogs;

import java.util.*;
import edu.duke.*;

/**
 *
 * @author Matt
 */
public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    
    public LogAnalyzer(){
        records = new ArrayList<LogEntry>();
    }
    
    public void readFile(String filename){
        FileResource fr = new FileResource(filename);
        // iterate over each line
        for(String line : fr.lines()){
            //use WebLogParser.parseEntry to create a LogEntry
            LogEntry entry = WebLogParser.parseEntry(line);
            // add resulting LogEntry to records
            records.add(entry);
        }
    }
    
    public void printAll(){
        for (LogEntry le : records){
            System.out.println(le);
        }
    }
    
    //Starting from this point, the below methonds were authored by Matt
    
    //This method counts the Unique IP address in a log file
    public int countUniqueAddresses(){
        ArrayList<String> uniqueAddresses = new ArrayList<String>();
        for (LogEntry le : records){
            String ip = le.getIpAddress();
            if (!uniqueAddresses.contains(ip)){
                uniqueAddresses.add(ip);
            }
        }
        return uniqueAddresses.size();
    }
    
    //this Method prints out all log entries that have a status code higher then the passed in number
    public void printAllHigherThanNum(int num){
        for (LogEntry le : records){
            int statusCode = le.getStatusCode();
            if (statusCode > num){
                System.out.println(le);
            }
        }
    }
    
    //This method prints out the unique IP address from a Log file on the passed in day
    //Day follows a Mmm DD format. Example: Mar 21
    public void uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueAddresses2 = new ArrayList<String>();
        for (LogEntry le : records){
            String date = le.getAccessTime().toString();
            String ip = le.getIpAddress();
            if (date.contains(someday)){
                if (!uniqueAddresses2.contains(ip)){
                uniqueAddresses2.add(ip);
                }
            }
        }
        //System.out.println(uniqueAddresses2.size());
        for (String ip : uniqueAddresses2){
            System.out.println(ip);
        }
        //return uniqueAddresses2;
    }
    
    //this method prints out the unique ip addresses that have a status code withing the range of the
    //passed in low/high ints
    public void countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueAddresses3 = new ArrayList<String>();
        for (LogEntry le : records){
            int statusCode = le.getStatusCode();
            if(statusCode >= low && statusCode <= high){
                String ip = le.getIpAddress();
                if (!uniqueAddresses3.contains(ip)){
                    uniqueAddresses3.add(ip);
                }
            }
        }
        System.out.println(uniqueAddresses3.size());
    }
    
    // this method return a hashmap of the unique ipaddresses and the amount of times that the ip address
    // visits
    public HashMap<String, Integer> countsVisitPerIP(){
        HashMap<String, Integer> counts = new HashMap<String, Integer>(); //makes enmpty hashmap
        for (LogEntry le : records){
            String ipAddress = le.getIpAddress(); //gets ip address
            if (!counts.containsKey(ipAddress)){ //check if ip is in counts
                //if not in, sets count to one and adds ip in hasmap
                counts.put(ipAddress, 1);
            }else{ //else it increments the value
                counts.put(ipAddress, counts.get(ipAddress) + 1);
            }
        }
        return counts;
    }
    
    // This method  prints out the unique IP address visits on the passed in day
    // Day follows a Mmm DD format. Example: Mar 21
    public void uniqueIPVisitsOnDay2(String someday){
        ArrayList<String> uniqueAddresses2 = new ArrayList<String>();
        for (LogEntry le : records){
            String date = le.getAccessTime().toString();
            String ip = le.getIpAddress();
            if (date.contains(someday)){
                uniqueAddresses2.add(ip);
            }
        }
        
        for (String ip : uniqueAddresses2){
            System.out.println(ip);
        }
        System.out.println("Unique Addesses on day " + someday + ": " + uniqueAddresses2.size());
    }
    
    // this method prints out the day from the log file with the most visits
    public void dayWithMostIPVisits(){
        ArrayList<String> uniqueAddresses2 = new ArrayList<String>();
        ArrayList<Integer> counts = new ArrayList<Integer>();
        ArrayList<String> dates = new ArrayList<String>();
        for (LogEntry le : records){
            String date = le.getAccessTime().toString();
            String ip = le.getIpAddress();
            if (!dates.contains(date)){
                uniqueAddresses2.add(ip);
                counts.add(1);
                dates.add(date);
            }else{
                int idx = uniqueAddresses2.indexOf(ip);
                Integer value = counts.get(idx);
                value = value + 1;
                counts.set(idx, value);
            }
        }
        int total = 0;
        for(int i : counts){
            if (i > total){
                total = i;
            }
        }
        
        int idx = counts.indexOf(total);
        System.out.println(dates.get(idx));
    }
    
    // this method  prints out the ips with the most visits on the passed in day
    // Day follows a Mmm DD format. Example: Mar 21
    public void iPsWithMostVisitsOnDay (String someday){
        ArrayList<String> uniqueAddresses2 = new ArrayList<String>();
        for (LogEntry le : records){
            String date = le.getAccessTime().toString();
            String ip = le.getIpAddress();
            if(date.contains(someday)){
                if(!uniqueAddresses2.contains(ip)){
                    uniqueAddresses2.add(ip);
                }
            }
        }
        
        for(String ip : uniqueAddresses2){
            System.out.println(ip);
        }
    }
}
