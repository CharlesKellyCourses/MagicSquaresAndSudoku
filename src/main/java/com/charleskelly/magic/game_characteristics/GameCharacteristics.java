package com.charleskelly.magic.game_characteristics;

import com.charleskelly.magic.pairs.GamePair;
import com.charleskelly.magic.transforms.TransformMatrix;
import com.charleskelly.magic.tuples.Tuple;
import com.charleskelly.magic.tuples.TupleException;
import com.charleskelly.magic.tuples.contents.CellContents;
import com.charleskelly.magic.tuples.contents.CellDifferences;
import com.charleskelly.magic.tuples.coordinates.CellCoordinates;
import com.charleskelly.magic.tuples.coordinates.CellOffsets;
import com.charleskelly.magic.utils.MathUtility;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Getter
@Setter
@Log4j2
@SuperBuilder
@ToString
public class GameCharacteristics
{
    protected int order;
    protected int dimensions;
    protected int midPoint;

    protected int totalNumberOfCells; 	// order^dimensions

    protected int[] polynomialBase;

    protected CellContents centerCellContents;
    protected CellCoordinates centerCellCoordinates;

    protected TransformMatrix transformMatrix;

    protected List<GamePair> gamePairList;

    public GameCharacteristics(int order, int dimensions) throws Exception
    {
        this.order = order;
        this.dimensions = dimensions;

        midPoint = (order - 1) / 2;
        totalNumberOfCells = MathUtility.integerExponentiation(order, dimensions);

        transformMatrix = new TransformMatrix(dimensions);

        preparePolynomialBase();
        prepareCenterCells();
    }

    public void preparePolynomialBase() throws Exception
    {
        try
        {
            polynomialBase = new int[dimensions];

            for (int i = 0; i < dimensions; i++)
            {
                int baseValue = MathUtility.integerExponentiation(order, i);
                polynomialBase[i] = baseValue;
            }
        }
        catch (Exception e)
        {
            log.error(e.toString() );
            throw new Exception(e); // trap for debugging
        }
    }


    protected void prepareCenterCells() throws Exception
    {
        try
        {
            centerCellContents = new CellContents(dimensions);
            centerCellCoordinates = new CellCoordinates(dimensions);

            int[] centerCellContentsData = centerCellContents.getTupleData();
            int[] centerCellCoordinatesData = centerCellCoordinates.getTupleData();

            int midPoint = (order - 1) / 2;

            for (int i = 0; i < centerCellContentsData.length; i++)
            {
                centerCellContentsData[i] = midPoint;
                centerCellCoordinatesData[i] = midPoint;
            }
        }
        catch (Exception e)
        {
            throw new Exception(e); // trap for debugging
        }
    }

    public CellCoordinates convertCellCounterToCellCoordinate(int index) throws TupleException
    {
        int quotient, dividend, divisor, remainder;

        dividend = index;
        int [] dataHolder = new int [dimensions];

        for (int i=dimensions - 1; i>=0; i--)
        {
            divisor        = polynomialBase[i];
            quotient       = dividend / divisor;
            remainder      = dividend % divisor;
            dataHolder[i]  = quotient;

            dividend       = remainder;
        }// for (int i=dimensions - 1; i>=0; i--)

        CellCoordinates cellCoordinates = new CellCoordinates(dataHolder);
        return cellCoordinates;
    }

    public CellDifferences calculateDifferencesFromOffsets(CellOffsets cellOffsets) throws Exception
    {
        CellDifferences cellDifferences = new CellDifferences(dimensions);
        int[][] offsetsMatrix = new int[dimensions][dimensions];

        try
        {
            for (int i=0; i<dimensions; i++)
            {
                int[] transformMatrixRow = transformMatrix.getRow(i);
                int cellOffsetComponent = cellOffsets.getTupleComponent(i);
                for (int j=0; j<dimensions; j++)
                {
                    offsetsMatrix[i][j]= cellOffsetComponent * transformMatrixRow[j];
                }
            }

            int[] differencesData = new int [dimensions];
            for (int i=0; i<dimensions; i++)
            {

                for (int j=0; j<dimensions; j++)
                {
                    differencesData[i] = differencesData[i] + offsetsMatrix[j][i];
                }
                differencesData[i] = differencesData[i] % order;
                cellDifferences.setTupleComponent(i, differencesData[i] );
            }


            return cellDifferences;
        }
        catch (Exception e)
        {
            log.error(e.toString() );
            throw new Exception(e); // trap for debugging
        }
    }

    public int[] reduceModulo(Tuple inputTuple) throws Exception
    {
        int[] reducedResult = new int[inputTuple.getTupleSize()];
        if ( ! (dimensions == inputTuple.getTupleSize()))
            throw new Exception("dimensions not equal inputTuple size");

        try
        {
            for (int i = 0; i < dimensions; i++)
            {
                int reducedComponent = 0;

                int component = inputTuple.getTupleComponent(i);

                if (Math.abs(component) >= order)
                    reducedComponent = component % order;
                else
                    reducedComponent = component;

                if (reducedComponent < 0)
                    reducedComponent += order;

                reducedResult[i] = reducedComponent;
            }

            return reducedResult;
        }
        catch (Exception e)
        {
            log.error(e.toString() );
            throw new Exception(e); // trap for debugging
        }
    }

    public int convertToInt(Tuple inputTuple) throws Exception
    {
        int[] result = new int[inputTuple.getTupleSize()];
        if ( ! (dimensions == inputTuple.getTupleSize()))
            throw new Exception("dimensions not equal inputTuple size");
        try
        {
            int accumulator = 0;
            for (int i = 0; i < dimensions; i++)
            {
                int component = inputTuple.getTupleComponent(i);
                int product = component * polynomialBase[i];

                accumulator += product;
            }

            return accumulator;
        }
        catch (Exception e)
        {
            log.error(e.toString() );
            throw new Exception(e); // trap for debugging
        }
    }



    public void print2D() throws Exception
    {
        try
        {
            int numberOfPairs = gamePairList.size();
            int gamePairListCounter = 0;

            while (gamePairListCounter < numberOfPairs)
            {
                for(int i = 0; i < order; i++)
                {
                    GamePair gamePair = gamePairList.get(gamePairListCounter ++);
                    String printString = printFunction(gamePair);
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

    protected String printFunction (GamePair gamePair) throws Exception
    {
        try
        {
            CellContents cellContents = gamePair.getCellContents();
            Integer integerToPrint =  convertToInt(cellContents);
            String printString = String.format("%5d", integerToPrint);

            return printString;
        }
        catch (Exception e)
        {
            log.error(e.toString() );
            throw new Exception(e); // trap for debugging
        }
    }


}
