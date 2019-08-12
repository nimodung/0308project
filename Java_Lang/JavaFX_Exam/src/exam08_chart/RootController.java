package exam08_chart;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;


public class RootController implements Initializable {

	@FXML PieChart piechart;
	@FXML BarChart barchart;
	@FXML AreaChart areachart;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		piechart.setData(FXCollections.observableArrayList(
				new PieChart.Data("¼­ÁöÈÆ",30),
				new PieChart.Data("±è¿ìÇõ",20),
				new PieChart.Data("ÃÖÀºÁÖ",40),
				new PieChart.Data("»Ç¾ß",10)
				));
		
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("¿ìÇõ");
		series1.setData(FXCollections.observableArrayList(
				new XYChart.Data("2015",70),
				new XYChart.Data("2016",40),
				new XYChart.Data("2017",50),
				new XYChart.Data("2018",30)
				));
		
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("ÀºÁÖ");
		series2.setData(FXCollections.observableArrayList(
				new XYChart.Data("2015",30),
				new XYChart.Data("2016",60),
				new XYChart.Data("2017",50),
				new XYChart.Data("2018",60)
				));
		
		XYChart.Series series3 = new XYChart.Series();
		series3.setName("ÁöÈÆ");
		series3.setData(FXCollections.observableArrayList(
				new XYChart.Data("2015",20),
				new XYChart.Data("2016",40),
				new XYChart.Data("2017",60),
				new XYChart.Data("2018",80)
				));
		
		
		
		barchart.getData().add(series1);
		barchart.getData().add(series2);
		barchart.getData().add(series3);
		
	
		
		XYChart.Series series4 = new XYChart.Series();
		series4.setName("Æò±Õ");
		series4.setData(FXCollections.observableArrayList(
				new XYChart.Data("2015",25),
				new XYChart.Data("2016",27),
				new XYChart.Data("2017",24),
				new XYChart.Data("2018",30)
				));
		
		XYChart.Series series5 = new XYChart.Series();
		series5.setName("ÃÖ°í¿Âµµ");
		series5.setData(FXCollections.observableArrayList(
				new XYChart.Data("2015",34),
				new XYChart.Data("2016",32),
				new XYChart.Data("2017",38),
				new XYChart.Data("2018",37)
				));
		
		XYChart.Series series6 = new XYChart.Series();
		series6.setName("ÃÖÀú¿Âµµ");
		series6.setData(FXCollections.observableArrayList(
				new XYChart.Data("2015",18),
				new XYChart.Data("2016",23),
				new XYChart.Data("2017",17),
				new XYChart.Data("2018",21)
				));
		areachart.getData().add(series4);
		areachart.getData().add(series5);
		areachart.getData().add(series6);
	}

}
