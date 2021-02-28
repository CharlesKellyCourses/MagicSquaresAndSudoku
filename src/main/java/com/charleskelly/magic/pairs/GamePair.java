package com.charleskelly.magic.pairs;

import com.charleskelly.magic.tuples.coordinates.CellCoordinates;

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
public class GamePair
{
    private CellCoordinates cellCoordinates;
    private CellContents cellContents;
}
