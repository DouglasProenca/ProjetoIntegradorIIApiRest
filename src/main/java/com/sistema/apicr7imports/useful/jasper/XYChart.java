package com.sistema.apicr7imports.useful.jasper;

import java.util.Optional;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYAreaRenderer;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;
import net.sf.jasperreports.engine.JRPropertiesMap;

public class XYChart implements JRChartCustomizer {

	@Override
	public void customize(JFreeChart chart, JRChart jasperChart) {
		Optional<JRPropertiesMap> pm = Optional.ofNullable(jasperChart.getPropertiesMap());
		
		Boolean rangeGridlinesVisible = Boolean.parseBoolean(pm.map(p -> p.getProperty("rangeGridlinesVisible")).orElse("true"));
		Boolean domainGridlinesVisible = Boolean.parseBoolean(pm.map(p -> p.getProperty("domainGridlinesVisible")).orElse("true"));
		Boolean horTickMarksVisible = Boolean.parseBoolean(pm.map(p -> p.getProperty("horTickMarksVisible")).orElse("true"));
		Boolean vertTickMarksVisible = Boolean.parseBoolean(pm.map(p -> p.getProperty("vertTickMarksVisible")).orElse("true"));
		Boolean legendVisible = Boolean.parseBoolean(pm.map(p -> p.getProperty("legendVisible")).orElse("true"));
		Boolean xyRenderer = Boolean.parseBoolean(pm.map(p -> p.getProperty("xyRenderer")).orElse("false"));
		String label = pm.map(p -> p.getProperty("label")).orElse("");
			

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
