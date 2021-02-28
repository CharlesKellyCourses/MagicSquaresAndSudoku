package com.charleskelly.magic.tuples;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Objects;


/**
 The class Tuple is the basic data structure for each cell in a Hyper Cube.
 If a Hyper Cube has d dimensions, then each cell in the Hyper Cube is represented
 by one object of class Tuple.  Each Tuple object will contain d numbers.
 The range for these numbers is 0..RowSize.
 RowSize is the "order" of the Hyper Cube.

 * <p></p>
 * @author Charles Kelly
 */

@Getter
@Setter
@Log4j2
@SuperBuilder
public class Tuple implements Comparable<Tuple>
{
    private final String 	OPEN_PRINT_DELIMINTER = "((";
    private final String 	CLOSE_PRINT_DELIMINTER = "))";

    private int     tupleSize;
    private int[]   tupleData;

    /**
     * Instantiate a new tuple with all components equal to zero
     */
    public Tuple(int size)
    {
        tupleSize = size;
        tupleData = new int [tupleSize];
        for (int k = 0; k<tupleSize; k++)
        {
            tupleData [k] = 0;
        }
    }

    public Tuple(int[] initializationData)
    {
        tupleData = initializationData;
        tupleSize = initializationData.length;
    }

    public int getTupleComponent (int index) throws TupleException
    {
        if (index < 0)
            throw new TupleException("index is less than zero: " + index);
        if (index >= tupleSize)
            throw new TupleException("index is >= tupleSize; index: " + index + " tupleSize: " + tupleSize);

        return tupleData [index];
    }
    public void setTupleComponent (int index, int new_value) throws TupleException
    {
        if (index < 0)
            throw new TupleException("index is less than zero: " + index);
        if (index >= tupleSize)
            throw new TupleException("index is >= tupleSize; index: " + index + " tupleSize: " + tupleSize);

        tupleData [index] = new_value;
    }

    public int compareTo(Tuple otherTuple)
    {
        int sizeComparison = this.tupleSize - otherTuple.getTupleSize();
        if (! (0 == sizeComparison))
            return sizeComparison;

        int dataComparison = 0;
        try
        {
            for (int i = 0; i<this.tupleSize; i++)
            {
                dataComparison = this.getTupleComponent(i) - otherTuple.getTupleComponent(i);
                if (! (0 == dataComparison))
                    return dataComparison;
            }// for (int i = 0; i<this.tupleSize; i++)
        }// try
        catch (Exception e)
        {
            return 0;
        }// catch (Exception e)

        return 0;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        String closeDelimiter = getCloseDelimiter();
        String openDelimiter = getOpenDelimiter();

        sb.append(openDelimiter);

        boolean multipleComponentFlag = false;
        for (int i=0; i<this.tupleSize; i++)
        {
            if (multipleComponentFlag)
                sb.append(", ");
            sb.append(this.tupleData[i]);
            multipleComponentFlag = true;
        }// for (int i=0; i<this.tupleSize; i++)

        sb.append(closeDelimiter);

        String tempString = sb.toString();
        return tempString;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Tuple)) return false;
        Tuple tuple = (Tuple) o;
        return getTupleSize() == tuple.getTupleSize() && Arrays.equals(getTupleData(), tuple.getTupleData());
    }

    @Override
    public int hashCode()
    {
        int result = Objects.hash(getTupleSize());
        result = 31 * result + Arrays.hashCode(getTupleData());
        return result;
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
