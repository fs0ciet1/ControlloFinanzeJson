import java.io.IOException;
import java.util.ArrayList;

/*                          CLASSE UTENTE
*   ATTRIBUTI:
*       - nome
*       - passoword
*       - saldo
*       - movimenti
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
    private static ArrayList<Movimento> movimenti;             //creo ma non inizializzo il mio arraylist di movimenti

    //=================================== COSTRUTTORE DI DEFAULT ===================================//
    public Utente(String nome, String password , Double saldo)
    {
        this.nome=nome;
        this.password=password;
        this.saldo=saldo;
        this.movimenti = new ArrayList<Movimento>();    //inizializzo un arraylist di movimenti vuoto
    }

    //=================================== FUNZIONE LOGIN ===================================//
    //prendo in input i valori nome, passw e cerco nell'arrayList se trovo un utente corrispondente
    //quindi do in output true se trovo questa corrispondenza, false altrimenti

    public static Boolean login(String nomeInserito,String passwordInserita)
    {
        ArrayList<Utente> listaDaControllare = new ArrayList<Utente>();
        //valorizzo listaDaCaricare con i valori del json
        listaDaControllare = GestioneFileJson.LeggiFile();
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
        listaDaControllare = GestioneFileJson.LeggiFile();

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
        GestioneFileJson.ScriviFile(listaDaControllare);
        return true;
    }
    //=================================== FUNZIONE MOSTRA SALDO ===================================//
    public static String mostraSaldo(String nomeInserito)
    {
        ArrayList<Utente> listaDaControllare = new ArrayList<Utente>();
        //valorizzo listaDaCaricare con i valori del json
        listaDaControllare = GestioneFileJson.LeggiFile();

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
        ArrayList<Utente> listaDaControllare = new ArrayList<Utente>();
        //valorizzo listaDaCaricare con i valori del json
        listaDaControllare = GestioneFileJson.LeggiFile();

        for (int i = 0; i < listaDaControllare.size(); i++)
        {
            if(nomeInserito.equals(listaDaControllare.get(i).nome))
            {
                Movimento movimentoTemp = new Movimento();
                //entrata
                if(tipoMovimento== true)
                {
                    //compongo annucio
                    movimentoTemp.setTipoMovimento(true);
                    movimentoTemp.setQuantita(quantita);
                    movimentoTemp.setNote(note);
                    movimenti.add(movimentoTemp);

                    //devo aggiungere questa somma di quanita al saldo e poi restituire il saldo in stringa
                    listaDaControllare.get(i).setSaldo(listaDaControllare.get(i).saldo+quantita);
                    GestioneFileJson.ScriviFile(listaDaControllare);
                    return listaDaControllare.get(i).getSaldo().toString();

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
                    listaDaControllare.get(i).setSaldo(listaDaControllare.get(i).saldo-quantita);
                    GestioneFileJson.ScriviFile(listaDaControllare);
                    return listaDaControllare.get(i).getSaldo().toString();
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
