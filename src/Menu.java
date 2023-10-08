import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;



public class Menu
{
    //=================================== ATTRIBUTI ===================================//


    //=================================== COSTRUTTORE DI DEFAULT ===================================//
    public  Menu() throws IOException
    {
        loginRegistrazioni();
    }
    //creazione del menu
    public void loginRegistrazioni() throws IOException {

        boolean controlloDiRitorno = false;
        while(true)
        {
            System.out.println("Sei già registrato [S/N]");

            Scanner inserimento = new Scanner(System.in);
            String sceltaSN = inserimento.nextLine();

            //login
            if(sceltaSN.equalsIgnoreCase("s"))
            {
                while(controlloDiRitorno==false)
                {
                    System.out.println("Inserisci nome e password");

                    Scanner inserimentoCredenziali = new Scanner(System.in);
                    String inserisciNome = inserimentoCredenziali.nextLine();
                    String inserisciPsw =  inserimentoCredenziali.nextLine();

                    //controllo che la funzione passata dalla classe Utente restituisca effettivamente true perchè il login vada a buon fine
                    if(Utente.login(inserisciNome, inserisciPsw) == true)
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

            //registrazione
            else if (sceltaSN.equalsIgnoreCase("n"))
            {

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
                    if(Utente.registrazione(inserisciNome, inserisciPsw, inserisciSaldo) == true)
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
            controlloDiRitorno=false;
        }

    }
    public void stampaSaldo(String inserisciNome) throws IOException {
        //lau mi restitusisce la funzione saldo ed io gli passo il nome affinchè lui mi restituisca il saldo corrispondente all'utente giusto
        Utente ute = new Utente("","",0.0);
        System.out.println("Il tuo saldo è:" + ute.mostraSaldo(inserisciNome));
        stampaMenu(inserisciNome);


    }
    public void stampaMenu(String inserisciNome) throws IOException {
        Scanner scelta = new Scanner(System.in);
        boolean controlloMenu=false;
        //Utente ute = new Utente("","",0.0);
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
                Scanner inserimentoEntrate = new Scanner(System.in);
                double quantita = inserimentoEntrate.nextDouble();
                String note = inserimentoEntrate.nextLine();

                //richiamo funzione inserimentoEntrate lau
                System.out.println("Adesso il saldo attuale è:" + Utente.inserimentoEntrate(inserisciNome, true, quantita, note));

            }
            else if (sceltaOpzioni.equalsIgnoreCase("b"))
            {
                System.out.println("Inscerisci le uscite:");
                Scanner inserimentoUscite = new Scanner(System.in);
                double quantita = inserimentoUscite.nextDouble();
                String note = inserimentoUscite.nextLine();
                //richiamo funzione inserimentoUsciote lau
                System.out.println("Adesso il saldo attuale è:" + Utente.inserimentoEntrate(inserisciNome, false, quantita, note));
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
