package com.charleskelly.magic.transforms;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TransformMatrix
{
    private int dimensions;
    private int[][] matrix;

    public TransformMatrix(int dimensions) throws Exception
    {
        this.dimensions = dimensions;
        matrix = new int[this.dimensions][this.dimensions];
        prepareTransformMatrix();
    }

    public void prepareTransformMatrix() throws Exception
    {
        try
        {
            int index = 0;

            for (int i = 0; i < dimensions; i++)
            {
                for (int j = 0; j < dimensions; j++)
                {
                    if ((i + j) < dimensions)
                        matrix[i][j] = 1;
                    else
                        matrix[i][j] = -1;

                }
            }

        } catch (Exception e)
        {
            log.error(e.toString());
            throw new Exception(e); // trap for debugging
        }
    }

    public int[] getRow(int rowIndex) throws Exception
    {
        try
        {
            return matrix[rowIndex];
        }
        catch (Exception e)
        {
            log.error(e.toString() );
            throw new Exception(e); // trap for debugging
        }
    }


    public int getDimensions()
    {
        return dimensions;
    }

    public void setDimensions(int dimensions)
    {
        this.dimensions = dimensions;
    }

    public int[][] getMatrix()
    {
        return matrix;
    }

    public void setMatrix(int[][] matrix)
    {
        this.matrix = matrix;
    }
}
