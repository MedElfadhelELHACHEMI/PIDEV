/*
 * ON METTERA ICI TOUT CE QUI POURRA ETRE NECESSAIRE POUR UNE VERIFICATION STATIC
 */
package outils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Iskander
 */
public class Verif {

    public Verif() {
    }

    //Verifier si la chaine donnée est représente un email valide ou pas.
    public static boolean isValidEMail(String str) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        //on ajoute le pattern email
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        //on verifie sa correspondance à la chaine et on retourne le résultat (correspond ou pas au pattern)
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    //Remplace les espaces en des +, c'est utile surtout pour les urls
    public static String ReplaceSpaceToPlus(String str) {
        String ch = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                ch += str.charAt(i);
            } else {
                ch += '+';
            }
        }
        return ch;
    }

    public static String dateAujourdhuiMoins(int n) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Créer un calendar avec la date d'aujourdhui
        Calendar calendar = Calendar.getInstance();
        // Soustraire n jour de la date d'aujourdhui
        calendar.add(Calendar.DATE, -n);
        Date yesterday = calendar.getTime();//nouvelle date
        return dateFormat.format(yesterday).toString();
    }
}
