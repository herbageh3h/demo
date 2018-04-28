package cc.hw3a.demo.act;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Arrays;
import java.util.UUID;

import org.activiti.bpmn.converter.BpmnXMLConverter;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;

import org.apache.commons.io.FileUtils;

public class BpmnModelGenerator {
    public static BpmnModel simple() {
		BpmnModel model = new BpmnModel();

		Process proc = new Process();
		proc.setId("simple");
        proc.setName("simple");
		model.addProcess(proc);

		StartEvent startEvent = new StartEvent();
		startEvent.setId("startEvent");
        startEvent.setName("start");
		proc.addFlowElement(startEvent);

		UserTask task1 = new UserTask();
		task1.setId(UUID.randomUUID().toString());
		task1.setName("apply");
        proc.addFlowElement(task1);

		SequenceFlow flow1 = new SequenceFlow();
		flow1.setSourceRef(startEvent.getId());
		flow1.setTargetRef(task1.getId());
        flow1.setId(UUID.randomUUID().toString());
        proc.addFlowElement(flow1);

        EndEvent endEvent = new EndEvent();
        endEvent.setId("endEvent");
        endEvent.setName("end");
        proc.addFlowElement(endEvent);

		SequenceFlow flow2 = new SequenceFlow();
		flow2.setSourceRef(task1.getId());
		flow2.setTargetRef(endEvent.getId());
        flow2.setId(UUID.randomUUID().toString());
        proc.addFlowElement(flow2);

        startEvent.setOutgoingFlows(Arrays.asList(flow1));
        task1.setOutgoingFlows(Arrays.asList(flow2));

        return model;
    }

	public static void main(String ... args) throws Exception {
        BpmnModel model = BpmnModelGenerator.simple();

        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);
        ByteArrayInputStream bais = new ByteArrayInputStream(bpmnBytes);
        FileUtils.copyInputStreamToFile(bais, new File("/Users/huanghao/tmp/aa.bpmn20.xml"));

        System.out.println("success");
	}
}
