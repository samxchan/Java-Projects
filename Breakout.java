import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/** An instance is the game breakout. Start it by executing
    Breakout.main(null);
    */
public class Breakout extends GraphicsProgram {
    /** Width of the game display (all coordinates are in pixels) */
    private static final int WIDTH= 450;
    /** Height of the game display */
    private static final int HEIGHT= 610;
    
    /** Width of the paddle */
    private static final int PADDLE_WIDTH= 60;
    /** Height of the paddle */
    private static final int PADDLE_HEIGHT= 10;
    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_BOTTOM_OFFSET= 30;
    
    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW= 10;
    /** Number of rows of bricks, in range 1..10. */
    private static final int NBRICK_ROWS= 10;
    /** Horizontal separation between bricks */
    private static final int BRICK_SEP_H= 5;
    /** Vertical separation between bricks */
    private static final int BRICK_SEP_V= 4;
    
    /** Width of a brick */
    private static final int BRICK_WIDTH= WIDTH / NBRICKS_PER_ROW - BRICK_SEP_H;
    /** Height of a brick */
    private static final int BRICK_HEIGHT= 8;
    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_BOTTOM_OFFSET= 71;
    
    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS= 10;
    
    /** Number of turns */
    private static final int NTURNS= 3;
    
    /** Run the program as an application. Parameter args is not used.
        A hint on how it works. The main program creates an instance of
        the class, giving the constructor the width and height of the graphics
        panel. It then calls method run() to start the computation.
      */
    
    private GRect paddle;
    private GOval ball;
    private RandomGenerator rgen= new RandomGenerator();
    private double vx, vy;
    
    public static void main(String[] args) {
        String[] sizeArgs= { "width=" + WIDTH, "height=" + HEIGHT };
        new Breakout().start(sizeArgs);
        
    }
    
    public void createBricks(){
    	
    	double x=BRICK_SEP_H/2;
    	double y=BRICK_Y_BOTTOM_OFFSET;
    	Color[]colors={Color.RED,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.CYAN};
    	for(int i=0;i<colors.length;i++){
    		for(int c=0;c<2;c++){
    			for(int r=0; r<11;r++ ){
    			
    				GRect block=new GRect(x,y,BRICK_WIDTH,BRICK_HEIGHT);
    				block.setFillColor(colors[i]);
    				block.setFilled(true);
    				add(block);
    				x+=BRICK_WIDTH+BRICK_SEP_H;
    			}	
    		y+=BRICK_SEP_V+BRICK_HEIGHT;
    		x=BRICK_SEP_H/2;
    		
    		}	
    	}
    }
    
   public void mouseMoved(MouseEvent e){
	   
	   GPoint p=new GPoint(e.getPoint());
	   double x = Math.min(p.getX(), WIDTH-PADDLE_WIDTH);
	   paddle.setLocation(x , HEIGHT-PADDLE_Y_BOTTOM_OFFSET);
   }
    
    /** Run the Breakout program. */
    public void run() {
       
    	createBricks();
    	paddle=new GRect(WIDTH/2,HEIGHT-PADDLE_Y_BOTTOM_OFFSET,PADDLE_WIDTH,PADDLE_HEIGHT);
    	paddle.setFillColor(Color.BLACK);
    	paddle.setFilled(true);
    	this.addMouseListeners();
    	add(paddle);
    	ball = new GOval(WIDTH/2, HEIGHT/2, BALL_RADIUS*2, BALL_RADIUS*2);
    	ball.setColor(Color.BLACK);
    	ball.setFilled(true);
    	add(ball);    	
    	vx= rgen.nextDouble(1.0, 3.0);
    	vy=3;    		
    	if (rgen.nextBoolean(0.5)) 
    		vx= -vx;
    	moveBall();
    }
    
    public void moveBall(){ 	
    
    	while(ball.getLocation().getY() < HEIGHT){ //once this is done, they die once
    		
    		GObject gob = getElementAt(ball.getX(), ball.getY());
        	GObject gob2 = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
        	GObject gob3 = getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
        	GObject gob4 = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
    		ball.setLocation(ball.getX()+ vx, ball.getY()+vy);
    		pause(10);	
    	//doesnt work...tisk tisk...log off next time ;)
    	
    	}
    }
    
}
