package com.icommerce.ochestrator.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.icommerce.inventory.client.InventorytRequestDto;
import com.icommerce.ochestrator.dto.OrchestratorRequestDto;
import com.icommerce.ochestrator.dto.OrchestratorResponseDto;
import com.icommerce.ochestrator.dto.OrderStatus;
import com.icommerce.ochestrator.exception.StepErrorException;
import com.icommerce.ochestrator.service.client.InventoryFeignServiceClient;
import com.icommerce.ochestrator.service.client.PaymentFeignServiceClient;
import com.icommerce.ochestrator.service.step.InventoryStep;
import com.icommerce.ochestrator.service.step.PaymentStep;
import com.icommerce.payment.client.PaymentRequestDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrchestratorService {

    @Autowired
    InventoryFeignServiceClient inventoryFeignServiceClient;

    @Autowired
    PaymentFeignServiceClient paymentFeignServiceClient;

    @Value("${app.inventory-service.host}")
    private String inventoryServiceUri;

    public OrchestratorResponseDto orderProduct(final OrchestratorRequestDto requestDto) {
        IWorkflow orderWorkflow = this.getOrderWorkflow(requestDto);
        boolean isAnyStepFail = false;
        try {
            orderWorkflow.getSteps().forEach(step -> {
                boolean isSuceed = step.process();
                if (!isSuceed) {
                    throw new StepErrorException("Error happen in : " + step.getClass() + " Status:" + step.getStatus());
                }
            });
        } catch (Exception ex) {
            isAnyStepFail = true;
            log.info(ex.getMessage());
        }
        if (isAnyStepFail) {
            log.info("One of step in order process is fail!");
            return revertOrder(orderWorkflow, requestDto);
        }
        log.info("Order process is completed!");
        return this.getResponseDto(requestDto, OrderStatus.ORDER_COMPLETED);
    }

    private OrchestratorResponseDto revertOrder(final IWorkflow workflow, final OrchestratorRequestDto requestDto) {
        workflow.getSteps().stream().filter(wf -> wf.getStatus().equals(WorkflowStepStatus.COMPLETE))
            .map(IWorkflowStep::revert).count();
        return this.getResponseDto(requestDto, OrderStatus.ORDER_CANCELLED);
    }

    private IWorkflow getOrderWorkflow(OrchestratorRequestDto requestDto) {
        IWorkflowStep inventoryStep =
            new InventoryStep(inventoryFeignServiceClient, this.getInventoryRequestDto(requestDto));

        IWorkflowStep paymentStep = new PaymentStep(paymentFeignServiceClient, this.getPaymentRequestDto(requestDto));

        return new OrderWorkflow(Arrays.asList(inventoryStep, paymentStep));
    }

    private OrchestratorResponseDto getResponseDto(OrchestratorRequestDto requestDto, OrderStatus status) {
        OrchestratorResponseDto responseDto = new OrchestratorResponseDto();
        responseDto.setOrderId(requestDto.getOrderId());
        responseDto.setAmount(requestDto.getAmount());
        responseDto.setProductId(requestDto.getProductId());
        responseDto.setUserId(requestDto.getUserId());
        responseDto.setStatus(status);
        return responseDto;
    }

    private InventorytRequestDto getInventoryRequestDto(OrchestratorRequestDto requestDto) {
        InventorytRequestDto inventoryRequestDto = new InventorytRequestDto();
        inventoryRequestDto.setUserId(requestDto.getUserId());
        inventoryRequestDto.setProductId(requestDto.getProductId());
        inventoryRequestDto.setOrderId(requestDto.getOrderId());
        return inventoryRequestDto;
    }

    private PaymentRequestDto getPaymentRequestDto(OrchestratorRequestDto requestDto) {
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setUserId(requestDto.getUserId());
        paymentRequestDto.setAmount(requestDto.getAmount());
        paymentRequestDto.setOrderId(requestDto.getOrderId());
        return paymentRequestDto;
    }

}
