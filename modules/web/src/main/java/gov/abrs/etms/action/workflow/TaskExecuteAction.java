/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package gov.abrs.etms.action.workflow;

import gov.abrs.etms.action.util.BaseAction;
import gov.abrs.etms.service.workflow.WorkFlowService;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;

@Results( { @Result(name = "success", type = "redirect", location = "${redirectURL}") })
public class TaskExecuteAction extends BaseAction {
	private static final long serialVersionUID = 7442255435394601200L;
	private String id;
	private String redirectURL;

	@Override
	public String execute() throws Exception {
		ProcessInstance pi = workFlowService.getProcessInstance(id);
		TaskInstance ti = workFlowService.findActiveTaskInstance(pi);
		workFlowService.startTaskInstance(ti);
		redirectURL = workFlowService.getRedirectURL(ti);
		return Action.SUCCESS;
	}

	private WorkFlowService workFlowService;

	@Autowired
	public void setWorkFlowService(WorkFlowService workFlowService) {
		this.workFlowService = workFlowService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}
}