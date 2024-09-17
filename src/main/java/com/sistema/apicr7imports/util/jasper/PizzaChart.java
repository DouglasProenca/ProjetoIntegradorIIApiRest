package com.sistema.apicr7imports.util.jasper;

import java.awt.Color;
import java.util.Optional;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;
import net.sf.jasperreports.engine.JRPropertiesMap;

public class PizzaChart implements JRChartCustomizer {

	Boolean linkVisible;
	Boolean legendVisible = false;
	Color shadowPaint;

	@Override
	public void customize(JFreeChart chart, JRChart jasperChart) {
		Optional<JRPropertiesMap> pm = Optional.ofNullable(jasperChart.getPropertiesMap());
		
		linkVisible = Boolean.parseBoolean(pm.map(p -> p.getProperty("linkVisible")).orElse("false"));
		legendVisible = Boolean.parseBoolean(pm.map(p -> p.getProperty("legendVisible")).orElse("false"));
		shadowPaint = convertColor(pm.map(p -> p.getProperty("shadowPaint")).orElse("255,255,255"));
		

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelLinksVisible(linkVisible);
		plot.setShadowPaint(shadowPaint); // Sombra do Gráfico
		plot.setLabelBackgroundPaint(Color.white);
		plot.setLabelOutlinePaint(Color.white);
		plot.setLabelShadowPaint(Color.white);

		chart.getLegend().setVisible(legendVisible); // Tirar Legenda
		chart.getLegend().setBorder(0, 0, 0, 0); // seta tamanho das bordas na legenda do gráfico

	}
	
	private Color convertColor(String color) {
		String corArray[] = color.split(",");
		Integer r = Integer.parseInt(corArray[0]);
		Integer g = Integer.parseInt(corArray[1]);
		Integer b = Integer.parseInt(corArray[2]);
		return new Color(r, g, b);
	}
}
