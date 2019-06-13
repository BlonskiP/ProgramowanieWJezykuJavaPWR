function moveTick(arr){
   //very stupid AI. Random pick.
    var row = Math.floor(Math.random()*4);
    var column = Math.floor(Math.random()*4);

    gameboard=[];
    for(var i=0;i<4;i++){
        gameboard[i]=new Array(4);
        for(var j=0; j<4;j++ )
            gameboard[i][j]=arr[i*4+j];
    }
    var symbol = gameboard[row][column];
    while(symbol=='x' || symbol=='o')
    {
        row = Math.floor(Math.random()*4);
        column = Math.floor(Math.random()*4);
        print(symbol)
    }
    var result = Java.to([row, column], "int[]");
    return result;
}