package model;

import controller.Step;

import java.util.*;

public class Savetxt implements java.io.Serializable{
    public Deque<Step>sav=new ArrayDeque<Step>();
    @Override
    public String toString(){
        return sav+"\n";
    }
}
