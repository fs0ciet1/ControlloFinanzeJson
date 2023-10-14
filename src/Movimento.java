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
    //=================================== ATTRIBUTI ===================================
    //private String idUtente;           //serve a identificare a chi appartrtiene un determinato movimento
    private boolean tipoMovimento;      //TRUE = entrata , FALSE = uscita
    private double quantita;
    private String note;                // NOTE: forse non utile e andra traformato in categoria

    //=================================== COSTRUTTORE ===================================
    //un movimento per il momento puo esistere vuoto per comodità di comoposizione dell annuncio in fase di entrata o uscita
    public Movimento()
    {

    }

    //=================================== aggiungiMovimento ===================================
    //ho necessità che la funzione Menu mi passi i parametri da input, poi qui richiamo il metodo scriviFileMovimenti e restituisco true o false se tutto va bene
    //quindi la funzione dentro utente di inserimento movimento non serve più e richiamo direttamente dal menu questa funz
    //ho necessità però di sapere id utente per poter poi gestire il suo saldo
    public static boolean AggiungiMovimento(Integer idUtente,Boolean tipoMovimento, Double quantita,String note)
    {
        //Variabili che necessitp di usare come appoggio
        ArrayList<Movimento> listaMovimentiTemp = new ArrayList<Movimento>();
        ArrayList<Utente>   listaUtentiTemp = new ArrayList<Utente>();
        Movimento movimentoTemp = new Movimento();

        //prelevo l'array list dal GestioneFileJson
        //listaMovimentiTemp=GestioneFileJson.LeggiFileMovimenti();

        //ho bisogno di prelevare anche l'array list di utenti per poter aggiornare il saldo di quell utente specifico
        //listaUtentiTemp = GestioneFileJson.LeggiFileUtenti();

        //creo il mio oggetto movimento con i dati che mi ha passato menu
        //movimentoTemp.setIdUtente(idUtente);
        movimentoTemp.setTipoMovimento(tipoMovimento);
        movimentoTemp.setQuantita(quantita);
        movimentoTemp.setNote(note);

        //cerco la corrispondenza dell id utente dentro la listaUtentiTemp
        for (int i = 0; i < listaUtentiTemp.size(); i++)
        {
            //quando trovo il corrispondente ma chiaramente devo avere idUtente dentro Utenti corrispondente dentro Movimenti
            /*if(listaUtentiTemp.get(i).getId==idUtente)
            {
                //controllo se entrata o uscita
                if(movimentoTemp.getTipoMovimento()==true)
                {
                    //ENTRATA positiva +  quindi dovrò aggiungere i soldi dal saldo del utente
                    //mi trovo all i-esimo utente quindi a quello giusto e adesso sommo al saldo
                    //quindi prendo il suo vecchio saldo lo aggiungo alla quantità e lo setto nuovamente
                    listaUtentiTemp.get(i).setSaldo(listaUtentiTemp.get(i).getSaldo()+quantita);
                }
                else
                {
                    //USCITA negativa -  quindi dovrò rimuovere i soldi dal saldo del utente
                    //mi trovo all i-esimo utente quindi a quello giusto e adesso sottraggo al saldo
                    //quindi prendo il suo vecchio saldo lo sottraggo alla quantità e lo setto nuovamente
                    listaUtentiTemp.get(i).setSaldo(listaUtentiTemp.get(i).getSaldo()-quantita);

                }
            }*/
        }

        //quindi aggiorno la lista Movimenti e Utenti(per via del saldo)
        //sul return potrei fare tipo così visto che se vanno a buon fine il caricamento dei file mi restiturisce dei true
        //if(GestioneFileJson.ScriviFileMovimenti() && GestioneFileJson.ScriviFileUtenti()) return true
        //else return false
        return true;
    }

    //=================================== mostraMovimenti ===================================
    //sarà da implementare, o meglio richiamare nel menù questa funzione che restituisce semplicemente un array di movimentei del utente specifico
    //quindi ho bisogno dell id del utente in chiamata e returno un array list di mov di quel utente
    //quindi tolgo il parametro arraylist di movimenti da dentro Utente
    public static ArrayList<Movimento> MostraMovimentiUtente(Integer idUtente)
    {
        ArrayList<Movimento> listaMovimentiTemp = new ArrayList<Movimento>();
        // quindi ho bisogno solo della lista dei movimenti, in teoria ne basta una

        //intanto prelevare la lista da GestioneFileJson
        //listaMovimentiTemp = GestioneFileJson.LeggiFileMovimenti();

        //poi scorrere tutto l'array e se idUtente è diverso da quello che mi è stato passato va eliminato, potrebbe non funziare quindi fare 2 array
        for (int i = 0; i < listaMovimentiTemp.size(); i++)
        {
            /*
            if (listaMovimentiTemp.get(i).getIdUtente()!=idUtente)
            {
                listaMovimentiTemp.remove(i);
            }*/
        }

        //quindi qui avrò rimosso tutti i movimenti che non corrispondono a quello specifico utente e posso ritornarla a Menu
        //ritorno la lista aggiornata e non devo scrivere nulla da nessuna parte
        return listaMovimentiTemp;
    }

    //=================================== GETTER ===================================
    public Boolean getTipoMovimento() {
        return tipoMovimento;
    }
    public Double getQuantita() {
        return quantita;
    }
    public String getNote() {
        return note;
    }

    //=================================== SETTER ===================================
    public void setTipoMovimento(Boolean tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }
    public void setQuantita(Double quantita) {
        this.quantita = quantita;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
