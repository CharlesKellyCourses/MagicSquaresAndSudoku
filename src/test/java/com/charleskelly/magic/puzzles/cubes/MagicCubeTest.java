package com.charleskelly.magic.puzzles.cubes;

import com.charleskelly.magic.game_characteristics.GameCharacteristics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class MagicCubeTest
{
    private static int order = 3;
    private static int dimensions = 2;


    @Test
    public void test010CreateMagicCube()
    {
        try
        {
            GameCharacteristics gameCharacteristics
                    = new GameCharacteristics(order, dimensions);

            MagicCube magicCube = new MagicCube(gameCharacteristics);
            magicCube.prepareGameCells();

            magicCube.print2D(true);
        }
        catch (Exception e)
        {
            fail (e.toString());
        }
    }
}
