package com.sistema.apicr7imports.util.jasper;

import java.util.Optional;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYAreaRenderer;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;
import net.sf.jasperreports.engine.JRPropertiesMap;

public class XYChart implements JRChartCustomizer {

	Boolean rangeGridlinesVisible;
	Boolean domainGridlinesVisible;
	Boolean horTickMarksVisible;
	Boolean vertTickMarksVisible;
	Boolean legendVisible;
	Boolean xyRenderer;
	String label = "";

	@Override
	public void customize(JFreeChart chart, JRChart jasperChart) {
		Optional<JRPropertiesMap> pm = Optional.ofNullable(jasperChart.getPropertiesMap());
		
		rangeGridlinesVisible = Boolean.parseBoolean(pm.map(p -> p.getProperty("rangeGridlinesVisible")).orElse("true"));
		domainGridlinesVisible = Boolean.parseBoolean(pm.map(p -> p.getProperty("domainGridlinesVisible")).orElse("true"));
		horTickMarksVisible = Boolean.parseBoolean(pm.map(p -> p.getProperty("horTickMarksVisible")).orElse("true"));
		vertTickMarksVisible = Boolean.parseBoolean(pm.map(p -> p.getProperty("vertTickMarksVisible")).orElse("true"));
		legendVisible = Boolean.parseBoolean(pm.map(p -> p.getProperty("legendVisible")).orElse("true"));
		xyRenderer = Boolean.parseBoolean(pm.map(p -> p.getProperty("xyRenderer")).orElse("false"));
		label = pm.map(p -> p.getProperty("label")).orElse("");
			

		XYPlot plot = (XYPlot) chart.getPlot();
		if (xyRenderer)
			plot.setRenderer(new XYAreaRenderer());
		
		plot.setRangeGridlinesVisible(rangeGridlinesVisible);
		plot.setDomainGridlinesVisible(domainGridlinesVisible);
		plot.getRangeAxis().setTickMarksVisible(horTickMarksVisible);
		plot.getDomainAxis().setTickMarksVisible(vertTickMarksVisible);
		plot.getRangeAxis().setLabel(label);

		chart.getLegend().setVisible(legendVisible);
		chart.getLegend().setBorder(0, 0, 0, 0);
	}

}