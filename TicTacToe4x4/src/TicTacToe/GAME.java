package TicTacToe;

public class GAME {
    public static char[][] gameBoard= new char[4][4];

    public static void newGameInit(){
        for(int i=0;i<4;i++)
        {
            for (int k = 0; k < 4; k++)
                    gameBoard[i][k]=' ';
        }
    }

    public static void printBoard()
    {
        System.out.println("---------------");
        for(int i=0;i<4;i++) {
            for (int k = 0; k < 4; k++){
                System.out.print("|" + gameBoard[i][k] + "| ");}
            System.out.println();
            System.out.println("---------------");
        }
    }
    public static boolean acceptMove(int[] move, char symbol)
    {
        int column = move[0];
        int row = move[1];

        if(gameBoard[column][row]==' '){
            gameBoard[column][row]=symbol;
            System.out.println("This move is OK. This tile will be taken by: " + gameBoard[column][row]);
            return true;}
        System.out.println("This move is forbidden. This tile is taken by: " + gameBoard[column][row]);
        return false;
    }

}
