package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.tejuino.TejuinoBoard;

public class Tej_Subs extends SubsystemBase {
  /** Creates a new Tesexjuino. */
  TejuinoBoard tesexjuino;

  int timer = 0;
  int mode = 0;
  int whiteBlink = 50;

  public Tej_Subs() {

    tesexjuino = new TejuinoBoard();
  }
 
  
 public void setMode(int m){
  mode = m;
 }
 public void periodic(){

    if( mode == 0){
      tesexjuino.all_leds_off(0);
      tesexjuino.all_leds_off(1);
      tesexjuino.all_leds_off(2);
    }

    if( mode == 1){
      if( timer < whiteBlink ){
        wl();
      }else{
          // call all led green
          gl();

      }
      if(timer == 2*whiteBlink){
        timer = 0;
      }
      timer += 1;

    }
 
 
if( mode == 2){
  if( timer < whiteBlink ){
    wl();
  }else{
      // call all led green
      bl();

  }
  if(timer == 2*whiteBlink){
    timer = 0;
  }
  timer += 1;
 
}


if( mode == 3){
  if( timer < whiteBlink ){
    wl();
  }else{
      // call all led green
      pl();

  }
  if(timer == 2*whiteBlink){
    timer = 0;
  }
  timer += 1;
 
}
}
  
  
  

  public void gl(){
  
  tesexjuino.all_leds_green(0);
  tesexjuino.all_leds_green(1);
  tesexjuino.all_leds_green(2);

  }

  public void wl(){
  
    tesexjuino.all_leds_white(0);
    tesexjuino.all_leds_white(1);
    tesexjuino.all_leds_white(2);
  
  }

  public void bl(){

    tesexjuino.all_leds_blue(0);
    tesexjuino.all_leds_blue(1);
    tesexjuino.all_leds_blue(2);

  }

  

  public void pl(){
    
  tesexjuino.all_leds_pink(0);
  tesexjuino.all_leds_pink(1);
  tesexjuino.all_leds_pink(2);
  
  
  }

  public void endmatch(){


  }

}
