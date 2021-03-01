package com.charleskelly.magic.game_characteristics;

import com.charleskelly.magic.pairs.GamePair;
import com.charleskelly.magic.tuples.contents.CellContents;
import com.charleskelly.magic.tuples.contents.CellDifferences;
import com.charleskelly.magic.tuples.coordinates.CellCoordinates;
import com.charleskelly.magic.tuples.coordinates.CellOffsets;
import com.charleskelly.magic.utils.MathUtility;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class GameCharacteristicsTest
{
    private static int order = 3;
    private static int dimensions = 4;

    private static List<GamePair> gamePairList;

    @Test
    public void test010CreateGameCharacteristics()
    {
        try
        {
            GameCharacteristics gameCharacteristics
                    = new GameCharacteristics(order, dimensions);

            int midPoint = gameCharacteristics.getMidPoint();
            assertEquals(((order -1)/2), midPoint);

            CellCoordinates centerCellCoordinates = gameCharacteristics.getCenterCellCoordinates();
            for (int i = 0; i < dimensions; i++)
            {
                assertEquals(midPoint, centerCellCoordinates.getTupleComponent(i));
            }

            assertEquals(MathUtility.integerExponentiation(order, dimensions), gameCharacteristics.getTotalNumberOfCells());
        }
        catch (Exception e)
        {
            fail (e.toString());
        }
    }


    @Test
    public void test020CreateCellCoordinates()
    {
        try
        {
            GameCharacteristics gameCharacteristics
                    = new GameCharacteristics(order, dimensions);
            gamePairList = new ArrayList<>();

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

            }

            gameCharacteristics.setGamePairList(gamePairList);
            gameCharacteristics.print2D();
        }
        catch (Exception e)
        {
            fail (e.toString());
        }
    }

}
