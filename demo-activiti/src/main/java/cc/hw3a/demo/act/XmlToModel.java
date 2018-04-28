package cc.hw3a.demo.act;

import java.io.File;
import java.io.InputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;

import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;

import org.apache.commons.io.FileUtils;

public class XmlToModel {
    public static void main(String... args) throws Exception {
        XmlToModel obj = new XmlToModel();
        InputStream is = obj.getClass().getClassLoader().getResourceAsStream("aa.bpmn20.xml");
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(is);

        BpmnXMLConverter converter = new BpmnXMLConverter();
        BpmnModel model = converter.convertToBpmnModel(reader);

        Process proc = model.getProcesses().get(0);
        for (FlowNode flowNode: proc.findFlowElementsOfType(FlowNode.class)) {
            System.out.println(flowNode.toString());

            for (SequenceFlow flow : flowNode.getOutgoingFlows()) {
                System.out.println(flow.getSourceRef());
                System.out.println(flow.getTargetRef());
            }
        }

        ProcessDiagramGenerator diagramGenerator = new DefaultProcessDiagramGenerator();
        InputStream dis = diagramGenerator.generateDiagram(model, "png", "Arial", "Arial", "Arial", null);
        FileUtils.copyInputStreamToFile(dis, new File("/Users/huanghao/tmp/aa.png"));

        System.out.println("success");
    }
}
