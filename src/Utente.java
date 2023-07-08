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
    private ArrayList<Movimento> movimenti;             //creo ma non inizializzo il mio arraylist di movimenti

    //=================================== COSTRUTTORE DI DEFAULT ===================================//
    public Utente(String nome, String password , Double saldo)
    {
        this.nome=nome;
        this.password=password;
        this.saldo=saldo;
        this.movimenti = new ArrayList<Movimento>();    //inizializzo un arraylist di movimenti vuoto
    }

    //=================================== FUNZIONE LOGIN ===================================//
    //prendo in input i valori nome, passw e la lista di utenti e cerco nell'arrayList se trovo un utente corrispondente
    //quindi do in output true se trovo questa corrispondenza, false altrimenti

    public static Boolean login(String nomeInserito,String passwordInserita, ArrayList<Utente> listaUtenti)
    {
        for (int i = 0; i <listaUtenti.size(); i++)
        {
            if(nomeInserito.equals(listaUtenti.get(i).nome) && passwordInserita.equals(listaUtenti.get(i).password))
            {
                return true;        //trovato
            }
        }
        return false;
    }
    //=================================== FUNZIONE REGISTRAZIONE ===================================//
    //se la registrazione va a buon fine aggiorno l'arraylist, divestamente restituisco false e non va a buon fine
    public static Boolean registrazione(String nomeInserito,String passwordInserito,Double saldoInserito,ArrayList<Utente> listaUtenti)
    {
        for (int i = 0; i < listaUtenti.size() ; i++)
        {
            if(nomeInserito.equals(listaUtenti.get(i).nome))
            {
                return false;
            }
        }
        //quindi se non trova un untente uguale, quindi già registrato può aggiungerlo e registralo all array
        Utente nuovoUtente = new Utente(nomeInserito, passwordInserito,saldoInserito);
        listaUtenti.add(nuovoUtente);
        return true;
    }
    //=================================== FUNZIONE MOSTRA SALDO ===================================//
    public static String mostraSaldo(String nomeInserito, ArrayList<Utente> listaUtenti)
    {
        for (int i = 0; i < listaUtenti.size(); i++)
        {
                if (nomeInserito.equals(listaUtenti.get(i).nome))
                {
                    return listaUtenti.get(i).saldo.toString();
                }
        }
        return "ERRORE, utente non trovato nella funzione mostra saldo";
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
