package me.collinpatrick.customcraft.Files;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;

import me.collinpatrick.customcraft.Models.Coordinates;
import org.bukkit.ChatColor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.bukkit.command.CommandSender;
import org.json.simple.*;


public class CoordinatesReadWrite {
    private final String coordsFileName = "CoordinatesList.json";
    private JSONArray coordArray = new JSONArray();
    private JSONParser jsonParser = new JSONParser();

    private ArrayList<Coordinates> CoordinatesArray = new ArrayList<Coordinates>();
    private boolean newWrite = true;
    public CoordinatesReadWrite() {
    }

    public void CoordinatesWriteOut(String absolutePath, String[] args, CommandSender sender) {
        //args[0] = xPos
        //args[1] = yPos
        //args[2] = zPos
        //args[3] = nameOfCoords
        if(args.length > 4) {
            sender.sendMessage("Incorrect parameters, usage is xPos yPos zPos Name");
        }
        Coordinates coordinate = new Coordinates(Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), args[3], sender.getName(), new Date());

        try{
            //check that the coords file is already there
            File coordsFile = new File(absolutePath + "/" + coordsFileName);
            FileReader fr;
            //Read in anything that might already be in the array
            coordsFile.createNewFile();
            if(coordsFile.length() > 0) {
                fr = new FileReader(coordsFile);
                coordArray = (JSONArray) jsonParser.parse(fr);
                fr.close();
            }
            //Build the new coordinate object
            JSONObject coordDetails = new JSONObject();
            coordDetails.put("xPos", coordinate.getxPos());
            coordDetails.put("yPos",coordinate.getyPos());
            coordDetails.put("zPos",coordinate.getzPos());
            coordDetails.put("NameOfCoords",coordinate.getNameOfCoords());
            coordDetails.put("NameOfCreator",coordinate.getNameOfCreator());
            coordDetails.put("DateCreated",coordinate.getDateCreated());
            JSONObject coordObject = new JSONObject();
            coordObject.put("coordinate", coordDetails);
            coordArray.add(coordObject);



            FileWriter fw = new FileWriter(coordsFile);
            fw.write(coordArray.toJSONString());
            fw.flush();
            fw.close();
            newWrite = true;

        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch (ParseException pe) {
            pe.printStackTrace();
        }


    }

    public void CoordinateReadIn(String absolutePath, String[] args, CommandSender sender) {
        if (this.newWrite) {
            try {
                File coordsFile = new File(absolutePath + "/" + coordsFileName);
                //Make sure the file is there even if empty
                coordsFile.createNewFile();
                if(coordsFile.length() > 0) {
                    FileReader fr = new FileReader(coordsFile);
                    //Read in the array
                    coordArray = (JSONArray) jsonParser.parse(fr);
                    coordArray.forEach(x -> parseCoords((JSONObject) x));
                    String messageOut = buildMessage();
                    sender.sendMessage(messageOut);
                    this.newWrite = false;
                }
                else {
                    sender.sendMessage("There are no coordinates currently saved on this server");
                    this.newWrite = false;
                }

            }
            catch(IOException e) {
                e.printStackTrace();
            }
            catch(ParseException pe) {
                pe.printStackTrace();
            }
        }
        else {
            buildMessage();
        }

    }
    public void parseCoords(JSONObject j) {
        JSONObject coordinateObject = (JSONObject) j.get("coordinate");
        Coordinates c = new Coordinates();
        c.setxPos( (Double) coordinateObject.get("xPos"));
        c.setyPos( (Double) coordinateObject.get("yPos"));
        c.setzPos( (Double) coordinateObject.get("zPos"));
        c.setName( (String) coordinateObject.get("NameOfCreator"));
        c.setNameOfCoords( (String) coordinateObject.get("NameOfCoords"));
        c.setDateCreated( (String) coordinateObject.get("DateCreated"));

        CoordinatesArray.add(c);

    }

    public String buildMessage() {
        String message = "The coordinates currently saved on the server are: \n";
        if(CoordinatesArray.size() == 0) {
            return "There are no coordinates currently saved on this server";
        }
        for(Coordinates c : CoordinatesArray) {
            message += (ChatColor.DARK_PURPLE + "[" + c.getNameOfCoords() + "]"  + " " + "[" + c.getDateCreated() + "]" + " " + "[" + c.getNameOfCreator() + "]") + " " + (ChatColor.AQUA + Double.toString(c.getxPos()) + " " + Double.toString(c.getyPos()) + " " + Double.toString(c.getzPos()) + "\n");
        }
        CoordinatesArray.clear();
        return message;
    }


}
