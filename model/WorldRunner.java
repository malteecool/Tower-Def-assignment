package model;

import controller.ObjectTypes;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A class that initiates and runs the math behind the map.
 * This class should be run on its own thread but can be interacted with.
 *
 * @author Max Malmer, c17mmr@cs.umu.se
 */
public class WorldRunner {

    private volatile boolean gameRunning;

    private volatile Integer wallet;

    private volatile Tiles[][] worldMap;

    private volatile ArrayList<GroundUnit> groundUnits;

    private volatile ArrayList<Tower> placedTowers;

    private final Object lock = new Object();

    private Integer finalXSize;

    private Integer finalYSize;

    private ArrayList<Position> goalPosition;

    private Position startPosition;

    private Integer tileSize;

    private Integer unitsToWin;

    private Integer secondsToLoss;

    /**
     * Class constructor which builds the map and runs the game.
     *
     * @param modelMap X size of map.
     */
    public WorldRunner (ModelMapStruct modelMap) {// (worldMap, )
        gameRunning = true;
        worldMap = modelMap.getWorldTiles();
        groundUnits = new ArrayList<>();
        placedTowers = new ArrayList<>();
        finalXSize = modelMap.getDimensionX();
        finalYSize = modelMap.getDimensionY();
        goalPosition = modelMap.getGoalPos();
        startPosition = new Position(modelMap.getSpawnPosX(), modelMap.getSpawnPosY());
        tileSize = 32;
        wallet = 1000;
        //TODO The following variables should be set by the map.
        unitsToWin = 50;
        secondsToLoss = 600;
    }

    public void decrementGameTime() {
        --secondsToLoss;
    }

    public Integer getGameTimePassed() {
        return (600 - secondsToLoss);
    }

    public Integer getGameTimeLeft() {
        return secondsToLoss;
    }

    public void decrementUnitsToWin() {
        --unitsToWin;
    }

    public boolean hasWon() {
        return (unitsToWin <= 0);
    }

    public boolean hasLost() {
        return (secondsToLoss <= 0);
    }

    /**
     * Stops the game.
     */
    public void stopGame() {

        synchronized (lock) {
            gameRunning = false;
        }
    }

    /**
     * Checks if the game is running.
     * @return true if it's running false otherwise.
     */
    public boolean isRunning() {

        synchronized (lock) {
            return gameRunning;
        }
    }

    /**
     * Adds a object to the map.
     *
     * @param tile Java Object to be added to the map.
     * @param xCord x coordinate of the object.
     * @param yCord y coordinate of the object.
     */
    public void addTile(Tiles tile, Integer xCord, Integer yCord) {

        synchronized (lock) {
            worldMap[xCord][yCord] = tile;
        }
    }

    /**
     * Gets a object from the map.
     *
     * @param xCord x coordinate of the object.
     * @param yCord y coordinate of the object.
     * @return the Java Object of the given position.
     */
    public Object getTile(Integer xCord, Integer yCord) {

        synchronized (lock) {
            return worldMap[xCord][yCord];
        }
    }

    /**
     * Gets the X size of world.
     *
     * @return X size of the world map.
     */
    public Integer getXLength() {
        return finalXSize;
    }

    /**
     * Gets the Y size of the world.
     *
     * @return Y size of the world map.
     */
    public Integer getYLength() {
        return finalYSize;
    }

    /**
     * Gets the current credits of the wallet.
     *
     * @return the current credits of the wallet.
     */
    public Integer getCurrentCredits() {
        return wallet;
    }

    /**
     * Decreases the wallet according to a cost.
     *
     * @param cost the cost of the expense.
     */
    public void decreaseWallet(Integer cost) {

        synchronized (lock) {
            this.wallet = this.wallet - cost;
        }
    }

    /**
     * Increases the wallet by a given amount.
     *
     * @param credits the amount to increase the wallet with.
     */
    public void increaseWallet(Integer credits) {

        synchronized (lock) {
            this.wallet = this.wallet + credits;
        }
    }

    /**
     * Creates a unit of the type name.
     * @param name the unit type to be created
     */
    public void createUnit(String name) throws IllegalUnitName {
        Position newStartPosition = new Position(startPosition.getXPosition() * 32, startPosition.getYPosition() * 32);

        switch (name) {
            case "testUnit":
                addUnit(new TestUnit(startPosition));
                break;

            case "UNIT_1":
                Unit1 newUnit = new Unit1(newStartPosition);

                if (newUnit.getCreditCost() > this.getCurrentCredits()) {
                    //TODO Maybe add exception to message user.
                    //TODO Maybe make separate method out of this.
                    break;
                }
                decreaseWallet(newUnit.getCreditCost());
                addUnit(new Unit1(newStartPosition));
                break;

            case "UNIT_2":
                Unit2 newUnit2 = new Unit2(newStartPosition);

                if (newUnit2.getCreditCost() > this.getCurrentCredits()) {
                    //TODO Maybe add exception to message user.
                    //TODO Maybe make separate method out of this.
                    break;
                }
                decreaseWallet(newUnit2.getCreditCost());
                addUnit(new Unit2(newStartPosition));
                break;

            default : throw new IllegalUnitName(name);
        }
    }

    //TODO write test

    public void addTowers(int numberOfTowers) {
        // finding suitable placements for towers
        ArrayList<Position> potentialTowerPlacement = new ArrayList<>();

        for (int x = 0; x < finalXSize; x++) {
            for (int y = 0; y < finalYSize; y++) {
                if (worldMap[x][y] != null) {
                    if (worldMap[x][y].canBeTraversed()) {

                        if (x+1 < finalXSize) {
                            if (worldMap[x+1][y].canHaveTower()) {
                                potentialTowerPlacement.add(new Position((x+1) * tileSize, y * tileSize));
                            }
                        }
                        if (x-1 > 0) {
                            if (worldMap[x-1][y].canHaveTower()) {
                                potentialTowerPlacement.add(new Position((x-1) * tileSize, y * tileSize));
                            }
                        }
                        if (y+1 < finalYSize) {
                            if (worldMap[x][y+1].canHaveTower()) {
                                potentialTowerPlacement.add(new Position(x * tileSize, (y+1) * tileSize));
                            }
                        }
                        if (y-1 > 0) {
                            if (worldMap[x][y-1].canHaveTower()) {
                                potentialTowerPlacement.add(new Position(x * tileSize, (y-1) * tileSize));
                            }
                        }
                    }
                }
            }
        }

        // placing towers
        for (int i = 0; i < numberOfTowers; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, potentialTowerPlacement.size());

            Tower1 newTower = new Tower1(potentialTowerPlacement.get(randomNum));
            potentialTowerPlacement.remove(randomNum);
            placedTowers.add(newTower);
        }
    }

    public ArrayList<Tower> getTowerList() {
        return placedTowers;
    }

    public ArrayList<Attack> runTowers() {
        //TODO Right now the towers are given all units
        //TODO This method must figure out whom where shot by whom. !Can be done with unit to tower index.
        ArrayList<Attack> attacks = new ArrayList<>();

        for (Tower placedTower : placedTowers) {

            if (groundUnits != null) {
                attacks.add(placedTower.runTower(groundUnits));
            }
        }
        return attacks;
    }

    /**
     * Adds a GroundUnit to the unit map.
     *
     */
    public void addUnit(GroundUnit unit) {
        groundUnits.add(unit);
    }

    /**
     * Removes all GroundUnits with hp lass than 0.
     */
    public void removeUnits() {

        for (int i = 0; i < groundUnits.size(); i++) {

            if (groundUnits.get(i) != null) {

                if (groundUnits.get(i).getHealth() <= 0) {
                    groundUnits.set(i, null);
                }
            }
        }
    }

    public void removeUnit(int index) {
        groundUnits.set(index, null);
    }

    /**
     * Gets the GroundUnit of a coordinate.
     *
     * @param index the index of the unit.
     * @return the GroundUnit of the coordinate.
     */
    public GroundUnit getUnit(int index) {
        return groundUnits.get(index);
    }

    /**
     * Gets the goal position of the map.
     *
     * @return the goal position.
     */
    public ArrayList<Position> getGoal() {
        return goalPosition;
    }

    /**
     * Gets the goal position of the start.
     *
     * @return the start position of the map.
     */
    public Position getStart() {
        return startPosition.getPosition();
    }

    /**
     * Sets the goalPosition of the map.
     *
     * @param goals the goals to be set.
     */
    public void setGoal(ArrayList goals) {
        goalPosition = goals;
    }

    /**
     * Sets the startPosition of the map.
     *
     * @param XCord the x coordinate of the start.
     * @param YCord the y coordinate of the start.
     */
    public void setStart(Integer XCord, Integer YCord) {
        startPosition.setPosition(XCord, YCord);
    }

    /**
     * sets worldMap
     * @param Tiles to 2d array of Tiles to be set
     */
    public void setWorldMap(Tiles[][] Tiles) {this.worldMap = Tiles;}

    /**
     * returns the number of towers that has been placed.
     * @return a certain number of towers
     */
    public int getNumberOfTowers() {return placedTowers.size();}

    public void moveAllUnits() {
        boolean isGoal = false;

        for (int i = 0; i < groundUnits.size(); i++) {

            if (groundUnits.get(i) != null) {

                for (int j = 0; j < goalPosition.size(); j++) {

                    if (groundUnits.get(i) != null) {

                        if (groundUnits.get(i).isGoal(goalPosition.get(j))) {
                            this.removeUnit(i);
                            this.decrementUnitsToWin();
                            System.out.println("hejhej");
                            isGoal = true;
                        }
                    }
                }

                if (isGoal) {
                    continue;
                }
                groundUnits.get(i).moveAgain();

                this.setUnitDirection(i);
            }
        }
    }

    public void setUnitDirection(int i) {

        if (this.isNewTile(i)) {

            if (worldMap[(int) ((groundUnits.get(i).getXPosition() / tileSize))]
                    [(int) (groundUnits.get(i).getYPosition() / tileSize)].landOn() instanceof TCrossingTile) {
                System.out.format("TCROSSING%n");
                TCrossingTile tCrossing =
                        (TCrossingTile) worldMap[(int) ((groundUnits.get(i).getXPosition() / tileSize))]
                                [(int) (groundUnits.get(i).getYPosition() / tileSize)].landOn();
                groundUnits.get(i).move(tCrossing.getDirection());
                return;
            }

            if (((groundUnits.get(i).getXPosition()) / tileSize) >= 0) {

                if (worldMap[(int) ((groundUnits.get(i).getXPosition() / tileSize) - 1)]
                        [(int) (groundUnits.get(i).getYPosition() / tileSize)].canBeTraversed()) {

                    if (groundUnits.get(i).getLastDirection() != Directions.WEST) {
                        groundUnits.get(i).move(Directions.WEST);
                        return;
                    }
                }
            }

            if (((groundUnits.get(i).getXPosition() + 1) / tileSize) < finalXSize - 1) {

                if (worldMap[(int) ((groundUnits.get(i).getXPosition() / tileSize) + 1)]
                        [(int) (groundUnits.get(i).getYPosition() / tileSize)].canBeTraversed()) {

                    if (groundUnits.get(i).getLastDirection() != Directions.EAST) {
                        groundUnits.get(i).move(Directions.EAST);
                        return;
                    }
                }
            }

            if (((groundUnits.get(i).getYPosition()) / tileSize) >= 0) {

                if (worldMap[(int) (groundUnits.get(i).getXPosition() / tileSize)]
                        [(int) ((groundUnits.get(i).getYPosition() / tileSize) - 1)].canBeTraversed()) {

                    if (groundUnits.get(i).getLastDirection() != Directions.NORTH) {
                        groundUnits.get(i).move(Directions.NORTH);
                        return;
                    }
                }
            }

            if (((groundUnits.get(i).getYPosition() + 1) / tileSize) < finalYSize - 1) {

                if (worldMap[(int) (groundUnits.get(i).getXPosition() / tileSize)]
                        [(int) (groundUnits.get(i).getYPosition() / tileSize) + 1].canBeTraversed()) {

                    if (groundUnits.get(i).getLastDirection() != Directions.SOUTH) {
                        groundUnits.get(i).move(Directions.SOUTH);
                        return;
                    }
                }
            }
        }
    }

    private boolean isNewTile(int i) {
        return ((groundUnits.get(i).getYPosition() % tileSize == 0)
                && (groundUnits.get(i).getXPosition()  % tileSize == 0));
    }



    public ArrayList<GroundUnit> getAllUnits() {
        return this.groundUnits;
    }
}
