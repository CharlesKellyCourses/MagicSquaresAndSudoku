package com.charleskelly.magic.tuples.contents;

import com.charleskelly.magic.tuples.Tuple;


import com.charleskelly.magic.tuples.TupleException;
import lombok.NonNull;

/**
 * CellContents represent the data contained within a cell of a multi-dimensional
 * matrix
 *
 * <p></p>
 * @author Charles Kelly
 */
public class CellContents extends Tuple
{
    private final String 	OPEN_PRINT_DELIMINTER = "<";
    private final String 	CLOSE_PRINT_DELIMINTER = ">";


    public CellContents(int[] initializationData)
    {
        super(initializationData);
    }

    public CellContents(int size)
    {
        super(size);
    }

    /**
     * "convenience" constructor
     *  instantiate new CellContents from sum of a CellContents and a CellDifference
     *
     * @param addend0CellContents
     * @param addend1CellDifferences
     * @throws Exception
     */
    public CellContents(@NonNull CellContents addend0CellContents, @NonNull CellDifferences addend1CellDifferences) throws Exception
    {
        super(addend0CellContents.getTupleSize());
        int size = addend0CellContents.getTupleSize();

        if (! (size == addend0CellContents.getTupleSize())  )
            throw new TupleException("size incorrect for coordinate1");

        for (int i = 0; i < size; i++)
        {
            int sum = addend0CellContents.getTupleComponent(i) + addend1CellDifferences.getTupleComponent(i);
            this.setTupleComponent(i, sum);
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
