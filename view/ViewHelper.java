package view;

import model.Position;

import java.awt.*;

public class ViewHelper {

    private Font h1 = new Font("Arial", Font.BOLD, 20);
    private Font p = new Font("Arial", Font.PLAIN, 12);
    private Font h2 = new Font("Arial", Font.BOLD, 16);


    /**
     * Helper function for setting GridBacLayout constraints
     * @param fill Constant defining how the component should fill its given space
     * @param weightY Component weight in the Y direction,  0=none, 1=highest
     * @param weightX Component weight in the X direction,  0=none, 1=highest
     * @param gridX Component placement in the grid, X direction
     * @param gridY Component placement in the grid, Y direction
     * @param gridWidth Sets the width of the grid in this row
     * @param padX Sets padding on the components right/left side
     * @param padY Sets Padding on the components top/bottom side
     * @param insets Sets inset thickness around whole component
     * @return New GridBagConstraint with the set settings
     */
    public GridBagConstraints setGridBagConstraints(int fill,
                                                    double weightY,
                                                    double weightX,
                                                    int gridX,
                                                    int gridY,
                                                    int gridWidth,
                                                    int padX,
                                                    int padY,
                                                    int insets ){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = fill;
        gbc.weightx = weightX;
        gbc.weighty = weightY;
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.gridwidth = gridWidth;
        gbc.ipady = padY;
        gbc.ipadx = padX;
        gbc.insets = new Insets(insets,insets,insets,insets);
        return  gbc;
    }

    Font getFontP(){
        return this.p;
    }
    Font getFontH1(){
        return this.h1;
    }
    Font getFontH2(){
        return this.h2;
    }

    Object[][] getTempData(){
        Object[][] tmpData = new Object[5][2];
        tmpData[0][0] = "N/A";
        tmpData[0][1] = "65";
        tmpData[1][0] = "N/A";
        tmpData[1][1] = "50";
        tmpData[2][0] = "N/A";
        tmpData[2][1] = "45";
        tmpData[3][0] = "N/A";
        tmpData[3][1] = "30";
        tmpData[4][0] = "N/A";
        tmpData[4][1] = "20";

        return tmpData;
    }

    String[] getColNames(){
        return new String[]{"Player", "Score"};
    }
    public Position calculateTrajectory(Position currentPosition, Position targetPosition, double rate, int imageWidth){
        double distance = calcDistance(currentPosition, targetPosition);
        Position direction = calcDirection(
                targetPosition.getXPosition() - currentPosition.getXPosition(),
                targetPosition.getYPosition() - currentPosition.getYPosition());
        Position newPos = new Position(
                currentPosition.getXPosition() + (direction.getXPosition() * rate),
                currentPosition.getYPosition() + (direction.getYPosition() * rate));
        if((int)distance < 16){ //IF WITHIN TARGETS IMAGE
            return null; //REACHED TARGET
        }
        return newPos;
    }
    private Position calcDirection(double x, double y){
        return new Position(x / length(x, y), y / length(x,y ));
    }
    private double calcDistance(Position start, Position end){
        return length(end.getXPosition() - start.getXPosition(), end.getYPosition() - start.getYPosition());
    }
    private double length(double x, double y){
        return Math.sqrt(x * x + y * y);
    }
}
