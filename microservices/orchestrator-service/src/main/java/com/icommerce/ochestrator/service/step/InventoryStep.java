package com.icommerce.ochestrator.service.step;

import com.icommerce.inventory.client.InventoryResponseDTO;
import com.icommerce.inventory.client.InventoryStatus;
import com.icommerce.inventory.client.InventorytRequestDto;
import com.icommerce.ochestrator.service.IWorkflowStep;
import com.icommerce.ochestrator.service.WorkflowStepStatus;
import com.icommerce.ochestrator.service.client.InventoryFeignServiceClient;

public class InventoryStep implements IWorkflowStep {

    
    private final InventorytRequestDto requestDTO;
    
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;
    
	InventoryFeignServiceClient inventoryFeignServiceClient;

    public InventoryStep(InventoryFeignServiceClient inventoryFeignServiceClient, InventorytRequestDto requestDTO) {
        this.requestDTO = requestDTO;
        this.inventoryFeignServiceClient = inventoryFeignServiceClient;
    }

    @Override
    public WorkflowStepStatus getStatus() {
        return this.stepStatus;
    }

    @Override
    public Boolean process() {
    	InventoryResponseDTO inventoryResponseDTO = inventoryFeignServiceClient.deductInventory(this.requestDTO);
    	if(inventoryResponseDTO != null && InventoryStatus.AVAILABLE.equals(inventoryResponseDTO.getStatus())) {
    	    this.stepStatus  = WorkflowStepStatus.COMPLETE;
    	}else {
    	    this.stepStatus  = WorkflowStepStatus.FAILED;
    	}
    	return (this.stepStatus == WorkflowStepStatus.COMPLETE);
    }

    @Override
    public Boolean revert() {
    	inventoryFeignServiceClient.addInventory(this.requestDTO);
    	return true;
    }
}
