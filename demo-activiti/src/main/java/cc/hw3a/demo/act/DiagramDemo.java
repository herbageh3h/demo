package cc.hw3a.demo.act;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.activiti.bpmn.BpmnAutoLayout;

import org.activiti.bpmn.converter.BpmnXMLConverter;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;

import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;

import org.apache.commons.io.FileUtils;

public class DiagramDemo {
    public static void main(String... args) throws Exception {
        BpmnModel model = BpmnModelGenerator.simple();
        new BpmnAutoLayout(model).execute();

        Process proc = model.getProcesses().get(0);
        for (FlowNode flowNode: proc.findFlowElementsOfType(FlowNode.class)) {
            System.out.println(flowNode.toString());

            for (SequenceFlow flow : flowNode.getOutgoingFlows()) {
                System.out.println(flow.getSourceRef());
                System.out.println(flow.getTargetRef());
            }
        }

        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);
        ByteArrayInputStream bais = new ByteArrayInputStream(bpmnBytes);
        FileUtils.copyInputStreamToFile(bais, new File("/Users/huanghao/tmp/aa.bpmn20.xml"));

        ProcessDiagramGenerator diagramGenerator = new DefaultProcessDiagramGenerator();
        InputStream is = diagramGenerator.generateDiagram(model, "png", "Arial", "Arial", "Arial", null);

        FileUtils.copyInputStreamToFile(is, new File("/Users/huanghao/tmp/aa.png"));

        System.out.println("success");
    }
}
