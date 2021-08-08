package com.icommerce.ochestrator.service;

import java.util.List;

public class OrderWorkflow implements IWorkflow {

    private final List<IWorkflowStep> steps;

    public OrderWorkflow(List<IWorkflowStep> steps) {
        this.steps = steps;
    }

    @Override
    public List<IWorkflowStep> getSteps() {
        return this.steps;
    }

}
