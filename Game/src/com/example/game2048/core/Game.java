package com.example.game2048.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
	private static final int SIZE = 4;
	private final int[][] values = new int[SIZE][SIZE];
	private boolean change = false;
	
	public Game() {
		
	}


    public void newGame() {
        for (int i = 0; i < SIZE; i++) {
        	for(int j = 0; j < SIZE; j++) {
        		values[i][j] = 0;
        	}
        }
        newBlock();
        newBlock();
    }
    
    public void newBlock() {
    	if (full()) return;
    	
    	int i = (int)(4 * Math.random());
		int j = (int)(4 * Math.random());	
		
		if(values[i][j] == 0) {
			values[i][j] = 2;
		} else {
			newBlock();
		}
    }
    
    public boolean full() {
		for(int i = 0; i < 4; i ++) {
			for(int j = 0; j < 4; j++) {
				if(values[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

}
