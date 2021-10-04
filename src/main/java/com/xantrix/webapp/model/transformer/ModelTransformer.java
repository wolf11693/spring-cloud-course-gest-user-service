package com.xantrix.webapp.model.transformer;

public interface ModelTransformer<M, D> {
	
	public default M getModelByDto(D dto) {
		return this.transformFrom(dto);
	}
	
	public M transformFrom(D dto);
}
