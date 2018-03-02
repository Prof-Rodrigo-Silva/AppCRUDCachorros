package controle;

import java.io.Serializable;

/**
 * Created by rodrigo on 06/11/17.
 */

public class Cachorro implements Serializable {
    private static final long serialVersionUID = 1L;
    public Long _id;
    public String nome;
    public String raca;
    public String sexo;
    public byte[] foto;

   
    @Override
    public String toString() {
        return "Cachorro{" +
                "_id=" + _id +
                ", nome='" + nome + '\'' +
                ", raca='" + raca + '\'' +
                ", sexo='" + sexo + '\'' +
                '}';
    }
}