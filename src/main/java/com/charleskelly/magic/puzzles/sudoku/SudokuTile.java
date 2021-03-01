package com.charleskelly.magic.puzzles.sudoku;


import com.charleskelly.magic.tuples.contents.CellContents;
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
public class SudokuTile
{
    private int order;

    private CellContents[][] cellContentsMatrix;

    public SudokuTile(int order)
    {
        this.order = order;
        cellContentsMatrix = new CellContents[order][order];
    }
}
