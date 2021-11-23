import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

public class CaixaPreta {

    public static BigDecimal processa(List<Integer> parametros) {
        if (parametros.size() != 10 || parametros.stream().anyMatch(parametro -> parametro < 0 || parametro > 100)) {
            throw new RuntimeException("erro");
        }

        String params = parametros.stream().map(Object::toString).reduce("", (s, s2) -> s + " " + s2);

        try {
            Runtime run = Runtime.getRuntime();
            Process pr = run.exec("/home/welson/Documents/modelo2linux " + params);
            pr.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = "";
            if ((line = buf.readLine()) != null) {
                return new BigDecimal(line);
            }
        } catch (IOException | InterruptedException | NumberFormatException e) {
            return BigDecimal.ZERO;
        }

        return BigDecimal.ZERO;
    }

}
