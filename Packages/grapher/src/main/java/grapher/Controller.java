package grapher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Screen;

public class Controller {
	@FXML private MenuBar menubar;
	@FXML private Text title;
	@FXML private Canvas canvas;
	@FXML private TextField function;

	@FXML public void initialize() {
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		menubar.useSystemMenuBarProperty().set(true);
		canvas.setWidth(bounds.getWidth()*.6);
		canvas.setHeight(bounds.getHeight()*.8);
		double width = canvas.getWidth();
		double height = canvas.getHeight();
		Affine affine = new Affine();
		affine.append(new Translate(width/2, height/2));
		affine.append(new Scale(1, -1));
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setTransform(affine);
		gc.strokeLine(-width/2, 0, width/2, 0);
		gc.strokeLine(0, -height/2, 0, height/2);
		for (double i = -width/100; i <= width/100; i++)
			gc.strokeLine(i*50, -2, i*50, 2);
		for (double j = -width/100; j <= width/100; j++)
			gc.strokeLine(-2, j*50, 2, j*50);
	}

	@FXML protected void action(ActionEvent event) {
		title.setText(function.getText());
		GraphicsContext gc = canvas.getGraphicsContext2D();
	}

}
