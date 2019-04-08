package ipg.vagner.adivinha;

import java.util.Random;

/**
 * Esta classe permite gerar números a adivinhar entre 1 e 10
 */
public class GeradorNumerosAdivinhar {
    private Random random = new Random();

    /**
     * Devolve o próximo a adivinhar
     * @return um número entre 1 e 10
     */
    public int getProximoNumeroAdivinhar() {
        return random.nextInt(10) + 1;
    }
}
