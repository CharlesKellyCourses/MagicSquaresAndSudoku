package com.charleskelly.magic.tuples.contents;

import com.charleskelly.magic.tuples.Tuple;
import com.charleskelly.magic.tuples.TupleException;


/**
 * CellDifferences are the mathematical difference of the components of two tuples
 * (found by subtraction; note the "modulo" parameter)
 * <p></p>
 * @author Charles Kelly
 */
public class CellDifferences extends Tuple
{
    private final String 	OPEN_PRINT_DELIMINTER = "{";
    private final String 	CLOSE_PRINT_DELIMINTER = "}";

    public CellDifferences(int[] initializationData)
    {
        super(initializationData);
    }

    public CellDifferences(int dimensions)
    {
        super(dimensions);
    }


    /**
     * @return the differences between cellContents1 - cellContents0 (may be negative)
     * Note: the constructor below with modulus parameter
     *
     * @param cellContents0 "lower row" before subtraction - subtrahend
     * @param cellContents1 "upper row" before subtraction - minuend
     * @throws Exception if size of cellContents0 not equal
     * size of cellContents1
     */
    public CellDifferences(CellContents cellContents0, CellContents cellContents1) throws TupleException
    {
        super(cellContents0.getTupleSize());

        int size = cellContents0.getTupleSize();

        if (! (size == cellContents1.getTupleSize())  )
            throw new TupleException("size incorrect for cellContents1");

        for (int i=0; i<size; i++)
        {
            int difference = cellContents1.getTupleComponent(i) - cellContents0.getTupleComponent(i);
            this.setTupleComponent(i, difference);
        }
    }


    public String getOpenDelimiter()
    {
        return this.OPEN_PRINT_DELIMINTER;
    }
    public String getCloseDelimiter()
    {
        return this.CLOSE_PRINT_DELIMINTER;
    }
}
