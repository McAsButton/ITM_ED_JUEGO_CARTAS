public enum NombreCarta {
    AS(10),
    DOS(2),
    TRES(3),
    CUATRO(4),
    CINCO(5),
    SEIS(6),
    SIETE(7),
    OCHO(8),
    NUEVE(9),
    DIEZ(10),
    JACK(10),
    QUEEN(10),
    KING(10);
    
    private int numero; // Declarar la variable numero
    
    // Constructor de la enumeración NombreCarta
    NombreCarta(final int NUMERO){
        this.numero=NUMERO;
    }
    
    // Método para retornar el número de la carta
    public int retornarNumero(){
        return numero;
    }
}
