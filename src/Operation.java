import java.io.IOException;
import java.util.ArrayList;
//TODO: sistemare la funzione viewOperationJson
public class Operation
{
    //=================================== ATTRIBUTI ===================================//

    //primary key
    private String username;
    private boolean operationType;      //TRUE = entrata , FALSE = uscita
    private double amount;
    private String note;                // NOTE: forse non utile e andra traformato in categoria

    //=================================== COSTRUTTORE ===================================
    public Operation(String username, boolean operationType, double  amount , String note)
    {
        this.username = username;
        this.operationType = operationType;
        this.amount = amount;
        this.note = note;
    }

    //=================================== FUN ADD OPERATION ===================================//
    public static void AddAndSubOperationJson(String username, boolean operationType, double amount, String note) throws IOException
    {
        ArrayList<Operation> operationsListJson = new ArrayList<Operation>();
        ArrayList<User> usersListJson = new ArrayList<User>();
        Operation newOperation = new Operation(username, operationType, amount, note);

        //prelevo l'array list dal GestioneFileJson
        operationsListJson= JsonFileManupulation.ReadOperationsFile();

        //ho bisogno di prelevare anche l'array list di utenti per poter aggiornare il saldo di quell utente specifico
        usersListJson = JsonFileManupulation.ReadUsersFile();

        //aggiunta del nuovo movimento all arraylist temp
        operationsListJson.add(newOperation);

        //cerco la corrispondenza del Nomeutente dentro la listaUtentiTemp
        for (int i = 0; i < usersListJson.size(); i++)
        {
            //trova e controlla username dato dal utente con l'arraylist
            if(usersListJson.get(i).getUsername().equals(username))
            {
                //controllo se entrata o uscita
                if(newOperation.getOperationType()==true)
                {
                    //ENTRATA positiva + quindi dovrò aggiungere i soldi dal saldo del utente
                    usersListJson.get(i).AddToBalance(amount);
                }
                else
                {
                    //USCITA negativa - quindi dovrò rimuovere i soldi dal saldo del utente
                    usersListJson.get(i).SubFromBalance(amount);
                }
            }

        }

        //sovrascrittuare del file json. Passo i cambiamenti fatti e ricaricalo il file
        JsonFileManupulation.WriteUsersFile(usersListJson);
        JsonFileManupulation.WriteOperationsFile(operationsListJson);
    }

    //=================================== FUN VIEW OPERATION ===================================//
    //restituisce semplicemente un array di movimentei del utente specifico
    public static ArrayList<Operation> ViewOperationJson(String username)
    {
        ArrayList<Operation> operationListJson = new ArrayList<Operation>();

        operationListJson = JsonFileManupulation.ReadOperationsFile();

        //poi scorrere tutto l'array e se idUtente è diverso da quello che mi è stato passato va eliminato, potrebbe non funziare quindi fare 2 array
        for (int i = 0; i < operationListJson.size(); i++)
        {
            /*
            if (listaMovimentiTemp.get(i).getNome()!=nome)
            {
                listaMovimentiTemp.remove(i);
            }*/
        }

        //quindi qui avrò rimosso tutti i movimenti che non corrispondono a quello specifico utente e posso ritornarla a Menu
        //ritorno la lista aggiornata e non devo scrivere nulla da nessuna parte
        return operationListJson;
    }

    //=================================== GETTER ===================================//
    public String getUsername() {
        return username;
    }
    public boolean getOperationType() {
        return operationType;
    }
    public double getAmount() {
        return amount;
    }
    public String getNote() {
        return note;
    }

    //=================================== SETTER ===================================//
    public void setUsername(String username) {
        this.username = username;
    }
    public void setOperationType(boolean operationType) {
        this.operationType = operationType;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setNote(String note) {
        this.note = note;
    }
}




