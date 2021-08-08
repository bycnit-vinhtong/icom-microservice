package com.icommerce.order.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String msg) {
		super(msg);
	}
	
	 /**
     * Constructs the exception with the necessary infos
     *
     * @param entity
     *            the entity class
     * @param id
     *            the id
     */
    public NotFoundException(final Class<?> entity, final Long id) {
        super(String.format("Cannot find entity of class [%s] with id [%d]", entity.getName(), id));
    }
}
