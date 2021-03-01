package com.charleskelly.magic.game_characteristics;


import com.charleskelly.magic.tuples.Tuple;
import com.charleskelly.magic.utils.MathUtility;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
@SuperBuilder
@ToString
public class SudokuGameCharacteristics extends GameCharacteristics
{
    private int sudokuDimensions;
    private int[] sudokuSelectionPolynomialBase;

    public SudokuGameCharacteristics(int order, int dimensions) throws Exception
    {
        super(order, dimensions);

        totalNumberOfCells = MathUtility.integerExponentiation(order, (dimensions));

        sudokuSelectionPolynomialBase = new int[dimensions / 2];
        for (int i = 0; i < (dimensions / 2); i++)
        {
            sudokuSelectionPolynomialBase[i] = polynomialBase[i];
        }
    }

    public int convertToSudokuInt(Tuple inputTuple) throws Exception
    {
        int[] inputArray = inputTuple.getTupleData();
        int[] tempArray = new int[dimensions / 2];

        for (int i = 0; i < (dimensions / 2); i++)
        {
            tempArray[i] = inputArray[2*i + 1];
        }

        int accumulator = 0;
        for (int i = 0; i < (dimensions / 2); i++)
        {
            int component = tempArray[i];
            int product = component * sudokuSelectionPolynomialBase[i];

            accumulator += product;
        }

        return accumulator;
    }


}
