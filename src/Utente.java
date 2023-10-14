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


    //=================================== COSTRUTTORE DI DEFAULT ===================================//
    public Utente(String nome, String password , Double saldo)
    {
        this.nome=nome;
        this.password=password;
        this.saldo=saldo;

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



}
