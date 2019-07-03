package regularexpression;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Shahd Hajaj
 */
public class RegularExpression {

    public static void main(String[] args) {

        String fileName = "showInterfaces.txt";
        String line = null;
        int interfaceCount = 0;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String namePattern = "(?:^|(?:[.!?]\\s))(\\w+)(\\D+)(\\d+)(?:\\/)?(\\d+)?(?:\\.)?(\\d+)?";
                String ipAddressPattern = "\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b";
                String macAddressPattern = "(?<=is) ((?:[0-9A-Fa-f]{2}([-: ]?))(?:[0-9A-Fa-f]{2}\\1){4}[0-9A-Fa-f]{2}|([0-9A-Fa-f]{4}\\.){2}[0-9A-Fa-f]{4})";
                String mtuPattern = "(?<=MTU) ([0-9]){1,}";
                String operationStatusPattern = "(?<= line protocol is )[a-zA-Z]{1,}";
                String descriptionPattern = "(?<=Description: )(?s)(.*$)";
                String duplexPattern = ".*duplex,";
                String ifspeedPattern = "(?<=duplex,) .+ {1,}";
                String adminStatusPattern = "(?<=[0-9] is) up";
                Pattern p = Pattern.compile(namePattern);
                Matcher m1 = p.matcher(line);
                Map<String, String> map = new HashMap<String, String>();
                while (m1.find()) {
                    interfaceCount++;
                    map.put("Name", line.substring(m1.start(), m1.end()));
                    list.add(map);
                }
                p = Pattern.compile(ipAddressPattern);
                m1 = p.matcher(line);
                while (m1.find()) {
                    //map.put("IP_Address", line.substring(m1.start(), m1.end()));
                    list.get(interfaceCount - 1).put("IP_Address", line.substring(m1.start(), m1.end()));
                }
                p = Pattern.compile(macAddressPattern);
                m1 = p.matcher(line);
                while (m1.find()) {
                    list.get(interfaceCount - 1).put("MAC_Address", line.substring(m1.start(), m1.end()));
                }
                p = Pattern.compile(mtuPattern);
                m1 = p.matcher(line);
                while (m1.find()) {
                    list.get(interfaceCount - 1).put("MTU", line.substring(m1.start(), m1.end()));
                }
                p = Pattern.compile(operationStatusPattern);
                m1 = p.matcher(line);
                while (m1.find()) {
                    list.get(interfaceCount - 1).put("Operation_Status", line.substring(m1.start(), m1.end()));
                }
                p = Pattern.compile(descriptionPattern);
                m1 = p.matcher(line);
                while (m1.find()) {
                    list.get(interfaceCount - 1).put("Description", line.substring(m1.start(), m1.end()));
                }
                p = Pattern.compile(duplexPattern);
                m1 = p.matcher(line);
                while (m1.find()) {
                    list.get(interfaceCount - 1).put("Duplex_Mode", line.substring(m1.start(), m1.end()));
                }
                p = Pattern.compile(ifspeedPattern);
                m1 = p.matcher(line);
                while (m1.find()) {
                    list.get(interfaceCount - 1).put("ifspeed", line.substring(m1.start(), m1.end()));
                }
                p = Pattern.compile(adminStatusPattern);
                m1 = p.matcher(line);
                while (m1.find()) {
                    list.get(interfaceCount - 1).put("Admin_Status", line.substring(m1.start(), m1.end()));
                }
            }
            Map[] maps = list.toArray(new HashMap[list.size()]);
            System.out.println("Matched interfaces are " + interfaceCount);
            int i = 0;
            for (Map mapf : maps) {
                System.out.println("Interface #" + (i+1));
                System.out.print("\tName: " + mapf.get("Name") + "\n");
                if (mapf.get("IP_Address") == null) {
                    System.out.print("\tIp Address: " + "\n");
                } else {
                    System.out.print("\tIp Address: " + mapf.get("IP_Address") + "\n");
                }
                if (mapf.get("Description") == null) {
                    System.out.print("\tDescription: " + "\n");
                } else {
                    System.out.print("\tDescription: " + mapf.get("Description") + "\n");
                }
                if (mapf.get("ifspeed") == null) {
                    System.out.print("\tIfspeed: " + "\n");
                } else {
                    System.out.print("\tIfspeed: " + mapf.get("ifspeed").toString().replace(",", "") + "\n");
                }
                if (mapf.get("Duplex_Mode") == null) {
                    System.out.print("\tDublex Mode: " + "\n");
                } else {
                    System.out.print("\tDublex Mode: " + mapf.get("Duplex_Mode").toString().replace(",", "") + "\n");
                }
                if (mapf.get("Admin_Status") == null) {
                    System.out.print("\tAdmin status: " + "\n");
                } else {
                    System.out.print("\tAdmin status: " + mapf.get("Admin_Status") + "\n");
                }
                if (mapf.get("Operation_Status") == null) {
                    System.out.print("\tOperation Status: " + "\n");
                } else {
                    System.out.print("\tOperation Status: " + mapf.get("Operation_Status") + "\n");
                }
                if (mapf.get("MAC_Address") == null) {
                    System.out.print("\tMAC Address: " + "\n");
                } else {
                    System.out.print("\tMAC Address: " + mapf.get("MAC_Address") + "\n");
                }
                if (mapf.get("MTU") == null) {
                    System.out.print("\tMTU: " + "\n");
                } else {
                    System.out.print("\tMTU: " + mapf.get("MTU") + "\n");
                }
                i++;
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

}
