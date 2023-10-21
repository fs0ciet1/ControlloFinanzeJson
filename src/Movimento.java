import java.io.IOException;
import java.util.ArrayList;

/*                          CLASSE MOVIMENTO
 *   ATTRIBUTI:
 *       - idUtente
 *       - tipoMovimento
 *       - quantita
 *       - note
 *
 *   FUNZIONI:
 *       - costruttore (al momento vuoto)
 *       - aggiungi Movimento
 *       - mostraMovimentiUtente
 *
 */
public class Movimento
{


    //=================================== ATTRIBUTI ===================================//
    private String nomeUtente;           //serve a identificare a chi appartrtiene un determinato movimento



    private boolean tipoMovimento;      //TRUE = entrata , FALSE = uscita
    private double quantita;
    private String note;                // NOTE: forse non utile e andra traformato in categoria

    //=================================== COSTRUTTORE ===================================
    //un movimento per il momento puo esistere vuoto per comodità di comoposizione dell movimento in fase di entrata o uscita
    public Movimento(String nomeUtente, boolean tipoMovimento, double  quantita ,String note)
    {
        this.nomeUtente = nomeUtente;
        this.tipoMovimento = tipoMovimento;
        this.quantita = quantita;
        this.note = note;
    }

    //=================================== aggiungiMovimento ===================================//

    // la funzione dentro utente d inserimento movimento non serve più e richiamo direttamente dal menu questa funz

    public static void AggiungiMovimento(String nomeUtente,boolean tipoMovimento, double quantita,String note) throws IOException {
        //Variabili che necessito di usare come appoggio
        ArrayList<Movimento> listaMovimentiTemp = new ArrayList<Movimento>();
        ArrayList<Utente>   listaUtentiTemp = new ArrayList<Utente>();
        Movimento movimentoTemp = new Movimento(nomeUtente, tipoMovimento, quantita, note);

        //prelevo l'array list dal GestioneFileJson
        listaMovimentiTemp=GestioneFileJson.LeggiFileMovimenti();

        //ho bisogno di prelevare anche l'array list di utenti per poter aggiornare il saldo di quell utente specifico
        listaUtentiTemp = GestioneFileJson.LeggiFileUtenti();

        //aggiunta del nuovo movimento all arraylist temp
        listaMovimentiTemp.add(movimentoTemp);

        //cerco la corrispondenza del Nomeutente dentro la listaUtentiTemp
        for (int i = 0; i < listaUtentiTemp.size(); i++)
        {
            //quando trovo il corrispondente ma chiaramente devo avere idUtente dentro Utenti corrispondente dentro Movimenti
            if(listaUtentiTemp.get(i).getNome().equals(nomeUtente))
            {
                //controllo se entrata o uscita
                if(movimentoTemp.isTipoMovimento()==true)
                {
                    //ENTRATA positiva + quindi dovrò aggiungere i soldi dal saldo del utente
                    //mi trovo all i-esimo utente quindi a quello giusto e adesso sommo al saldo
                    //quindi prendo il suo vecchio saldo lo aggiungo alla quantità e lo setto nuovamente

                    //listaUtentiTemp.get(i).setSaldo(listaUtentiTemp.get(i).getSaldo()+quantita);

                    listaUtentiTemp.get(i). aggiungiSaldo(quantita);

                }
                else
                {
                    //USCITA negativa - quindi dovrò rimuovere i soldi dal saldo del utente
                    //mi trovo all i-esimo utente quindi a quello giusto e adesso sottraggo al saldo
                    //quindi prendo il suo vecchio saldo lo sottraggo alla quantità e lo setto nuovamente
                    //listaUtentiTemp.get(i).setSaldo(listaUtentiTemp.get(i).getSaldo()-quantita);
                    listaUtentiTemp.get(i). sottraiSaldo(quantita);

                }
            }

        }

        /*for (int i = 0; i < listaUtentiTemp.size(); i++) {
            System.out.println(listaUtentiTemp.get(i).getNome() + listaUtentiTemp.get(i).getSaldo());

        }

        for (int i = 0; i < listaMovimentiTemp.size() ; i++) {
            System.out.println(listaMovimentiTemp.get(i).getNomeUtente()+ listaMovimentiTemp.get(i).getQuantita() + listaMovimentiTemp.get(i).getNote());
        }*/

        //sovrascrittuare del file json. Passo i cambiamenti fatti e ricaricalo il file
        GestioneFileJson.ScriviFileUtenti(listaUtentiTemp);
        GestioneFileJson.ScriviFileMovimenti(listaMovimentiTemp);
    }

    //=================================== mostraMovimenti ===================================//
    //sarà da implementare, o meglio richiamare nel menù questa funzione che restituisce semplicemente un array di movimentei del utente specifico
    //quindi ho bisogno dell id del utente in chiamata e returno un array list di mov di quel utente
    //quindi tolgo il parametro arraylist di movimenti da dentro Utente

    //IMPORTANTE >> capire se si puo modificare la funzione LeggiFileMovimenti prelevando dal JSON solo i movimenti di uno specifico utente

    public static ArrayList<Movimento> MostraMovimentiUtente(String nome)
    {
        ArrayList<Movimento> listaMovimentiTemp = new ArrayList<Movimento>();
        // quindi ho bisogno solo della lista dei movimenti, in teoria ne basta una

        //intanto prelevare la lista da GestioneFileJson
        //listaMovimentiTemp = GestioneFileJson.LeggiFileMovimenti();

        //poi scorrere tutto l'array e se idUtente è diverso da quello che mi è stato passato va eliminato, potrebbe non funziare quindi fare 2 array
        for (int i = 0; i < listaMovimentiTemp.size(); i++)
        {
            /*
            if (listaMovimentiTemp.get(i).getNome()!=nome)
            {
                listaMovimentiTemp.remove(i);
            }*/
        }

        //quindi qui avrò rimosso tutti i movimenti che non corrispondono a quello specifico utente e posso ritornarla a Menu
        //ritorno la lista aggiornata e non devo scrivere nulla da nessuna parte
        return listaMovimentiTemp;
    }

    //=================================== GETTER ===================================//
    public String getNomeUtente() {
        return nomeUtente;
    }
    public boolean isTipoMovimento() {
        return tipoMovimento;
    }
    /*public boolean getTipoMovimento() {
        return tipoMovimento;
    }*/
    public double getQuantita() {
        return quantita;
    }
    public String getNote() {
        return note;
    }

    //=================================== SETTER ===================================//
    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }
    public void setTipoMovimento(boolean tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }
    public void setQuantita(double quantita) {
        this.quantita = quantita;
    }
    public void setNote(String note) {
        this.note = note;
    }
}




