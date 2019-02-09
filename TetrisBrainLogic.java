package edu.stanford.cs108.tetris;

import java.awt.*;
import java.util.Random;

public class TetrisBrainLogic extends TetrisLogic {
	protected boolean brainMode = false;
	DefaultBrain smartie;
	
	public TetrisBrainLogic(TetrisUIInterface uiInterface) {
		super(uiInterface);
		// TODO Auto-generated constructor stub
	}
	
	public void setBrainMode(boolean brain) {
        brainMode = brain;
    }
}