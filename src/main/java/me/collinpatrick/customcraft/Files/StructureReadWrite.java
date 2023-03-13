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
        try{
            System.out.println("Made it to making the directory");
            System.out.println("/default" + absolutePath);
            File directory = new File("/default" + absolutePath);
            Files.createDirectory(directory.toPath());

            System.out.println("Made it to making the json file");
            System.out.println("/default" + absolutePath + "/" + fileName);
            File file = new File("/default" + absolutePath + "/" + fileName);
            file.createNewFile();


            System.out.println("Made it to the writer");
            FileWriter fr = new FileWriter(file);
            System.out.println(fileName);
            fr.write(writeList.toJSONString());
            fr.flush();
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