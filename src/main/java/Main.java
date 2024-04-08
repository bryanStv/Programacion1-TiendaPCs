import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final OrdenadorRepositoryImpl ordenadorRepository = new OrdenadorRepositoryImpl();
    public static void main(String[] args) throws SQLException {
        while(true){
            menu();
        }
    }

    private static void menu() throws SQLException {
        int option;
        Scanner tc = new Scanner(System.in);
        System.out.println("\n\n\t\t\t\t\t\tBIENVENIDOS A PCs&Components");
        System.out.println("Salir(0) | Listar todos los PC(1) | Buscar PC Portátil(2) | Buscar PC Premontado(3)");
        option = tc.nextInt();
        switch (option){
            case 0:
                System.exit(0);
            case 1:
                List<Ordenador> ordenadores = ordenadorRepository.findAll();
                ordenadores.forEach(System.out::println);
                break;
            case 2:
                List<Ordenador> portatiles = ordenadorRepository.findPortatil();
                portatiles.forEach(System.out::println);
                break;
            case 3:
                List<Ordenador> notPortatiles = ordenadorRepository.findNotPortatil();
                notPortatiles.forEach(System.out::println);
                break;
        }
    }

    private static void comprar() throws SQLException {
        Scanner tc = new Scanner(System.in);
        System.out.println("¿Te interesa comprar alguno de estos PCs(Escribe el modelo)? ");
        String modelo = tc.nextLine();
        Ordenador ordenadorAcomprar = ordenadorRepository.findByModelo(modelo);
        System.out.println("OrdenadorAcomprar: "+ordenadorAcomprar.toString());
    }
}
