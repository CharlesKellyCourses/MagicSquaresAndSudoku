package com.charleskelly.magic.puzzles.cubes;


import com.charleskelly.magic.game_characteristics.GameCharacteristics;
import com.charleskelly.magic.pairs.GamePair;
import com.charleskelly.magic.tuples.contents.CellContents;
import com.charleskelly.magic.tuples.contents.CellDifferences;
import com.charleskelly.magic.tuples.coordinates.CellCoordinates;
import com.charleskelly.magic.tuples.coordinates.CellOffsets;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Getter
@Setter
@Log4j2
@SuperBuilder
@ToString
public class MagicCube
{
    protected GameCharacteristics gameCharacteristics;

    protected int order;
    protected int dimensions;
    protected int midPoint;

    protected int[] polynomialBase;

    protected static List<GamePair> gamePairList = new ArrayList<>();
    protected LinkedHashMap<CellCoordinates, CellContents> gameMap  = new LinkedHashMap<>();

    public MagicCube(GameCharacteristics gameCharacteristics) throws Exception
    {
        this.gameCharacteristics = gameCharacteristics;

        this.order = gameCharacteristics.getOrder();
        this.dimensions = gameCharacteristics.getDimensions();
        this.midPoint = gameCharacteristics.getMidPoint();

        polynomialBase = gameCharacteristics.getPolynomialBase();

//        prepareGameCells();
    }

    public void prepareGameCells() throws Exception
    {
        try
        {
            for (int i = 0; i < gameCharacteristics.getTotalNumberOfCells(); i++)
            {
                CellCoordinates cellCoordinates = gameCharacteristics.convertCellCounterToCellCoordinate(i);
                CellOffsets cellOffsets = new CellOffsets(gameCharacteristics.getCenterCellCoordinates(), cellCoordinates);

                CellDifferences cellDifferences = gameCharacteristics.calculateDifferencesFromOffsets(cellOffsets);

                CellContents cellContents = new CellContents(gameCharacteristics.getCenterCellContents(), cellDifferences);

                CellContents reducedCellContents = new CellContents(gameCharacteristics.reduceModulo(cellContents));
                int cellContentInt = gameCharacteristics.convertToInt(reducedCellContents);

                GamePair gamePair = GamePair.builder()
                        .cellCoordinates(cellCoordinates)
                        .cellContents(reducedCellContents)
                        .build();
                gamePairList.add(gamePair);
                gameMap.put(cellCoordinates, reducedCellContents);
            }
        }
        catch (Exception e)
        {

            log.error(e.toString() );
            throw new Exception(e); // trap for debugging
        }
    }


    public void print2D(boolean ...tupleFlag) throws Exception
    {
        try
        {
            boolean tupleFlagSelectionFlag = false;
            int tupleFlagArraySize = tupleFlag.length;
            if (tupleFlagArraySize > 0)
                tupleFlagSelectionFlag = tupleFlag[0];

            int numberOfPairs = gamePairList.size();
            int gamePairListCounter = 0;

            while (gamePairListCounter < numberOfPairs)
            {
                for(int i = 0; i < order; i++)
                {
                    GamePair gamePair = gamePairList.get(gamePairListCounter ++);
                    String printString = printFunction(gamePair, tupleFlagSelectionFlag);
                    System.out.print(printString + "\t");
                }
                System.out.println("\t\t");
                for(int i = 2; i < dimensions; i++)
                {
                    int modulus = gamePairListCounter % polynomialBase[i];
                    if (0 == modulus)
                        System.out.println();
                }
            }
        }
        catch (Exception e)
        {
            throw new Exception(e); // trap for debugging
        }
    }

    protected String printFunction (GamePair gamePair, boolean tupleFlag) throws Exception
    {
        try
        {
            String printString;
            CellContents cellContents = gamePair.getCellContents();

            if(tupleFlag)
            {
                printString = String.format("%10s", cellContents);
            }
            else
            {
                Integer integerToPrint = gameCharacteristics.convertToInt(cellContents);
                printString = String.format("%5d", integerToPrint);
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
