package ma.enset.streamservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Facture {
    private Long numero;
    private String nomClient;
    private double montant;
}
