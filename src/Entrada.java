import java.util.Scanner;


public final class Entrada {

    private static final Scanner SC = new Scanner(System.in);

    private Entrada() {
    }


    public static String texto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = SC.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("Campo obrigatório.");
        }
    }


    public static String textoOpcional(String mensagem) {
        System.out.print(mensagem);
        return SC.nextLine().trim();
    }

    public static int inteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(SC.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    public static double decimal(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                double valor = Double.parseDouble(SC.nextLine().trim().replace(',', '.'));
                if (valor < 0) {
                    System.out.println("O valor não pode ser negativo.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido (ex.: 19,90).");
            }
        }
    }


    public static int inteiroOpcional(String mensagem, int atual) {
        while (true) {
            String valor = textoOpcional(mensagem);
            if (valor.isEmpty()) {
                return atual;
            }
            try {
                int numero = Integer.parseInt(valor);
                if (numero >= 0) {
                    return numero;
                }
                System.out.println("O valor não pode ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }


    public static double decimalOpcional(String mensagem, double atual) {
        while (true) {
            String valor = textoOpcional(mensagem);
            if (valor.isEmpty()) {
                return atual;
            }
            try {
                double numero = Double.parseDouble(valor.replace(',', '.'));
                if (numero >= 0) {
                    return numero;
                }
                System.out.println("O valor não pode ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido (ex.: 19,90).");
            }
        }
    }


    public static String email(String mensagem) {
        while (true) {
            String valor = texto(mensagem);
            if (valor.contains("@") && valor.contains(".")) {
                return valor;
            }
            System.out.println("E-mail inválido.");
        }
    }
}
 