package me.collinpatrick.customcraft.Files;
import org.bukkit.plugin.Plugin;
import org.json.simple.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;


public class StructureReadWrite {
    private String filePathBase = "";

    public void structureWriteOut(String absolutePath, String fileName, JSONArray writeList) {
        //check if the Folder has been made
        File dirFolder = new File(absolutePath);
        dirFolder.mkdir();


        try {
            File fileOut = new File(absolutePath + "/" + fileName);
            //Check if fileOut is already there
            boolean made = fileOut.createNewFile();
            System.out.println(writeList.toJSONString());
            FileWriter fr = new FileWriter(fileOut);
            fr.write(writeList.toJSONString());
            fr.flush();
            fr.close();

        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }



}
//    //Second Employee
//    JSONObject employeeDetails2 = new JSONObject();
//        employeeDetails2.put("firstName", "Brian");
//                employeeDetails2.put("lastName", "Schultz");
//                employeeDetails2.put("website", "example.com");
//
//                JSONObject employeeObject2 = new JSONObject();
//                employeeObject2.put("employee", employeeDetails2);
//
//                //Add employees to list
//                JSONArray employeeList = new JSONArray();
//                employeeList.add(employeeObject);
//                employeeList.add(employeeObject2);
//
//                //Write JSON file
//                try (FileWriter file = new FileWriter("employees.json")) {
//                //We can write any JSONArray or JSONObject instance to the file
//                file.write(employeeList.toJSONString());
//                file.flush();
//
//                } catch (IOException e) {
//                e.printStackTrace();
//                }