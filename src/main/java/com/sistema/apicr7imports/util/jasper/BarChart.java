package com.sistema.apicr7imports.util.jasper;

import java.awt.Color;
import java.util.Optional;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.plot.CategoryPlot;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;
import net.sf.jasperreports.engine.JRPropertiesMap;

public class BarChart implements JRChartCustomizer {

	@Override
	public void customize(JFreeChart chart, JRChart jasperChart) {
		Optional<JRPropertiesMap> pm = Optional.ofNullable(jasperChart.getPropertiesMap());
		
		Boolean gridLinesVisible = Boolean.parseBoolean(pm.map(p -> p.getProperty("gridLinesVisible")).orElse("false"));

		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setRangeGridlinesVisible(gridLinesVisible);
		plot.getRangeAxis().setAxisLinePaint(Color.white);
		plot.getDomainAxis().setAxisLinePaint(Color.white);
		plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
	}
}
