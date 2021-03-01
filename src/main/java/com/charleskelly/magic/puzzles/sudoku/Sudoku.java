package com.charleskelly.magic.puzzles.sudoku;

import com.charleskelly.magic.game_characteristics.SudokuGameCharacteristics;
import com.charleskelly.magic.puzzles.cubes.MagicCube;
import com.charleskelly.magic.tuples.contents.CellContents;
import com.charleskelly.magic.tuples.coordinates.CellCoordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;

import java.util.Map;


@Getter
@Setter
@Log4j2
@SuperBuilder
@ToString
public class Sudoku extends MagicCube
{
    Map<CellCoordinates, SudokuTile> sudokuTileMap;

    public Sudoku(SudokuGameCharacteristics sudokuGameCharacteristics) throws Exception
    {
        super(sudokuGameCharacteristics);

    }

    public CellCoordinates rowColumnMapper(int rowIndex, int columnIndex) throws Exception
    {
        try
        {
            int[] cellCoordinatesArray = new int[4];

            int tileRow = rowIndex / order;
            int ordinaryRow = rowIndex % order;
            cellCoordinatesArray[0] = tileRow;
            cellCoordinatesArray[2] = ordinaryRow;

            int tileColumn = columnIndex / order;
            int ordinaryColumn = columnIndex % order;
            cellCoordinatesArray[1] = tileColumn;
            cellCoordinatesArray[3] = ordinaryColumn;


            CellCoordinates cellCoordinates = new CellCoordinates(cellCoordinatesArray);
            return cellCoordinates;
        }
        catch (Exception e)
        {
            log.error(e.toString() );
            throw new Exception(e); // trap for debugging
        }
    }

    public void printSudoku2D(boolean ...tupleFlag) throws Exception
    {
        try
        {
            boolean tupleFlagSelectionFlag = false;
            int tupleFlagArraySize = tupleFlag.length;
            if (tupleFlagArraySize > 0)
                tupleFlagSelectionFlag = tupleFlag[0];

            int rowCounter = 0;
            int columnCounter = 0;
            for (int i = 0; i < (order * order); i++)
            {
                for (int j = 0; j < (order * order); j++)
                {
                    CellCoordinates cellCoordinates = rowColumnMapper(i, j);
                    boolean containsFlag = gameMap.containsValue(cellCoordinates);
                    if (! containsFlag)
                        throw new Exception ("gameMap does not contains entry for cell coordinates: " + cellCoordinates);
                    CellContents cellContents = gameMap.get(cellCoordinates);

                    String printString = sudokuPrintFormatFunction(cellContents, tupleFlagSelectionFlag);
                    System.out.print(printString);

                    columnCounter ++;
                    if (0 == (columnCounter % order))
                        System.out.print("\t");
                }
                System.out.println();
                rowCounter ++;
                if (0 == (rowCounter % order))
                    System.out.println();
            }
        }
        catch (Exception e)
        {
            log.error(e.toString() );
            throw new Exception(e); // trap for debugging
        }
    }

    protected String sudokuPrintFormatFunction(CellContents cellContents, boolean tupleFlag) throws Exception
    {
        String printString = "";
        try
        {
            if(tupleFlag)
            {
                printString = String.format("%15s", cellContents);
            }
            else
            {
               int sudokuInt = ((SudokuGameCharacteristics)gameCharacteristics).convertToSudokuInt(cellContents);
               printString = String.format("%3d", sudokuInt);
            }

            return printString;
        }
        catch (Exception e)
        {
            log.error(e.toString() );
            throw new Exception(e); // trap for debugging
        }
    }
}
