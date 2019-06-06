function moveTick(testArray){
   //very stupid AI. Random pick.
    var row = Math.floor(Math.random()*4);
    var column = Math.floor(Math.random()*4);
    var symbol = testArray[row,column];

    while(symbol==='x' || symbol==='o')
    {
        row = Math.floor(Math.random()*4);
        column = Math.floor(Math.random()*4);
        print(symbol)
    }
    var result = Java.to([row, column], "int[]");
    return result;
}