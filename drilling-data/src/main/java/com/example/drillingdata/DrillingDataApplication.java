package com.example.drillingdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.ServiceActivator;

@SpringBootApplication
@EnableBinding(Processor.class)
public class DrillingDataApplication {

	private static Logger logger = LoggerFactory.getLogger(DrillingDataApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DrillingDataApplication.class, args);
	}

	@ServiceActivator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
//	@StreamListener(Processor.INPUT)
//	@SendTo(Processor.OUTPUT)
	public DrillingPrediction process(DrillingData data) throws Exception {
		logger.info("rowId={}, wellId={}, runId={}, globalRunId={}, depth={}", data.rowId, data.wellId, data.runId,
				data.globalRunId, data.depth);

		DrillingPrediction prediction = new DrillingPrediction();
		prediction.setPrediction("foobar");

		logger.info("process: data={}, prediction={}", data, prediction);

		return prediction;
	}

	static class DrillingPrediction {

		String prediction;

		public String getPrediction() {
			return prediction;
		}

		public void setPrediction(String prediction) {
			this.prediction = prediction;
		}
	}

	static class DrillingData {

		int rowId;
		int wellId;
		int runId;
		int globalRunId;
		int depth;

		public DrillingData() {

		}

		public int getWellId() {
			return wellId;
		}

		public void setWellId(int wellId) {
			this.wellId = wellId;
		}

		public int getRunId() {
			return runId;
		}

		public void setRunId(int runId) {
			this.runId = runId;
		}

		public int getGlobalRunId() {
			return globalRunId;
		}

		public void setGlobalRunId(int globalRunId) {
			this.globalRunId = globalRunId;
		}

		public int getDepth() {
			return depth;
		}

		public void setDepth(int depth) {
			this.depth = depth;
		}

		public int getRowId() {

			return rowId;
		}

		public void setRowId(int rowId) {
			this.rowId = rowId;
		}
	}
}
