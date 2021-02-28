package com.charleskelly.magic.tuples.coordinates;


import com.charleskelly.magic.tuples.Tuple;
import com.charleskelly.magic.tuples.TupleException;

/**
 * CellOffsets represent the difference between 2 CellCoordinates
 * (components of "geometric" distance between cells
 *
 * <p></p>
 * @author Charles Kelly
 */
public class CellOffsets extends Tuple
{
    private final String 	OPEN_PRINT_DELIMINTER = "(";
    private final String 	CLOSE_PRINT_DELIMINTER = ")";

    public CellOffsets(int[] initializationData)
    {
        super(initializationData);
    }
    /**
     *
     *
     * @param minuendCellCoordinates
     * @param subtrahendCellCoordinates
     * @throws Exception if size of minuendCellCoordinates not equal
     * size of subtrahendCellCoordinates
     *
     * @return an offset, each of whose components is equal to difference = minuend - subrahend;
     * an offset component can be positive, zero, or negative
     */
    public CellOffsets(CellCoordinates minuendCellCoordinates, CellCoordinates subtrahendCellCoordinates) throws TupleException
    {
        super(minuendCellCoordinates.getTupleSize());

        int size = minuendCellCoordinates.getTupleSize();

        if (! (size == subtrahendCellCoordinates.getTupleSize())  )
            throw new TupleException("size incorrect for coordinate1");

        for (int i=0; i<size; i++)
        {
            int difference = minuendCellCoordinates.getTupleComponent(i) - subtrahendCellCoordinates.getTupleComponent(i);
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
