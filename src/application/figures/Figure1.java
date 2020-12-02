package application.figures;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import math.Vector;

public class Figure1 extends Figure {
	final double AXIS_SIZE = 250.0;

	public Figure1(Slider[] params) {
		super();

		this.params = params;
	}

	@Override
	public void draw(Canvas canvas) {
		Slider paramA = params[0];
		Slider paramB = params[1];
		Slider paramC = params[2];
		GraphicsContext gc = canvas.getGraphicsContext2D();
		double p = paramA.getValue()/( paramC.getValue());
		double lastX = p*Math.cos(  paramC.getValue());
		double lastY = p*Math.sin( paramC.getValue());


		gc.setStroke(Color.BLACK);

		for (double t = paramC.getValue(); t > paramB.getValue() && t>0 && t<Math.PI*2 && lastX<AXIS_SIZE; t -= 0.001) {
			 p = paramA.getValue()/t;
			double newX = p*Math.cos(t);
			double  newY = p*Math.sin(t);
			Vector nextPoint = new Vector(newX,
					newY, 0,1.0);
			nextPoint = mat.transform(nextPoint).perspectiveDivide();
			Vector prevPoint = mat.transform(new Vector(lastX,lastY,0,1)).perspectiveDivide();
			gc.strokeLine(prevPoint.getX(), prevPoint.getY(), nextPoint.getX(), nextPoint.getY());
			lastX = newX;
			lastY = newY;
		}
	}

	private Slider[] params;
}
