package handlers;

import application.Updater;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import application.figures.*;
import math.Matrix;

import java.awt.*;

public class HandlerFigures implements ChangeListener<Number>, EventHandler<MouseEvent>, Updater {
	public HandlerFigures(Canvas canvas, TabPane tabPane, Slider[] paramsFig1) {
		prevMouseX = 0.0;
		prevMouseY = 0.0;
		this.canvas = canvas;
		this.tabPane = tabPane;
		figures = new Figure[] {new Figure1(paramsFig1), new CoordsSystemFigure()};
	}

	@Override
	public void handle(MouseEvent mouseEvent) {
		EventType<? extends MouseEvent> e = mouseEvent.getEventType();

		if (e == MouseEvent.MOUSE_PRESSED) {
			prevMouseX = mouseEvent.getSceneX();
			prevMouseY = mouseEvent.getSceneY();
		}
		else if (e == MouseEvent.MOUSE_DRAGGED) {
			double mouseX = mouseEvent.getSceneX();
			double mouseY = mouseEvent.getSceneY();
			double deltaX = mouseX - prevMouseX;
			double deltaY = mouseY - prevMouseY;

			prevMouseX = mouseX;
			prevMouseY = mouseY;

			update(deltaY, deltaX);
		}
	}

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		update(0.0, 0.0);
	}

	private void update(double deltaAngleX, double deltaAngleY) {
		double WIDTH = canvas.getWidth();
		double HEIGHT = canvas.getHeight();

		Matrix viewMat = new Matrix().initTranslation(0.0, 0.0, 1001.0);
		Matrix projMat = new Matrix().initPerspective(70.0, WIDTH / HEIGHT, 1.0, 100.0);
		Matrix screenMat = new Matrix().initScreenSpace(WIDTH, HEIGHT);
		Matrix mat = screenMat.mul(projMat.mul(viewMat));
		figures[figures.length - 1].setAngles(mat, figures[0].getAngleX(), figures[0].getAngleY());
		figures[figures.length - 1].draw(canvas);
		figures[0].addAngles(mat, deltaAngleX, deltaAngleY);
		figures[0].draw(canvas);

	}

	private double prevMouseX;
	private double prevMouseY;
	private Canvas canvas;
	private TabPane tabPane;
	private Figure[] figures;

	@Override
	public void update() {
		update(0,0);
	}
}
