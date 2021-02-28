package com.charleskelly.magic.tuples.coordinates;


import com.charleskelly.magic.tuples.Tuple;
import com.charleskelly.magic.tuples.TupleException;

/**
 * CellCoordinates represent the "Euclidean" coordinates of a cell.
 * <p></p>
 * @author Charles Kelly
 */
public class CellCoordinates extends Tuple
{
    private final String 	OPEN_PRINT_DELIMINTER = "[";
    private final String 	CLOSE_PRINT_DELIMINTER = "]";

    public CellCoordinates(int size) throws TupleException
    {
        super(size);
    }

    public CellCoordinates(int[] initializationData)
    {
        super(initializationData);
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
