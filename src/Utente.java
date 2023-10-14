import java.io.IOException;
import java.util.ArrayList;

/*                          CLASSE UTENTE
*   ATTRIBUTI:
*       - nome
*       - passoword
*       - saldo
*
*
*   FUNZIONI:
*       - login
*       - registrazione
*
*
*/
public class Utente
{

    //=================================== ATTRIBUTI ===================================//
    private String nome;
    private String password;
    private Double saldo;
    private static ArrayList<Movimento> movimenti;             //non mi servira qui ma nelle varie funzioni

    //=================================== COSTRUTTORE DI DEFAULT ===================================//
    public Utente(String nome, String password , Double saldo)
    {
        this.nome=nome;
        this.password=password;
        this.saldo=saldo;
        this.movimenti = new ArrayList<Movimento>();    //si potra eliminare
    }

    //=================================== FUNZIONE LOGIN ===================================//
    //prendo in input i valori nome, passw e cerco nell'arrayList se trovo un utente corrispondente
    //quindi do in output true se trovo questa corrispondenza, false altrimenti

    public static Boolean login(String nomeInserito,String passwordInserita)
    {
        ArrayList<Utente> listaDaControllare = new ArrayList<Utente>();
        //valorizzo listaDaCaricare con i valori del json
        listaDaControllare = GestioneFileJson.LeggiFileUtenti();
        for (int i = 0; i <listaDaControllare.size(); i++)
        {
            if(nomeInserito.equals(listaDaControllare.get(i).nome) && passwordInserita.equals(listaDaControllare.get(i).password))
            {
                return true;        //trovato
            }
        }
        return false;
    }
    //=================================== FUNZIONE REGISTRAZIONE ===================================//
    //se la registrazione va a buon fine aggiorno l'arraylist altrimenti restituisco false e non va a buon fine
    public static Boolean registrazione(String nomeInserito,String passwordInserito,Double saldoInserito) throws IOException {
        ArrayList<Utente> listaDaControllare = new ArrayList<Utente>();
        //valorizzo listaDaCaricare con i valori del json
        listaDaControllare = GestioneFileJson.LeggiFileUtenti();

        //controllo se esiste gia un utente con lo stesso nome
        for (int i = 0; i < listaDaControllare.size() ; i++)
        {
            if(nomeInserito.equals(listaDaControllare.get(i).nome))
            {
                return false;
            }
        }
        //se non trova un untente uguale, quindi già registrato può aggiungerlo e registralo all array
        Utente nuovoUtente = new Utente(nomeInserito, passwordInserito,saldoInserito);
        listaDaControllare.add(nuovoUtente);
        GestioneFileJson.ScriviFileUtenti(listaDaControllare);
        return true;
    }
    //=================================== FUNZIONE MOSTRA SALDO ===================================//
    public static String mostraSaldo(String nomeInserito)
    {
        ArrayList<Utente> listaDaControllare = new ArrayList<Utente>();
        //valorizzo listaDaControllare con i valori del json
        listaDaControllare = GestioneFileJson.LeggiFileUtenti();

        for (int i = 0; i < listaDaControllare.size(); i++)
        {
                if (nomeInserito.equals(listaDaControllare.get(i).nome))
                {
                    return listaDaControllare.get(i).saldo.toString();
                }
        }
        return "ERRORE, utente non trovato nella funzione mostra saldo";
    }

    //=================================== FUNZIONE INSERIMENTO ENTRATE ===================================//
    public static String inserimentoEntrate(String nomeInserito, Boolean tipoMovimento, Double quantita ,String note) throws IOException {
        ArrayList<Utente> listaDaControllareUtenti = new ArrayList<Utente>();
        //valorizzo listaDaCaricare con i valori del json
        listaDaControllareUtenti = GestioneFileJson.LeggiFileUtenti();

        for (int i = 0; i < listaDaControllareUtenti.size(); i++)
        {
            if(nomeInserito.equals(listaDaControllareUtenti.get(i).nome))
            {
                Movimento movimentoTemp = new Movimento();
                //entrata
                if(tipoMovimento== true)
                {
                    //aggiungo movimenti
                    movimentoTemp.setTipoMovimento(true);
                    movimentoTemp.setQuantita(quantita);
                    movimentoTemp.setNote(note);
                    movimenti.add(movimentoTemp); // probabilmente mi servira un nuovo oggetto interno nella fun

                    //devo aggiungere questa somma di quanita al saldo e poi restituire il saldo in stringa
                    listaDaControllareUtenti.get(i).setSaldo(listaDaControllareUtenti.get(i).saldo+quantita);
                    GestioneFileJson.ScriviFileUtenti(listaDaControllareUtenti);
                    return listaDaControllareUtenti.get(i).getSaldo().toString();

                }
                //ucita
                else
                {
                    //compongo annucio
                    movimentoTemp.setTipoMovimento(false);
                    movimentoTemp.setQuantita(quantita);
                    movimentoTemp.setNote(note);
                    movimenti.add(movimentoTemp);
                    //devo sottrarre questa somma di quanita al saldo e poi restituire il saldo in stringa
                    listaDaControllareUtenti.get(i).setSaldo(listaDaControllareUtenti.get(i).saldo-quantita);
                    GestioneFileJson.ScriviFileUtenti(listaDaControllareUtenti);
                    return listaDaControllareUtenti.get(i).getSaldo().toString();
                }
            }
        }
        return "ERRORE, utente non trovato in inserimentoEntrate";
    }
    //=================================== GETTER ===================================//
    public String getNome()
    {
        return nome;
    }
    public String getPassword()
    {
        return password;
    }
    public Double getSaldo()
    {
        return saldo;
    }
    public ArrayList<Movimento> getMovimenti()
    {
        return movimenti;
    }

    //=================================== SETTER ===================================//
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setMovimenti(ArrayList<Movimento> movimenti) {
        this.movimenti = movimenti;
    }

}
