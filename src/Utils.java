// Utils.java
import java.util.*;

public class Utils {
    
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        //Podem testar cada uma das funções aqui
    }

    /* Esta função returna se o numero esta de valido com as regras defenidas pelos atributos:
    Positivo(true) - aceita numeros positivos
    Zero(true) - aceita o 0
    Todos(true) - aceita qualquer numero*/
    public static boolean validarNumeroInt(int num, boolean positivo, boolean zero, boolean todos) {

        if (todos) {
            return true;
        } else if (positivo && zero) {
            return num >= 0;
        } else if (positivo && !zero) {
            return num > 0;
        } else if (!positivo && !zero) {
            return num < 0;
        } else {
            return num <= 0;
        }
    }

    // Pede um numero int e valida
    public static int pedirNumeroInt(boolean positivo, boolean zero, boolean todos) {

        if (todos) {
            System.out.println("Insira um numero inteiro:");
        } else if (positivo && zero) {
            System.out.println("Insira um numero inteiro maior ou igual a 0:");
        } else if (positivo && !zero) {
            System.out.println("Insira um numero inteiro maior que 0:");
        } else if (!positivo && !zero) {
            System.out.println("Insira um numero inteiro menor que 0:");
        } else {
            System.out.println("Insira um numero inteiro menor ou igual 0:");
        }


        while (true) {
            try {
                int num = sc.nextInt();

                if (validarNumeroInt(num, positivo, zero, todos)) {
                    return num;
                }
                System.out.println("Numero invalido tente novamente\n");

            } catch (InputMismatchException e) {
                System.out.println("Somente numeros inteiros permitidos\n");
                sc.next();
            }
        }
    }

    public static void arrayIntPreencher(int[] array, boolean positivo, boolean zero, boolean todos) {
        int arrayTamanho = array.length;

        if (arrayTamanho == 1) {
            System.out.println("Insira " + arrayTamanho + " numero");
        }
        else{
            System.out.println("Insira " + arrayTamanho + " numeros");
        }

        for(int i = 0; i < arrayTamanho; i++){
            array[i] = pedirNumeroInt(positivo, zero, todos);
        }
    }

    public static void arrayIntCorrer(int[] array, int minimo, int maximo, int incremento, String mensagem) {
        
        for (int i = minimo; i < maximo; i+=incremento){
            System.out.println((mensagem == null ? "" : mensagem) + array[i]);
        }
    }
}
