// TicTacToe_CppMove.cpp : Defines the exported functions for the DLL application.
//

#include "stdafx.h"
#include "TicTacToe_CppMove.h"
#include <iostream>
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */
JNIEXPORT jintArray JNICALL Java_TicTacToe_CppMove_makeMove(JNIEnv *env, jobject thisObj, jobjectArray arr)
{
	srand(time(NULL));
	jcharArray board = (jcharArray)arr;
	jchar *rows = env->GetCharArrayElements(board,NULL);
	
	char localBoard[4][4];
	for (int i = 0; i < 4; i++)
	{
		jcharArray oneRow = (jcharArray)env->GetObjectArrayElement(arr, i);
		jchar *element = env->GetCharArrayElements(oneRow, 0);
		for (int k = 0; k < 4; k++)
		{
			localBoard[i][k] = element[k];
		}
	}

	int row = rand() % 4;
	int column = rand() % 4;
	while (!localBoard[column][row] == ' ')
	{
		row = rand() % 4;
		column = rand() % 4;
	}

	jintArray result = env->NewIntArray(2);
	jint *elements = env->GetIntArrayElements(result, NULL);
	elements[0] = column;
	elements[1] = row;

	env->ReleaseIntArrayElements(result, elements, NULL);
	env->ReleaseCharArrayElements(board, rows, NULL);
	return result;

	

}
