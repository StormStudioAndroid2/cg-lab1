package application;

import javafx.scene.canvas.Canvas;

public class CustomCanvas extends Canvas {
	public CustomCanvas(double width, double height) {
		super(width, height);
	}
    private Updater updater;
	@Override
	public boolean isResizable() {
		return true;
	}

	@Override
	public void resize(double width, double height) {
		setWidth(width);
		setHeight(height);
		updater.update();
	}
    @Override
    public double minHeight(double width)
    {
        return 64;
    }

    @Override
    public double maxHeight(double width)
    {
        return 1000;
    }

    @Override
    public double prefHeight(double width)
    {
        return minHeight(width);
    }

    @Override
    public double minWidth(double height)
    {
        return 0;
    }

    @Override
    public double maxWidth(double height)
    {
        return 10000;
    }
    public void setUpdater(Updater updater) {
	    this.updater = updater;
    }


}
