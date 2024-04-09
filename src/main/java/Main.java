import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    //COLORS
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private static final OrdenadorRepositoryImpl ordenadorRepository = new OrdenadorRepositoryImpl();
    public static void main(String[] args) throws SQLException {
        banner();
        while(true){
            menu();
        }
    }

    private static void menu() throws SQLException {
        int option;
        Scanner tc = new Scanner(System.in);
        System.out.println(ANSI_PURPLE+"\n\n\t\t\t\t\t\tBIENVENIDOS A PCs&Components");
        System.out.println("Salir(0) | Listar todos los PC(1) | Buscar PC Portátil(2) | Buscar PC Premontado(3) | Comprar(4) | Menú Administración(5)");
        option = tc.nextInt();
        switch (option){
            case 0:
                System.exit(0);
            case 1:
                sobremesa();
                List<Ordenador> ordenadores = ordenadorRepository.findAll();
                ordenadores.forEach(System.out::println);
                break;
            case 2:
                portatil();
                List<Ordenador> portatiles = ordenadorRepository.findPortatil();
                portatiles.forEach(System.out::println);
                break;
            case 3:
                sobremesa();
                List<Ordenador> notPortatiles = ordenadorRepository.findNotPortatil();
                notPortatiles.forEach(System.out::println);
                break;
            case 4:
                sobremesa();
                comprar();
                break;
            case 5:
                administracion();
                break;
        }
    }

    private static void comprar() throws SQLException {
        List<Ordenador> ordenadors = ordenadorRepository.findAll();
        ordenadors.forEach(System.out::println);
        Scanner tc = new Scanner(System.in);
        System.out.print("¿Te interesa comprar alguno de estos PCs(Escribe el id)? ");
        int id = tc.nextInt();
        Ordenador ordenadorAcomprar = ordenadorRepository.findById(id);
        System.out.println("Ordenador a comprar --> "+ ordenadorAcomprar.toString());
        tc.close();
    }

    private static void administracion() throws SQLException {
        System.out.println(ANSI_RED+"Salir(0) | Añadir PC(1) | Borrar PC(2)");
        Scanner tc = new Scanner(System.in);
        System.out.print("¿Qué opción eliges? ");
        int option = tc.nextInt();
        switch (option){
            case 0:
                System.exit(0);
            case 1:
                System.out.print("Modelo: ");
                String modelo = tc.next();
                System.out.println("Precio: ");
                float precio = tc.nextFloat();
                System.out.println("Es portatil(1-Si,0-No): ");
                int portatil = tc.nextInt();
                Ordenador pc = new Ordenador(modelo,precio,portatil);
                ordenadorRepository.save(pc);
                break;
            case 2:
                List<Ordenador> ordenadors = ordenadorRepository.findAll();
                ordenadors.forEach(System.out::println);
                System.out.println("Cuál quieres borrar(id) --> ");
                int id = tc.nextInt();
                Ordenador erase = ordenadorRepository.findById(id);
                ordenadorRepository.delete(erase);
                ordenadors.forEach(System.out::println);
                break;
        }
        tc.close();
    }

    private static void banner(){
        System.out.println(ANSI_GREEN+" *******   ******                 **            ******                                                                   **        \n" +
                "/**////** **////**               */ *          **////**                      ******                                     /**        \n" +
                "/**   /****    //  ******       / **          **    //   ******  ********** /**///** ******  *******   *****  *******  ************\n" +
                "/*******/**       **////         */ * *      /**        **////**//**//**//**/**  /****////**//**///** **///**//**///**///**/**//// \n" +
                "/**//// /**      //*****        *  / *       /**       /**   /** /** /** /**/******/**   /** /**  /**/******* /**  /**  /**//***** \n" +
                "/**     //**    **/////**      /*   /*       //**    **/**   /** /** /** /**/**/// /**   /** /**  /**/**////  /**  /**  /** /////**\n" +
                "/**      //****** ******       / **** *       //****** //******  *** /** /**/**    //******  ***  /**//****** ***  /**  //******** \n" +
                "//        ////// //////         //// /         //////   //////  ///  //  // //      //////  ///   //  ////// ///   //    ////////  "+ANSI_RESET);
    }

    private static void sobremesa(){
        System.out.println(ANSI_RED+"                  .----.\n" +
                "      .---------. | == |\n" +
                "      |.-\"\"\"\"\"-.| |----|\n" +
                "      ||       || | == |\n" +
                "      ||       || |----|\n" +
                "      |'-.....-'| |::::|\n" +
                "      `\"\")---(\"\"` |___.|\n" +
                "     /:::::::::::\\\" _  \"\n" +
                "    /:::=======:::\\`\\`\\\n" +
                "jgs `\"\"\"\"\"\"\"\"\"\"\"\"\"`  '-'"+ANSI_RESET);
    }

    private static void portatil(){
        System.out.println(ANSI_RED+"   +--------------+\n" +
                "   |.------------.|\n" +
                "   ||            ||\n" +
                "   ||            ||\n" +
                "   ||            ||\n" +
                "   ||            ||\n" +
                "   |+------------+|\n" +
                "   +-..--------..-+\n" +
                "   .--------------.\n" +
                "  / /============\\ \\\n" +
                " / /==============\\ \\\n" +
                "/____________________\\\n" +
                "\\____________________/"+ANSI_RESET);
    }
}
