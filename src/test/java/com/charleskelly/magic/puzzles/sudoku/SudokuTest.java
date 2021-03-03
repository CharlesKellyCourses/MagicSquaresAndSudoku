package com.charleskelly.magic.puzzles.sudoku;

import com.charleskelly.magic.game_characteristics.SudokuGameCharacteristics;
import com.charleskelly.magic.tuples.contents.CellContents;
import com.charleskelly.magic.tuples.coordinates.CellCoordinates;
import com.charleskelly.magic.utils.MathUtility;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest
{
    private static int order = 3;
    private static int dimensions = 2;      // do NOT change; only two-dimensional sudoku puzzles


    public void test010CreateSudokuGameCharacteristics()
    {
        try
        {
            SudokuGameCharacteristics sudokuGameCharacteristics
                    = new SudokuGameCharacteristics(order, dimensions);

            assertNotNull (sudokuGameCharacteristics);

            int[] polynomialBase = sudokuGameCharacteristics.getPolynomialBase();
            assertEquals (2 * dimensions, polynomialBase.length);

            int[] sudokuSelectionPolynomialBase = sudokuGameCharacteristics.getSudokuSelectionPolynomialBase();
            assertEquals (dimensions, sudokuSelectionPolynomialBase.length);

            int sudokuDimensions = sudokuGameCharacteristics.getSudokuDimensions();
            assertEquals (2 * dimensions, sudokuDimensions);

            int totalNumberOfCells = sudokuGameCharacteristics.getTotalNumberOfCells();
            assertEquals (MathUtility.integerExponentiation(order, (dimensions * 2)), totalNumberOfCells);
        }
        catch (Exception e)
        {
            fail (e.toString());
        }
    }


    @Test
    public void test020CreateSudoku()
    {
        try
        {
            SudokuGameCharacteristics sudokuGameCharacteristics
                    = new SudokuGameCharacteristics(order, (dimensions * 2) );

            Sudoku sudoku = new Sudoku(sudokuGameCharacteristics);
            sudoku.prepareGameCells();

//            List<CellContents> cellContentsList = getSudokuGroup(sudoku.getGameMap(), 0, 0);
//            for (CellContents cellContents : cellContentsList)
//            {
//                int sudokuInt = sudokuGameCharacteristics.convertToSudokuInt(cellContents);
//                System.out.println(sudokuInt);
//            }

            sudoku.printSudoku2D(false);
        }
        catch (Exception e)
        {
            fail (e.toString());
        }
    }

    public List<CellContents> getSudokuGroup(LinkedHashMap<CellCoordinates, CellContents> gameMap, int wCoordinate, int xCoordinate) throws Exception
    {
        try
        {
            List<CellContents> cellContentsList = new ArrayList<>();

            for (CellCoordinates cellCoordinates : gameMap.keySet())
            {
                int[] cellCoordinatesArray = cellCoordinates.getTupleData();
                if ( (wCoordinate == cellCoordinatesArray[0] ) && (xCoordinate == cellCoordinatesArray[1]) )
                {
                    CellContents cellContents = gameMap.get(cellCoordinates);
                    cellContentsList.add(cellContents);
                }
            }

            return cellContentsList;
        }
        catch (Exception e)
        {
            throw new Exception(e); // trap for debugging
        }
    }
}
