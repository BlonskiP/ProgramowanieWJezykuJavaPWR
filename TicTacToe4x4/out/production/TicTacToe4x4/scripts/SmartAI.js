var AI = "o";
var HUMAN = "x";
function Move(row,col)
{
    this.row=row;
    this.col=col;
    return this;
}
function isMovesLeft(board)
{
    for (var i = 0; i < 4; i++)
        for (var j = 0; j < 4; j++)
            if (board[i][j] == ' ')
                return true;
    return false;
}

function evaluate(b)
{

    // Checking for Rows for X or O victory.
    for (var i = 0; i < 4; i++)
    {
        var k = 1;
        if (b[i][k] != ' ') {

            if (b[i][k - 1] == b[i][k] && b[i][k + 1] == b[i][k])
            {
                if (b[i][k] == AI){ return +10;}
                else if (b[i][k] == HUMAN) {return -10;}
            }

            k++;
            if (b[i][k] != ' ')

                if (b[i][k - 1] == b[i][k] && b[i][k + 1] == b[i][k])
                {
                    if (b[i][k] == AI) {return +10}
                    else if (b[i][k] == HUMAN) {return -10}
                }
        }
    }

    // Checking for Columns for X or O victory.
    for (var k = 0; k < 4; k++)
    {
        var i = 1;

        if (b[i][k] != ' ')
            if (b[i + 1][k] == b[i][k] && b[i - 1][k] == b[i][k]) {
                if (b[i][k] == AI){ return +10}
                else if (b[i][k] == HUMAN) {return -10}
            }
        i++;
        if (b[i][k] != ' ')
            if (b[i + 1][k] == b[i][k] && b[i - 1][k] == b[i][k]) {
                if (b[i][k] == AI) {return +10}
                else if (b[i][k] == HUMAN) {return -10}
            }
    }

    for (var i = 1; i < 3; i++)
    {
        for (var k = 1; k < 3; k++)
        {
            if (b[i][k] != ' ') {
                if (b[i - 1][k - 1] == b[i][k] && b[i + 1][k + 1] == b[i][k])
                    if (b[i][k] == AI) {return +10}
                    else if (b[i][k] == HUMAN){return -10}
                if (b[i - 1][k + 1] == b[i][k] && b[i + 1][k - 1] == b[i][k])
                    if (b[i][k] == AI){ return +10}
                    else if (b[i][k] == HUMAN) {return -10}
            }
        }
    }

    // Else if none of them have won then return 0
    return 0;
}

function minimax(board, depth, isMax)
{
    var score = evaluate(board);

    // If Maximizer has won the game return his/her
    // evaluated score
    if (score == 10)
        return score;

    // If Minimizer has won the game return his/her
    // evaluated score
    if (score == -10)
        return score;

    // If there are no more moves and no winner then
    // it is a tie
    if (isMovesLeft(board) == false)
        return 0;

    // If this maximizer's move
    if (isMax)
    {
        var best = -1000;

        // Traverse all cells
        for (var i = 0; i < 3; i++)
        {
            for (var j = 0; j < 3; j++)
            {
                // Check if cell is empty
                if (board[i][j] == ' ')
                {
                    // Make the move
                    board[i][j] = AI;

                    // Call minimax recursively and choose
                    // the maximum value
                    best = Math.max(best, minimax(board, depth + 1, !isMax));

                    // Undo the move
                    board[i][j] = ' ';
                }
            }
        }
        return best;
    }

    // If this minimizer's move
    else
    {
        var best = 1000;

        // Traverse all cells
        for (var i = 0; i < 4; i++)
        {
            for (var j = 0; j < 4; j++)
            {
                // Check if cell is empty
                if (board[i][j] == ' ')
                {
                    // Make the move
                    board[i][j] = HUMAN;

                    // Call minimax recursively and choose
                    // the minimum value
                    best = Math.min(best,minimax(board, depth + 1, !isMax));

                    // Undo the move
                    board[i][j] = ' ';
                }
            }
        }
        return best;
    }
}

// This will return the best possible move for the player
function findBestMove(board)
{
    var bestVal = -1000;
    var bestMove=Move(-1,-1);

    // Traverse all cells][ evaluate minimax function for
    // all empty cells. And return the cell with optimal
    // value.
    for (var i = 0; i < 4; i++)
    {
        for (var j = 0; j < 4; j++)
        {   print("BOARD: " + i + " " + j +"=" + board[i][j])
            // Check if cell is empty
            if (board[i][j] == ' ')
            {
                // Make the move
                board[i][j] = AI;

                // compute evaluation function for this
                // move.
                var moveVal = minimax(board, 0, false);

                // Undo the move
                board[i][j] = ' ';

                // If the value of the current move is
                // more than the best value][ then update
                // best/
                if (moveVal > bestVal)
                {
                    bestMove=Move(i,j);
                    bestVal = moveVal;
                }
            }
        }
    }



    return bestMove;
}
function moveTick(arr){
    var best = Move(0,0)
    gameboard=[];
    for(var i=0;i<4;i++){
        gameboard[i]=new Array(4);
        for(var j=0; j<4;j++ )
            gameboard[i][j]=arr[i*4+j];
    }

    best = findBestMove(gameboard);
    var col = best.col;
    var row = best.row;
    var result= Java.to([row,col],"int[]");

    return result;
}



