package mkgosisejo.models;

public class Position {
    private int _x;
    private int _y;

    public Position(){
        this._x = 0;
        this._y = 0;
    }

    public Position(int x, int y){
        this._x = x;
        this._y = y;
    }

    public void setX(int x){
        this._x = x;
    }

    public void setY(int y){
        this._y = y;
    }

    public void setXY(int x, int y){
        this._x = x;
        this._y = y;
    }

    public int getX(){
        return (this._x);
    }

    public int getY(){
        return (this._y);
    }

    public boolean isEquals(Position position){
        return (position._x == this._x && position._y == this._y);
    }

    public Position getCopy(){
        return (new Position(this._x, this._y));
    }

    public void moveUp(){
        this._x--;
    }

    public void moveRight(){
        this._y++;
    }

    public void moveDown(){
        this._x++;
    }

    public void moveLeft(){
        this._y--;
    }
}