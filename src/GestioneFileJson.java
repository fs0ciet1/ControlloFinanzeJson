import java.io.File;
import java.io.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/*                          CLASSE GESTIONE FILE JSON
 *   ATTRIBUTI:
 *       - path fileJson di utenti
 *
 *
 *   FUNZIONI:
 *       - creazione file
 *       - lettura file
 *       - scrittura file
 *
 *
 */
public class GestioneFileJson
{
    //=================================== ATTRIBUTI ===================================//
    //creazione attributo String filepath generico + nome file
    private static final String cartellaPiuFileUtenti = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "DATI"+ File.separator + "listaUtenti.json" ;
    private static final String cartellaPiuFileMovimenti = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "DATI"+ File.separator + "listaMovimenti.json";
    //=================================== COSTRUTTORE DI DEFAULT ===================================//
    public GestioneFileJson() {
    }



    //=================================== FUNZIONE CREAZIONE FILE UTENTI===================================//
    // qui si richiama il controlloEsistenzaFile e poi si crea
    public static boolean CreazioneFilePiuCartellaUtenti() throws IOException
    {
        File utenti = new File(cartellaPiuFileUtenti);


        if (utenti.exists())
        {
            System.out.println("File exists: " + utenti.getName());
            return true;
        }
        else
        {
            //System.out.println("File not exists, CREATED");
            utenti.getParentFile().mkdirs();
            utenti.createNewFile();
            return false;

        }
    }

    //=================================== FUNZIONE CREAZIONE FILE MOVIMENTI===================================//
    //creazione fun CreazioneFileMovimenti
    public static boolean CreaFilePiuCartellaMovimenti() throws IOException {
        File movimenti = new File(cartellaPiuFileMovimenti);
        if (movimenti.exists())
        {
            System.out.println("File exists: " + movimenti.getName());
            return true;
        }
        else
        {
            System.out.println("File not exists, CREATED");
            movimenti.getParentFile().mkdirs();
            movimenti.createNewFile();

            return false;

        }
    }


    //=================================== FUNZIONE LETTURA FILE UTENTI===================================//
    //Creazione fun LeggiFileUtenti return di array
    public static ArrayList<Utente> LeggiFileUtenti()
    {
        File theFile = new File(cartellaPiuFileUtenti);
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

    //=================================== FUNZIONE LETTURA FILE MOVIMENTI ===================================//
    //Creazione fun LeggiFileMovimenti return di array
    public static ArrayList<Movimento> LeggiFileMovimenti()
    {
        File theFile = new File(cartellaPiuFileMovimenti);
        ArrayList<Movimento> caricamentoListaMovimenti = new ArrayList<Movimento>();

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
            caricamentoListaMovimenti =gson2.fromJson(fileReader, type);
            fileReader.close();
        }  catch (FileNotFoundException e )
        {
            System.err.println("Error in creating fileReadr obj");
        }
        catch (IOException e) {
            System.err.println("Error in closing file");
        }

        // Verifica se l'ArrayList è null.
        if(caricamentoListaMovimenti == null)
        {
            // L'ArrayList è null, lo inizializza nuovamente
            caricamentoListaMovimenti = new ArrayList<>();
        }
        return caricamentoListaMovimenti;
    }

    //=================================== FUNZIONE SCRITTURA FILE UTENTi ===================================//
    //if file exist then writefile
    public static boolean ScriviFileUtenti(ArrayList<Utente> listaDaControllareU ) throws IOException
    {
        //nel caso il file non esistestesse per qualsiasi ragione, esso viene ricreato prima di popolarlo
        CreazioneFilePiuCartellaUtenti();

        Gson gson = new Gson();
        FileWriter file = new FileWriter(cartellaPiuFileUtenti);
        try
        {
            //sostituisce i valori del file json che ha subito modifiche tramite un arraylist
            file.write(gson.toJson(listaDaControllareU));
            return true;
        } catch (IOException e) {
            return false;
        }finally {
            file.close();
        }
    }

    //=================================== FUNZIONE SCRITTURA FILE MOVIMENTI ===================================//
    //public static boolean ScriviFileMovimenti(){}
    public static boolean ScriviFileMovimenti(ArrayList<Movimento> listaDaControllareM ) throws IOException
    {
        //nel caso il file non esistestesse per qualsiasi ragione, esso viene ricreato prima di popolarlo
        CreaFilePiuCartellaMovimenti();

        Gson gson = new Gson();
        FileWriter file = new FileWriter(cartellaPiuFileMovimenti);
        try
        {
            //sostituisce i valori del file json che ha subito modifiche tramite un arraylist
            file.write(gson.toJson(listaDaControllareM));
            return true;
        } catch (IOException e) {
            return false;
        }finally {
            file.close();
        }
    }

}
