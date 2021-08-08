package com.icommerce.ochestrator.service.step;

import com.icommerce.ochestrator.service.IWorkflowStep;
import com.icommerce.ochestrator.service.WorkflowStepStatus;
import com.icommerce.ochestrator.service.client.PaymentFeignServiceClient;
import com.icommerce.payment.client.PaymentRequestDto;
import com.icommerce.payment.client.PaymentResponseDTO;
import com.icommerce.payment.client.PaymentStatus;

public class PaymentStep implements IWorkflowStep {

    
    private final PaymentRequestDto requestDTO;
    
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;
    
	PaymentFeignServiceClient paymentFeignServiceClient;

    public PaymentStep(PaymentFeignServiceClient paymentFeignServiceClient, PaymentRequestDto requestDTO) {
        this.requestDTO = requestDTO;
        this.paymentFeignServiceClient = paymentFeignServiceClient;
    }

    @Override
    public WorkflowStepStatus getStatus() {
        return this.stepStatus;
    }

    @Override
    public Boolean process() {
    	PaymentResponseDTO inventoryResponseDTO = paymentFeignServiceClient.debit(this.requestDTO);
    	if(inventoryResponseDTO != null && PaymentStatus.PAYMENT_APPROVED.equals(inventoryResponseDTO.getStatus())) {
    	    this.stepStatus  = WorkflowStepStatus.COMPLETE;
    	}else {
    	    this.stepStatus  = WorkflowStepStatus.FAILED;
    	}
    	return (this.stepStatus == WorkflowStepStatus.COMPLETE);
    }

    @Override
    public Boolean revert() {
        paymentFeignServiceClient.credit(this.requestDTO);
    	return true;
    }
}
