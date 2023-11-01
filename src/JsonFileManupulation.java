import java.io.File;
import java.io.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//TODO throw exception
public class JsonFileManupulation
{
    //=================================== ATTRIBUTI ===================================//
    //creazione attributo String filepath generico + nome file
    private static final String usersFolderAndFile = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "DATI"+ File.separator + "usersList.json" ;
    private static final String operationFolderAndFile = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "DATI"+ File.separator + "operationsList.json";

    //=================================== COSTRUTTORE DI DEFAULT ===================================//
    public JsonFileManupulation()
    {
    }

    //=================================== FUN CREATE USERS FILE ===================================//
    public static boolean FolderAndFileUsersCreation() throws IOException
    {
        File usersFile = new File(usersFolderAndFile);

        if (usersFile.exists())
        {
            System.out.println("File exists: " + usersFile.getName());
            return true;
        }
        else
        {
            usersFile.getParentFile().mkdirs();
            usersFile.createNewFile();
            return false;
        }
    }

    //=================================== FUNZIONE CREATE OPERATIONS FILE ===================================//
    public static boolean FolderAndFileOperationsCreation() throws IOException
    {
        File operationsFile = new File(operationFolderAndFile);
        if (operationsFile.exists())
        {
            System.out.println("File exists: " + operationsFile.getName());
            return true;
        }
        else
        {
            System.out.println("File not exists, CREATED");
            operationsFile.getParentFile().mkdirs();
            operationsFile.createNewFile();

            return false;
        }
    }


    //=================================== FUN READ FILE ===================================//
    public static ArrayList<User> ReadUsersFile()
    {
        File usersReadFile = new File(usersFolderAndFile);
        ArrayList<User> usersListFromJson = new ArrayList<User>();

        /*
        *necessaria per prendere i dati dal file json tramite getType
        *e caricare i file da json a arraylist con .fromJson
        *tutto questo per poter comunicare tra java e file Json
        */

        try
        {
            FileReader fileReader = new FileReader(usersReadFile);
            Type type = new TypeToken<ArrayList<User>>(){}.getType();
            Gson gson2 = new Gson();
            usersListFromJson =gson2.fromJson(fileReader, type);
            fileReader.close();
        }  catch (FileNotFoundException e ) {
            System.err.println("Error in creating fileReadr obj");
        } catch (IOException e) {
            System.err.println("Error in closing file");
        }

        // Verifica se l'ArrayList è null perche non si puo lavorare su arraylist null
        if(usersListFromJson == null)
        {
            // L'ArrayList è null, lo inizializza nuovamente
            usersListFromJson = new ArrayList<>();
        }
         return usersListFromJson;
    }

    //=================================== FUNZIONE READ OPERATION FILE ===================================//
    public static ArrayList<Operation> ReadOperationsFile()
    {
        File operationsReadFile = new File(operationFolderAndFile);
        ArrayList<Operation> operationsListFromJson = new ArrayList<Operation>();

        /*
         *necessaria per prendere i dati dal file json tramite getType
         *e caricare i file da json ad arraylist con .fromJson
         *tutto questo per poter comunicare tra java e file Json
         */
        try
        {
            FileReader fileReader = new FileReader(operationsReadFile);
            Type type = new TypeToken<ArrayList<Operation>>(){}.getType();
            Gson gson2 = new Gson();
            operationsListFromJson =gson2.fromJson(fileReader, type);
            fileReader.close();
        }  catch (FileNotFoundException e ) {
            System.err.println("Error in creating fileReadr obj");
        } catch (IOException e) {
            System.err.println("Error in closing file");
        }

        // Verifica se l'ArrayList è null perche non si puo lavorare su arraylist null
        if(operationsListFromJson == null)
        {
            // L'ArrayList è null, lo inizializza nuovamente
            operationsListFromJson = new ArrayList<>();
        }
        return operationsListFromJson;
    }

    //=================================== FUNZIONE WRITE FILE UTENTi ===================================//
    public static boolean WriteUsersFile(ArrayList<User> uploadUsersList) throws IOException
    {
        //nel caso il file non esistestesse per qualsiasi ragione, esso viene ricreato prima di popolarlo
        FolderAndFileUsersCreation();

        Gson gson = new Gson();
        FileWriter file = new FileWriter(usersFolderAndFile);
        try
        {
            //sostituisce i valori del file json che ha subito modifiche tramite un arraylist
            file.write(gson.toJson(uploadUsersList));
            return true;
        } catch (IOException e){
            return false;
        }finally{
            file.close();
        }
    }

    //=================================== FUNZIONE SCRITTURA FILE MOVIMENTI ===================================//
    public static boolean WriteOperationsFile(ArrayList<Operation> uploadOperationsList ) throws IOException
    {
        //nel caso il file non esistestesse per qualsiasi ragione, esso viene ricreato prima di popolarlo
        FolderAndFileOperationsCreation();

        Gson gson = new Gson();
        FileWriter file = new FileWriter(operationFolderAndFile);
        try
        {
            //sostituisce i valori del file json che ha subito modifiche tramite un arraylist
            file.write(gson.toJson(uploadOperationsList));
            return true;
        } catch (IOException e){
            return false;
        }finally {
            file.close();
        }
    }

}
