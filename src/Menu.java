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


            if(sceltaSN.equals("S")|| sceltaSN.equals("s"))
            {
                while(controlloDiRitorno==false)
                {
                    System.out.println("Inserisci nome e password");

                    Scanner inserimentoCredenziali = new Scanner(System.in);
                    String inserisciNome = inserimentoCredenziali.nextLine();
                    String inserisciPsw =  inserimentoCredenziali.nextLine();

                    //controllo che le funzione passate dalla classe Utente restituiscano effettivamente true per poter accedere
                    if(Utente.login(inserisciNome, inserisciPsw, listaUtenti) == true)
                    {
                        System.out.println("LOGIN EFFETTUATO CON SUCCESSO");
                        controlloDiRitorno=true;
                    }
                    else
                    {
                        System.out.println("DATI NON VALIDI");

                    }
                }
                //richiamo la classe saldo per farmi stampare il saldo attuale dell'utente (inserito al momento della registrazione nella classe Utente)
                mostraSaldo();

            }
            else if (sceltaSN.equals("N") || sceltaSN.equals("n"))
            {
                //registrazione
            }
            else
            {
                System.out.println( "SCELTA NON ESISTENTE");
            }
        }




    }
    public void mostraSaldo()
    {
        System.out.println("Il tuo saldo è:" + "funzioneDiLau");
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

            if (sceltaOpzioni.equals("A")|| sceltaOpzioni.equals("a"))
            {
                System.out.println("Inserisci entrate:");
                //richiamo funzione inserimentoEntrate lau
            }
            else if (sceltaOpzioni.equals("B")||sceltaOpzioni.equals("b"))
            {
                System.out.println("Inscerisci le uscite:");
                //richiamo funzione inserimentoUsciote lau
            }
            else if (sceltaOpzioni.equals("C")||sceltaOpzioni.equals("C"))
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
