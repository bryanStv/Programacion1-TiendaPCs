import java.sql.SQLException;
import java.util.Scanner;

public class SocialNetwork {

    private static int currentScreen = 0;
    public static void main(String[] args) throws SQLException {
        int opcion;
        printBanner();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        while(!salir) {
            printMenu();
            opcion = selectedOption();
            if (currentScreen == 0){
                switch (opcion) {
                    case 0:
                        salir = true;
                        break;
                    case 1:
                        PostController.printAllPosts();
                        break;
                    case 2:
                        if (UserController.login())
                            currentScreen = 1;
                        break;
                    case 3:
                        UserController.addUser();
                        break;
                   /* case 4:
                        UserController.deleteUser();
                        break;
                    case 5:
                        UserController.findByName();*/
                }
            }else{
                switch (opcion){
                    case 0:
                        salir = true;
                        break;
                    case 1: PostController.printUserPosts();
                        break;
                    case 2:
                        PostController.addPost();
                        break;
                    case 3:
                        CommentController.addComment();
                        break;
                    case 4:
                        PostController.like();
                        break;
                    case 5:
                        PostController.printOthersPosts();
                        break;
                    case 6: UserController.logout();
                        currentScreen = 0;
                        break;
                }
            }


                //clearTerminal();
            }


    }

    private static void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void printBanner(){
        System.out.println(AnsiColor.BLUE.getCode());
        System.out.println("   _____            _       _   _   _      _                      _    ");
        System.out.println("  / ____|          (_)     | | | \\ | |    | |                    | |   ");
        System.out.println(" | (___   ___   ___ _  __ _| | |  \\| | ___| |___      _____  _ __| | __");
        System.out.println("  \\___ \\ / _ \\ / __| |/ _` | | | . ` |/ _ \\ __\\ \\ /\\ / / _ \\| '__| |/ /");
        System.out.println("  ____) | (_) | (__| | (_| | | | |\\  |  __/ |_ \\ V  V / (_) | |  |   < ");
        System.out.println(" |_____/ \\___/ \\___|_|\\__,_|_| |_| \\_|\\___|\\__| \\_/\\_/ \\___/|_|  |_|\\_\\");
        System.out.println(AnsiColor.RESET.getCode());
    }
    public static int selectedOption() {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        while (true) {
            try{
                opcion = Integer.parseInt(sc.next());
                if ((currentScreen == 0 && opcion <= 3) || (currentScreen == 1 && opcion <= 6)) {
                    break;
                } else {
                    System.out.println(AnsiColor.RED.getCode());
                    System.out.println("Incorrect option");
                    System.out.println(AnsiColor.RESET.getCode());
                }
            }catch (IllegalArgumentException iae){
                System.out.println(AnsiColor.RED.getCode());
                System.out.println("Incorrect option");
                System.out.println(AnsiColor.RESET.getCode());
            }
        }
        return opcion;
    }
    public static void printMenu(){
        //System.out.println("0 Exit | " + "1 Print | " + "2 Add | " + "3 Update | " + "4 Delete | " + "5 Search");
        System.out.println(AnsiColor.BLUE.getCode());
        System.out.println("------------------------------------------------------------------------------------------");
        if (currentScreen == 0)
            System.out.println("0 Exit | 1 All Posts | 2 Login | 3 Register");
        else if (currentScreen == 1) {
            System.out.println("0 Exit | 1 My Posts | 2 New Post | 3 New Comment | 4 Like | 5 Other's Posts | 6 Logout " +
                    SocialNetworkService.user.getName());
        }
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println(AnsiColor.RESET.getCode());
    }

}
