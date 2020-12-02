package application.figures;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import math.Vector;

public class CoordsSystemFigure extends Figure {
	@Override
	public void draw(Canvas canvas) {
		final double AXIS_SIZE = 250.0;
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0.0, 0.0, canvas.getWidth(), canvas.getHeight());
		gc.setStroke(Color.RED);

		Vector axisCenter = new Vector(0.0, 0.0,0, 1.0);
		Vector axisPointXPositive = mat.transform(new Vector(AXIS_SIZE, 0,0.0, 1.0)).perspectiveDivide();
		Vector axisPointYPositive = mat.transform(new Vector(0.0, AXIS_SIZE,0, 1.0)).perspectiveDivide();
		Vector axisPointXNegative =mat.transform( new Vector(-AXIS_SIZE, 0.0, 0,1.0)).perspectiveDivide();
		Vector axisPointYNegative =mat.transform( new Vector(0.0, -AXIS_SIZE, 0,1.0)).perspectiveDivide();
		axisCenter = mat.transform(axisCenter).perspectiveDivide();

		double axisLength =  Vector.getVectorLength(axisPointXPositive.getX(),axisPointXPositive.getY(),axisPointXNegative.getX(),axisPointXNegative.getY());
		gc.setStroke(Color.RED);
		gc.strokeLine(axisPointXNegative.getX(),axisPointXNegative.getY(),axisPointXPositive.getX(),axisPointXPositive.getY());
		gc.setStroke(Color.GREEN);
		gc.strokeLine(axisPointYNegative.getX(), axisPointYNegative.getY(), axisPointYPositive.getX(), axisPointYPositive.getY());
		gc.setStroke(Color.BLUE);

		for (int i = 0; i*axisLength/25 < axisLength; ++i) {
			drawCircle(canvas,axisCenter.getX(),axisCenter.getY(),25*i);
		}
	}
	private void drawCircle(Canvas canvas, double v,double v1, double v2) {
		double lastX = v2* Math.cos(0);
		double lastY = v2*Math.sin(0);
		for (double i = 0.0; i <Math.PI*2; i+=0.01) {
			double newX = v2* Math.cos(i);
			double newY = v2*Math.sin(i);
			Vector v3 = mat.transform(new Vector(lastX,lastY,0,1)).perspectiveDivide();
			Vector v4 = mat.transform(new Vector(newX,newY,0,1)).perspectiveDivide();
			canvas.getGraphicsContext2D().strokeLine(v3.getX(),v3.getY(),v4.getX(),v4.getY());
			lastX = newX;
			lastY = newY;
		}
	}
}
