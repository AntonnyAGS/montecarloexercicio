package br.usjt.modsiscomp.montecarlo;

public class Pair {
  private int x;
  private int y;

  public Pair(int x, int y){
    super();
    this.x = x;
    this.y = y;
  }

  //GETTERS 
  public int getX(){
    return this.x;
  }
  public int getY(){
    return this.y;
  }

  //SETTERS
  public void setX(int x){
    this.x = x;
  }
  public void setY(int y){
    this.y = y;
  }

  @Override
  public String toString(){
    return "Este é um ponto que possui como X: " + this.x + " e Y: " + this.y;
  }
}