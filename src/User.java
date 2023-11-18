import java.io.IOException;
import java.util.ArrayList;

public class User
{
    //=================================== ATTRIBUTI ===================================//
    private String username;
    private String password;
    private Double balance;

    //=================================== COSTRUTTORE ===================================//
    public User(String username, String password , Double balance)
    {
        this.username =username;
        this.password=password;
        this.balance =balance;
    }

    //=================================== FUN LOGIN JSON ===================================//
    public static Boolean LoginJson(String inputUsername, String inputPassword)
    {
        ArrayList<User> usersListJson = new ArrayList<User>();

        //valorizzo usersListJson con i valori del json per controllare se già presete o no
        usersListJson = JsonFileManupulation.ReadUsersFile();

        for (int i = 0; i <usersListJson.size(); i++)
        {
            if(inputUsername.equals(usersListJson.get(i).username) && inputPassword.equals(usersListJson.get(i).password))
            {
                //untente idoneo al login
                return true;
            }
        }
        //utente non esistente
        return false;
    }
    //=================================== FUN REGISTRAZIONE JSON ===================================//
    public static Boolean RegistrationJson(String inputUsername, String inputPassword, Double inputBalance) throws IOException
    {
        ArrayList<User> usersListJson = new ArrayList<User>();

        //valorizzo usersListJson con i valori del json per controllare se già presete o no
        usersListJson = JsonFileManupulation.ReadUsersFile();

        //controllo se esiste gia un utente con lo stesso nome
        for (int i = 0; i < usersListJson.size() ; i++)
        {
            if(inputUsername.equals(usersListJson.get(i).username))
            {
                return false;
            }
        }

        //se l'untente NON E' registrato viene inserito nel Json
        User newUserRegistration = new User(inputUsername, inputPassword,inputBalance);
        usersListJson.add(newUserRegistration);
        JsonFileManupulation.WriteUsersFile(usersListJson);

        return true;
    }

    //=================================== FUN MOSTRA SALDO JSON ===================================//
    public static String ViewBalanceJson(String inputUsername)
    {
        ArrayList<User> usersListJson = new ArrayList<User>();

        //valorizzo usersListJson con i valori del json
        usersListJson = JsonFileManupulation.ReadUsersFile();

        //cerco l'utente corrispondente
        for (int i = 0; i < usersListJson.size(); i++)
        {
                if (inputUsername.equals(usersListJson.get(i).username))
                {
                    return usersListJson.get(i).balance.toString();
                }
        }
        return "ERRORE, utente non trovato nella funzione mostra saldo";
    }

    //=================================== FUN AGGIUNGI SALDO ===================================//
    public void AddToBalance(double amount)
    {
        balance += amount;
    }

    //=================================== FUN SOTTRAI SALDO ===================================//
    public void SubFromBalance(double amount)
    {
        balance -= amount;
    }

    //=================================== GETTER ===================================//
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public Double getBalance()
    {
        return balance;
    }

    //=================================== SETTER ===================================//
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
