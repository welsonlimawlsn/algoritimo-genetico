import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.function.Function;

public class Individuo {

    public static final Random RANDOM = new Random();

    private UUID id;

    private List<Integer> genetica;

    private Integer geracao;

    private BigDecimal qualidade;

    private boolean testado;

    public Individuo() {
        this(new ArrayList<>(), 1);

        criaGenomas();
    }

    private void criaGenomas() {
        for (int i = 0; i < 10; i++) {
            genetica.add(RANDOM.nextInt(101));
        }
    }

    private Individuo(List<Integer> genetica, Integer geracao) {
        id = UUID.randomUUID();
        qualidade = BigDecimal.ZERO;
        testado = false;
        this.genetica = genetica;
        this.geracao = geracao;
    }

    public void testa(Function<List<Integer>, BigDecimal> caixaPreta) {
        qualidade = caixaPreta.apply(genetica);
        testado = true;
    }

    public List<Integer> getGenetica() {
        return genetica;
    }

    public void setQualidade(BigDecimal qualidade) {
        this.qualidade = qualidade;
    }

    public BigDecimal getQualidade() {
        return qualidade;
    }

    public boolean isTestado() {
        return testado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Individuo individuo = (Individuo) o;
        return Objects.equals(id, individuo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Individuo{" +
                "genetica=" + genetica +
                ", geracao=" + geracao +
                ", qualidade=" + qualidade +
                '}';
    }

    public Individuo cruza(Individuo individuo) {
        List<Integer> novaGenetica = new ArrayList<>();

        for (int i = 0; i < genetica.size(); i++) {
            if (i % 2 == 0) {
                novaGenetica.add(genetica.get(i));
            } else {
                novaGenetica.add(individuo.genetica.get(i));
            }
        }

        return new Individuo(novaGenetica, (geracao > individuo.geracao ? geracao : individuo.geracao) + 1);
    }

    public Individuo sofreMutacao() {
        int i1 = RANDOM.nextInt(10);
        Individuo individuo = new Individuo(new ArrayList<>(genetica), geracao + 1);
        for (int l = 0; l < i1; l++) {
            int i = RANDOM.nextInt(10);
            individuo.getGenetica().set(i, RANDOM.nextInt(101));
        }

        return individuo;
    }

}
