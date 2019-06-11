package dto;

public class BrugerDTO
{
    /** Bruger id i området 1-99999999. Vælges af brugerne */
    int oprId;
    /** Bruger navn (opr_navn) min. 2 max. 20 karakterer */
    String oprNavn;
    /** Bruger initialer min. 2 max. 4 karakterer */
    String ini;
    /** Bruger cpr-nr 10 karakterer */
    String cpr;

    /** Liste over roller */
    String rolle;
}