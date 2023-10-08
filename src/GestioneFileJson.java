import java.io.File;
import java.io.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GestioneFileJson
{
    //=================================== ATTRIBUTI ===================================//
    //creazione attributo String filepath generico + nome file
    private static final String filePahtName = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "test.json";

    //=================================== COSTRUTTORE DI DEFAULT ===================================//
    public GestioneFileJson() {
    }


    //=================================== FUNZIONE CREAZIONE FILE ===================================//
    // qui si richiama il controlloEsistenzaFile e poi si crea
    public static boolean CreaFile() throws IOException
    {
        File myObj = new File(filePahtName);

        if (myObj.exists())
        {
            System.out.println("File exists: " + myObj.getName());
            return true;
        }
        else
        {
            System.out.println("File not exists, CREATED");
            myObj.createNewFile();
            return false;

        }
    }

    //=================================== FUNZIONE LETTURA FILE ===================================//
    //Creazione fun LeggiFile return di array
    public static ArrayList<Utente> LeggiFile()
    {
        File theFile = new File(filePahtName);
        ArrayList<Utente> caricamentoListaUtenti = new ArrayList<Utente>();

        /*
        *necessaria per prendere i dati dal file json tramite getType
        *e caricare i file da json a arraylist con .fromJson
        *tutto questo per poter comunicare tra java e file Json
         */
        try
        {
            FileReader fileReader = new FileReader(theFile);
            Type type = new TypeToken<ArrayList<Utente>>(){}.getType();
            Gson gson2 = new Gson();
            caricamentoListaUtenti =gson2.fromJson(fileReader, type);
            fileReader.close();
        }  catch (FileNotFoundException e )
        {
            System.err.println("Error in creating fileReadr obj");
        }
        catch (IOException e) {
            System.err.println("Error in closing file");
        }

        // Verifica se l'ArrayList è null.
        if(caricamentoListaUtenti == null)
        {
            // L'ArrayList è null, lo inizializza nuovamente
            caricamentoListaUtenti = new ArrayList<>();
        }
         return caricamentoListaUtenti;
    }

    //=================================== FUNZIONE SCRITTURA FILE ===================================//
    //if file exist then writefile
    public static boolean ScriviFile(ArrayList<Utente> listaDaControllare ) throws IOException
    {
        //nel caso il file non esistestesse per qualsiasi ragione, esso viene ricreato prima di popolarlo
        CreaFile();

        //dichiaro e inizializzo un oggetto temporaneo a cui passo dei valori che poi aggiungero all arraylist
        //Utente uteTemp = new Utente(nome, password, saldo);
        //creo un arralyst  temp che verra popolato dal file json mediante LeggiFile
        //ArrayList<Utente> listaDaCaricare = new ArrayList<Utente>();
        //valorizzo listaDaCaricare con i valori del json
        //listaDaCaricare = LeggiFile();
        //aggiungo utenteTemp all arraylist
        //listaDaCaricare.add();

        Gson gson = new Gson();
        FileWriter file = new FileWriter(filePahtName);
        try
        {
            //sostituisce i valori del file json che ha subito modifiche tramite un arraylist
            file.write(gson.toJson(listaDaControllare));
            return true;
        } catch (IOException e) {
            return false;
        }finally {
            file.close();
        }
    }

    //=================================== FUNZIONE MODIFICA FILE ===================================//
    //creazione fun ModificaFile
    /*private void ModificaFile()
    {
        LeggiFile();

    }*/


}
