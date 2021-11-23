import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Populacao {

    private List<Individuo> individuos;

    public Populacao(int tamanho) {
        individuos = new ArrayList<>();

        for (int i = 0; i < tamanho; i++) {
            individuos.add(new Individuo());
        }
    }

    public void testaPopulacao(Function<List<Integer>, BigDecimal> caixaPreta) {
        individuos.parallelStream()
                .filter(individuo -> !individuo.isTestado())
                .forEach(individuo -> individuo.testa(caixaPreta));
    }

    public void ordernarIndividuos() {
        individuos.sort(Comparator.comparing(Individuo::getQualidade).reversed());
    }

    public void imprimir() {
        individuos.forEach(System.out::println);
    }

    public void cruzaMelhores() {
        ordernarIndividuos();

        int i1 = individuos.size() / 3;
        for (int i = 0; i < i1; i += 2) {
            individuos.set(i1 + i, individuos.get(i).cruza(individuos.get(individuos.size() - 1 - i)));
        }

        for (int i = i1 * 2; i < individuos.size(); i++) {
            if (i % 2 == 0) {
                individuos.set(i, individuos.get(0).sofreMutacao());
            } else {
                individuos.set(i, new Individuo());
            }
        }
    }

}
