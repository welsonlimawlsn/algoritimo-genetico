public class Principal {

    public static void main(String[] args) {

        Populacao populacao = new Populacao(20);
        while (true) {
            populacao.testaPopulacao(CaixaPreta::processa);

            populacao.ordernarIndividuos();

            populacao.imprimir();

            populacao.cruzaMelhores();

            System.out.println("-----------------------------------------------------------");

        }
    }

}
