import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class Menu
{
    //=================================== ATTRIBUTI ===================================//


    //=================================== COSTRUTTORE DI DEFAULT ===================================//
    public  Menu() throws IOException
    {
        //prima di far partire il menu creo cartella + file sia di utenti che movimenti
        JsonFileManupulation.FolderAndFileUsersCreation();
        JsonFileManupulation.FolderAndFileOperationsCreation();
        LoginAndRegistration();
    }
    //creazione del menu
    public void LoginAndRegistration() throws IOException
    {
        boolean progressControl = false;
        while(true)
        {
            System.out.println("Sei già registrato [Y/N]");

            Scanner input = new Scanner(System.in);
            String YNChoice = input.nextLine();

            //login
            if(YNChoice.equalsIgnoreCase("y"))
            {
                //prende i dati utente e cicla finche non sono corretti
                while(progressControl==false)
                {
                    System.out.println("Inserisci nome e password");

                    Scanner insertCredentials = new Scanner(System.in);
                    String insertUsername = insertCredentials.nextLine();
                    String insertPsw =  insertCredentials.nextLine();

                    //controllo che la funzione passata dalla classe Utente restituisca effettivamente true perchè il login vada a buon fine
                    if(User.LoginJson(insertUsername, insertPsw) == true)
                    {
                        System.out.println("LOGIN EFFETTUATO CON SUCCESSO");
                        progressControl=true;
                        PrintOperations(insertUsername);
                        //richiamo la funzione saldo per farmi stampare il saldo attuale dell'utente (inserito al momento della registrazione nella classe Utente)
                        PrintBalance(insertUsername);

                    }
                    else
                    {
                        System.out.println("DATI NON VALIDI");

                    }
                }

            }

            //registrazione
            else if (YNChoice.equalsIgnoreCase("n"))
            {
                while(progressControl==false)
                {
                    Scanner inputCredentials = new Scanner(System.in);
                    System.out.println("Inserisci nome:");
                    String inputUsername = inputCredentials.nextLine();

                    System.out.println("Inserisci password:");
                    String inputPsw =  inputCredentials.nextLine();

                    System.out.println("Inserisci saldo:");
                    double inputBalance = inputCredentials.nextDouble();

                    //controllo che la funzione passata dalla classe Utente restituisca effettivamente true perchè la registrazione vada a buon fine
                    if(User.RegistrationJson(inputUsername, inputPsw, inputBalance) == true)
                    {
                        System.out.println("REGISTRAZIONE EFFETTUATA");
                        progressControl=true;

                        PrintBalance(inputUsername);

                    }
                    else
                    {
                        System.out.println("UTENTE GIÀ ESISTENTE, REINSERISCI CREDENZIALI:");
                    }

                }
            }
            else
            {
                System.out.println( "SCELTA NON ESISTENTE");
            }
            progressControl=false;
        }

    }
    public void PrintBalance(String inputUsername) throws IOException
    {
        System.out.println("Il tuo saldo è:" + User.ViewBalanceJson(inputUsername));
        PrintMenu(inputUsername);
    }
    public void PrintOperations (String inputUsername)
    {
        ArrayList<Operation> operationsUserList = new ArrayList<Operation>();
        operationsUserList = Operation.ViewOperationJson(inputUsername);
        System.out.println("I tuoi movimenti sono:");
        for (int i = 0; i < operationsUserList.size(); i++)
        {
            if (operationsUserList.get(i).getOperationType() == true)
                System.out.println("> Entrate: " + operationsUserList.get(i).getAmount() + " Note: " + operationsUserList.get(i).getNote());
            else
                System.out.println("> Uscite: " + operationsUserList.get(i).getAmount() + " Note: " + operationsUserList.get(i).getNote());

        }
    }
    public void PrintMenu(String inputUsername) throws IOException
    {
        Scanner input = new Scanner(System.in);
        boolean menuController=false;

        while(menuController==false)
        {
            System.out.println("-----MENU-----");
            System.out.println("Cosa vuoi fare?");
            System.out.println("A: Inserisci le tue entrate");
            System.out.println("B: Inserisci le tue uscite");
            System.out.println("C: Esci");


            String inputChoice = input.nextLine();

            //entrate
            if (inputChoice.equalsIgnoreCase("a"))
            {
                Scanner inputAmount = new Scanner(System.in);
                Scanner inputNote = new Scanner(System.in);

                System.out.println("Inserisci entrate:");
                double amount = inputAmount.nextDouble();

                System.out.println("Inserisci note:");
                String note = inputNote.nextLine();

                //richiamo funzione inputAmount lau
                Operation.AddAndSubOperationJson(inputUsername, true, amount, note);
            }
            //uscite
            else if (inputChoice.equalsIgnoreCase("b"))
            {
                Scanner inputAmount = new Scanner(System.in);
                Scanner inputNote = new Scanner(System.in);

                System.out.println("Inserisci uscite:");
                double amount = inputAmount.nextDouble();

                System.out.println("Inserisci note:");
                String note = inputNote.nextLine();

                //richiamo funzione inserimentoEntrate lau
                Operation.AddAndSubOperationJson(inputUsername, false, amount, note);
            }
            //esc
            else if (inputChoice.equalsIgnoreCase("c"))
            {
                System.out.println("Log Out effettuato!");
                menuController=true;

            }
            else
            {
                System.out.println("SCELTA ERRATA!");
            }
        }
    }
}
