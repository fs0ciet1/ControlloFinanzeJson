/*                          CLASSE MOVIMENTO
 *   ATTRIBUTI:
 *       - tipoMovimento
 *       - quantita
 *       - note
 *
 *   FUNZIONI:
 *
 *
 */
public class Movimento
{
    //=================================== ATTRIBUTI ===================================
    private Boolean tipoMovimento;      //true = entrata , false = uscita
    private Double quantita;
    private String note;
    //IMPORTANTE: ID UNIVOCO UTENTE

    //=================================== GETTER ===================================
    public Boolean getTipoMovimento()
    {
        return tipoMovimento;
    }
    public Double getQuantita()
    {
        return quantita;
    }
    public String getNote()
    {
        return note;
    }

    //=================================== SETTER ===================================
    public void setTipoMovimento(Boolean tipoMovimento)
    {
        this.tipoMovimento = tipoMovimento;
    }
    public void setQuantita(Double quantita)
    {
        this.quantita = quantita;
    }
    public void setNote(String note)
    {
        this.note = note;
    }
}
