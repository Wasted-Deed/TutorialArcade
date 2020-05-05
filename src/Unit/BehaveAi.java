package Unit;

import ocean.Building;

import java.util.ArrayList;
import java.util.LinkedList;

public interface BehaveAi extends Behave
{
    public void behave(ArrayList<? extends Unit> unit, LinkedList<Building> buildings);
}