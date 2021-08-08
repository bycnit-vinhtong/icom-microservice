package com.icommerce.ochestrator.service;

public interface IWorkflowStep {

	WorkflowStepStatus getStatus();

	Boolean process();

	Boolean revert();

}
