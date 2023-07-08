import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;



public class Menu
{
    //=================================== ATTRIBUTI ===================================//

    //creo ma non inizializzo il mio arraylist di utenti
    private ArrayList<Utente> listaUtenti;

    //=================================== COSTRUTTORE DI DEFAULT ===================================//
    public  Menu()
    {
        //inizializzo un arraylist di tipo Utente perchè inserirò una lista di utenti
        this.listaUtenti = new ArrayList<Utente>();

        //inizializzo un utente di prova che poi inserisco nel mio arraylist
        Utente utenteProva = new  Utente ("pippo", "123", 500.00);

        //aggiungio un utente di prova dentro il mio arraylist
        listaUtenti.add(utenteProva);
        loginRegistrazioni();
    }
    //creazione del menu
    public void loginRegistrazioni()
    {

        boolean controlloDiRitorno = false;
        while(true)
        {
            System.out.println("Sei già registrato [S/N]");

            Scanner inserimento = new Scanner(System.in);
            String sceltaSN = inserimento.nextLine();


            if(sceltaSN.equalsIgnoreCase("s"))
            {
                while(controlloDiRitorno==false)
                {
                    System.out.println("Inserisci nome e password");

                    Scanner inserimentoCredenziali = new Scanner(System.in);
                    String inserisciNome = inserimentoCredenziali.nextLine();
                    String inserisciPsw =  inserimentoCredenziali.nextLine();

                    //controllo che la funzione passata dalla classe Utente restituisca effettivamente true perchè il login vada a buon fine
                    if(Utente.login(inserisciNome, inserisciPsw, listaUtenti) == true)
                    {
                        System.out.println("LOGIN EFFETTUATO CON SUCCESSO");
                        controlloDiRitorno=true;
                        //richiamo la funzione saldo per farmi stampare il saldo attuale dell'utente (inserito al momento della registrazione nella classe Utente)
                        stampaSaldo(inserisciNome);
                    }
                    else
                    {
                        System.out.println("DATI NON VALIDI");

                    }
                }

            }
            else if (sceltaSN.equalsIgnoreCase("n"))
            {
                //registrazione
                while(controlloDiRitorno==false)
                {
                    Scanner inserimentoCredenziali = new Scanner(System.in);
                    System.out.println("Inserisci nome:");
                    String inserisciNome = inserimentoCredenziali.nextLine();

                    System.out.println("Inserisci password:");
                    String inserisciPsw =  inserimentoCredenziali.nextLine();

                    System.out.println("Inserisci saldo:");
                    double inserisciSaldo = inserimentoCredenziali.nextDouble();

                    //controllo che la funzione passata dalla classe Utente restituisca effettivamente true perchè la registrazione vada a buon fine
                    if(Utente.registrazione(inserisciNome, inserisciPsw, inserisciSaldo, listaUtenti) == true)
                    {
                        System.out.println("REGISTRAZIONE EFFETTUATA");
                        controlloDiRitorno=true;

                        stampaSaldo(inserisciNome);

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
            break;
        }

    }
    public void stampaSaldo(String inserisciNome)
    {
        //lau mi restitusisce la funzione saldo ed io gli passo il nome affinchè lui mi restituisca il saldo corrispondente all'utente giusto
        Utente ute = new Utente("","",0.0);
        System.out.println("Il tuo saldo è:" + ute.mostraSaldo(inserisciNome, listaUtenti));
        stampaMenu();
    }
    public void stampaMenu()
    {
        Scanner scelta = new Scanner(System.in);
        boolean controlloMenu=false;

        while(controlloMenu==false)
        {
            System.out.println("-----MENU-----");
            System.out.println("Cosa vuoi fare?");
            System.out.println("A: Inserisci le tue entrate");
            System.out.println("B: Inserisci le tue uscite");
            System.out.println("C: Esci");


            String sceltaOpzioni = scelta.nextLine();

            if (sceltaOpzioni.equalsIgnoreCase("a"))
            {
                System.out.println("Inserisci entrate:");
                //richiamo funzione inserimentoEntrate lau
            }
            else if (sceltaOpzioni.equalsIgnoreCase("b"))
            {
                System.out.println("Inscerisci le uscite:");
                //richiamo funzione inserimentoUsciote lau
            }
            else if (sceltaOpzioni.equalsIgnoreCase("c"))
            {
                System.out.println("Log Out effettuato!");
                controlloMenu=true;

            }
            else
            {
                System.out.println("SCELTA ERRATA!");
            }
        }
    }
}
